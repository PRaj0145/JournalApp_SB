package com.rootZero.journalApp.service;

import com.rootZero.journalApp.entity.User;
import com.rootZero.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest
public class UserServiseTests {

    @Autowired
    private UserRepository userRepository;

   @Disabled
    @Test
   public void testfindByUserName(){

       User user = userRepository.findByUserName("Ram");
       assertTrue(!user.getJournalEntries().isEmpty());
   }




}
