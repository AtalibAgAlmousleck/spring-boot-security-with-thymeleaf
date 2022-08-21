package com.codinglevel.school.school.security.repository;

import com.codinglevel.school.school.security.entities.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    ApplicationUser findByUsername(String username);
}
