package com.admin.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.constants.Constants;
import com.admin.entity.Branch;
import com.admin.entity.BranchAddress;
import com.admin.entity.BranchContact;
import com.admin.repository.BranchAddressRepository;
import com.admin.repository.BranchContactRepository;
import com.admin.repository.BranchRepository;
import com.admin.request.BranchAddressRequest;
import com.admin.request.BranchContactRequest;
import com.admin.request.BranchRequest;
import com.admin.service.BranchService;

@Service
public class BranchServiceImpl implements BranchService{

	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private BranchAddressRepository branchAddressRepository;
	
	@Autowired
	private BranchContactRepository branchContactRepository;
	
	// =================================================================
	//                       Save Branch Details
	// =================================================================
	
	@Override
	public Boolean saveBranchDetails(BranchRequest branchRequest) {
		
		Boolean isSaved = false;
		
		Branch branch = null;
		BranchAddress branchAddress = null;
		BranchContact branchContact = null;
		
		if(branchRequest != null) {
			BranchAddressRequest branchaddressRequest = branchRequest.getBranchAddress();
			BranchContactRequest branchContactRequest = branchRequest.getBranchContact();
			
			branch = new Branch();
			branch.setStatus(Constants.active);
			BeanUtils.copyProperties(branchRequest, branch);
			
			if(branchaddressRequest != null) {
				branchAddress = new BranchAddress();
				BeanUtils.copyProperties(branchaddressRequest, branchAddress);
			}
			if(branchContactRequest != null) {
				branchContact = new BranchContact();
				BeanUtils.copyProperties(branchContactRequest, branchContact);
			}
			
			branch = branchRepository.save(branch);
			branchAddress = branchAddressRepository.save(branchAddress);
			branchContact = branchContactRepository.save(branchContact);
		}
		
		if(branch != null && branchAddress != null && branchContact != null) {
			isSaved = true;
		}
		
		return isSaved;
	}

}
