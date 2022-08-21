package com.codinglevel.school.school.fakedata;

import com.codinglevel.school.school.model.Student;
import com.codinglevel.school.school.repository.StudentRepository;
import com.codinglevel.school.school.security.service.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class FakeDao implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final SecurityService securityService;

    @Override
    public void run(String... args) throws Exception {
        saveUsers();
        loadedData();
    }

    private void saveUsers() {
        securityService.saveNewUser("Nelson", "password", "password");
        securityService.saveNewUser("Ata-lib", "password", "password");
        securityService.saveNewUser("Mohamed", "password", "password");

        securityService.saveNewRole("USER", "Have access to user role");
        securityService.saveNewRole("ADMIN", "Have access to admin role");

        securityService.addRoleToUser("Nelson", "USER");
        securityService.addRoleToUser("Ata-lib", "ADMIN");
    }

    private void loadedData() {
        if(studentRepository.count() == 0) {
            Student student1 = new Student(1L,"Mohamed", "mohamed@gmail.com", "Medicine", "Mali");
            Student student2 = new Student(2L, "Aziz", "aziz@gmail.com", "Marketing", "Niger");
            Student student3 = new Student(3L, "Mark", "mark@gmail.com", "Sociology", "Tunisia");
            Student student4 = new Student(4L, "Hamdi", "hamdi@gmail.com", "Science", "Algeria");

           studentRepository.saveAll(List.of(
                   student1, student2,
                   student3, student4
           ));
        }

    }
}
