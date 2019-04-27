package com.littlehotel.service;

import com.littlehotel.model.Booking;
import com.littlehotel.model.Room;
import com.littlehotel.model.User;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    void saveBooking(Booking booking);
    List<Booking> getAll();
    List<Booking> getВookedByDateIn(LocalDate dateIn, LocalDate dateOut);
    List<Booking> getВookedByDateOut(LocalDate dateIn, LocalDate dateOut);
    List<Booking> getВookedByDateInAndDateOut(LocalDate dateIn, LocalDate dateOut);
    List<Booking> getВookedByDateInAndDateOut2(LocalDate dateIn, LocalDate dateOut);
    List<Booking> getBookedListByUser (User user);
    Booking getBookingByID(Integer id);
}
