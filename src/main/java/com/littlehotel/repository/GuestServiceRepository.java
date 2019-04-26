package com.littlehotel.repository;

import com.littlehotel.model.GuestService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestServiceRepository extends JpaRepository<GuestService, Integer> {

    GuestService findByNameService(String name);

}
