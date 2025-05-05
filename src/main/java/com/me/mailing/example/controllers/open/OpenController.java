package com.me.mailing.example.controllers.open;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.mailing.example.models.User;

import jakarta.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("public")
public class OpenController {

    @GetMapping("/open")
    public String open() {
        return "Open endpoint accessed";
    }

    List<User> users = new ArrayList<>();
      
    @GetMapping("/users")
    public List<User> getUsers() {
        return users;
    }
    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        users.add(user);
        return "User added";
    }

    @GetMapping("/get-csrf")
    public CsrfToken getCrsf(HttpServletRequest req){;
        return (CsrfToken) req.getAttribute("_csrf");
    }
}
