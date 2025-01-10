package com.rootZero.journalApp.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class UserSchedulersTest {


    @Autowired
    private UserScheduler userScheduler;

    public void testFetchUsersAndSendSaMail(){
        userScheduler.fetchUsersAndSendSaMail();
    }
}
