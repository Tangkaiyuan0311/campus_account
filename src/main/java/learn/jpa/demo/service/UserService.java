package learn.jpa.demo.service;

import learn.jpa.demo.dto.UserDto;
import learn.jpa.demo.entity.Course;
import learn.jpa.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void saveUser(UserDto userDto, String roleName);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();

    void enrollCourse(User user, Course course);

    void removeCourse(User user, Course course);
}
