package learn.jpa.demo.controller;

import jakarta.validation.Valid;
import learn.jpa.demo.dto.UserDto;
import learn.jpa.demo.entity.Course;
import learn.jpa.demo.entity.Room;
// import learn.jpa.demo.entity.Student;
import learn.jpa.demo.entity.User;
import learn.jpa.demo.security.Authority;
import learn.jpa.demo.security.CourseAuthority;
import learn.jpa.demo.service.CourseService;
import learn.jpa.demo.service.RoomService;
// import learn.jpa.demo.service.StudentService;
import learn.jpa.demo.service.UserService;
import learn.jpa.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

// mvc controller for course and room service
@Controller
@RequestMapping(value = "/mvc")
// @RestController assume @ResponseBody semantics by default.
// This means that it's designed to write directly to the response body,
// rather than returning a view name to be resolved by a view resolver, like Thymeleaf
public class mvcController {

    private CourseService courseService;
    private RoomService roomService;
    private UserService userService;
    // private StudentService studentService;

    @Autowired
    public mvcController(CourseService courseService, RoomService roomService, UserService userService) {
        this.courseService = courseService;
        this.roomService = roomService;
        this.userService = userService;
    }

    /*
    @GetMapping("/date")
    public String getTime(Model model) {
        model.addAttribute("theDate", new Date());
        return "current_date";
    }
     */

    @GetMapping("/")
    public String getHome(Authentication authentication, Model model) {
        if (isLogged(authentication)) {
            model.addAttribute("isLogged", true);
            User user = userService.findUserByEmail(authentication.getName());
            model.addAttribute("firstName", UserServiceImpl.entity2dto(user).getFirstName());
        }
        else
            model.addAttribute("isLogged", false);
        return "main-page/dist/index";
    }

    private boolean isLogged(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);
    }

    @GetMapping("/courses")
    public String getCourses(Model model, Authentication authentication) {
        model.addAttribute("theCourses", courseService.findAllByOrderByCollegeNameAsc());
        Set<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());
        Authority authority = new CourseAuthority(); // read/update/delete default to false
        authority.setRead(true);
        if (roles.contains("ROLE_ADMIN")) {
            authority.setUpdate(true);
            authority.setDelete(true);
        }
        model.addAttribute("authority", authority);
        // if student, add attributes of enrolled courses
        String userEmail = authentication.getName();
        User theStudent = userService.findUserByEmail(userEmail);
        HashSet<Integer> enrolledCoursesId = theStudent.getEnrolledCourses().stream().map(course -> course.getId()).collect(Collectors.toCollection(HashSet::new));
        model.addAttribute("enrolledCoursesId", enrolledCoursesId);
        return "course/course-list";
    }

    // return the form for user input prompt
    @GetMapping("/addCourseForm")
    public String showCourseAddForm(Model model) {
        // the exact created object will be populated by thymeleaf (setter) based on the post form
        model.addAttribute("theCourse", new Course());
        List<Integer> roomIds = roomService.findAll().stream().map(r->r.getRoomNumber()).collect(Collectors.toCollection(ArrayList::new));
        model.addAttribute("roomIds", roomIds);
        return "course/course-form"; // html rendering
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("theCourse") Course newCourse, @RequestParam("roomId")int roomId) {
        Room theRoom = roomService.findById(roomId);
        newCourse.setRoom(theRoom);
        theRoom.getCourses().add(newCourse);
        courseService.save(newCourse);
        roomService.save(theRoom);
        return "redirect:/mvc/courses"; // url redirect
    }

    @GetMapping("/updateCourseForm")
    public String getUpdateCourseForm (@RequestParam("courseId")int courseId, Model theModel) {
        theModel.addAttribute("theCourse", courseService.findById(courseId));
        List<Integer> roomIds = roomService.findAll().stream().map(r->r.getRoomNumber()).collect(Collectors.toCollection(ArrayList::new));
        theModel.addAttribute("roomIds", roomIds);
        return "course/course-form";
    }

    @GetMapping("/deleteCourse")
    public String deleteCourse(@RequestParam("courseId") int courseId) {
        Course theCourse = courseService.findById(courseId);
        for (User student : theCourse.getUsers())
            userService.removeCourse(student, theCourse);
        courseService.deleteById(courseId);
        return "redirect:/mvc/courses";
    }

    @GetMapping("/showLoginForm")
    public String showCustomLogin() {
        return "login";
    }

    @GetMapping("/showRoom")
    public String showRoomInfo(@RequestParam("roomId") int roomId, Model model) {
        Room theRoom = roomService.findById(roomId);
        model.addAttribute("theRoom", theRoom);
        return "room/room_info";
    }

    @GetMapping("/myStudentInfo")
    public String showStudentInfo(Authentication authentication, Model model) {
        String userEmail = authentication.getName();
        User theStudent = userService.findUserByEmail(userEmail);
        model.addAttribute("theStudent", UserServiceImpl.entity2dto(theStudent));
        model.addAttribute("enrolledCourses", theStudent.getEnrolledCourses());
        return "student/student_info";
    }

    @GetMapping("/removeCourse")
    public String removeCourse(Authentication authentication, @RequestParam("courseId") int courseId) {
        String userEmail = authentication.getName();
        User theStudent = userService.findUserByEmail(userEmail);
        Course theCourse = courseService.findById(courseId);
        userService.removeCourse(theStudent, theCourse);
        courseService.removeStudent(theCourse, theStudent);
        return "redirect:/mvc/myStudentInfo";
    }

    @GetMapping("/enrollCourse")
    public String enrollCourse(@RequestParam("courseId") int courseId, Authentication authentication) {
        String userEmail = authentication.getName();
        User theStudent = userService.findUserByEmail(userEmail);
        Course theCourse = courseService.findById(courseId);
        userService.enrollCourse(theStudent, theCourse);
        courseService.addStudent(theCourse, theStudent);
        return "redirect:/mvc/courses";
    }

    @GetMapping("/registerForm")
    public String getRegisterForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "registerForm";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("userDto") @Valid UserDto userDto, BindingResult result, @RequestParam("pw_repeat") String repeated_pw, Model model) {
        // password repetition
        if (!repeated_pw.equals(userDto.getPassword()))
            result.rejectValue("password", null, "passwords should match");
        // user exist
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }
        if(result.hasErrors()){
            model.addAttribute("userDto", userDto);
            return "registerForm";
        }

        // success
        userService.saveUser(userDto, "ROLE_STUDENT");
        return "redirect:/mvc/registerForm?success";
    }


}
