package com.rootZero.journalApp.controller;

import com.rootZero.journalApp.cache.AppCache;
import com.rootZero.journalApp.entity.User;
import com.rootZero.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    private AppCache appCache;

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> all = userService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

   @PostMapping("/create-admin-user")
   public void createUser(@RequestBody User user){
        userService.saveAdmin(user);
    }


    @GetMapping("clear_app_cache")
    public void clearAppCache(){
        appCache.init();
    }
}
