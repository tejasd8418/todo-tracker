package com.niit.ToDo.controller;

import com.niit.ToDo.exception.CategoryNotFoundException;
import com.niit.ToDo.exception.UserAlreadyExistsException;
import com.niit.ToDo.exception.UserNotFoundException;
import com.niit.ToDo.model.Category;
import com.niit.ToDo.model.Guest;
import com.niit.ToDo.model.Todo;
import com.niit.ToDo.model.User;
import com.niit.ToDo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class TodoController {

    private TodoService todoService;
    private ResponseEntity<?> responseEntity;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser1(@RequestBody User user) throws UserAlreadyExistsException {
        try {
            responseEntity =  new ResponseEntity<>(todoService.registerUser1(user), HttpStatus.CREATED);
        }
        catch(UserAlreadyExistsException e)
        {
            throw new UserAlreadyExistsException();
        }
        return responseEntity;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws UserNotFoundException {
        try {
            responseEntity =  new ResponseEntity<>(todoService.login(user.getEmailId(), user.getPassword()), HttpStatus.CREATED);
        }
        catch(UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }


    @PostMapping("/user/category/{emailid}")
    public ResponseEntity<?> saveCategoryToList(@RequestBody Category category, @PathVariable String emailid) throws UserNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(todoService.saveCategoryToListWithDefaultStatuses(category, emailid), HttpStatus.CREATED);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }


    @GetMapping("/user/categories/{emailid}")
    public ResponseEntity<?> getAllCategoriesFromList(@PathVariable String emailid) throws UserNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(todoService.getAllCategories(emailid), HttpStatus.OK);
        }catch(UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }


    @DeleteMapping("/user/{emailid}/{categoryid}")
    public ResponseEntity<?> deleteCategoryFromList(@PathVariable String emailid,@PathVariable int categoryid) throws UserNotFoundException, CategoryNotFoundException
    {
        try {
            responseEntity = new ResponseEntity<>(todoService.deleteCategoryFromList(emailid, categoryid), HttpStatus.OK);
        }
        catch ( CategoryNotFoundException m) {
            throw new CategoryNotFoundException();
        }
        catch (UserNotFoundException u){
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @PutMapping("user/updatecategory/{emailid}")
    public ResponseEntity<?> updateCategoryFromList(@PathVariable String emailid, @RequestBody Category category){
        try{
            responseEntity = new ResponseEntity<>(todoService.updateCategory(category, emailid), HttpStatus.OK);
        }
        catch (UserNotFoundException userNotFoundException) {
            userNotFoundException.printStackTrace();
        }
        return responseEntity;
    }

    @PostMapping("/user/todo/{emailid}/{categoryid}")
    public ResponseEntity<?> saveTodoToList(@RequestBody Todo todo, @PathVariable int categoryid, @PathVariable String emailid) throws UserNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(todoService.saveTodoToList(todo, categoryid, emailid ), HttpStatus.CREATED);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @GetMapping("/user/todos/{emailid}/{categoryid}")
    public ResponseEntity<?> getAllTodo(@PathVariable int categoryid, @PathVariable String emailid) throws UserNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(todoService.getAllTodo(categoryid, emailid), HttpStatus.OK);
        }catch(UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @DeleteMapping("/user/deletetodo/{emailid}/{categoryid}/{todoid}")
    public ResponseEntity<?> deleteTodoFromList(@PathVariable int todoid,@PathVariable int categoryid,@PathVariable String emailid) throws UserNotFoundException, CategoryNotFoundException
    {
        try {
            responseEntity = new ResponseEntity<>(todoService.deleteTodoFromList(todoid, categoryid, emailid), HttpStatus.OK);
        }
        catch (UserNotFoundException u){
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @PostMapping("/user/updatetodo/{emailid}/{categoryid}")
    public ResponseEntity<?> updateTodoToList(@RequestBody Todo todo,@PathVariable int categoryid, @PathVariable String emailid) throws UserNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(todoService.updateTodoToList(todo, categoryid, emailid ), HttpStatus.CREATED);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @PostMapping("/user/addguest/{emailid}/{categoryid}/{todoid}")
    public ResponseEntity<?> addGuest(@RequestBody Guest guest, @PathVariable int todoid, @PathVariable int categoryid, @PathVariable String emailid) throws UserNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(todoService.addGuest(guest, todoid, categoryid, emailid ), HttpStatus.CREATED);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }





}
