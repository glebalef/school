package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {

    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long lastId = 0;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        faculties.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFaculty (Long id) {
        return faculties.get(id);
    }

    public Faculty editFaculty (Faculty faculty) {
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty (Long id) {
        return faculties.remove(id);
    }

    public ArrayList<Faculty> getFacultyByColor (String color) {
        ArrayList<Faculty> newList = new ArrayList<>();
        Faculty[] values = faculties.values().toArray(new Faculty[faculties.size()]);
        for (int i = 0; i < faculties.values().size(); i++) {
            if (values[i].getColor().equals(color)) {
                newList.add(values[i]);
            }
        }
        return newList;
    }
}
