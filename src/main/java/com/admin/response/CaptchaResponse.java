package com.admin.response;

import java.io.Serializable;

public class CaptchaResponse implements Serializable{

	private static final long serialVersionUID = -6766634704986382558L;

	private String captchaId;
	
    private String captchaText;
    
    private long expiresInSeconds;

	public String getCaptchaId() {
		return captchaId;
	}

	public void setCaptchaId(String captchaId) {
		this.captchaId = captchaId;
	}

	public String getCaptchaText() {
		return captchaText;
	}

	public void setCaptchaText(String captchaText) {
		this.captchaText = captchaText;
	}

	public long getExpiresInSeconds() {
		return expiresInSeconds;
	}

	public void setExpiresInSeconds(long expiresInSeconds) {
		this.expiresInSeconds = expiresInSeconds;
	}

	public CaptchaResponse(String captchaId, String captchaText, long expiresInSeconds) {
		super();
		this.captchaId = captchaId;
		this.captchaText = captchaText;
		this.expiresInSeconds = expiresInSeconds;
	}
	
}
