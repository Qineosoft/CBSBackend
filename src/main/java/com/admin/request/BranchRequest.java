package com.admin.request;

import java.io.Serializable;
import java.util.Date;

public class BranchRequest implements Serializable{

	private static final long serialVersionUID = -3454220409938835795L;

	private String branchType;
	
	private String branchId;

	private String branchName;
	
	private String ifscCode;
	
	private String location;
	
	private String operatingHrs;
	
	private Date dateOpened;
	
	private String status;
	
	private BranchContactRequest branchContact;
	
	private BranchAddressRequest branchAddress;

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

	public BranchContactRequest getBranchContact() {
		return branchContact;
	}

	public void setBranchContact(BranchContactRequest branchContact) {
		this.branchContact = branchContact;
	}

	public BranchAddressRequest getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(BranchAddressRequest branchAddress) {
		this.branchAddress = branchAddress;
	}
	
}
