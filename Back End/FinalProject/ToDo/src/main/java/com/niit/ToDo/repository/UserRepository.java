package com.niit.ToDo.repository;


import com.niit.ToDo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findUserByEmailIdAndPassword(String emailId, String password);
}
