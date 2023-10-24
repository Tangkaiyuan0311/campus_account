package learn.jpa.demo;

import learn.jpa.demo.dao.RoleRepository;
// import learn.jpa.demo.dao.StudentDao;
import learn.jpa.demo.dao.UserRepository;
import learn.jpa.demo.entity.*;
import learn.jpa.demo.service.CourseService;
import learn.jpa.demo.service.RoomService;
// import learn.jpa.demo.service.StudentService;
import learn.jpa.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Autowired
	@Bean //When JavaConfig encounters such a method, it will execute that method and register the return value as a bean within a BeanFactory
	// CommandLineRunner: Spring Boot will automatically call the run method of all beans implementing this interface after the application context has been loaded
	public CommandLineRunner commandLineRun(CourseService courseService, RoomService roomService, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		// runner is just the placeholder: String...
		return runner-> {
			boolean init = true;
			boolean clear = false;
			if (init) {
				data_init(courseService, roomService, userRepository, roleRepository, passwordEncoder);
			}
		};
	}



	private void data_init(CourseService courseService, RoomService roomService, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {

		// room init
		Room room_1 = new Room(1001, 45);
		Room room_2 = new Room(1002, 40);
		Room room_3 = new Room(1003, 50);
		roomService.save(room_1);
		roomService.save(room_2);
		roomService.save(room_3);

		// course init
		Course course_1 = new Course("Science", "4", "Engineering", room_1, 50);
		Course course_2 = new Course("History", "3", "Literature", room_2, 50);
		Course course_3 = new Course("Math", "2", "Science", room_3, 50);
		courseService.save(course_1);
		courseService.save(course_2);
		courseService.save(course_3);

		Role role_admin = new Role("ROLE_ADMIN");
		Role role_student = new Role("ROLE_STUDENT");

		User user1 = new User("steve kevin", "kt27kevin@gmail.com", passwordEncoder.encode("1234"), true, Set.of(role_student), Set.of(course_1, course_2));
		User user2 = new User("alice peach", "admin@gmail.com", passwordEncoder.encode("1234"), true, Set.of(role_admin), Set.of(course_2, course_3));

		userRepository.save(user1);
		userRepository.save(user2);
		roleRepository.save(role_student);
		roleRepository.save(role_admin);

		/*
		// student init
		Student student_1 = new Student("AA", "BB", "CC@gmail.com", Set.of(course_1, course_2), "kevin");
		Student student_2 = new Student("BB", "CC", "DD@gmail.com", Set.of(course_2, course_3), "alice");
		Student student_3 = new Student("CC", "DD", "EE@gmail.com", Set.of(course_1, course_3), "bob");
		Student student_4 = new Student("DD", "EE", "FF@gmail.com", Set.of(course_1, course_2, course_3), "anna");

		studentService.addStudent(student_1);
		studentService.addStudent(student_2);
		studentService.addStudent(student_3);
		studentService.addStudent(student_4);

		 */



	}

	/*
	// hard code a student and save in the database
	private void createStudents(StudentService studentService) {
		Student student_1 = new Student("AA", "BB", "CC@gmail.com");
		Student student_2 = new Student("BB", "CC", "DD@gmail.com");
		Student student_3 = new Student("CC", "DD", "EE@gmail.com");
		Student student_4 = new Student("DD", "EE", "FF@gmail.com");

		studentService.addStudent(student_1);
		System.out.println("Saving student: " + student_1 + "...");

		studentService.addStudent(student_2);
		System.out.println("Saving student: " + student_2 + "...");

		studentService.addStudent(student_3);
		System.out.println("Saving student: " + student_3 + "...");

		studentService.addStudent(student_4);
		System.out.println("Saving student: " + student_4 + "...");
	}
	private void createCoursesAndRoom(CourseService courseService, RoomService roomService) {

		Room room_1 = new Room(1001, 45);
		Room room_2 = new Room(1002, 40);
		Room room_3 = new Room(1003, 50);
		roomService.save(room_1);
		roomService.save(room_2);
		roomService.save(room_3);

		Course course_1 = new Course("Science", "4", "Engineering", room_1);
		Course course_2 = new Course("History", "3", "Literature", room_2);
		Course course_3 = new Course("Math", "2", "Science", room_3);

		courseService.save(course_1);
		System.out.println("Saving course: " + course_1 + "...");
		courseService.save(course_2);
		System.out.println("Saving course: " + course_2 + "...");
		courseService.save(course_3);
		System.out.println("Saving course: " + course_3 + "...");
	}

	 */

}
