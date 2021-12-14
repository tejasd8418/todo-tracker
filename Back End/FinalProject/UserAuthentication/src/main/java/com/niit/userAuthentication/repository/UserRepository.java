package com.niit.userAuthentication.repository;


import com.niit.userAuthentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{
    public User findByUserNameAndPassword(String userName,String password);
    public User findByEmailIdAndPassword(String emailId,String password);
}
