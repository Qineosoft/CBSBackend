package com.admin.response;

import java.io.Serializable;
import java.util.Date;

public class BranchResponse implements Serializable{

	private static final long serialVersionUID = 7095141586921195805L;

	private String branchType;

	private String branchId;

	private String branchName;

	private String ifscCode;

	private String location;

	private String operatingHrs;

	private Date dateOpened;

	private String status;

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOperatingHrs() {
		return operatingHrs;
	}

	public void setOperatingHrs(String operatingHrs) {
		this.operatingHrs = operatingHrs;
	}

	public Date getDateOpened() {
		return dateOpened;
	}

	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
