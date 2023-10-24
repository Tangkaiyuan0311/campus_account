package learn.jpa.demo.service;

import learn.jpa.demo.dto.UserDto;
import learn.jpa.demo.entity.Course;
import learn.jpa.demo.entity.User;

// import learn.jpa.demo.entity.Student;

import java.util.List;

public interface CourseService {

    // number of rows
    long count();

    void deleteById(int id);
    void deleteAll();
    boolean existsById(int id);
    List<Course> findAll();

    /**
     * If a value is present, returns the value.<br>
     * Otherwise null.
     */
    Course findById(int id);
    /**
     * Save entity with empty id it will do a save.<br>
     * Save entity with existing id it will do an update.
     */
    Course save(Course course);

    List<Course> findByCourseName(String name);
    List<Course> findAllByOrderByCollegeNameAsc();

    void removeStudent(Course course, User user);

    void addStudent(Course course, User user);


}
