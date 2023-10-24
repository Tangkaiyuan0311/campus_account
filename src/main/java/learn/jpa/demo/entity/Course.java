package learn.jpa.demo.entity;

import jakarta.persistence.*;
import learn.jpa.demo.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor // required by JPA
@ToString
@Table(name = "course")
public class Course {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incremented, maintained by database
    @Column(name = "id")
    private int id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_credit")
    private String courseCredit;

    @Column(name = "college_name")
    private String collegeName;

    // foreign key reference to room
    // many to one mapping
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "capacity")
    private Integer capacity;

    @ManyToMany(mappedBy = "enrolledCourses")
    private Set<User> users = new HashSet<>();


    // define the required no-arg constructor

    public Course(String courseName, String courseCredit, String collegeName, Room room, int capacity) {
        this.courseName = courseName;
        this.courseCredit = courseCredit;
        this.collegeName = collegeName;
        this.room = room;
        this.capacity = capacity;
    }

    /*
    @PreRemove
    public void removeStudentAssociation() {
        for (Student student : students) {
            student.getEnrolledCourses().remove(this);
        }
    }

     */


    // getter and setter and toString automatically added by lombok
}


