package com.littlehotel.service;

import com.littlehotel.model.Room;

import java.util.List;

public interface RoomService {
    void saveRoom (Room room);
    Room getRoomByName (String name);
    List<Room> getAllRooms();
    Room getRoomByID(Integer id);
    void deleteRoom (Room room);
    List<Room> getRoomByCategory (String category);
}
