package com.me.mailing.example.dtos;

import com.me.mailing.example.models.Role;

public record RegisterRequest(
    String name,
    String email,
    String password,
    Role role
) {
}
