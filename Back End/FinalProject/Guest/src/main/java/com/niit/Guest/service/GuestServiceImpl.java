package com.niit.Guest.service;


import com.niit.Guest.domain.Category;
import com.niit.Guest.domain.Guest;
import com.niit.Guest.domain.Todo;
import com.niit.Guest.domain.User;
import com.niit.Guest.exception.CategoryNotFoundException;
import com.niit.Guest.exception.UserAlreadyExistsException;
import com.niit.Guest.exception.UserNotFoundException;
import com.niit.Guest.repository.GuestRepository;


import com.niit.rabbitmq.domain.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {

    private GuestRepository guestRepository;

    

    @Autowired
    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }


    public int getCategoryIdByCategoryName(String emailId, String categoryName) throws UserNotFoundException {
        if(guestRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = guestRepository.findByEmailId(emailId);
        List<Category> categories = user.getCategories();
        if(categories!=null) {
            for (Category category : categories) {
                if (category.getCategoryName().equals(categoryName)) {
                    return category.getCategoryId();
                }
            }
        }
        return 0;
    }


    @Override
    public User saveCategoryToListWithDefaultStatuses(Category category, String emailId) throws UserNotFoundException {

        if(guestRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = guestRepository.findByEmailId(emailId);
        if(user.getCategories() == null)
        {
            category.setCategoryId(1);
            user.setCategories(Arrays.asList(category));
        }
        else {
            List<Category> categories= user.getCategories();
            int index = -1;
            int newId = 1;
            if(categories.size() == 0){
                index = -1;
                newId = 1;
            }
            else {
                index = categories.size() - 1;
                Category category1 = categories.get(index);
                newId = category1.getCategoryId() + 2;
            }

            category.setCategoryId(newId);
            categories.add(category);
            user.setCategories(categories);
        }
        return guestRepository.save(user);
    }



    @Override
    public User saveTodoToList(Todo todo, int categoryId, String emailId) throws UserNotFoundException {
        if(guestRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        System.out.println(todo + "1");
        User user = guestRepository.findByEmailId(emailId);
        for (Category category: user.getCategories()) {
            System.out.println(category.getCategoryName());
            System.out.println(category.getCategoryId());
            if(category.getCategoryId() == categoryId){
                        if(category.getTodos() == null){
                            todo.setTodoId(1);
                            category.setTodos(Arrays.asList(todo));
                            System.out.println(todo + "2");
                            return guestRepository.save(user);
                        }
                        else {
                            List<Todo> todos = category.getTodos();
                            int index = -1;
                            int newId = 1;
                            if(todos.size() == 0){
                                index = -1;
                                newId = 1;
                            }
                            else {
                                index = todos.size() - 1;
                                Todo todo1 = todos.get(index);
                                newId = todo1.getTodoId() + 1;
                            }
                            todo.setTodoId(newId);
                            todos.add(todo);
                            category.setTodos(todos);
                            System.out.println(todo + "3");
                            return guestRepository.save(user);
                        }

                    }
        }
        return null;
    }



    public Todo findTodoById(int todoId, int categoryId, String emailId) throws UserNotFoundException {
        List<Todo> todoList = new ArrayList<>();
        if(guestRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = guestRepository.findByEmailId(emailId);
        for (Category category: user.getCategories()) {
            if(category.getCategoryId() == categoryId) {
                todoList = category.getTodos();
                for (Todo todo : todoList) {
                    if (todo.getTodoId() == todoId) {
                        return todo;
                    }
                }
            }
        }
        return null;
    }

}
