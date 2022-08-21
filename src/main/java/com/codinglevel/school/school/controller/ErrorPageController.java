package com.codinglevel.school.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController {

    @GetMapping("/errorPage")
    public String getTheErrorPage() {
        return "errorPage";
    }
}
