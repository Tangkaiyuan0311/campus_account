package learn.jpa.demo.service;

import learn.jpa.demo.dao.CourseRepository;
import learn.jpa.demo.dao.UserRepository;
import learn.jpa.demo.dto.UserDto;
import learn.jpa.demo.entity.Course;
// import learn.jpa.demo.entity.Student;
import learn.jpa.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements  CourseService{

    // no need for @Transactional

    private final CourseRepository courseRepository;
    // private final UserRepository userRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        // this.userRepository = userRepository;
    }
    @Override
    public long count() {
        return courseRepository.count();
    }

    @Override
    public void deleteById(int id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        courseRepository.deleteAll();
    }

    @Override
    public boolean existsById(int id) {
        return courseRepository.existsById(id);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    // If a value is present, returns the value
    // otherwise null
    @Override
    public Course findById(int id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty())
            return null;
        return course.get();
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> findByCourseName(String name) {
        return courseRepository.findByCourseName(name);
    }

    @Override
    public List<Course> findAllByOrderByCollegeNameAsc() {
        return courseRepository.findAllByOrderByCollegeNameAsc();
    }

    /*
    @Override
    public void removeStudent(Course course, Student student) {
        course.getStudents().remove(student);
        courseRepository.save(course);
    }

     */

    @Override
    public void removeStudent(Course course, User user) {
        course.getUsers().remove(user);
        courseRepository.save(course);
    }

    @Override
    public void addStudent(Course course, User user) {
        course.getUsers().add(user);
        courseRepository.save(course);
    }

    /*
    @Override
    public void addStudent(Course course, Student student) {
        course.getStudents().add(student);
        courseRepository.save(course);
    }

     */
}
