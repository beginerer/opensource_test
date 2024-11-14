package com.example.opensource_blog.service;

import com.example.opensource_blog.domain.users.User;
import com.example.opensource_blog.domain.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(String userId, String username, String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        User user = User.of(userId, username, encodedPassword);
        return userRepository.save(user);
    }

    public Optional<User> findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
    // 이미 아이디가 있으면 -> false
    public boolean isAlreadySameId(String userId) {
        return userRepository.existsByUserId(userId);
    }

}
