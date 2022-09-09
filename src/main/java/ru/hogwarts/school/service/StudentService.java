package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.ArrayList;

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
        return studentRepository.findById(id).get();
    }

    public Student editStudent (Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent (Long id) {
       studentRepository.deleteById(id);
    }
//    public ArrayList<Student> getStudentByAge (int age) {
//        ArrayList<StudentRepository> newList = new ArrayList<>();
//        for (int i = 0; i < studentRepository..values().size(); i++) {
//            if (values[i].getAge() == age) {
//                newList.add(values[i]);
//            }
//        }
//        return newList;
  //  }
}