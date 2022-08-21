package com.codinglevel.school.school.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class TeacherController {

    @GetMapping("/teacher-page")
    public String homePage() {

        return "teacher-page";
    }
}
