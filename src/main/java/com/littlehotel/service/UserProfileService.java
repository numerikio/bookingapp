package com.littlehotel.service;

import com.littlehotel.model.UserProfile;

import java.util.List;

public interface UserProfileService {

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();

	void saveUserProfile(UserProfile userProfile);
	
}
