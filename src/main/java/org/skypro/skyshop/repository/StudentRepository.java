package org.skypro.skyshop.repository;

import org.skypro.skyshop.example.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}