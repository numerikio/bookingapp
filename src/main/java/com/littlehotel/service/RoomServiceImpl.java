package com.littlehotel.service;

import com.littlehotel.model.Room;
import com.littlehotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roomService")
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public Room getRoomByName(String name) {
        return roomRepository.findByNameOfRoom(name);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomByID(Integer id) {
        Room room = roomRepository.getOne(id);
        return room;
    }

    @Override
    public void deleteRoom(Room room) {
        roomRepository.delete(room);
    }

    @Override
    public List<Room> getRoomByCategory(String category) {
        return roomRepository.findByCategory(category);
    }


}
