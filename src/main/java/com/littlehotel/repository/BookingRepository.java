package com.littlehotel.repository;

import com.littlehotel.model.Booking;
import com.littlehotel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByDateInBetween(LocalDate dateIn, LocalDate dateOut);

    List<Booking> findByDateOutBetween(LocalDate dateIn, LocalDate dateOut);

    List<Booking> findByDateInAfterAndDateOutBefore(LocalDate dateIn, LocalDate dateOut);

    List<Booking> findByUser(User user);
}
