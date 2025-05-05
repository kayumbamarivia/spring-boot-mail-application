package com.me.mailing.example.services;

import org.springframework.stereotype.Service;

import com.me.mailing.example.dtos.RegisterRequest;
import com.me.mailing.example.models.User;
import com.me.mailing.example.repositories.UserRepo;
@Service
public class UserService {

    private final UserRepo userRepository;
    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public String register(RegisterRequest req){
        User user = new User();
        user.setName(req.name());
        user.setEmail(req.email());
        user.setPassword(req.password());
        user.setRole(req.role());
        userRepository.save(user);
        return "User registered successfully with email: " + req.email();
    }
}
