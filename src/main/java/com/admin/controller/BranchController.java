package com.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.constants.Constants;
import com.admin.repository.BranchRepository;
import com.admin.request.BranchRequest;
import com.admin.response.BranchResponse;
import com.admin.responseData.ResponseData;
import com.admin.service.BranchService;
import com.admin.validation.BranchValidation;

import jakarta.servlet.http.HttpServletRequest;

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
		ResponseData responseData = null;
		if(branchRequest.getId() == null) {
		String isValidated = BranchValidation.isBranchvalidate(branchRequest);
		if(!isValidated.equals(Constants.success)) {
			return new ResponseData(isValidated, "Please Fill The Required Field", HttpStatus.BAD_REQUEST);
		}
		
		isSaved = branchService.saveBranchDetails(branchRequest);
		
		if(isSaved) {
			responseData = new ResponseData(isSaved, "Branch Details Saved Successfully", HttpStatus.CREATED);
		}else {
			responseData = new ResponseData(isSaved, "Not Able To Save Branch Details", HttpStatus.NOT_FOUND);
		}
		}else if(branchRequest.getId() != null) {
			isSaved = branchService.updateBranchDetails(branchRequest);
			
			if(isSaved) {
				responseData = new ResponseData(isSaved, "Branch Details Updated Successfully", HttpStatus.CREATED);
			}else {
				responseData = new ResponseData(isSaved, "Not Able To Update Branch Details", HttpStatus.NOT_FOUND);
			}
		}
		return responseData;
	}
	
	// ===========================================================================
	//                          Get All Branch Details
	// ===========================================================================
	
	@GetMapping("/get/byid")
	private ResponseData getBranchById(@RequestParam(required = true) Long id) {
		BranchResponse branchResponse = branchService.getBranchById(id);
		
		if(branchResponse != null) {
			return new ResponseData(branchResponse, "Branch Details Fetch successfully", HttpStatus.OK);
		}else {
			return new ResponseData(branchResponse, "Not Able TO Fetch Branch Details", HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	// ===========================================================================
	//                          Get Branch Details
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
	
	// ===========================================================================
	//                          Delete Branch Details
	// ===========================================================================
	
	@PostMapping("/delete")
	private ResponseData deleteBranch(Long id) {
		Boolean isDeleted = false;
		
		isDeleted = branchService.deleteBranch(id);
		
		if(isDeleted) {
			return new ResponseData(isDeleted,"Branch Deleted Successfully", HttpStatus.OK);
		}else {
			return new ResponseData(isDeleted, "Not Able To Delete Branch", HttpStatus.NOT_FOUND);
		}
	}
	
	// ===========================================================================
	//                          Get Branch Id Name
	// ===========================================================================
	
	@GetMapping("/get/naem/id/all")
	private ResponseData getAllBranchNameId() {
		Map<String, String> map = null;
		map = branchService.getAllBranchNameId();
		
 		if(map != null && !map.isEmpty()) {
			return new ResponseData(map, "Branch Name Id Fetched Successfully", HttpStatus.OK);
		}else {
			return new ResponseData(map, "Unable To Fetch Branch Name Id", HttpStatus.NOT_FOUND);
		}
	}
	
}
