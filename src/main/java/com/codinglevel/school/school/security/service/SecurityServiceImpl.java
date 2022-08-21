package com.codinglevel.school.school.security.service;

import com.codinglevel.school.school.security.entities.ApplicationRole;
import com.codinglevel.school.school.security.entities.ApplicationUser;
import com.codinglevel.school.school.security.repository.ApplicationRoleRepository;
import com.codinglevel.school.school.security.repository.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class SecurityServiceImpl implements SecurityService{

    private final ApplicationUserRepository applicationUserRepository;
    private final ApplicationRoleRepository applicationRoleRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public ApplicationUser saveNewUser(String username, String password, String confirmPassword) {
        if(!password.equals(confirmPassword)) throw new RuntimeException("Please confirm the password");
        String encodePassword = passwordEncoder.encode(password);
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername(username);
        applicationUser.setPassword(encodePassword);
        applicationUser.setActive(true);
        applicationUserRepository.save(applicationUser);
        return applicationUser;
    }

    @Override
    public ApplicationRole saveNewRole(String role, String description) {
        ApplicationRole applicationRole = applicationRoleRepository.findByRole(role);
        if(applicationRole != null) throw new RuntimeException("Role already existed");
        applicationRole = new ApplicationRole();
        applicationRole.setRole(role);
        applicationRole.setDescription(description);
        applicationRoleRepository.save(applicationRole);
        return applicationRole;
    }

    @Override
    public void addRoleToUser(String username, String role) {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if(applicationUser == null) throw new RuntimeException("User not found");
        ApplicationRole applicationRole = applicationRoleRepository.findByRole(role);
        if(applicationRole == null) throw new RuntimeException("Role not found");
        applicationUser.getRoles().add(applicationRole);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if(applicationUser == null) throw new RuntimeException("User not found");
        ApplicationRole applicationRole = applicationRoleRepository.findByRole(role);
        if(applicationRole == null) throw new RuntimeException("Role not found");
        applicationUser.getRoles().remove(applicationRole);
    }

    @Override
    public ApplicationUser loadUserByUsername(String username) {
        return applicationUserRepository.findByUsername(username);
    }
}
