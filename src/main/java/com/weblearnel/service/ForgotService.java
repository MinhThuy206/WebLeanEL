// package com.weblearnel.service;

// import java.time.Duration;
// import java.time.LocalDateTime;
// import java.util.Optional;
// import java.util.UUID;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.weblearnel.model.User;
// import com.weblearnel.repository.ForgotRepository;

// @Service
// public class ForgotService {
// private static final long EXPIRE_TOKEN = 30;

// @Autowired
// private ForgotRepository forgotRepository;

// public String forgotPass(String email) {
// Optional<User> userOptional =
// Optional.ofNullable(forgotRepository.findByEmail(email));

// if (!userOptional.isPresent()) {
// return "Invalid email id.";
// }

// User user = userOptional.get();
// user.setToken(generateToken());
// user.setTokenCreationDate(LocalDateTime.now());

// user = forgotRepository.save(user);
// return user.getToken();
// }

// public String resetPass(String token, String password) {
// Optional<User> userOptional =
// Optional.ofNullable(forgotRepository.findByToken(token));

// if (!userOptional.isPresent()) {
// return "Invalid token";
// }
// LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

// if (isTokenExpired(tokenCreationDate)) {
// return "Token expired.";

// }

// User user = userOptional.get();

// user.setPassword(password);
// user.setToken(null);
// user.setTokenCreationDate(null);

// forgotRepository.save(user);

// return "Your password successfully updated.";
// }

// private String generateToken() {
// StringBuilder token = new StringBuilder();

// return token.append(UUID.randomUUID().toString())
// .append(UUID.randomUUID().toString()).toString();
// }

// private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

// LocalDateTime now = LocalDateTime.now();
// Duration diff = Duration.between(tokenCreationDate, now);

// return diff.toMinutes() >= EXPIRE_TOKEN;
// }

// }