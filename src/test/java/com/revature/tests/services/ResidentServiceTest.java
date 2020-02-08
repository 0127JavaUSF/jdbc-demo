package com.revature.tests.services;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.runners.MockitoJUnitRunner;

import com.revature.daos.HouseDao;
import com.revature.daos.ResidentDao;
import com.revature.exceptions.ValidationException;
import com.revature.models.House;
import com.revature.models.Resident;
import com.revature.services.IResidentService;
import com.revature.services.ResidentService;

@RunWith(MockitoJUnitRunner.class)
public class ResidentServiceTest {
	
	static IResidentService residentService = new ResidentService();
	
	@Mock
	static ResidentDao mockResidentDao;
	
	@Mock
	static HouseDao mockHouseDao;
	
	String validEmail = "valid@email.com";
	String validName = "Name";
	int validHouseId = 1;
	int validRent = 600;
	
	@Before
	public void setup() throws NoSuchFieldException, SecurityException {
		// Dao fields are private, setting values using Mockito utility class FieldSetter
		new FieldSetter(residentService, residentService.getClass().getDeclaredField("houseDao")).set(mockHouseDao);;
		new FieldSetter(residentService, residentService.getClass().getDeclaredField("residentDao")).set(mockResidentDao);
		
		// Default stubs for methods used by the residentService
		when(mockHouseDao.getHouse(0)).thenReturn(new House());
		when(mockResidentDao.createResident(Mockito.any(Resident.class))).thenReturn(new Resident());
	}
	
	@Test()
	public void testHappyPath() throws Exception {
		
		Resident resident = new Resident(0, validName, validRent, validHouseId, validEmail);
		Resident resultResident = residentService.createResident(resident);
		if (resultResident == null) fail();
	}
	
	@Test(expected = ValidationException.class)
	public void testInvalidEmail() throws Exception {
		String invalidEmail = "bad\\/a---lsk.22228.-ad";
		Resident resident = new Resident(0, validName, validRent, validHouseId, invalidEmail);
		residentService.createResident(resident);
		fail();
	}
	
	@Test(expected = ValidationException.class)
	public void testEmptyName() throws Exception {
		String invalidName = "";
		Resident resident = new Resident(0, invalidName, validRent, validHouseId, validEmail);
		residentService.createResident(resident);
		fail();
	}
	
	@Test(expected = ValidationException.class)
	public void testLongName() throws Exception {
		// Create string of length 36
		String invalidName = IntStream.range(0, 36).mapToObj(i -> "a").reduce("", (a, b) ->  a+b);
		
		Resident resident = new Resident(0, invalidName, validRent, validHouseId, validEmail);
		residentService.createResident(resident);
		fail();
	}
	
	@Test(expected = ValidationException.class)
	public void testBadHouseId() throws Exception {
		int invalidHouseId = 10;
		
		// Reset the mock to test with a failing stub
		Mockito.reset(mockHouseDao);
		when(mockHouseDao.getHouse(invalidHouseId)).thenReturn(null);
		
		Resident resident = new Resident(0, validName, validRent, invalidHouseId, validEmail);
		
		residentService.createResident(resident);
		fail();
	}
	
}
