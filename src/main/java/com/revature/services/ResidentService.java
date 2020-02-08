package com.revature.services;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.revature.daos.HouseDao;
import com.revature.daos.ResidentDao;
import com.revature.exceptions.ValidationException;
import com.revature.models.House;
import com.revature.models.Resident;

public class ResidentService implements IResidentService {

	private ResidentDao residentDao;
	private HouseDao houseDao;
	
	@Override
	public Resident createResident(Resident resident) throws ValidationException {
		validateResident(resident);
		return residentDao.createResident(resident);			
	}
	
	/**
	 * Validates that the Resident data is valid and ready for database entry.
	 * @param resident
	 * @return
	 * @throws ValidationException - Thrown when invalid properties are found.
	 */
	private boolean validateResident(Resident resident) throws ValidationException {
		ValidationException validationException = new ValidationException();
		
		validateName(resident.getName(), validationException);
		validateEmail(resident.getEmail(), validationException);
		validateHouseId(resident.getId(), validationException);
		
		if (validationException.hasErrors()) {
			throw validationException;
		}
		
		return true;
	}
	
	private void validateName(String name, ValidationException validationException) {
		if (name.isEmpty()) {
			validationException.addError("name", "Name cannot be empty");
		}
		
		if (name.length() > 35) {
			validationException.addError("name", "Name length cannot be longer than 35 characters");
		}
	}
	
	private void validateEmail(String email, ValidationException validationException) {
		try {
			InternetAddress.parse(email, true)[0].validate();
		} catch (AddressException e) {
			validationException.addError("email", "E-mail address is invalid");
		}
	}

	private void validateHouseId(int houseId, ValidationException validationException) {
		 House house = houseDao.getHouse(houseId);
		 if(house == null) {
			 validationException.addError("houseId", "There is no house with the ID: " + houseId);
		 }
	}

}
