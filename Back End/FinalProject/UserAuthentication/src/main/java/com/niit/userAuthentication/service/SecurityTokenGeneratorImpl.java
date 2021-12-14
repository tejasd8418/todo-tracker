package com.niit.userAuthentication.service;


import com.niit.userAuthentication.model.User;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator {
    @Override
    public Map<String, String> generateToken(User user) {
        String jwtToken;
        jwtToken = Jwts.builder().setSubject(user.getUserName()).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"secretkey").compact();
        Map<String,String> map = new HashMap<>();
        map.put("jwtToken",jwtToken);
        map.put("message","login successfull");
        return map;
    }
}
