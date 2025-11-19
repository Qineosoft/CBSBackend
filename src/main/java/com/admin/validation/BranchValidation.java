package com.admin.validation;

import com.admin.constants.Constants;
import com.admin.request.BranchAddressRequest;
import com.admin.request.BranchContactRequest;
import com.admin.request.BranchRequest;

public class BranchValidation {

	// ==================================================================
	//                  Check validation For String
	// ===================================================================
	private static Boolean isValid(String str) {
		Boolean validation = false;

		if (str != null && !str.trim().isEmpty()) {
			validation = true;
		}
		return validation;
	}
	
	// ==================================================================
	//                Check validation For Email
	// ===================================================================
	
	private static boolean isValidEmail(String email) {
	    if (!isValid(email)) return false;

	    String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	    return email.matches(regex);
	}
	
	// ==================================================================
	//                Check validation For Phone
	// ===================================================================
	
	private static boolean isValidPhone(String phone) {
	    if (!isValid(phone)) return false;

	    return phone.matches("\\d{10}");
	}



	// ==================================================================
	// Check validation For Branch
	// ===================================================================

	public static String isBranchvalidate(BranchRequest branchRequest) {

		StringBuilder validationMessage = new StringBuilder();
		BranchContactRequest branchContact = branchRequest.getBranchContact();
		BranchAddressRequest branchAddress = branchRequest.getBranchAddress();

		if (branchRequest != null) {
			if (!isValid(branchRequest.getBranchType())) {
				validationMessage.append("Branch Type Should not be Empty");
			} else if (!isValid(branchRequest.getBranchId())) {
				validationMessage.append("Branch ID Should not be Empty");
			} else if (!isValid(branchRequest.getBranchName())) {
				validationMessage.append(validationMessage + "Branch Name Should not be Empty");
			} else if (!isValid(branchRequest.getIfscCode())) {
				validationMessage.append(validationMessage + "IFSC Code Should not be Empty");
			} else if (!isValid(branchRequest.getLocation())) {
				validationMessage.append("Location Should not be Empty");
			} else if (!isValid(branchRequest.getOperatingHrs())) {
				validationMessage.append(validationMessage + "Operating Hours Should not be Empty");
			} else if (branchRequest.getDateOpened() == null) {
				validationMessage.append("Date Of Opening Should not be Empty");
			}
		}

			if (branchContact == null) {
				validationMessage.append("Branch Contact details cannot be empty. ");
			} else {
				if (!isValid(branchContact.getManagerName())) {
					validationMessage.append("Manager Name should not be empty. ");
				}

				else if (!isValidEmail(branchContact.getManagerEmail())) {
					validationMessage.append("Please Provide Correct Manager Email. ");
				}

				else if (!isValidPhone(branchContact.getManagerPhone())) {
					validationMessage.append("Please Provide Correct Manager Phone Number. ");
				}

				else if (!isValidPhone(branchContact.getBranchContactNum())) {
					validationMessage.append("Please Provide Correct Branch Contact Number. ");
				}

				else if (!isValidEmail(branchContact.getBranchEmail())) {
					validationMessage.append("Please Provide Correct Branch Email Id. ");
				}
			}
			if (branchAddress == null) {
				validationMessage.append("Branch Address details cannot be empty. ");
			} else {
				if (!isValid(branchAddress.getFullAddress())) {
					validationMessage.append("Full Address should not be empty. ");
				}

				else if (!isValid(branchAddress.getCity())) {
					validationMessage.append("City should not be empty. ");
				}

				else if (!isValid(branchAddress.getState())) {
					validationMessage.append("State should not be empty. ");
				}

				else if (!isValid(branchAddress.getPincode())) {
					validationMessage.append("Pincode should not be empty. ");
				}

				else if (!isValid(branchAddress.getCountry())) {
					validationMessage.append("Country should not be empty. ");
				}
			}

			if (validationMessage.length() > 0) {
				return validationMessage.toString().trim();
			}
			return Constants.success;
	}

}
