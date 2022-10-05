package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.Requests.AllStudents;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository  extends JpaRepository<Student,Long> {
    List<Student> findByAge(int age);
    List<Student> findByAgeBetween(int minAge, int maxAge);

    @Query (value = "select count(id) from student", nativeQuery = true)
    Integer getStudentsQuantity();

    @Query (value = "select avg(age) from student", nativeQuery = true)
    Integer getAverageAge();

    @Query (value = "select * from student order by id desc limit 5", nativeQuery = true)
    List<Student> getLastFive();
    }






