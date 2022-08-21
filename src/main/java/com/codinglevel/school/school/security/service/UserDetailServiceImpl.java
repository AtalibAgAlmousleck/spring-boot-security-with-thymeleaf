package com.codinglevel.school.school.security.service;

import com.codinglevel.school.school.security.entities.ApplicationUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final SecurityService securityService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = securityService.loadUserByUsername(username);

//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        applicationUser.getRoles().forEach(applicationRole -> {
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(applicationRole.getRole());
//            authorities.add(authority);
//        });

        Collection<GrantedAuthority> authorities1 = applicationUser.getRoles().stream()
                .map(applicationRole -> new SimpleGrantedAuthority(applicationRole.getRole()))
                .collect(Collectors.toList());

        User user = new User(
                applicationUser.getUsername(),
                applicationUser.getPassword(),
                authorities1
        );
        return user;
    }
}
