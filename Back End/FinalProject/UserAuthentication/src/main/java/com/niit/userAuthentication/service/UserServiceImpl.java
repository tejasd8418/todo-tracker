package com.niit.userAuthentication.service;

import com.niit.userAuthentication.exception.UserNotFoundException;
import com.niit.userAuthentication.model.User;
import com.niit.userAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUserNameAndPassword(String userName, String password) throws UserNotFoundException {
        User user = userRepository.findByUserNameAndPassword(userName,password);
        if(user==null){
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public User findByEmailIdAndPassword(String emailId, String password) throws UserNotFoundException {
        User user = userRepository.findByEmailIdAndPassword(emailId,password);
        if(user==null){
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
