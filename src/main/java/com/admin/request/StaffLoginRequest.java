package com.admin.request;

import java.io.Serializable;

public class StaffLoginRequest implements Serializable{

	private static final long serialVersionUID = 6068731007075614371L;

	private String staffId;
	
    private String password;
    
    private String captchaToken;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaptchaToken() {
		return captchaToken;
	}

	public void setCaptchaToken(String captchaToken) {
		this.captchaToken = captchaToken;
	}
}
