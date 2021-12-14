package com.niit.ToDo.proxy;

import com.niit.ToDo.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="user-authentication", url="localhost:8085")
public interface UserProxy {

    @PostMapping("/api/v2/register")
    public ResponseEntity<?> saveUser(@RequestBody User user);
}
