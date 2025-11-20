package com.admin.serviceImpl;

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
//			branch.setBranchId(staffRequest.getBranchId());
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
}
