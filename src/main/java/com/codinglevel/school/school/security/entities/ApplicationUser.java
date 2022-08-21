package com.codinglevel.school.school.security.entities;

import com.codinglevel.school.school.security.entities.ApplicationRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean active;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<ApplicationRole> roles = new ArrayList<>();
}
