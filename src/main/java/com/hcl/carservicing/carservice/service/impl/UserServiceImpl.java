package com.hcl.carservicing.carservice.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.carservicing.carservice.model.AppUser;
import com.hcl.carservicing.carservice.repository.AppUserRepository;
import com.hcl.carservicing.carservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final AppUserRepository userRepository;

    public UserServiceImpl(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public AppUser register(AppUser user) {
        // Check for existing userId
        Optional<AppUser> existing = userRepository.findByUsername(user.getUsername());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("UserId already exists: " + user.getUsername());
        }
        // Check for existing contact number
        Optional<AppUser> existingContactNumber = userRepository.findByContactNumber(user.getContactNumber());
        if (existingContactNumber.isPresent()) {
            throw new IllegalArgumentException("Contact Number already exists: " + user.getContactNumber());
        }
        // TODO: Encode password before saving
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public AppUser login(String userId, String password) {
        AppUser user = userRepository.findByUsername(userId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        // TODO: Compare encoded passwords
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        return user;
    }
}
