package com.example.codewithmongodb.repository;

import com.example.codewithmongodb.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserCustomRepository {

    private final MongoTemplate mongoTemplate;

    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<User> findUsers(Pageable pageable) {
        Query query = new Query();
        long count = this.mongoTemplate.count(query, User.class);

        query.with(pageable);
        List<User> users = mongoTemplate.find(query, User.class);
        return PageableExecutionUtils.getPage(users, pageable, () -> count);
    }

    @Override
    public Page<User> findUsersByFirstNameLetter(String letter, Pageable pageable) {
        Query query = new Query();
        long count;

        if(!ObjectUtils.isEmpty(letter)) {
            query.addCriteria(Criteria.where("firstName").regex("^" + letter));
            count = this.mongoTemplate.count(query, User.class);
        } else {
            count = this.mongoTemplate.count(query, User.class);
        }

        query.with(pageable);
        List<User> users = mongoTemplate.find(query, User.class);
        return PageableExecutionUtils.getPage(users, pageable, () -> count);
    }
}
