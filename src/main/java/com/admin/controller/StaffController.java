package com.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.constants.Constants;
import com.admin.constants.UrlConstants;
import com.admin.request.StaffRequest;
import com.admin.response.StaffResponse;
import com.admin.responseData.ResponseData;
import com.admin.service.StaffService;
import com.admin.validation.StaffVelidation;

@RestController
@RequestMapping(UrlConstants.mainUrl)
public class StaffController {

	@Autowired
	private StaffService staffService;
	
	// ==================================================
	//                    Save Staff
	// ==================================================
	
	@PostMapping(UrlConstants.saveStaff)
	private ResponseData saveStaff(@RequestBody() StaffRequest staffRequest) {
		Boolean isSave = false;

		String isValidateStaff = StaffVelidation.validateStaff(staffRequest);

		if (isValidateStaff.equals(Constants.success)) {
			isSave = staffService.saveStaff(staffRequest);
			if (isSave) {
				return new ResponseData(isSave, "Staff Record Saved Successfully", HttpStatus.CREATED);
			} else {
				return new ResponseData(isSave, "Unable TO Save Staff", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseData(isValidateStaff, "Please Provide Correct Credentials", HttpStatus.BAD_REQUEST);
		}
	}
	
	// ==================================================
	//                    Update Staff
	// ==================================================
	
	@PostMapping(UrlConstants.updateStaff)
	private ResponseData updateStaff(@RequestBody() StaffRequest staffRequest) {
		Boolean isUpdate = false;

		String isValidateStaff = StaffVelidation.validateStaffForUpdate(staffRequest);

		if (isValidateStaff.equals(Constants.success)) {
			isUpdate = staffService.updateStaff(staffRequest);
			if (isUpdate) {
				return new ResponseData(isUpdate, "Staff Record Update Successfully", HttpStatus.CREATED);
			} else {
				return new ResponseData(isUpdate, "Unable TO Update Staff", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseData(isValidateStaff, "Please Provide Correct Credentials", HttpStatus.BAD_REQUEST);
		}
	}
	
	// ==================================================
	//                    Get All Staff
	// ==================================================
	
	@GetMapping(UrlConstants.getAllStaff)
	private ResponseData getAllStaff() {
		List<StaffResponse> staffResponses = staffService.getAllStaff();
		
		if(staffResponses != null && !staffResponses.isEmpty()) {
			return new ResponseData(staffResponses, "All Staff Details Fetched Successfully", HttpStatus.OK);
		}else {
			return new ResponseData(staffResponses, "Unable To Fetch Staff Details", HttpStatus.NOT_FOUND);
		}
	}
	
	// ==================================================
	//                   Get Staff By Id
	// ==================================================
	
	@GetMapping(UrlConstants.getById)
	private ResponseData getStaffById(@RequestParam(required = true) Long id) {
		StaffResponse staffResponse = staffService.getStaffById(id);
		
		if(staffResponse != null) {
			return new ResponseData(staffResponse, "Staff Details Fetched Successfully", HttpStatus.OK);
		}else {
			return new ResponseData(staffResponse, "Unable To Fetch Staff Details", HttpStatus.NOT_FOUND);
		}
	}
	
	// ==================================================
	//                   Delete Staff By Id
	// ==================================================
	
	@PostMapping(UrlConstants.deleteById)
	private ResponseData deleteById(@RequestParam(required = true) Long id) {
		Boolean isDeleted = false;
		
		isDeleted = staffService.deleteById(id);
		
		if(isDeleted != null) {
			return new ResponseData(isDeleted, "Staff Deleted Successfully", HttpStatus.OK);
		}else {
			return new ResponseData(isDeleted, "Unable To Delete Staff", HttpStatus.NOT_FOUND);
		}
	}
	
	// ==================================================
	//                   Delete Staff By Id
	// ==================================================
	
	@PostMapping(UrlConstants.passwordReset)
	public ResponseData resetPassword(@RequestParam(required = true) Long id,@RequestParam(required = true) String newPassword) {
	    Boolean isReset = staffService.resetPassword(id, newPassword);

	    if (isReset) {
	        return new ResponseData(true, "Password reset successfully. New credentials sent to staff email.", HttpStatus.OK);
	    }
	    return new ResponseData(false, "Unable to reset password. Staff not found.", HttpStatus.NOT_FOUND);
	}

}
