package com.littlehotel.service;

import com.littlehotel.model.UserProfile;
import com.littlehotel.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userProfileService")
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository repository;

    public UserProfile findByType(String type) {
        return repository.findByType(type);
    }

    public List<UserProfile> findAll() {
        return repository.findAll();
    }

    public void saveUserProfile(UserProfile userProfile) {
        repository.save(userProfile);
    }
}
