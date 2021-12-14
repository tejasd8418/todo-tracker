package com.niit.ToDo.repository;

import com.niit.ToDo.model.Category;
import com.niit.ToDo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface TodoRepository extends MongoRepository<User,String> {
    User findByEmailId(String emailId);
}
