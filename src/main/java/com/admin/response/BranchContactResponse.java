package com.admin.response;

import java.io.Serializable;

public class BranchContactResponse implements Serializable{

	private static final long serialVersionUID = -1492288879266402702L;

	private Long id;
	
	private String managerName;

	private String managerEmail;

	private String managerPhone;

	private String branchContactNum;

	private String branchEmail;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getBranchContactNum() {
		return branchContactNum;
	}

	public void setBranchContactNum(String branchContactNum) {
		this.branchContactNum = branchContactNum;
	}

	public String getBranchEmail() {
		return branchEmail;
	}

	public void setBranchEmail(String branchEmail) {
		this.branchEmail = branchEmail;
	}
	
}
