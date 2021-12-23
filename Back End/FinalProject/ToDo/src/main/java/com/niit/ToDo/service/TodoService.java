package com.niit.ToDo.service;

import com.niit.ToDo.exception.CategoryNotFoundException;
import com.niit.ToDo.exception.UserAlreadyExistsException;
import com.niit.ToDo.exception.UserNotFoundException;
import com.niit.ToDo.model.*;

import java.util.List;

public interface TodoService {

    User registerUser(User user) throws UserAlreadyExistsException;
    User login(String emailId, String password) throws UserNotFoundException;
    User registerUser1(User user) throws UserAlreadyExistsException;
    User saveCategoryToListWithDefaultStatuses(Category category, String emailId) throws UserNotFoundException;
    User deleteCategoryFromList(String emailId,int categoryId) throws UserNotFoundException, CategoryNotFoundException;
    List<Category> getAllCategories(String emailId) throws UserNotFoundException;
    User updateCategory(Category category, String emailId) throws UserNotFoundException;


    User saveTodoToList(Todo todo,  int categoryId, String emailId) throws UserNotFoundException;
    User deleteTodoFromList(int todoId, int categoryId, String emailId) throws UserNotFoundException;
    User updateTodoToList(Todo todo, int categoryId, String emailId) throws UserNotFoundException;
    List<Todo> getAllTodo( int categoryId, String emailId) throws UserNotFoundException;

    public Todo addGuest(Guest guest, int todoId, int categoryId, String emailId) throws UserNotFoundException;



    }
