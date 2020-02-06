package com.revature.views;

import com.revature.daos.HouseDao;
import com.revature.models.House;
import com.revature.util.InputUtil;

public class HouseView implements View {

	HouseDao houseDao = new HouseDao();
	
	@Override
	public void showMenu() {
		System.out.println("1. Load House");
		System.out.println("2. Create House");
		System.out.println("0. Back");
	}

	@Override
	public View selectOption() {
		int selection = InputUtil.getIntInRange(0, 2);
		switch (selection) {
		case 1: loadHouse(); return this;
		case 2: createHouse(); return this;
		default:
		case 0: return new MainMenu();
		}
	}

	private void loadHouse() {
		System.out.println("Enter house ID: ");
		int id = InputUtil.getIntInRange(1, Integer.MAX_VALUE);
		
		House house = houseDao.getHouse(id);
				
		System.out.println(house);
	}
	
	private void createHouse() {
		System.out.println("Please enter building name: ");
		String buildName = InputUtil.getNextString();
		
		System.out.println("Please enter apartment number: ");
		String apartmentNumber = InputUtil.getNextString();
		
		House house = new House(0, buildName, apartmentNumber);
		house = houseDao.createHouse(house);
		System.out.println(house);
		
	}
	
}
