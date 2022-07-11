package com.webapp.projectmanagement.controller;

import com.webapp.projectmanagement.model.User;
import com.webapp.projectmanagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")

public class UserController {
    @Autowired
    UserRepo userRepo;

    @GetMapping("/listUsers")
    public List<User> getUsers(){
        return userRepo.findAll();
    }

}
