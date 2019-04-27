package com.littlehotel.service;

import com.littlehotel.model.User;

import java.util.List;

public interface UserService {
    void saveUser (User user);
    User getUserByName (String name);
    User getUserDyID (Integer id);
    List<User> getAllUsers();
    void deleteUser (Integer id);
    void updateUser (User user);
    boolean isUserNameUnique(Integer id, String name);
}
