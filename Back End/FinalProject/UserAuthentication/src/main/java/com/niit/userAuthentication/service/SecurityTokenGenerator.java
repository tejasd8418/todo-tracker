package com.niit.userAuthentication.service;



import com.niit.userAuthentication.model.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(User user);

}
