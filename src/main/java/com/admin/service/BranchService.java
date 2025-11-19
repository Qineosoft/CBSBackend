package com.admin.service;

import java.util.List;
import java.util.Map;

import com.admin.request.BranchRequest;
import com.admin.response.BranchResponse;

public interface BranchService {

	public Boolean saveBranchDetails(BranchRequest branchRequest);

	public List<BranchResponse> getAllBranch();

	public Boolean updateBranchDetails(BranchRequest branchRequest);

	public Boolean deleteBranch(Long id);

	public BranchResponse getBranchById(Long id);

	public Map<String, String> getAllBranchNameId();

}
