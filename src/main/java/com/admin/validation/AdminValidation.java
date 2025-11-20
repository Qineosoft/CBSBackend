package com.admin.validation;

import com.admin.constants.Constants;
import com.admin.request.AdminRequest;

public class AdminValidation {
	
	// ===============================================================
    //            Basic Null / Empty Validation
    // ===============================================================
	
    private static Boolean isValid(String str) {
        return (str != null && !str.trim().isEmpty());
    }

    // ===============================================================
    //                   Email Validation
    // ===============================================================
    
    private static Boolean isValidEmail(String email) {
        if (!isValid(email)) return false;

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }

    // ===============================================================
    //            Password Validation (Strong Password)
    // ===============================================================
    
    private static Boolean isValidPassword(String password) {
        if (!isValid(password)) return false;

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        
        return password.matches(regex);
    }

    // ===============================================================
    //            Main Validation Method for Admin
    // ===============================================================
    
    public static String isAdminValidate(AdminRequest adminRequest) {

        StringBuilder validationMessage = new StringBuilder();

        if (adminRequest == null) {
            return "Invalid request. Admin details cannot be empty.";
        }
        if (!isValidEmail(adminRequest.getEmail())) {
            validationMessage.append("Please provide a valid email address. ");
        }
        if (!isValidPassword(adminRequest.getPassword())) {
            validationMessage.append("Password must contain minimum 8 characters including uppercase, lowercase, number, and special character. ");
        }
        if (!isValid(adminRequest.getConfirmPassword()) || !adminRequest.getPassword().equals(adminRequest.getConfirmPassword())) {
            validationMessage.append("Password and Confirm Password must match. ");
        }

        if (validationMessage.length() > 0) {
            return validationMessage.toString().trim();
        }

        return Constants.success;
    }
    
    // ===============================================================
    //                 Admin Login validation 
    // ===============================================================
    
    public static String isLoginValid(String email, String password) {

        StringBuilder validationMessage = new StringBuilder();

        if (!isValidEmail(email)) {
            validationMessage.append("Please provide a valid email address. ");
        }
        if (isValidPassword(password)) {
        	validationMessage.append("Password must be at least 8 characters and include uppercase, lowercase, number, and special character. ");
        }
        if (validationMessage.length() > 0) {
            return validationMessage.toString().trim();
        }

        return Constants.success;
    }

}
