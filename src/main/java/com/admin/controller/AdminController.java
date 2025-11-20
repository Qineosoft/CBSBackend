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
		
		if(map != null && !map.isEmpty()) {
		return new ResponseData(map, "OTP Send Successfully", HttpStatus.CREATED);
		}else {
			return new ResponseData(map, "Please Provide Correct Credential", HttpStatus.NOT_FOUND);
		}
		
	}
	
	// ============================================================================
	//                          Verify OTP
	// ============================================================================
	
	@GetMapping("/verify/otp")
	private ResponseData verifyOtp(@RequestParam(required = true) String otp, @RequestParam(required = false) Long id,@RequestParam(required = false) String  email) {
		Boolean isCorrect = adminService.verifyOtp(otp, id, email);
		
		ResponseData responseDate = null;
		
		if(id != null) {
		if(isCorrect) {
			responseDate = new ResponseData("Admin Registered Successfully", "Admin Registered Successfully", HttpStatus.CREATED);
		}
		else {
			responseDate = new ResponseData("Not Able To Register Admin", "OTP Is Not Correct", HttpStatus.NOT_FOUND);
		}
		}else if(email != null) {
			if(isCorrect) {
				responseDate = new ResponseData("Password Changed Successfully", "Password Changed Successfully", HttpStatus.CREATED);
			}
			else {
				responseDate = new ResponseData("Not Able To Change Password", "Bed Credentials", HttpStatus.NOT_FOUND);
			}
		}
		
		return responseDate;
		
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
			return new ResponseData("Not Able To Login Admin", "Login Failed", HttpStatus.NOT_FOUND);
	}
	
	// ============================================================================
	//                          Forgot Password
	// ============================================================================
	
	@PostMapping("/password/change")
	private ResponseData passwordChange(@RequestParam(required = true) String emailId, @RequestParam(required = true) String password, @RequestParam(required = true) String confirmPassword) {
		
		if(!password.equals(confirmPassword)) {
			return new ResponseData("Password Is Not Correct", "Password Is Not Correct", HttpStatus.BAD_REQUEST);
		}
		
		Boolean isPasswordChanged = adminService.passwordChange(emailId, password, confirmPassword);
		
		if(isPasswordChanged) {
			return new ResponseData(isPasswordChanged, "Password Changed Successfully", HttpStatus.OK);
		}
		else {
			return new ResponseData("Not Able To Change Password", "Bad Credentials", HttpStatus.NOT_FOUND);
		}
	}
	
	// ============================================================================
	//                          Send OTP BY Mail
	// ============================================================================
	
	@GetMapping("/sendOTP/bymail")
	private ResponseData sendOtpToMail(@RequestParam(required = true) String emailId) {

		Boolean iscorrectEmail = adminService.isEmailExist(emailId);

		if (iscorrectEmail) {

			Boolean isOtpSend = adminService.sendOtpToMail(emailId);

			if (isOtpSend) {
				return new ResponseData(isOtpSend, "OTP Sent Successfully", HttpStatus.OK);
			} else {
				return new ResponseData("Not Able To Send OTP", "Bad Credentials", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseData(iscorrectEmail, "Email is Not Registered", HttpStatus.NOT_FOUND);
		}
	}
}
