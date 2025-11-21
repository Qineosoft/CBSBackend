package com.admin.captcha;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.admin.response.CaptchaResponse;

@Service
public class CaptchaServiceImpl implements CaptchaService{

    private final Map<String, CaptchaStore> captchaCache = new ConcurrentHashMap<>();

    private static final SecureRandom random = new SecureRandom();
    private static final long EXPIRY_SECONDS = 180;

    // ==============================================================
    //                       Generate Captcha
    // ==============================================================
    
    @Override
    public CaptchaResponse generateCaptcha() {

        String captchaId = UUID.randomUUID().toString();
        String captchaText = generateRandomCaptchaText(6);
        Instant expiryTime = Instant.now().plusSeconds(EXPIRY_SECONDS);

        captchaCache.put(captchaId, new CaptchaStore(captchaText, expiryTime));

        return new CaptchaResponse(captchaId, captchaText, EXPIRY_SECONDS);
    }
    
    // ================================================================
    //                       Validate Captcha
    // ================================================================

    @Override
    public Boolean validateCaptcha(String captchaId, String userInput) {
        if (!captchaCache.containsKey(captchaId)) {
            return false;
        }

        CaptchaStore data = captchaCache.get(captchaId);
        if (Instant.now().isAfter(data.expiryTime())) {
            captchaCache.remove(captchaId);
            return false;
        }

        boolean valid = data.captchaText().equalsIgnoreCase(userInput);
        captchaCache.remove(captchaId);

        return valid;
    }

    // =========================================================
    //                Generate Random Captcha
    // =========================================================
    
    private String generateRandomCaptchaText(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    // =========================================================
    //                     Captcha Store
    // =========================================================
    
    private record CaptchaStore(String captchaText, Instant expiryTime) {}

}
