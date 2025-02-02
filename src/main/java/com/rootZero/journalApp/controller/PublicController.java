package com.rootZero.journalApp.controller;

import com.rootZero.journalApp.entity.User;
import com.rootZero.journalApp.service.UserDetailsServiceImpl;
import com.rootZero.journalApp.service.UserService;
import com.rootZero.journalApp.utilis.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/public")
public class PublicController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "ok";
    }


    @PostMapping("/signup")
    public void signup(@RequestBody User user){
        userService.saveNewUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            jwtUtil.generateToken(userDetails.getUsername())
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){

            log.error("Exception occured while creatAuthenticationToken "+ e);
            return new ResponseEntity<>("Incorrect UserName and Password", HttpStatus.BAD_REQUEST);
        }
    }
}