package com.admin.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    // ============================================================
    //            Send OTP Email (Simple Text)
    // ============================================================
    
    public void sendMail(String mail, String otp, String message) {

        String finalMessage =
                "Dear User,\n\n" +
                message + "\n\n" +
                "Your One-Time Password (OTP) is: " + otp + "\n\n" +
                "This OTP is valid for the next 10 minutes. " +
                "Please do not share it with anyone for security reasons.\n\n" +
                "Regards,\n" +
                "Admin Team";

        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo(mail);
        simpleMessage.setSubject("OTP Verification");
        simpleMessage.setText(finalMessage);

        javaMailSender.send(simpleMessage);
    }


    // ==========================================================================
    //                  Send Staff Credentials (HTML Email)
    // ==========================================================================
    
    public void sendUserIDPasswordToMail(String email, String staffId, String password) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);
            helper.setSubject("Your Staff Portal Login Credentials");

            String body = "<h2>Welcome to Our Portal</h2>"
                    + "<p>Your staff account has been successfully created.</p>"
                    + "<p><b>User ID:</b> " + staffId + "</p>"
                    + "<p><b>Temporary Password:</b> " + password + "</p>"
                    + "<p>Please use these credentials to log in to your dashboard.</p>"
                    + "<p><b>Note:</b> Change your password after first login for security.</p>"
                    + "<br>"
                    + "<p>Regards,<br>Admin Team</p>";

            helper.setText(body, true); // HTML enabled

            javaMailSender.send(message);

            System.out.println("User credentials mail sent successfully to: " + email);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error sending user credentials email: " + e.getMessage());
        }
    }
    
    // ==========================================================================
    //                         Send Expire Reminder
    // ==========================================================================
    
    public void sendExpiryReminder(String email, String userId, LocalDate expiryDate) {
        String subject = "Password Expiry Reminder";
        String body = "Hello,\n\nYour password will expire on: "
                + expiryDate
                + ". Please contact admin for a new password.\n\nRegards,\nAdmin Team";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }

    // ==========================================================================
    //                        Send Password Reset Mail
    // ==========================================================================

	public void sendPasswordResetMail(String email, String newPassword) {
		try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);
            helper.setSubject("Your New Password (Reset Successful)");

            String body = "<h3>Password Reset Successfully</h3>"
                    + "<p>Your password has been updated by the administrator.</p>"
                    + "<p><b>New Password:</b> " + newPassword + "</p>"
                    + "<p>Please log in using the new password.</p>"
                    + "<p><b>Note:</b> Your password will expire in 90 days.</p>"
                    + "<br>"
                    + "<p>Regards,<br>Admin Team</p>";

            helper.setText(body, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

}
