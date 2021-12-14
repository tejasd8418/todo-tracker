package com.niit.userAuthentication.controller;


import com.niit.userAuthentication.exception.UserNotFoundException;
import com.niit.userAuthentication.model.User;
import com.niit.userAuthentication.service.SecurityTokenGenerator;

import com.niit.userAuthentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.Map;

@RestController
@RequestMapping("/api/v2")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;


    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;

    private ResponseEntity responseEntity;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException {
        Map<String,String> map = null;
        try {
            User user1 = userService.findByEmailIdAndPassword(user.getEmailId(),user.getPassword());
            if (user1.getEmailId().equals(user.getEmailId())){
                map = securityTokenGenerator.generateToken(user);
            }
            responseEntity = new ResponseEntity(map,HttpStatus.OK);
        }
        catch (UserNotFoundException ex){
            throw new UserNotFoundException();
        }
        catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity("internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    public ResponseEntity<?> fallbackLogin(@RequestBody User user){
        String message= "login failed";
        return new ResponseEntity<>(message,HttpStatus.BAD_GATEWAY);
    }
}
