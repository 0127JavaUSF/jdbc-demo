package com.revature.views;

import com.revature.util.InputUtil;

public class HouseView implements View {

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
		default: createHouse(); return this;
		case 0: return new MainMenu();
		}
	}

	private void loadHouse() {
		System.out.println("Enter house ID: ");
		int id = InputUtil.getIntInRange(1, Integer.MAX_VALUE);
		
		House house = // TODO
				
		System.out.println(house);
	}
	
	private void createHouse() {
		
	}
	
}
