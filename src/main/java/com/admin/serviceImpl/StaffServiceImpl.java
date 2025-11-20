package com.admin.serviceImpl;

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
import com.admin.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService{

	@Autowired
	public StaffRepository staffRepository;
	
	@Autowired
	public BranchRepository branchRepository;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	// ======================================================
	//                      Save Staff
	// ======================================================
	
	@Override
	public Boolean saveStaff(StaffRequest staffRequest) {
		Boolean isSaved = false;
		Staff staff = null;
		
		Branch branch = branchRepository.findByBranchId(staffRequest.getBranchId());
		String staffId = generateStaffId(staffRequest.getBranchId());
		
		if(staffRequest != null) {
			staff = new Staff();
			staff.setBranch(branch);
			staff.setStaffId(staffId);
			staff.setPassword(passwordEncoder.encode(staffRequest.getPassword()));
			staff.setStatus(Constants.active);
			staff.setRole(String.join(",", staffRequest.getRoles()));
			staff.setPermissions(String.join(",", staffRequest.getPermissions()));
			BeanUtils.copyProperties(staffRequest, staff);
		}
		
		staff = staffRepository.save(staff);
		if(staff != null) {
			isSaved = true;
		}
		return isSaved;
	}
	
	// ======================================================
	//                      Generate Staff id
	// ======================================================

	public String generateStaffId(String branchId) {
		String prefix = branchId.substring(0, 2).toUpperCase();

		String staffId = staffRepository.finRecentStaffId();

		if (staffId == null || staffId.isEmpty()) {
			return prefix + "1000";
		}

		String lastNumberPart = staffId.substring(2);
		int number = Integer.parseInt(lastNumberPart);
		number++;

		return prefix + number;
	}

	// ======================================================
	//                      Save Staff
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
		List<Staff> staffs = staffRepository.findAll();
		
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
}
