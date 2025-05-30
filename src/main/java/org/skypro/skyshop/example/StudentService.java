package org.skypro.skyshop.example;

import org.skypro.skyshop.repository.StudentRepository;
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

}