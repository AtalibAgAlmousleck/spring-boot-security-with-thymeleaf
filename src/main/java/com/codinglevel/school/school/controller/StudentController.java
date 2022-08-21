package com.codinglevel.school.school.controller;

import com.codinglevel.school.school.model.Student;
import com.codinglevel.school.school.repository.StudentRepository;
import com.codinglevel.school.school.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;

    @GetMapping("/studenthome")
    public String getStudent(Model model) {
        List<Student> student = studentService.fetchStudents();
        model.addAttribute("student", student);
        return "student-page";
    }

    @GetMapping("/create-student")
    public String showStudent(Model model, Student student) {
        model.addAttribute("student", student);
        return "add-student";
    }

    @PostMapping("/register")
    public String registerStudent(@Valid Student student,
                                  BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "add-student";
        }
        model.addAttribute("student", student);
        studentService.saveStudent(student);
        return "redirect:/studenthome";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Student student = studentRepository.findById(id)
                        .orElseThrow(() -> new IllegalStateException("Student with this id not found"));
        model.addAttribute("student", student);
        return "update-form";
    }

    @PostMapping("/student/{id}")
    public String updateStudent(@PathVariable("id") Long id, @Valid Student student,
                                BindingResult result, Model model) {
        if(result.hasErrors()) {
            student.setId(id);
            return "update-form";
        }
        student.setFullName(student.getFullName());
        student.setEmail(student.getEmail());
        student.setMajor(student.getMajor());
        student.setNationality(student.getNationality());
        studentService.saveStudent(student);
        return "redirect:/studenthome";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/studenthome";
    }


}
