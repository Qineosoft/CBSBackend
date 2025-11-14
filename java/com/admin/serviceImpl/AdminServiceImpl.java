package com.admin.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.admin.constants.Constants;
import com.admin.entity.Admin;
import com.admin.repository.AdminRepository;
import com.admin.request.AdminRequest;
import com.admin.service.AdminService;
import com.admin.service.EmailSender;

@Service
public class AdminServiceImpl implements AdminService{

	
	@Autowired
	public AdminRepository adminRepository;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	public EmailSender emailSender;
	
	@Autowired
	public Random random;
	
	// ====================================================
	//                  Is Email Exist
	// ====================================================
	
	@Override
	public Boolean isEmailExist(String email) {
		Boolean isPresent = false;
		Admin admin = adminRepository.findByEmail(email);
		if(admin != null)
			isPresent = true;
		
		return isPresent;
	}

	// ====================================================
	//                  User Login
	// ====================================================
	
	@Override
	public Map<String, Object> loginUser(AdminRequest adminRequest) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String message = "OTP For Email Verification: ";
		Admin admin = null;
		Map<String, Object> map = null;
		
		try {
			if(adminRequest != null) {
				admin = new Admin();
				
				String adminId = generateId();
				Integer otp = 100000 + random.nextInt(900000);
				
				admin.setAdminId(adminId);
				admin.setCreatedAt(new Date());
				admin.setEmail(adminRequest.getEmail());
				admin.setOtp(otp.toString());
				admin.setPassword(passwordEncoder.encode(adminRequest.getPassword()));
				admin.setStatus(Constants.inactive);
//				admin.setUpdatedAt(null);      Need to be update latter
			}
			admin = adminRepository.save(admin);
			emailSender.sendMail(adminRequest.getEmail(), admin.getOtp(), message);
			
			if(admin != null) {
				map = new HashMap<>();
				map.put("id", admin.getId());
				map.put("message", "User Registered Successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	// ====================================================
	//                  Generate Admin ID
	// ====================================================

	private String generateId() {
		String adminId = adminRepository.generateId();
		if(adminId != null) {
			 int idNum = Integer.parseInt(adminId.replace("ADM", ""));
		        adminId = "ADM" + String.format("%03d", idNum + 1);
		}else {
			adminId = "ADM" + 1000;
		}
		return adminId;
	}

	// ====================================================
	//                  verify OTP
	// ====================================================
	
	@Override
	public Boolean verifyOtp(String otp, Long id) {
		Boolean isVerify = false;
		Admin admin = adminRepository.findById(id).orElse(null);
		
		if(otp.equals(admin.getOtp()))
			isVerify = true;
		
		if(isVerify)
			admin.setStatus(Constants.active);
		    admin.setUpdatedAt(new Date());
		    
		    admin = adminRepository.save(admin);
		
			
		return isVerify;
	}
	
	// ====================================================
	//                  Login User
	// ====================================================

	@Override
	public Boolean loginAdmin(String emailId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
