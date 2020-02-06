package com.revature.views;

import com.revature.util.InputUtil;

public class MainMenu implements View {

	@Override
	public void showMenu() {
		System.out.println("Enter number corresponding to choice.");
		System.out.println("1. Houses Menu");
		System.out.println("2. Residents Menu");
		System.out.println("0. Quit");
	}

	@Override
	public View selectOption() {
		int selection = InputUtil.getIntInRange(0, 2);
		// User selects something - should be reusable
		// Do something with their selection, custom to this class
		switch(selection) {
			case 0: return null;
			case 1: return new HouseView();
			case 2: return null;
			default: return null;
		}
		
	}
	
}
