package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.*;
import java.util.stream.Stream;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Faculty {} is created",faculty);
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty (Long id) {
        logger.info("findFaculty is started");
        if (facultyRepository.findById(id).isEmpty()) {
            logger.error("No faculty with id {} is found", id);
            throw new RuntimeException();
        } else {
        }  logger.info("faculty with id {} is found", id);
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty editFaculty (Faculty faculty) {
        logger.info("editFaculty is executed");
        if (facultyRepository.findById(faculty.getId()).isEmpty()) {
            logger.error("faculty is not found");
            throw new RuntimeException();
        }
        logger.info("faculty profile has been changed");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty (Long id) {
        logger.info("deleteFaculty is executed");
        if (facultyRepository.findById(id).isEmpty())
            logger.error("faculty with id {} is not found", id);
        else
            logger.info("faculty with id {} is deleted", id);
        facultyRepository.deleteById(id);
    }

    public List<Faculty> getFacultyByColor (String color) {
        logger.info("getFacultyByColor is executed");
        return facultyRepository.findByColor(color);
    }

    public List<Faculty> getFacultyByColorOrName (String colorOrName) {
        logger.info("getFacultyByColorOrName is executed");
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(colorOrName, colorOrName);
    }
public Collection<Student> getStudentsByFaculty (Long id) {
    logger.info("getStudentsByFaculty");
        return Objects.requireNonNull(facultyRepository.findById(id).orElse(null)). getStudent();
    }

    public String getLongestName () {
         return facultyRepository
                 .findAll()
                 .stream()
                 .map (p -> p.getName())
                 .max(Comparator.comparingInt(String::length))
                 .get();
    }
}
