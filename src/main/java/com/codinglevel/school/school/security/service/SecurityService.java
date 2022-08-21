package com.codinglevel.school.school.security.service;

import com.codinglevel.school.school.security.entities.ApplicationRole;
import com.codinglevel.school.school.security.entities.ApplicationUser;

public interface SecurityService{

    ApplicationUser saveNewUser(String username, String password, String confirmPassword);
    ApplicationRole saveNewRole(String role, String description);
    void addRoleToUser(String username, String role);
    void removeRoleFromUser(String username, String role);
    ApplicationUser loadUserByUsername(String username);
}
