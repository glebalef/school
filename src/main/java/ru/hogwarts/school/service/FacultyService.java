package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty (Long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty (Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty (Long id) {
        facultyRepository.deleteById(id);
    }

    public List<Faculty> getFacultyByColor (String color) {
        return facultyRepository.findByColor(color);
    }

    public Faculty getFacultyByColorOrName (String color, String name) {
        return facultyRepository.findByColorOrNameIgnoreCase(color, name);
    }
public Collection<Student> getStudentsByFaculty (Long id) {
        return facultyRepository.findById(id).get().getStudent();
    }
}
