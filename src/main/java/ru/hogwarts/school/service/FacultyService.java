package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.ArrayList;

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

//    public ArrayList<Faculty> getFacultyByColor (String color) {
//        ArrayList<Faculty> newList = new ArrayList<>();
//        Faculty[] values = faculties.values().toArray(new Faculty[faculties.size()]);
//        for (int i = 0; i < faculties.values().size(); i++) {
//            if (values[i].getColor().equals(color)) {
//                newList.add(values[i]);
//            }
//        }
//        return newList;
//    }
}
