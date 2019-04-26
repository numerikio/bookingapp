package com.littlehotel.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate dateIn;

    private LocalDate dateOut;

    private Double totalPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "booking_guestService",
            joinColumns = @JoinColumn(name = "booking_ID", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "guestService_ID", referencedColumnName="id"))
    private List<GuestService> guestServiceList = new ArrayList<>();

    public Booking() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateIn() {
        return dateIn;
    }

    public void setDateIn(LocalDate dateIn) {
        this.dateIn = dateIn;
    }

    public LocalDate getDateOut() {
        return dateOut;
    }

    public void setDateOut(LocalDate dateOut) {
        this.dateOut = dateOut;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<GuestService> getGuestServiceList() {
        return guestServiceList;
    }

    public void setGuestServiceList(List<GuestService> guestServiceList) {
        this.guestServiceList = guestServiceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(dateIn, booking.dateIn) &&
                Objects.equals(dateOut, booking.dateOut) &&
                Objects.equals(room, booking.room) &&
                Objects.equals(user, booking.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateIn, dateOut, room, user);
    }
}
