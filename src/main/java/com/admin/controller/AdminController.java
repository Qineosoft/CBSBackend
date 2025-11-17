package com.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.request.AdminRequest;
import com.admin.responseData.ResponseData;
import com.admin.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	public AdminService adminService;
	
	// ============================================================================
	//                          SignUp
	// ============================================================================
	
	@PostMapping("/signUp")
	private ResponseData signUp(@RequestBody AdminRequest adminRequest) {
		Boolean isAvailable = adminService.isEmailExist(adminRequest.getEmail());
		Map<String, Object> map = new HashMap<>();
		
		if(isAvailable)
			return new ResponseData(isAvailable, "User alreaddy exist", HttpStatus.BAD_REQUEST);
		
		map = adminService.loginUser(adminRequest);
		
		return new ResponseData(map, "", HttpStatus.CREATED);
		
	}
	
	// ============================================================================
	//                          Verify OTP
	// ============================================================================
	
	@GetMapping("/verify/otp")
	private ResponseData verifyOtp(@RequestParam(required = true) String otp, @RequestParam(required = true) Long id) {
		Boolean isCorrect = adminService.verifyOtp(otp, id);
		
		if(isCorrect) {
			return new ResponseData("Admin Registered Successfully", "Admin Registered Successfully", HttpStatus.CREATED);
		}
		else {
			return new ResponseData("Not Able To Register Admin", "OTP Is Not Correct", HttpStatus.NOT_FOUND);
		}
		
	}
	
	// ============================================================================
	//                          Login User
	// ============================================================================
	
	@GetMapping("/login")
	private ResponseData loginAdmin(@RequestParam(required = true) String emailId, @RequestParam(required = true) String password) {
		
		Boolean iscorrectEmailPassword = adminService.loginAdmin(emailId, password);
		
		if(iscorrectEmailPassword) {
			return new ResponseData(iscorrectEmailPassword, "Admin Login Successfully", HttpStatus.OK);
		}
		else
			return new ResponseData("Not Able To Login Admin", "Bed Credentials", HttpStatus.NOT_FOUND);
	}
}
