package com.niit.service;



import com.niit.domain.Category;
import com.niit.domain.Guest;
import com.niit.domain.Todo;
import com.niit.domain.User;
import com.niit.exception.CategoryNotFoundException;
import com.niit.exception.UserAlreadyExistsException;
import com.niit.exception.UserNotFoundException;
import com.niit.rabbitmq.domain.UserDTO;
import com.niit.repository.ArchivesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ArchivesServiceImpl implements ArchivesService {

    private ArchivesRepository archivesRepository;


    @Autowired
    public ArchivesServiceImpl(ArchivesRepository archivesRepository) {
        this.archivesRepository = archivesRepository;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        UserDTO userDTO = new UserDTO(user.getEmailId(), user.getUserName(), user.getPassword(), user.getPhoneNo(), null);
        if(archivesRepository.findById(user.getEmailId()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        else {
            archivesRepository.save(user);

        }
        return archivesRepository.save(user);
    }

    @Override
    public User saveCategory(Category category, String emailId) throws UserNotFoundException {

        if(archivesRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = archivesRepository.findByEmailId(emailId);
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
        return archivesRepository.save(user);
    }


    @Override
    public User saveTodo(Todo todo, int categoryId, String emailId) throws UserNotFoundException {
        if(archivesRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        System.out.println(todo + "1");
        User user = archivesRepository.findByEmailId(emailId);
        for (Category category: user.getCategories()) {
            System.out.println(category.getCategoryName());
            System.out.println(category.getCategoryId());
            if(category.getCategoryId() == categoryId){
                        if(category.getTodos() == null){
                            todo.setTodoId(1);
                            category.setTodos(Arrays.asList(todo));
                            System.out.println(todo + "2");
                            return archivesRepository.save(user);
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
                            return archivesRepository.save(user);
                        }

                    }
        }
        return null;
    }

    @Override
    public User deleteTodo(int todoId, int categoryId, String emailId) throws UserNotFoundException {
        boolean categoryIdIsPresent = false;
        if(archivesRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = archivesRepository.findById(emailId).get();
        for (Category category: user.getCategories()) {
                    if(category.getCategoryId() == categoryId) {
                        category.getTodos().removeIf(x -> x.getTodoId() == todoId);
                        return archivesRepository.save(user);
                    }
                };
        return null;
    }


    @Override
    public List<Todo> getAllTodo( int categoryId, String emailId) throws UserNotFoundException {
        List<Todo> todoList = new ArrayList<>();
        if(archivesRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = archivesRepository.findByEmailId(emailId);
        for (Category category: user.getCategories()) {
            if(category.getCategoryId() == categoryId) {
                todoList = category.getTodos();
            }
        }

        return todoList;
    }

    public Todo findTodoById(int todoId, int categoryId, String emailId) throws UserNotFoundException {
        List<Todo> todoList = new ArrayList<>();
        if(archivesRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = archivesRepository.findByEmailId(emailId);
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
