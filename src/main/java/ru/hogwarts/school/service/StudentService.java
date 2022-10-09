package ru.hogwarts.school.service;

import liquibase.sdk.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class StudentService {

    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student createStudent(Student student) {
        logger.info("Student{} is created", student);
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        logger.info("Method find Student is started for id {}", id);
        if (studentRepository.findById(id).isEmpty()) {
            logger.error("Student with id {} is not found", id);
            throw new RuntimeException();
        } else {
            logger.info("Method find Student is successfully executed for id {}", id);
            return studentRepository.findById(id).orElse(null);
        }
    }

    public Student editStudent(Student student) {
        logger.info("Method editStudent started for {}", student);
        if (studentRepository.findById(student.getId()).isEmpty()) {
            logger.error("No Student {} in database", student);
            throw new RuntimeException();
        } else
            studentRepository.save(student);
        logger.info("New student data is {}", student);
        return student;
    }

    public void deleteStudent(Long id) {
        logger.info("deleteStudent started for id {}", id);
        if (studentRepository.findById(id).isEmpty()) {
            logger.error("Student with id {} is not found", id);
            throw new RuntimeException();
        } else {
            logger.info("student with id {} was deleted", id);
            studentRepository.deleteById(id);
        }
    }

    public List<Student> getStudentByAge(int age) {
        logger.info("getStudent method is executed for age {}", age);
        if (studentRepository.findByAge(age).isEmpty()) {
            logger.error("No students with age {}", age);
            throw new RuntimeException();
        }
        return studentRepository.findByAge(age);
    }

    public Faculty getFacultyByStudent(Long id) {
        logger.info("getFacultyByStudent is executed");
        if (studentRepository.findById(id).isEmpty()) {
            logger.info("No student with id {}", id);
            throw new RuntimeException();
        } else {
            logger.info("getFacultyByStudent is executed for id {}", id);
            return Objects.requireNonNull(studentRepository.findById(id).orElse(null)).getFaculty();
        }
    }

    public List<Student> getStudentByAgeBetween(int minAge, int maxAge) {
        logger.info("showed students with age between {} and {}", minAge, maxAge);
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Integer getStudentsQuantity() {
        logger.info("getStudentsQuantity is executed");
        return studentRepository.getStudentsQuantity();
    }

    public Integer getAverageAge() {
        logger.info("getAverageAge is executed");
        return studentRepository.getAverageAge();
    }

    public List<Student> getLastFive() {
        logger.info("getLastFive is executed");
        if (studentRepository.getStudentsQuantity() < 5)
            logger.debug("quantity of students is less than 5");
        return studentRepository.getLastFive();
    }

    public Stream<String> getSortedNames() {
        Stream<Student> studentStream = studentRepository.findAll().stream();
        return studentStream
                .map(Student::getName)
                .sorted()
                .filter(e -> e.startsWith("A"));
    }

    public Double getAverageAgeOfStudents() {
        Stream<Student> studentStream = studentRepository.findAll().stream();
        return studentStream
                .mapToInt(Student::getAge)
                .average()
                .getAsDouble();
    }

    final Object flag = new Object();

    public String allStudentsThreads() {
        List<Student> students = studentRepository.findAll();

        System.out.println(students.get(0));
        System.out.println(students.get(1));

        new Thread(() -> {
            System.out.println(students.get(2));
            System.out.println(students.get(3));
        }).start();

        new Thread(() -> {
            System.out.println(students.get(4));
            System.out.println(students.get(5));
        }).start();
        return "Threads method has been evoked";
    }

    public  synchronized String allStudentsThreadsSync() {
        List<Student> students = studentRepository.findAll();

        System.out.println(students.get(0));
        System.out.println(students.get(1));

            new Thread(() -> {
                System.out.println(students.get(2));
                System.out.println(students.get(3));
            }).start();

            new Thread(() -> {
                System.out.println(students.get(4));
                System.out.println(students.get(5));
            }).start();
            return "Synchronized method has been evoked";
        }
    }



