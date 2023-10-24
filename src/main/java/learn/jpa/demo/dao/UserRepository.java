package learn.jpa.demo.dao;

import learn.jpa.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // inherit some interfaces

    // impl will use Spring Data JPA's method name query creation feature
    User findByEmail(String email);
}
