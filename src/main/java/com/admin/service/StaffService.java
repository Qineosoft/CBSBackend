package com.admin.service;

import java.util.List;

import com.admin.request.StaffRequest;
import com.admin.response.StaffResponse;

public interface StaffService {

	public Boolean saveStaff(StaffRequest staffRequest);

	public Boolean updateStaff(StaffRequest staffRequest);

	public List<StaffResponse> getAllStaff();

	public StaffResponse getStaffById(Long id);

	public Boolean deleteById(Long id);

}
