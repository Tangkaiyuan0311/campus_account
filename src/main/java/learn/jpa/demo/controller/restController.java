/*
package learn.jpa.demo.controller;

import learn.jpa.demo.entity.Course;
import learn.jpa.demo.entity.Student;
import learn.jpa.demo.service.CourseService;
import learn.jpa.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class restController {
    private StudentService studentService;
    private CourseService courseService;

    // constructor injection
    @Autowired
    restController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("hello") // @RequestMapping(method = RequestMethod. GET)
    public String echo() {
        return "hello";
    }

    @GetMapping("students")
    public List<Student> getStudents() {
        return studentService.findAll(); // jackson: pojo to json
    }
    @GetMapping("courses")
    public List<Course> getCourses() {
        return courseService.findAll();
    }

    // Spring uses built-in type converters to convert the path variable string to the desired type of the method parameter
    // custom type conversion: implementing the Converter interface.
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        Student s = studentService.findById(studentId);
        if (s == null)
            throw new StudentNotFoundException("Invalid student id: " + studentId);
        return s ; // jackson: pojo to json
    }

    @GetMapping("/courses/course-id/{courseId}")
    public Course getCourse(@PathVariable int courseId) {
        Course c  = courseService.findById(courseId);
        if (c == null)
            throw new CourseNotFoundException("Invalid course id: " + courseId);
        return c ; // jackson: pojo to json
    }
    @GetMapping("/courses/course-name/{courseName}")
    public List<Course> getCourse(@PathVariable String courseName) {
        List<Course> courses = courseService.findByCourseName(courseName);
        return courses ; // jackson: pojo to json
    }

    // add a new student
    // id fields should be specified or not exist
    // jackson: json to pojo
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PostMapping("/courses")
    @PutMapping("/courses")
    public Course saveCourse(@RequestBody Course course) {
        return courseService.save(course);
    }

    // update existing student
    // id field should already exist
    // for new id: add another row with system assigned id
    @PutMapping("/students")
    public Student SaveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }


    @DeleteMapping("/students/{studentId}")
    public Student DeleteStudent(@PathVariable int studentId) {
        Student s = studentService.findById(studentId);
        if (s == null)
            throw new StudentNotFoundException("Invalid student id: " + studentId);
        return studentService.deleteStudent(studentId);
    }





}

 */
