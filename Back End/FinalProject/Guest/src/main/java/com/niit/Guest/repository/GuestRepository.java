package com.niit.Guest.repository;

import com.niit.Guest.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GuestRepository extends MongoRepository<User,String>  {
    User findByEmailId(String emailId);

}
