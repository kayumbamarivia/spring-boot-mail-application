package com.me.mailing.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.mailing.example.models.User;

public interface UserRepo  extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
