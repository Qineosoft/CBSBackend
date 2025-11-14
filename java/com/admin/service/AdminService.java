package com.admin.service;

import java.util.Map;

import com.admin.request.AdminRequest;

public interface AdminService {

	public Boolean isEmailExist(String email);

	public Map<String, Object> loginUser(AdminRequest adminRequest);

	public Boolean verifyOtp(String otp, Long id);

	public Boolean loginAdmin(String emailId, String password);

}
