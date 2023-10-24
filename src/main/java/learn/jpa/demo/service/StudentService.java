/*
package learn.jpa.demo.service;

import learn.jpa.demo.entity.Course;
import learn.jpa.demo.entity.Student;

import java.util.List;

public interface StudentService {
    Student findById(int id);
    Student findByUserName(String userName);

    // retrieve all students
    List<Student> findAll();


    Long getNumberOfStudents();


    /**
     * Add a new unmanaged student entity into database.<br>
     * Param entity is tracked and synchronized within the transactional boundary.<br>
     * @return the tracked entity.
     */
    //Student addStudent(Student theStudent);

    /**
     * Update if id exist.<br>
     * Add new for new id.<br>
     * Entity context stay the same.<br>
     * @return the tracked entity
     */
    //Student saveStudent(Student theStudent);

    //Student deleteStudent(int id);
    //void deleteAllStudents();

    //void removeCourse(Student student, Course course);
    //void enrollCourse(Student student, Course course);
//}
