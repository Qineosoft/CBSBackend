package com.admin.captcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.constants.UrlConstants;
import com.admin.response.CaptchaResponse;
import com.admin.responseData.ResponseData;

@RestController
@RequestMapping(UrlConstants.captchaMainUrl)
public class CaptchaController {

	@Autowired
	public CaptchaService captchaService;
	
	// ==============================================
	//             Generate Captcha
	// ==============================================
	
    @GetMapping(UrlConstants.captchaGenerate)
    public ResponseData generateCaptcha() {
    	CaptchaResponse captchaResponse = captchaService.generateCaptcha();
    	
    	if(captchaResponse != null) {
        return new ResponseData(captchaResponse, "Captcha Generate Successfully", HttpStatus.CREATED);
    	}else {
    		return new ResponseData(captchaResponse, "Not Able To Generate Captcha", HttpStatus.NOT_FOUND);
    	}
    }

    // ======================================================
    //                   Validate Captcha 
    // ======================================================
    
    @PostMapping(UrlConstants.captchaValidate)
    public ResponseData validateCaptcha(@RequestParam String captchaId, @RequestParam String userInput) {
        Boolean result = captchaService.validateCaptcha(captchaId, userInput);
        
        if(result) {
        	return new ResponseData(result, "Captcha Verified Successfully", HttpStatus.OK);
        }else {
        	return new ResponseData(result, "Please Provide Correct Captcha", HttpStatus.NOT_FOUND);
        }
    }
}
