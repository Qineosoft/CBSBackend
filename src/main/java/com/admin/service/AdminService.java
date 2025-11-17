package com.admin.service;

import java.util.Map;

import com.admin.request.AdminRequest;

public interface AdminService {

	public Boolean isEmailExist(String email);

	public Map<String, Object> loginUser(AdminRequest adminRequest);

	public Boolean verifyOtp(String otp, Long id, String email);

	public Boolean loginAdmin(String emailId, String password);

	public Boolean passwordChange(String emailId, String password, String confirmPassword);

	public Boolean sendOtpToMail(String emailId);

	public Boolean loginAdmin(String emailId);

}
