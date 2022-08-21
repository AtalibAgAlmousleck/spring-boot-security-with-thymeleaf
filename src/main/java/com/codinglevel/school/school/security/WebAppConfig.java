package com.codinglevel.school.school.security;

import com.codinglevel.school.school.security.service.UserDetailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebAppConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;
    private final UserDetailServiceImpl userDetailServiceImpl;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.jdbcAuthentication()
//                    .dataSource(dataSource)
//                    .usersByUsernameQuery("select username as principal, password as credentials, active from users where username = ?")
//                    .authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username = ?")
//                    .rolePrefix("ROLE_")
//                    .passwordEncoder(passwordEncoder);
        auth.userDetailsService(userDetailServiceImpl);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("/home/**").permitAll();
        http.authorizeRequests().antMatchers("/delete/**", "/edit/**",
                "/create-student/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/home");
        http.exceptionHandling().accessDeniedPage("/errorPage");


    }
}
