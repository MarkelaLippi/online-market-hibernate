package com.gmail.roadtojob2019.servicemodule.services;

public interface EmailService {
    void sendUserPassword(String userEmail, String mailSubject, String password);
}
