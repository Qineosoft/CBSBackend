package com.admin.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.admin.entity.Staff;
import com.admin.repository.StaffRepository;
import com.admin.service.EmailSender;

@Component
public class PasswordExpiryScheduler {

	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private EmailSender emailSender;
	
	@Scheduled(cron = "0 0 0 * * *")
    public void sendPasswordExpiryReminder() {

        List<Staff> staffList = staffRepository.findAll();

        for (Staff staff : staffList) {
            LocalDate expiryDate = staff.getPasswordExpiryDate();

            if (expiryDate != null &&
                expiryDate.minusDays(10).equals(LocalDate.now())) {

                emailSender.sendExpiryReminder(
                        staff.getEmail(),
                        staff.getStaffId(),
                        expiryDate
                );
            }
        }
    }
}
