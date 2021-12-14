package com.niit.ToDo.service;


import com.niit.ToDo.config.archives.ProducerArchives;
import com.niit.ToDo.config.guest.ProducerGuest;
import com.niit.ToDo.exception.CategoryNotFoundException;
import com.niit.ToDo.exception.UserAlreadyExistsException;
import com.niit.ToDo.exception.UserNotFoundException;
import com.niit.ToDo.model.*;
import com.niit.ToDo.proxy.UserProxy;
import com.niit.ToDo.repository.TodoRepository;
import com.niit.ToDo.repository.UserRepository;
import com.niit.rabbitmq.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    @Autowired
    private UserProxy userProxy;

    @Autowired
    ProducerArchives producerArchives;

    @Autowired
    ProducerGuest producerGuest;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser1(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getEmailId()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        ResponseEntity responseEntity = userProxy.saveUser(user);
        System.out.println(responseEntity.getBody());
        return userRepository.save(user);
    }

    @Override
    public User login(String emailId, String password) throws UserNotFoundException {
        User user = userRepository.findUserByEmailIdAndPassword(emailId, password);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        UserDTO userDTO = new UserDTO(user.getEmailId(), user.getUserName(), user.getPassword(), user.getPhoneNo(), null);
        if(todoRepository.findById(user.getEmailId()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        else {
            todoRepository.save(user);
            producerArchives.sendMessageToRabbitMqArchives(userDTO);
            producerGuest.sendMessageToRabbitMqGuest(userDTO);

        }
        return todoRepository.save(user);
    }

    @Override
    public User saveCategoryToListWithDefaultStatuses(Category category, String emailId) throws UserNotFoundException {

        if(todoRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = todoRepository.findByEmailId(emailId);
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
        return todoRepository.save(user);
    }

    @Override
    public User deleteCategoryFromList(String emailId, int categoryId) throws UserNotFoundException, CategoryNotFoundException {
        boolean categoryIdIsPresent = false;
        if(todoRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = todoRepository.findById(emailId).get();
        List<Category> categories = user.getCategories();
        categoryIdIsPresent = categories.removeIf(x->x.getCategoryId() == categoryId);
        if(!categoryIdIsPresent)
        {
            throw new CategoryNotFoundException();
        }
        user.setCategories(categories);
        return todoRepository.save(user);
    }

    @Override
    public List<Category> getAllCategories(String emailId) throws UserNotFoundException {
        if(todoRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        return todoRepository.findById(emailId).get().getCategories();
    }

    @Override
    public User updateCategory(Category category, String emailId) throws UserNotFoundException {
        int index = 0;
        if (todoRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = todoRepository.findByEmailId(emailId);
        List<Category> categories = user.getCategories();
        for(Category category1: categories){
            if(category1.getCategoryId() == category.getCategoryId()){
                index = categories.indexOf(category1);
            }
        }
        categories.remove(index);
        categories.add(index, category);
        user.setCategories(categories);
//        return todoRepository.save(user);
        return null;

    }

    @Override
    public User saveTodoToList(Todo todo, int categoryId, String emailId) throws UserNotFoundException {
        if(todoRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        System.out.println(todo + "1");
        User user = todoRepository.findByEmailId(emailId);
        for (Category category: user.getCategories()) {
            System.out.println(category.getCategoryName());
            System.out.println(category.getCategoryId() + "catid");
            if(category.getCategoryId() == categoryId){
                        if(category.getTodos() == null){
                            todo.setTodoId(1);
                            category.setTodos(Arrays.asList(todo));
                            System.out.println(todo + "2");
                            return todoRepository.save(user);
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
                                System.out.println(todo1.getTodoContent());
                                System.out.println(todo1.getTodoId() + "todoId");
                                newId = todo1.getTodoId() + 1;
                            }
                            todo.setTodoId(newId);
                            todos.add(todo);
                            category.setTodos(todos);
                            System.out.println(todo + "3");
                            return todoRepository.save(user);
                        }

                    }
        }
        return null;
    }

    @Override
    public User deleteTodoFromList(int todoId, int categoryId, String emailId) throws UserNotFoundException {
        boolean categoryIdIsPresent = false;
        if(todoRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = todoRepository.findById(emailId).get();
        for (Category category: user.getCategories()) {
                    if(category.getCategoryId() == categoryId) {
                        category.getTodos().removeIf(x -> x.getTodoId() == todoId);
                        return todoRepository.save(user);
                    }
                };
        return null;
    }

    @Override
    public User updateTodoToList(Todo todo, int categoryId, String emailId) throws UserNotFoundException {
        System.out.println(todo.getTodoContent() );
        if(todoRepository.findById(emailId).isEmpty())
        {
            System.out.println("no");
            throw new UserNotFoundException();
        }
        User user1 = new User();
        User user = todoRepository.findByEmailId(emailId);
        System.out.println("noooo");
        List<Category> categories = user.getCategories();
        for (Category category: categories) {
            System.out.println("noooooo");
            System.out.println(category.getCategoryId() + "noo");
            System.out.println(categoryId + "yaa");
            if(category.getCategoryId() == categoryId){
                System.out.println("noooooooooooo");
                        List<Todo> todos = category.getTodos();
                        int index = 0;
                System.out.println(todo.getTodoContent()   + "2");
                        for (Todo todo1: todos) {
                            System.out.println(todo.getTodoContent() + "3" );
                            if(todo1.getTodoId() == todo.getTodoId()){
                                index = todos.indexOf(todo1);
                            }
                        }
                        todos.removeIf(x-> todo.getTodoId() == x.getTodoId());
                        todos.add(index, todo);
                        category.setTodos(todos);
                        user.setCategories(categories);
                return todoRepository.save(user);
                    }

            }
        return null;
    }

    @Override
    public List<Todo> getAllTodo( int categoryId, String emailId) throws UserNotFoundException {
        List<Todo> todoList = new ArrayList<>();
        if(todoRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = todoRepository.findByEmailId(emailId);
        for (Category category: user.getCategories()) {
            if(category.getCategoryId() == categoryId) {
                todoList = category.getTodos();
            }
        }

        return todoList;
    }

    public Todo findTodoById(int todoId, int categoryId, String emailId) throws UserNotFoundException {
        List<Todo> todoList = new ArrayList<>();
        if(todoRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = todoRepository.findByEmailId(emailId);
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

    @Override
    public Todo addGuest(Guest guest, int todoId, int categoryId, String emailId) throws UserNotFoundException {
        Todo todo =  findTodoById(todoId, categoryId, emailId);
        List<Guest> guests = new ArrayList<>();
        if(todo.getGuests()!=null) {
            guests = todo.getGuests();
        }
        System.out.println(guests.size());
        guests.add(guest);
        todo.setGuests(guests);
        updateTodoToList(todo,categoryId,emailId);
        return todo;
    }
}
