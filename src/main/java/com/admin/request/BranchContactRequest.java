package com.admin.request;

import java.io.Serializable;

public class BranchContactRequest implements Serializable{

	private static final long serialVersionUID = 4763107148993615348L;

	private String managerName;
	
    private String managerEmail;
	
    private String managerPhone;

    private String branchContactNum;
	
    private String branchEmail;

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

//    @OneToOne
//    @JoinColumn(name = "branch_id")
//    private Branch branch;
    
}
