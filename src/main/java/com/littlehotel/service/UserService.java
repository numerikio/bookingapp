package com.littlehotel.service;

import com.littlehotel.model.User;

import java.util.List;

public interface UserService {
    void saveUser (User user);
    User getUserByName (String name);
    List<User> getAllUsers();
    void deleteUser (User user);
    void updateUser (User user);
    boolean isUserNameUnique(Integer id, String name);
}
