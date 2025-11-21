package com.admin.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.admin.constants.Constants;
import com.admin.entity.Branch;
import com.admin.entity.Staff;
import com.admin.repository.BranchRepository;
import com.admin.repository.StaffRepository;
import com.admin.request.StaffRequest;
import com.admin.response.StaffResponse;
import com.admin.service.EmailSender;
import com.admin.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService{

	@Autowired
	public StaffRepository staffRepository;
	
	@Autowired
	public BranchRepository branchRepository;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	public EmailSender emailSender;
	
	// ======================================================
	//                      Save Staff
	// ======================================================
	
	@Override
	public Boolean saveStaff(StaffRequest staffRequest) {
	    if (staffRequest == null) {
	        return false;
	    }

	    Branch branch = branchRepository.findByBranchId(staffRequest.getBranchId());
	    String staffId = generateUserId(staffRequest.getFullName());
	    

	    Staff staff = new Staff();

	    staff.setBranch(branch);
	    staff.setStaffId(staffId);
	    staff.setPassword(passwordEncoder.encode(staffRequest.getPassword()));
	    staff.setStatus(Constants.active);
	    staff.setRole(String.join(",", staffRequest.getRoles()));
	    staff.setPermissions(String.join(",", staffRequest.getPermissions()));
	    staff.setPasswordCreatedDate(LocalDate.now());
	    staff.setPasswordExpiryDate(LocalDate.now().plusDays(90));

	    BeanUtils.copyProperties(staffRequest, staff, 
	            "branch", "staffId", "password", "status", "role", "permissions");

	    Staff saved = staffRepository.save(staff);
	    emailSender.sendUserIDPasswordToMail(staffRequest.getEmail(), staffId, staffRequest.getPassword());

	    return saved != null;
	}

	
	// ======================================================
	//                      Generate Staff id
	// ======================================================

	public String generateUserId(String fullName) {
	    String prefix = fullName.substring(0, 4).toUpperCase();
	    
	    String lastStaffId = staffRepository.finRecentStaffId();
	    if (lastStaffId == null || lastStaffId.isEmpty()) {
	        return prefix + "0001";
	    }

	    String numericPart = lastStaffId.substring(4);
	    int number = Integer.parseInt(numericPart);
	    number++; 
	    String newNumber = String.format("%04d", number);

	    return prefix + newNumber;
	}
	

	// ======================================================
	//                      Update Staff
	// ======================================================
	
	@Override
	public Boolean updateStaff(StaffRequest staffRequest) {
		Boolean isUpdate = false;
		
		Staff staff = staffRepository.findById(staffRequest.getId()).orElse(null);
		
		if(staffRequest != null) {
			staff.setPassword(passwordEncoder.encode(staffRequest.getPassword()));
			staff.setRole(String.join(",", staffRequest.getRoles()));
			staff.setPermissions(String.join(",", staffRequest.getPermissions()));
			BeanUtils.copyProperties(staffRequest, staff);
		}
		
		staff = staffRepository.save(staff);
		if(staff != null) {
			isUpdate = true;
		}
		return isUpdate;
	}

	// ======================================================
	//                   Get All Staff Details
	// ======================================================
	
	@Override
	public List<StaffResponse> getAllStaff() {
		List<StaffResponse> staffResponses = null;
		StaffResponse staffResponse = null;
		List<Staff> staffs = staffRepository.findAllActiveStaff(Constants.active);
		
		if(staffs != null && !staffs.isEmpty()) {
			staffResponses = new ArrayList<>();
			for(Staff staff:staffs) {
			staffResponse = new StaffResponse();
			staffResponse.setRole(Arrays.asList(staff.getRole().split(",")));
			staffResponse.setPermissions(Arrays.asList(staff.getPermissions().split(",")));
			staffResponse.setBranchId(staff.getBranch().getBranchId());
			BeanUtils.copyProperties(staff, staffResponse);
			staffResponses.add(staffResponse);
			}
		}
		
		return staffResponses;
	}
	
	// ======================================================
	//                   Get Staff Details By Id
	// ======================================================
	
	@Override
	public StaffResponse getStaffById(Long id) {
		StaffResponse staffResponse = null;
		Staff staff = staffRepository.findById(id).orElse(null);
		
		if(staff != null) {
			staffResponse = new StaffResponse();
			staffResponse.setRole(Arrays.asList(staff.getRole().split(",")));
			staffResponse.setPermissions(Arrays.asList(staff.getPermissions().split(",")));
			staffResponse.setBranchId(staff.getBranch().getBranchId());
			BeanUtils.copyProperties(staff, staffResponse);
			}
		return staffResponse;
	}
	
	// ======================================================
	//                   Delete Staff Details
	// ======================================================

	@Override
	public Boolean deleteById(Long id) {
		Boolean isDeleted = false;
		
			int deleteStaff = staffRepository.deleteStaff(id, Constants.inactive);
			
			if(deleteStaff != 0) {
				isDeleted = true;
			}
		return isDeleted;
	}
}
