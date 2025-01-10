package com.rootZero.journalApp.controller;


import com.rootZero.journalApp.api.response.WeatherResponse;
import com.rootZero.journalApp.entity.User;
import com.rootZero.journalApp.repository.UserRepository;
import com.rootZero.journalApp.service.UserService;

import com.rootZero.journalApp.service.WheatherService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
   private UserRepository userRepository;

    @Autowired
    private WheatherService weatherService;

   @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String userName = authentication.getName();

       User userInDB = userService.findByUserName(userName);
       userInDB.setUserName(user.getUserName());
          userInDB.setPassword(user.getPassword());
          userService.saveNewUser(userInDB);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse wheatherResponse = weatherService.getWheather("Mumbai");
        String greeting = "";
        if(wheatherResponse != null){
            greeting= "Wheather feels like" + wheatherResponse.getCurrent().getFeelslike();

        }
        return new ResponseEntity<>("hii" + authentication.getName() + greeting ,HttpStatus.OK);
    }

   }






