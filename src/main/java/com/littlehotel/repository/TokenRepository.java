package com.littlehotel.repository;

import com.littlehotel.model.PersistentLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<PersistentLogin, String> {

    List<PersistentLogin> findByUsername(String username);
}
