package learn.jpa.demo.service;

import learn.jpa.demo.entity.Course;
import learn.jpa.demo.entity.Room;

import java.util.List;

public interface RoomService {
    Room save(Room room);

    Room findById(int id);

    List<Room> findAll();
}
