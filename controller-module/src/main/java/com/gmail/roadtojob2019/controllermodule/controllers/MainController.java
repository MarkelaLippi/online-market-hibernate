package com.gmail.roadtojob2019.controllermodule.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/administrator")
    public String getAdminPage() {
        return "administrator";
    }

    @GetMapping("/sale")
    public String getSalePage() {
        return "sale";
    }

    @GetMapping("/customer")
    public String getCustomerPage() {
        return "customer";
    }

    @GetMapping("/secure")
    public String getSecurePage() {
        return "secure";
    }

    @GetMapping("/error")
    public String getErrorPage() {
        return "error";
    }
}
