package com.codinglevel.school.school.security.repository;

import com.codinglevel.school.school.security.entities.ApplicationRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRoleRepository extends JpaRepository<ApplicationRole, Long> {

    ApplicationRole findByRole(String role);
}
