package com.admin.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	// ======================================================
	//                      Save Staff
	// ======================================================
	
	@Override
	public Boolean saveStaff(StaffRequest staffRequest) {
		Boolean isSaved = false;
		Staff staff = null;
		
		String branch = branchRepository.findBrnach(staffRequest.getBranchId());
		
		if(staffRequest != null) {
			staff = new Staff();
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

}
