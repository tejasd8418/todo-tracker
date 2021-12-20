package com.niit.Guest.controller;

import com.niit.Guest.domain.Category;
import com.niit.Guest.domain.Todo;
import com.niit.Guest.domain.User;
import com.niit.Guest.exception.CategoryNotFoundException;
import com.niit.Guest.exception.UserAlreadyExistsException;
import com.niit.Guest.exception.UserNotFoundException;
import com.niit.Guest.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v5")
@CrossOrigin("*")
public class GuestController {

    private GuestService guestService;
    private ResponseEntity<?> responseEntity;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
//        try {
//            responseEntity =  new ResponseEntity<>(guestService.registerUser(user), HttpStatus.CREATED);
//        }
//        catch(UserAlreadyExistsException e)
//        {
//            throw new UserAlreadyExistsException();
//        }
//        return responseEntity;
//    }

    @GetMapping("/getcategoryid/{emailid}/{categoryname}")
    public ResponseEntity<?> getCategoryId(@PathVariable String emailid, @PathVariable String categoryname) throws UserNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(guestService.getCategoryIdByCategoryName(emailid, categoryname), HttpStatus.CREATED);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }


    @PostMapping("/user/category/{emailid}")
    public ResponseEntity<?> saveCategoryToList(@RequestBody Category category, @PathVariable String emailid) throws UserNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(guestService.saveCategoryToListWithDefaultStatuses(category, emailid), HttpStatus.CREATED);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }


//    @GetMapping("/user/categories/{emailid}")
//    public ResponseEntity<?> getAllCategoriesFromList(@PathVariable String emailid) throws UserNotFoundException {
//        try{
//            responseEntity = new ResponseEntity<>(guestService.getAllCategories(emailid), HttpStatus.OK);
//        }catch(UserNotFoundException e)
//        {
//            throw new UserNotFoundException();
//        }
//        return responseEntity;
//    }


//    @DeleteMapping("/user/{emailid}/{categoryid}")
//    public ResponseEntity<?> deleteCategoryFromList(@PathVariable String emailid,@PathVariable int categoryid) throws UserNotFoundException, CategoryNotFoundException
//    {
//        try {
//            responseEntity = new ResponseEntity<>(guestService.deleteCategoryFromList(emailid, categoryid), HttpStatus.OK);
//        }
//        catch ( CategoryNotFoundException m) {
//            throw new CategoryNotFoundException();
//        }
//        catch (UserNotFoundException u){
//            throw new UserNotFoundException();
//        }
//        return responseEntity;
//    }

    @PostMapping("/user/todo/{emailid}/{categoryid}")
    public ResponseEntity<?> saveTodoToList(@RequestBody Todo todo, @PathVariable String emailid, @PathVariable int categoryid) throws UserNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(guestService.saveTodoToList(todo, categoryid, emailid ), HttpStatus.CREATED);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

//    @GetMapping("/user/todos/{emailid}/{categoryid}")
//    public ResponseEntity<?> getAllTodo(@PathVariable int categoryid, @PathVariable String emailid) throws UserNotFoundException {
//        try{
//            responseEntity = new ResponseEntity<>(guestService.getAllTodo(categoryid, emailid), HttpStatus.OK);
//        }catch(UserNotFoundException e)
//        {
//            throw new UserNotFoundException();
//        }
//        return responseEntity;
//    }

//    @DeleteMapping("/user/deletetodo/{emailid}/{categoryid}/{todoid}")
//    public ResponseEntity<?> deleteTodoFromList(@PathVariable int todoid,@PathVariable int categoryid,@PathVariable String emailid) throws UserNotFoundException, CategoryNotFoundException
//    {
//        try {
//            responseEntity = new ResponseEntity<>(guestService.deleteTodoFromList(todoid,categoryid, emailid), HttpStatus.OK);
//        }
//        catch (UserNotFoundException u){
//            throw new UserNotFoundException();
//        }
//        return responseEntity;
//    }

//    @PostMapping("/user/updatetodo/{emailid}/{categoryid}")
//    public ResponseEntity<?> updateTodoToList(@RequestBody Todo todo, @PathVariable String emailid, @PathVariable int categoryid) throws UserNotFoundException {
//        try {
//            responseEntity = new ResponseEntity<>(guestService.updateTodoToList(todo, categoryid, emailid ), HttpStatus.CREATED);
//        }
//        catch (UserNotFoundException e)
//        {
//            throw new UserNotFoundException();
//        }
//        return responseEntity;
//    }

}
