package com.admin.service;

import java.util.List;

import com.admin.request.BranchRequest;
import com.admin.response.BranchResponse;

public interface BranchService {

	public Boolean saveBranchDetails(BranchRequest branchRequest);

	public List<BranchResponse> getAllBranch();

	public Boolean updateBranchDetails(BranchRequest branchRequest);

	public Boolean deleteBranch(Long id);

}
