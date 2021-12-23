package com.niit.service;



import com.niit.domain.Category;
import com.niit.domain.Guest;
import com.niit.domain.Todo;
import com.niit.domain.User;
import com.niit.exception.CategoryNotFoundException;
import com.niit.exception.UserAlreadyExistsException;
import com.niit.exception.UserNotFoundException;

import java.util.List;

public interface ArchivesService {

    User registerUser(User user) throws UserAlreadyExistsException;
    User saveCategory(Category category, String emailId) throws UserNotFoundException;



    User saveTodo(Todo todo, int categoryId, String emailId) throws UserNotFoundException;
    User deleteTodo(int todoId, int categoryId, String emailId) throws UserNotFoundException;
    List<Todo> getAllTodo( int categoryId, String emailId) throws UserNotFoundException;


    }
