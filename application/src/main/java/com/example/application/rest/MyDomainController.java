package com.example.application.rest;

import com.example.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
class MyDomainController {
    private UserService userService;
    @Autowired
    public MyDomainController(UserService userService) {
        this.userService = userService;
    }
}
