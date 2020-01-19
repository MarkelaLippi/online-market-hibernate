package com.gmail.roadtojob2019.controllermodule.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/admin")
    public String getStartingPage() {
        return "startingPage";
    }
    @GetMapping("/1")
    public String get1() {
        return "1";
    }
    @GetMapping("/2")
    public String get2() {
        return "2";
    }
    @GetMapping("/3")
    public String get3() {
        return "3";
    }
    @GetMapping("/4")
    public String get4() {
        return "4";
    }
    @GetMapping("/403")
    public String get403() {
        return "403";
    }
}
