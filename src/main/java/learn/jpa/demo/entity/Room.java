package learn.jpa.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor // required by JPA
@ToString
@Table(name = "room")
public class Room {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int roomNumber;

    @Column(name = "capacity")
    private int capacity;

    @OneToMany(mappedBy = "room")
    private List<Course> courses = new ArrayList<>();

    public Room(int roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
    }
}
