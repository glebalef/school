package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public Student getStudentInfo (@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @PostMapping
    public Student createStudent (@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public Student editStudent(@RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("age/{age}")
    public Collection<Student> getByAge (@PathVariable int age) {
        return studentService.getStudentByAge(age);
    }

    @GetMapping("age/{minAge}/  {maxAge}")
    public Collection<Student> getByAgeBetween (@PathVariable int minAge, @PathVariable int maxAge) {
        return studentService.getStudentByAgeBetween(minAge, maxAge);
    }

    @GetMapping ("/faculty/{id}")
    public String getFacultyByStudent (@PathVariable Long id) {
        return studentService.getFacultyByStudent(id);
    }
}
