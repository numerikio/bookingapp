package com.littlehotel.repository;

import com.littlehotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    Room findByNameOfRoom (String name);
    List<Room> findByCategory (String category);
}
