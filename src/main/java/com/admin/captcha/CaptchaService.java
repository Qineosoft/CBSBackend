package com.admin.captcha;

import com.admin.response.CaptchaResponse;

public interface CaptchaService {

	public CaptchaResponse generateCaptcha();

	public Boolean validateCaptcha(String captchaId, String userInput);

}
