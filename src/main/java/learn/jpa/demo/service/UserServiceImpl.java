package learn.jpa.demo.service;

import learn.jpa.demo.dao.RoleRepository;
import learn.jpa.demo.dao.UserRepository;
import learn.jpa.demo.dto.UserDto;
import learn.jpa.demo.entity.Course;
import learn.jpa.demo.entity.Role;
import learn.jpa.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(UserDto userDto, String roleName) {
        // convert the input data transfer object to the actual database object
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        // default role: admin
        Role role = roleRepository.findByName(roleName);
        user.setRoles(Set.of(role));
        user.setActive(true);
        // persist the new user entity
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map(user->entity2dto(user)).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void enrollCourse(User user, Course course) {
        user.getEnrolledCourses().add(course);
        userRepository.save(user);
    }

    @Override
    public void removeCourse(User user, Course course) {
        user.getEnrolledCourses().remove(course);
        userRepository.save(user);
    }

    public static UserDto entity2dto(User user) {
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    // required by UserDetailsService
    // required by spring security
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
