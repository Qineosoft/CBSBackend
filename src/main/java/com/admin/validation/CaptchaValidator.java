package com.admin.validation;

public class CaptchaValidator {

	public static boolean validateCaptcha(String captchaToken) {
        return captchaToken != null && captchaToken.equals("VALID_CAPTCHA");
    }
}
