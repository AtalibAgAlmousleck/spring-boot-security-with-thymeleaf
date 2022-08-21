package com.codinglevel.school.school.service;

import com.codinglevel.school.school.model.Student;
import com.codinglevel.school.school.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> fetchStudents() {
        return studentRepository.findAll();
    }

//    public void updateStudent(Long studentId) {
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new IllegalStateException("Student id: " + studentId + " not found"));
//        studentRepository.save(student);
//    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Student id: " + id + " not found"));
        studentRepository.delete(student);
    }
}
