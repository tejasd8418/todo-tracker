package com.niit.controller;

import com.niit.domain.Category;
import com.niit.domain.Todo;
import com.niit.domain.User;
import com.niit.exception.CategoryNotFoundException;


import com.niit.exception.TodoAlreadyExistException;
import com.niit.exception.UserAlreadyExistsException;
import com.niit.exception.UserNotFoundException;
import com.niit.service.ArchivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v4/archivesservice")
@CrossOrigin("*")
public class ArchivesController {
    @Autowired
    private ArchivesService archivesService;
    private ResponseEntity<?> responseEntity;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
        try {
            responseEntity =  new ResponseEntity<>(archivesService.registerUser(user), HttpStatus.CREATED);
        }
        catch(UserAlreadyExistsException e)
        {
            throw new UserAlreadyExistsException();
        }
        return responseEntity;
    }

    @PostMapping("/archives/category/{emailid}")
    public ResponseEntity<?> saveCategoryToList(@RequestBody Category category, @PathVariable String emailid) throws UserNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(archivesService.saveCategory(category, emailid), HttpStatus.CREATED);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }


    @PostMapping("/archives/todo/{emailid}/{categoryid}")
    public ResponseEntity<?> saveTodoToList(@RequestBody Todo todo, @PathVariable String emailid, @PathVariable int categoryid) throws UserNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(archivesService.saveTodo(todo, categoryid, emailid ), HttpStatus.CREATED);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @GetMapping("/category/todos/{emailid}/{categoryid}")
    public ResponseEntity<?> getAllTodo(@PathVariable int categoryid, @PathVariable String emailid) throws UserNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(archivesService.getAllTodo(categoryid, emailid), HttpStatus.OK);
        }catch(UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @DeleteMapping("/user/deletetodo/{emailid}/{categoryid}/{todoid}")
    public ResponseEntity<?> deleteTodoFromList(@PathVariable int todoid,@PathVariable int categoryid,  @PathVariable String emailid) throws UserNotFoundException, CategoryNotFoundException
    {
        try {
            responseEntity = new ResponseEntity<>(archivesService.deleteTodo(todoid, categoryid, emailid), HttpStatus.OK);
        }
        catch (UserNotFoundException u){
            throw new UserNotFoundException();
        }
        return responseEntity;
    }



}
