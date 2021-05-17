package com.vidme.demo.controller;

import com.vidme.demo.model.Request.LoginRequest;
import com.vidme.demo.model.Request.PasswordRequest;
import com.vidme.demo.model.User;
import com.vidme.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {   private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //http://localhost:portnumber/auth/users/register
    @PostMapping("/register")
    public User createUser(@RequestBody User userObject) {
        System.out.println("calling createUser");
        return userService.createUser(userObject);
    }

    //http://localhost:9092/auth/users/login
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("calling loginUser");
        return userService.loginUser(loginRequest);
    }

    @PutMapping("/passwordreset")
    public ResponseEntity<?> passwordReset(@RequestBody PasswordRequest passwordRequest)
    {
        System.out.println("calling passwordReset");
        return userService.passwordReset(passwordRequest);
    }
}



