package com.littlehotel.service;

import com.littlehotel.model.Booking;
import com.littlehotel.model.User;
import com.littlehotel.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public void saveBooking(Booking booking) {
            bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getВookedByDateIn(LocalDate dateIn, LocalDate dateOut) {
        return bookingRepository.findByDateInBetween(dateIn,dateOut);
    }

    @Override
    public List<Booking> getВookedByDateOut(LocalDate dateIn, LocalDate dateOut) {
        return bookingRepository.findByDateOutBetween(dateIn,dateOut);
    }

    @Override
    public List<Booking> getВookedByDateInAndDateOut(LocalDate dateIn, LocalDate dateOut) {
        return bookingRepository.findByDateInAfterAndDateOutBefore(dateIn,dateOut);
    }

    @Override
    public List<Booking> getВookedByDateInAndDateOut2(LocalDate dateIn, LocalDate dateOut) {
        return bookingRepository.findByDateInBeforeAndDateOutAfter(dateIn,dateOut);
    }

    @Override
    public List<Booking> getBookedListByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    @Override
    public Booking getBookingByID(Integer id) {
        return bookingRepository.getOne(id);
    }
}
