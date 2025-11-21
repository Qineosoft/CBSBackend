package com.admin.validation;

import com.admin.constants.Constants;
import com.admin.request.StaffRequest;

public class StaffVelidation {

	// ===============================================================
    //                Basic Null / Empty String Validation
    // ===============================================================
	
    private static Boolean isValid(String str) {
        return (str != null && !str.trim().isEmpty());
    }

    // ===============================================================
    //                Email Validation
    // ===============================================================
    
    private static Boolean isValidEmail(String email) {
        if (!isValid(email)) return false;

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }

    // ===============================================================
    //                Phone Validation (10 digits only)
    // ===============================================================
    
    private static Boolean isValidPhone(String phone) {
        if (!isValid(phone)) return false;

        return phone.matches("\\d{10}");
    }
    
	// ===============================================================
    //  Password Validation (Strong Password)
    //===============================================================
	private static Boolean isValidPassword(String password) {
		if (!isValid(password))
			return false;

		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		return password.matches(regex);
	}


    // ===============================================================
    //           Main Staff Validation Method
    // ===============================================================
    
    public static String validateStaff(StaffRequest staffRequest) {

        StringBuilder validationMessage = new StringBuilder();

        if (staffRequest == null) {
            return "Invalid request. Staff details cannot be empty.";
        }
        if (!isValid(staffRequest.getFullName())) {
            validationMessage.append("Full Name should not be empty. ");
        }
        if (!isValidEmail(staffRequest.getEmail())) {
            validationMessage.append("Please provide a valid email address. ");
        }
        if (!isValidPhone(staffRequest.getPhone())) {
            validationMessage.append("Please provide a valid 10-digit phone number. ");
        }
        if (!isValid(staffRequest.getBranchId())) {
            validationMessage.append("Branch ID should not be empty. ");
        }
        if (!isValidPassword(staffRequest.getPassword())) {
        	validationMessage.append("Password must be at least 8 characters and include uppercase, lowercase, number, and special character. ");
        }
        if (staffRequest.getRoles() == null || staffRequest.getRoles().isEmpty()) {
            validationMessage.append("At least one role must be assigned. ");
        }
        if (staffRequest.getPermissions() == null || staffRequest.getPermissions().isEmpty()) {
            validationMessage.append("At least one permission must be provided. ");
        }
        if (validationMessage.length() > 0) {
            return validationMessage.toString().trim();
        }

        return Constants.success;
    }
    
    // ===============================================================
    //           Main Staff Validation Method For Update
    // ===============================================================
    
    public static String validateStaffForUpdate(StaffRequest staffRequest) {

        StringBuilder validationMessage = new StringBuilder();

        if (staffRequest == null) {
            return "Invalid request. Staff details cannot be empty.";
        }
        if (!isValid(staffRequest.getFullName())) {
            validationMessage.append("Full Name should not be empty. ");
        }
//        if (!isValidEmail(staffRequest.getEmail())) {
//            validationMessage.append("Please provide a valid email address. ");
//        }
        if (!isValidPhone(staffRequest.getPhone())) {
            validationMessage.append("Please provide a valid 10-digit phone number. ");
        }
//        if (!isValid(staffRequest.getBranchId())) {
//            validationMessage.append("Branch ID should not be empty. ");
//        }
//        if (!isValidPassword(staffRequest.getPassword())) {
//        	validationMessage.append("Password must be at least 8 characters and include uppercase, lowercase, number, and special character. ");
//        }
        if (staffRequest.getRoles() == null || staffRequest.getRoles().isEmpty()) {
            validationMessage.append("At least one role must be assigned. ");
        }
        if (staffRequest.getPermissions() == null || staffRequest.getPermissions().isEmpty()) {
            validationMessage.append("At least one permission must be provided. ");
        }
        if (validationMessage.length() > 0) {
            return validationMessage.toString().trim();
        }

        return Constants.success;
    }
}
