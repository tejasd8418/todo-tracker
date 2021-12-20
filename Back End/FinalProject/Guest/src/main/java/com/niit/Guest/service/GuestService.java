package com.niit.Guest.service;


import com.niit.Guest.domain.Category;
import com.niit.Guest.domain.Guest;
import com.niit.Guest.domain.Todo;
import com.niit.Guest.domain.User;
import com.niit.Guest.exception.CategoryNotFoundException;
import com.niit.Guest.exception.UserAlreadyExistsException;
import com.niit.Guest.exception.UserNotFoundException;

import java.util.List;

public interface GuestService {

//    User registerUser(User user) throws UserAlreadyExistsException;
    int getCategoryIdByCategoryName(String emailId, String categoryName) throws UserNotFoundException;

    User saveCategoryToListWithDefaultStatuses(Category category, String emailId) throws UserNotFoundException;
//    User deleteCategoryFromList(String emailId,int categoryId) throws UserNotFoundException, CategoryNotFoundException;
//    List<Category> getAllCategories(String emailId) throws UserNotFoundException;
//    User updateCategory(Category category, String emailId) throws UserNotFoundException;


    User saveTodoToList(Todo todo,  int categoryId, String emailId) throws UserNotFoundException;
//    User deleteTodoFromList(int todoId, int categoryId, String emailId) throws UserNotFoundException;
//    User updateTodoToList(Todo todo, int categoryId, String emailId) throws UserNotFoundException;
//    List<Todo> getAllTodo( int categoryId, String emailId) throws UserNotFoundException;
//
//    public Todo addGuest(Guest guest, int todoId, int categoryId, String emailId) throws UserNotFoundException;


    }
