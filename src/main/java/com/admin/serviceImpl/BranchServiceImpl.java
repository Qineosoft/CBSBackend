package com.admin.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.admin.response.BranchAddressResponse;
import com.admin.response.BranchContactResponse;
import com.admin.response.BranchResponse;
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
			
			if(branchaddressRequest != null) {
				branchAddress = new BranchAddress();
				BeanUtils.copyProperties(branchaddressRequest, branchAddress);
			}
			if(branchContactRequest != null) {
				branchContact = new BranchContact();
				BeanUtils.copyProperties(branchContactRequest, branchContact);
			}
			
			branchAddress = branchAddressRepository.save(branchAddress);
			branchContact = branchContactRepository.save(branchContact);
			
			branch.setStatus(Constants.active);
			branch.setBranchAddress(branchAddress);
			branch.setBranchContact(branchContact);
			branch.setServices(String.join(",", branchRequest.getServices()));
			BeanUtils.copyProperties(branchRequest, branch);
			branch = branchRepository.save(branch);
		}
		
		if(branch != null && branchAddress != null && branchContact != null) {
			isSaved = true;
		}
		
		return isSaved;
	}
	
	// =================================================================
	//                       Fetch Branch Details
	// =================================================================

	@Override
	public List<BranchResponse> getAllBranch() {
		List<BranchResponse> branchResponseList = new ArrayList<>();
		
		List<Branch> branchList = branchRepository.findAllBranchWithActiveStatus(Constants.active);
		
		 for (Branch branch : branchList) {

		        BranchResponse branchResponse = new BranchResponse();
		        
		        // -------- BRANCH CONTACT RESPONSE --------
		        BranchContact contact = branch.getBranchContact();
		        if (contact != null) {
		            BranchContactResponse contactResponse = new BranchContactResponse();
		            
		            BeanUtils.copyProperties(contact, contactResponse);
		            branchResponse.setBranchContactResponse(contactResponse);
		        }

		        // -------- BRANCH ADDRESS RESPONSE --------
		        BranchAddress address = branch.getBranchAddress();
		        if (address != null) {
		            BranchAddressResponse addressResponse = new BranchAddressResponse();
		            
		            BeanUtils.copyProperties(address, addressResponse);
		            branchResponse.setBranchAddressResponse(addressResponse);
		        }
		        
		        BeanUtils.copyProperties(branch, branchResponse);

		        branchResponseList.add(branchResponse);
		    }
		return branchResponseList;
	}

	// =================================================================
	//                       Update Branch Details
	// =================================================================
	
	@Override
	public Boolean updateBranchDetails(BranchRequest branchRequest) {
		Boolean isUpdate = false;
		
		Branch branch = null;
		BranchAddress branchAddress = null;
		BranchContact branchContact = null;
		
		if(branchRequest != null) {
			BranchAddressRequest branchaddressRequest = branchRequest.getBranchAddress();
			BranchContactRequest branchContactRequest = branchRequest.getBranchContact();
			
			branch = branchRepository.findById(branchRequest.getId()).orElse(null);
			
			if(branchaddressRequest != null) {
				branchAddress = branch.getBranchAddress();
				if(branchAddress != null) {
				BeanUtils.copyProperties(branchaddressRequest, branchAddress);
				}else {
					branchAddress = new BranchAddress();
					BeanUtils.copyProperties(branchaddressRequest, branchAddress);
				}
			}
			if(branchContactRequest != null) {
				branchContact = branch.getBranchContact();
				if(branchContact != null) {
				BeanUtils.copyProperties(branchContactRequest, branchContact);
				}else {
					branchContact = new BranchContact();
					BeanUtils.copyProperties(branchContactRequest, branchContact);
				}
			}
			
			branchAddress = branchAddressRepository.save(branchAddress);
			branchContact = branchContactRepository.save(branchContact);
			
			branch.setStatus(Constants.active);
			branch.setBranchAddress(branchAddress);
			branch.setBranchContact(branchContact);
			BeanUtils.copyProperties(branchRequest, branch);
			branch = branchRepository.save(branch);
		}
		
		if(branch != null && branchAddress != null && branchContact != null) {
			isUpdate = true;
		}
		
		return isUpdate;
	}

	// =================================================================
	//                       Delete Branch Details
	// =================================================================
	
	@Override
	public Boolean deleteBranch(Long id) {
		Boolean isDeleted = false;
		Branch branch = branchRepository.findById(id).orElse(null);
		
		branch.setStatus(Constants.inactive);
		
		branch = branchRepository.save(branch);
		
		if(branch != null) {
			isDeleted = true;
		}
		return isDeleted;
	}

	// =================================================================
	//                       Get Branch By Id
	// =================================================================
	
	@Override
	public BranchResponse getBranchById(Long id) {
		BranchResponse branchResponse = null;
		BranchAddressResponse branchAddressResponse = null;
		BranchContactResponse branchContactResponse = null;
		Branch branch = branchRepository.findById(id).orElse(null);
		
		if(branch != null) {
			branchResponse = new BranchResponse();
			if(branch.getBranchAddress() != null) {
				branchAddressResponse = new BranchAddressResponse();
				BeanUtils.copyProperties(branch.getBranchAddress(),branchAddressResponse);
			}
			if(branch.getBranchContact() != null) {
				branchContactResponse = new BranchContactResponse();
				BeanUtils.copyProperties(branch.getBranchContact(), branchContactResponse);
			}
			branchResponse.setBranchAddressResponse(branchAddressResponse);
			branchResponse.setBranchContactResponse(branchContactResponse);
			List<String> servicesList = Arrays.asList(branch.getServices().split(","));
			branchResponse.setServices(servicesList);
			BeanUtils.copyProperties(branch, branchResponse);
		}
		
		return branchResponse;
	}

	// =================================================================
	//                       Fetch Branch Name Id
	// =================================================================
	
	@Override
	public Map<String, String> getAllBranchNameId() {
		Map<String, String> map = new HashMap<>();
		
		List<Branch> branchList = branchRepository.findAll();
		if(branchList != null && !branchList.isEmpty()) {
			for(Branch branch : branchList) {
				map.put(branch.getBranchId(), branch.getBranchName());
			}
		}
		return map;
	}
	
	// =================================================================
	//                       Validate For Duplicate
	// =================================================================
	
//	@Override
//	public String ValidateForDuplicate(BranchRequest branchRequest) {
//		String message = null;
//		
//		if(branchRequest != null) {
//			
////		}
////	}

}
