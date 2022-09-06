package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class StudentService {

    private final HashMap<Long, Student> students = new HashMap<>();
    private long lastId = 0;

    public Student createStudent(Student student) {
        student.setId(++lastId);
        students.put(lastId, student);
        return student;
    }

    public Student findStudent (Long id) {
        return students.get(id);
    }

    public Student editStudent (Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public Student deleteStudent (Long id) {
        return students.remove(id);
    }

    public ArrayList<Student> getStudentByAge (int age) {
        ArrayList<Student> newList = new ArrayList<>();
        Student[] values = students.values().toArray(new Student[students.size()]);
        for (int i = 0; i < students.values().size(); i++) {
            if (values[i].getAge() == age) {
                newList.add(values[i]);
            }
        }
        return newList;
    }
}