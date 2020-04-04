package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.servicemodule.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Override
    public void sendUserPassword(String userEmail, String mailSubject, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject(mailSubject);
        message.setText(password);
        emailSender.send(message);
    }
}
