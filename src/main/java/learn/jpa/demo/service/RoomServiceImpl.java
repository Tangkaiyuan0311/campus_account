package learn.jpa.demo.service;

import learn.jpa.demo.dao.RoomRepository;
import learn.jpa.demo.entity.Course;
import learn.jpa.demo.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room findById(int id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isEmpty())
            return null;
        return room.get();
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }
}
