package com.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.constants.Constants;
import com.admin.request.BranchRequest;
import com.admin.responseData.ResponseData;
import com.admin.service.BranchService;
import com.admin.validation.BranchValidation;

@RestController
@RequestMapping("/branch")
public class BranchController {
	
	@Autowired
	private BranchService branchService;
	
	@PostMapping("/save")
	private ResponseData saveBranchDetails(@RequestBody BranchRequest branchRequest) {
		Boolean isSaved =  false;
		
		String isValidated = BranchValidation.isBranchvalidate(branchRequest);
		if(!isValidated.equals(Constants.success)) {
			return new ResponseData(isValidated, "Please Fill The Required Field", HttpStatus.BAD_REQUEST);
		}
		
		isSaved = branchService.saveBranchDetails(branchRequest);
		
		if(isSaved) {
			return new ResponseData(isSaved, "Branch Details Saved Successfully", HttpStatus.CREATED);
		}else {
			return new ResponseData(isSaved, "Not Able To Save Branch Details", HttpStatus.NOT_FOUND);
		}
	}
}
