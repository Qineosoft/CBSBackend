package com.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.request.BranchRequest;
import com.admin.responseData.ResponseData;
import com.admin.service.BranchService;
import com.admin.validation.BranchValidation;

@RestController
@RequestMapping("/branch")
public class BranchController {
	
	@Autowired
	private BranchService branchService;
	
//	private BranchValidation branchValidation;

//	private ResponseData saveBranchDetails(@RequestBody BranchRequest branchRequest) {
//		Boolean isSaved =  false;
//		
//		String isValidated = BranchValidation.isBranchvalidate(branchRequest);
//	}
}
