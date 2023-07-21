package com.weblearnel.service;

import java.util.List;

import com.weblearnel.model.User;
import com.weblearnel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //add word
    public User addUser(User user) {
        if (user != null) {
            return userRepository.save(user);
        }
        return null;
    }

    // find all words
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //find word
    public User getOneUser(long id) {
        return userRepository.findById(id).get();
    }

    // update word
    public User updateUser(User user, long id) {
        if (user != null) {
            User user1 = userRepository.getReferenceById(id);
            user1.setUsername(user.getUsername());
            user1.setEmail(user.getEmail());
            user1.setAddress(user.getAddress());
            user1.setLevel(user.getLevel());
            user1.setFullname(user.getFullname());
            user1.setRole(user.getRole());
            user1.setLogs(user.getLogs());
            user1.setCreateDate(user.getCreateDate());
            user1.setPassword(user.getPassword());
            user1.setMobile(user.getMobile());
            user1.setStatus(user.getStatus());
            return userRepository.save(user1);
        }
        return null;
    }

    // Delete word
    public boolean deleteUser(long id) {
        if (id >= 1) {
            User user = userRepository.getReferenceById(id);
            userRepository.delete(user);
            return true;
        }
        return false;
    }
}
