package com.littlehotel.repository;

import com.littlehotel.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    UserProfile findByType(String type);
}
