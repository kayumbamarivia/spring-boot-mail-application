package com.me.mailing.example.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.me.mailing.example.repositories.UserRepo;
@Service
public class MyUserDetails  implements UserDetailsService{
    private final UserRepo repo;
    public MyUserDetails(UserRepo repo) {
        this.repo = repo;
    }
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByEmail(username);
    }
}
