package com.littlehotel.service;

import com.littlehotel.model.User;
import com.littlehotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User getUserDyID(Integer id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        User entity = userRepository.getOne(user.getId());
        if (entity != null) {
            entity.setName(user.getName());
            if (!user.getPassword().equals(entity.getPassword())) {
                entity.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            entity.setEmail(user.getEmail());
            entity.setUserProfiles(user.getUserProfiles());
            userRepository.save(entity);
        }
    }

    @Override
    public boolean isUserNameUnique(Integer id, String name) {
        User user = getUserByName(name);
        return (user == null || ((id != null) && (user.getId() == id)));
    }
}
