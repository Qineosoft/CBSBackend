package com.admin.serviceImpl;

import java.util.ArrayList;
import java.util.List;

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
	
	// =================================================================
	//                       Fetch Branch Details
	// =================================================================

	@Override
	public List<BranchResponse> getAllBranch() {
		List<BranchResponse> branchResponseList = new ArrayList<>();
		BranchContactResponse branchContactResponse = null;
		BranchAddressResponse branchAddressResponse = null;
		
		List<Branch> branchList = branchRepository.findAll();
		
		 for (Branch branch : branchList) {

		        BranchResponse branchResponse = new BranchResponse();
		        branchResponse.setBranchType(branch.getBranchType());
		        branchResponse.setBranchId(branch.getBranchId());
		        branchResponse.setBranchName(branch.getBranchName());
		        branchResponse.setIfscCode(branch.getIfscCode());
		        branchResponse.setLocation(branch.getLocation());
		        branchResponse.setOperatingHrs(branch.getOperatingHrs());
		        branchResponse.setDateOpened(branch.getDateOpened());

		        // -------- BRANCH CONTACT RESPONSE --------
		        BranchContact contact = branch.getBranchContact();
		        if (contact != null) {
		            BranchContactResponse contactResponse = new BranchContactResponse();
		            contactResponse.setManagerName(contact.getManagerName());
		            contactResponse.setManagerEmail(contact.getManagerEmail());
		            contactResponse.setManagerPhone(contact.getManagerPhone());
		            contactResponse.setBranchContactNum(contact.getBranchContactNum());
		            contactResponse.setBranchEmail(contact.getBranchEmail());
		            branchResponse.setBranchContactResponse(contactResponse);
		        }

		        // -------- BRANCH ADDRESS RESPONSE --------
		        BranchAddress address = branch.getBranchAddress();
		        if (address != null) {
		            BranchAddressResponse addressResponse = new BranchAddressResponse();
		            addressResponse.setFullAddress(address.getFullAddress());
		            addressResponse.setCity(address.getCity());
		            addressResponse.setState(address.getState());
		            addressResponse.setPincode(address.getPincode());
		            addressResponse.setCountry(address.getCountry());
		            branchResponse.setBranchAddressResponse(addressResponse);
		        }

		        branchResponseList.add(branchResponse);
		    }
		return branchResponseList;
	}

}
