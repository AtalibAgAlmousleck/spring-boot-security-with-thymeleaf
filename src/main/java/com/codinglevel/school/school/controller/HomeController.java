package com.codinglevel.school.school.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@AllArgsConstructor
public class HomeController {

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
}
