package com.revature.views;

import com.revature.exceptions.ValidationException;
import com.revature.models.Resident;
import com.revature.services.IResidentService;
import com.revature.util.InputUtil;

public class ResidentView implements View {

	IResidentService residentService;

	@Override
	public void showMenu() {
		System.out.println("1. Create Resident");
		System.out.println("0. Back");
	}

	@Override
	public View selectOption() {
		int selection = InputUtil.getIntInRange(0, 1);

		switch (selection) {
		case 1:
			createResident();
			return this;
		case 0:
		default:
			return new MainMenu();
		}
	}

	private void createResident() {

		Resident createdResident = null;
		while (createdResident == null) {
		String name = InputUtil.getNextString();
		int rent = InputUtil.getIntInRange(100, 1500);
		int houseId = InputUtil.getIntInRange(1, 5);
		String email = InputUtil.getNextString();
		Resident resident = new Resident(0, name, rent, houseId, email);
			try {
				createdResident = residentService.createResident(resident);
			} catch (ValidationException e) {
				e.printErrors();
			}

		}
	}

}
