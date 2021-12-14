package com.niit.repository;

import com.niit.domain.Todo;
import com.niit.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchivesRepository extends MongoRepository<User,String> {
    User findByEmailId(String emailId);
}
