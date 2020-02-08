package com.revature.services;

import com.revature.exceptions.ValidationException;
import com.revature.models.Resident;

/**
 * Service layers generally apply internal business logic separate from input/output and database interactivity.
 * This service layer will be used to validate a Resident instance against the rules of our application.
 * If the validation fails, a ValidationException will be thrown which will provide validation details which can then be
 * printed to the user in the View.
 */
public interface IResidentService {
	public Resident createResident(Resident resident) throws ValidationException;
}
