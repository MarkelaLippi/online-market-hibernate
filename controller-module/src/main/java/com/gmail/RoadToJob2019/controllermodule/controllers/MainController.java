package com.gmail.RoadToJob2019.controllermodule.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String getStartingPage () {
        return "startingPage";
    }
}
