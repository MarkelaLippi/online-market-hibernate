package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.servicemodule.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendUserPassword(String userEmail, String mailSubject, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject(mailSubject);
        message.setText(password);
        emailSender.send(message);
    }
}
