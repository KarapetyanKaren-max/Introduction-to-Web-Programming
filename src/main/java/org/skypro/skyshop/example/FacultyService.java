package org.skypro.skyshop.example;

import org.skypro.skyshop.repository.FacultyRepository;
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
}