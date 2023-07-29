package com.weblearnel.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.weblearnel.model.ConfirmationToken;
import com.weblearnel.model.User;
import com.weblearnel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    // add word
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

    // find user
    public User getUser(String username) {
        return userRepository.findByUsername(username).get();
    }

    // update word
    public User updateUser(User user, long id) {
        if (user != null) {
            User user1 = userRepository.getReferenceById(id);
            user1.setUsername(user.getUsername());
            user1.setAddress(user.getAddress());
            user1.setFullname(user.getFullname());
            user1.setPassword(user.getPassword());
            user1.setMobile(user.getMobile());
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
    public String signUpUser(User user) {
        boolean userExists = userRepository.findByUsername(user.getUsername()).isPresent();
        boolean emailExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalStateException("user already exists");
        }
        if (emailExists) {
            throw new IllegalStateException("email already exists");
        }
        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        // String link = "http://localhost:8080/tokens/confirm?token=" + token;
        // String to = emailService.buildEmail(user.getUsername(), link);
        // emailService.send(user.getEmail(), to);

        return token;
    }

    public void enableUser(String email) {
        userRepository.enableUser(email);
    }
}
