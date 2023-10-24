/*
package learn.jpa.demo.service;

import learn.jpa.demo.dao.StudentDao;
import learn.jpa.demo.dao.StudentDaoImpl;
import learn.jpa.demo.entity.Course;
import learn.jpa.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
    @Override
    public Student findById(int id) {
        return studentDao.findById(id);
    }

    @Override
    public Student findByUserName(String userName) {
        return studentDao.findByUserName(userName);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public Long getNumberOfStudents() {
        return studentDao.getNumberOfStudents();
    }

    @Override
    @Transactional
    public Student addStudent(Student theStudent) {
        return studentDao.addStudent(theStudent);
    }

    @Override
    @Transactional
    public Student saveStudent(Student theStudent) {
        return studentDao.saveStudent(theStudent);
    }

    @Override
    @Transactional
    public Student deleteStudent(int id) {
        return studentDao.deleteStudent(id);
    }

    @Override
    @Transactional
    public void deleteAllStudents() {
        studentDao.deleteAllStudents();
    }

    @Override
    @Transactional
    public void removeCourse(Student student, Course course) {
        Set<Course> courses = student.getEnrolledCourses();
        courses.remove(course);
        studentDao.saveStudent(student);
    }

    @Override
    @Transactional
    public void enrollCourse(Student student, Course course) {
        student.getEnrolledCourses().add(course);
        studentDao.saveStudent(student);
    }

}

 */
