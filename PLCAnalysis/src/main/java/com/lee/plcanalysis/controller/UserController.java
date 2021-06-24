package com.lee.plcanalysis.controller;

import com.lee.plcanalysis.pojo.User;
import com.lee.plcanalysis.service.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody User user){
        try{
            user = userService.addUser(user);
            if(user != null) return new ResponseEntity<>(user, HttpStatus.OK);

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
