package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
         return studentRepository.save(student);
    }

    public Student findStudent (Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent (Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent (Long id) {
       studentRepository.deleteById(id);
    }
    public List<Student> getStudentByAge (int age) {
        return studentRepository.findByAge(age);
    }

    public List<Student> getStudentByAgeBetween (int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Faculty getFacultyByStudent (Long id) {
        return Objects.requireNonNull(studentRepository.findById(id).orElse(null)).getFaculty();
    }

}