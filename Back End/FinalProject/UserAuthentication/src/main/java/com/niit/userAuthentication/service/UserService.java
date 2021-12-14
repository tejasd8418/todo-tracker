package com.niit.userAuthentication.service;

import com.niit.userAuthentication.exception.UserNotFoundException;
import com.niit.userAuthentication.model.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);
    public User findByUserNameAndPassword(String userName,String password) throws UserNotFoundException;
    public User findByEmailIdAndPassword(String emailId,String password) throws UserNotFoundException;
    public List<User> getAllUsers();
}
