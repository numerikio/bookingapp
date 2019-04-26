package com.littlehotel.service;

import com.littlehotel.model.GuestService;

import java.util.List;

public interface GuestServiceService {
    List<GuestService> getAll();
    void saveGuestService(GuestService guestService);
    GuestService findByName(String name);
}
