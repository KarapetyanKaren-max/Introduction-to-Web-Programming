package org.skypro.skyshop.repository;

import org.skypro.skyshop.example.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}