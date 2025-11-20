package com.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.constants.Constants;
import com.admin.constants.UrlConstants;
import com.admin.request.StaffRequest;
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
	private  ResponseData saveStaff(@RequestBody StaffRequest staffRequest) {
		Boolean isSave = false;
		
		String isValidateStaff = StaffVelidation.validateStaff(staffRequest);
		
		if (!isValidateStaff.equals(Constants.success)) {
	        return new ResponseData(isValidateStaff, "Please correct the validation errors", HttpStatus.BAD_REQUEST);
	    }
		
		isSave = staffService.saveStaff(staffRequest);
		if(isSave) {
			return new ResponseData(isSave, "Staff Record Saved Successfully", HttpStatus.CREATED);
		}else {
			return new ResponseData(isSave, "Unable TO Save Staff", HttpStatus.NOT_FOUND);
		}
	}
	
	
}
