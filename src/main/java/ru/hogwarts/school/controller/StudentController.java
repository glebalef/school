package ru.hogwarts.school.controller;

import liquibase.pro.packaged.S;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public Student getStudentInfo(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
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

    @GetMapping("/{age}/age")
    public Collection<Student> getByAge(@PathVariable int age) {
        return studentService.getStudentByAge(age);
    }

    @GetMapping("/age")
    public Collection<Student> getByAgeBetween(@RequestParam int minAge, @RequestParam int maxAge) {
        return studentService.getStudentByAgeBetween(minAge, maxAge);
    }

    @GetMapping ("/number_of_students")
    public Integer getNumberOfStudents() {
        return studentService.getStudentsQuantity();
    }

    @GetMapping ("/average_age")
    public Integer getAverageAge() {
        return studentService.getAverageAge();
    }

    @GetMapping ("/last5")
    public List<Student> getLastFive() {
        return studentService.getLastFive();
    }

    @GetMapping("/names")
    public Stream<String> getSortedNames() {return studentService.getSortedNames();}

    @GetMapping ("/average_student_age")
    public Double aveAgeByStream() {return studentService.getAverageAgeOfStudents();}


    @GetMapping ("/non_parallel_sum")
    public Integer getNonParallelHomeWork () {
        long time  = System.currentTimeMillis();
        Stream
                .iterate(1, a -> a +1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b );
        time = System.currentTimeMillis() - time;
        return (int) time;
    }

    @GetMapping ("/parallel_sum")
    public Integer getParallelHomeWork () {
        long time  = System.currentTimeMillis();
        Stream
                .iterate(1, a -> a +1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b );
        time = System.currentTimeMillis() - time;
        return (int) time;
    }

    @GetMapping ("/thread")
    public String getThreadPart1 () {
        return studentService.allStudentsThreads();}

    @GetMapping ("/synchronized")
    public String getSynchro () {
        return studentService.allStudentsThreadsSync();}
}