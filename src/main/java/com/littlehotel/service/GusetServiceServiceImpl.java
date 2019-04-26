package com.littlehotel.service;

import com.littlehotel.model.GuestService;
import com.littlehotel.repository.GuestServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("guestServiceService")
public class GusetServiceServiceImpl implements GuestServiceService {

    @Autowired
    private GuestServiceRepository guestServiceRepository;

    @Override
    public List<GuestService> getAll() {
        return guestServiceRepository.findAll();
    }

    @Override
    public void saveGuestService(GuestService guestService) {
        guestServiceRepository.save(guestService);
    }

    @Override
    public GuestService findByName(String name) {
        return guestServiceRepository.findByNameService(name);
    }
}
