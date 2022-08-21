package com.codinglevel.school.school.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String fullName;
    @Email(message = "Please enter a valid email")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Name is required")
    private String major;
    @NotBlank(message = "Name is required")
    private String nationality;

}
