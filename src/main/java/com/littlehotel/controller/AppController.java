package com.littlehotel.controller;

import com.littlehotel.LocalDateHandler;
import com.littlehotel.UserHandler;
import com.littlehotel.model.*;
import com.littlehotel.service.BookingService;
import com.littlehotel.service.GuestServiceService;
import com.littlehotel.service.RoomService;
import com.littlehotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Transactional
public class AppController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private GuestServiceService guestService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserHandler userHandler;

    @Autowired
    private LocalDateHandler localDateHandler;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {

        Set<String> categories = new HashSet<>();
        for (Room room : roomService.getAllRooms()
        ) {
            categories.add(room.getCategory());
        }
        categories.add("All");

        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("loggedinuser", userHandler.getPrincipal());
        model.addAttribute("categories", categories);
        return "mainPage";
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    public String findRoom(@RequestParam("categor") String category,
                           @RequestParam("date") String date,
                           ModelMap model) {

        Set<String> categories = new HashSet<>();
        for (Room room : roomService.getAllRooms()
        ) {
            categories.add(room.getCategory());
        }
        categories.add("All");

        model.addAttribute("dateString", date);
        model.addAttribute("days", localDateHandler.getDaysOfPeriodByString(date));
        model.addAttribute("loggedinuser", userHandler.getPrincipal());
        model.addAttribute("rooms", getRoomSet(getBookedRoomByPeriod(date), getFindRooms(category)));
        model.addAttribute("categories", categories);
        return "mainPage";
    }

    @RequestMapping(value = "bookingPage", method = RequestMethod.GET)
    public String bookingPage(ModelMap model) {

        List<Booking> bookings = bookingService.getBookedListByUser(userService.getUserByName(userHandler.getPrincipal()));
        getTotalBookingCost(bookings);
        model.addAttribute("bookedList", bookings);
        model.addAttribute("loggedinuser", userHandler.getPrincipal());
        return "bookingPage";
    }

    @RequestMapping(value = "servPage", method = RequestMethod.GET)
    public String servPage(@RequestParam("booked") String bookedID,
                           ModelMap model) {

        Booking booking = bookingService.getBookingByID(Integer.valueOf(bookedID));

        model.addAttribute("booked", bookedID);
        model.addAttribute("guestServices", guestService.getAll());
        model.addAttribute("days", localDateHandler.getDaysOfPeriod(booking.getDateIn(), booking.getDateOut()) + 1);
        return "addServicePage";
    }

    @RequestMapping(value = "addServ", method = RequestMethod.GET)
    public String addServ(@RequestParam(value = "param") String[] params,
                          @RequestParam(value = "booked") String bookedID,
                          ModelMap model) {
        List<GuestService> services = new ArrayList<>();
        if (params != null) {
            for (String s : params
            ) {
                services.add(guestService.findByName(s));
            }
        }
        Booking booking = bookingService.getBookingByID(Integer.valueOf(bookedID));
        booking.setGuestServiceList(services);
        bookingService.saveBooking(booking);

        return "redirect:bookingPage";
    }

    private List<Room> getFindRooms(String s) {
        if (s.equals("All")) {
            return roomService.getAllRooms();
        } else {
            return roomService.getRoomByCategory(s);
        }
    }


    @RequestMapping(value = "bookingRoom", method = RequestMethod.GET)
    public String bookingRoom(@RequestParam("date") String date,
                              @RequestParam("roomId") String roomId,
                              ModelMap model) {

        List<LocalDate> localDates = localDateHandler.getFirstAndLastDay(date);


        Booking booking = new Booking();
        Room room = roomService.getRoomByID(Integer.valueOf(roomId));
        booking.setUser(userService.getUserByName(userHandler.getPrincipal()));
        booking.setRoom(room);
        booking.setDateIn(localDates.get(0));
        booking.setDateOut(localDates.get(1));
        bookingService.saveBooking(booking);
        return "redirect:/";
    }

    private void getTotalBookingCost(List<Booking> bookings) {
        for (Booking booking : bookings
        ) {
            booking.setTotalPrice(getCostServ(booking) + getTotalByCostRoom(booking.getRoom(), localDateHandler.getDaysOfPeriod(booking.getDateIn(), booking.getDateOut())));
            bookingService.saveBooking(booking);
        }

    }

    private Double getCostServ(Booking booking) {
        Double total = 0.0;
        for (GuestService guestService : booking.getGuestServiceList()
        ) {
            total += getTotal(guestService, localDateHandler.getDaysOfPeriod(booking.getDateIn(), booking.getDateOut()));
        }
        return total;
    }

    private Double getTotal(GuestService guestService, Long days) {
        if (guestService.getRegularity().equals("all")) {
            return guestService.getCost() * (days + 1);
        } else return guestService.getCost();
    }


    private Set<Room> getBookedRoomByPeriod(String dates) {
        List<LocalDate> localDates = localDateHandler.getFirstAndLastDay(dates);
        LocalDate dateIn = localDates.get(0);
        LocalDate dateOut = localDates.get(1);
        Set<Booking> bookings = new HashSet<>();
        bookings.addAll(bookingService.getВookedByDateIn(dateIn, dateOut));
        bookings.addAll(bookingService.getВookedByDateOut(dateIn, dateOut));
        bookings.addAll(bookingService.getВookedByDateInAndDateOut(dateIn, dateOut));
        Set<Room> rooms = new HashSet<>();
        for (Booking booking : bookings
        ) {
            rooms.add(booking.getRoom());
        }
        return rooms;
    }

    private Set<Room> getRoomSet(Set<Room> rooms, List<Room> roomList) {
        Set<Room> set = new HashSet<>();
        for (Room room : roomList
        ) {
            if (!rooms.contains(room)) {
                set.add(room);
            }
        }
        return set.stream().sorted((o1, o2) -> o1.compareTo(o2)).collect(Collectors.toSet());
    }

    private Double getTotalByCostRoom(Room room, Long days) {
        if (days == 0) {
            return room.getCost();
        } else return days * room.getCost();
    }

}

