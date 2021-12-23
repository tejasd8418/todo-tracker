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

}
