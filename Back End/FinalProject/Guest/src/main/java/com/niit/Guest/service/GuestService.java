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

    int getCategoryIdByCategoryName(String emailId, String categoryName) throws UserNotFoundException;
    User saveCategoryToListWithDefaultStatuses(Category category, String emailId) throws UserNotFoundException;
    User saveTodoToList(Todo todo,  int categoryId, String emailId) throws UserNotFoundException;



    }
