package com.rootZero.journalApp.repository;

import com.rootZero.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSA(){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is("Piraj"));
        query.addCriteria((Criteria.where("email").regex("^[a-zA-Z0-9_.±]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$")));
        query.addCriteria(Criteria.where("sentimentAnalysis").is("true"));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
