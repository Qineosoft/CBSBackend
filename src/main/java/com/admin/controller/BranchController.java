package com.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.constants.Constants;
import com.admin.request.BranchRequest;
import com.admin.response.BranchResponse;
import com.admin.responseData.ResponseData;
import com.admin.service.BranchService;
import com.admin.validation.BranchValidation;

@RestController
@RequestMapping("/branch")
public class BranchController {
	
	@Autowired
	private BranchService branchService;
	
	// ===========================================================================
	//                          Save Branch Details
	// ===========================================================================
	
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
	
	// ===========================================================================
	//                          Get All Branch Details
	// ===========================================================================
	
	@GetMapping("/get/all")
	private ResponseData getAllBranch() {
		List<BranchResponse> branchResponses = branchService.getAllBranch();
		
		if(branchResponses != null && !branchResponses.isEmpty()) {
			return new ResponseData(branchResponses, "Branch Details Fetched Successfully", HttpStatus.OK);
		}else {
			return new ResponseData(branchResponses, "Unable To Fetch Branch Details", HttpStatus.OK);
		}
	}
}
