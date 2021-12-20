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

//    @Override
//    public User registerUser(User user) throws UserAlreadyExistsException {
////        UserDTO userDTO = new UserDTO(user.getEmailId(), user.getUserName(), user.getPassword(), user.getPhoneNo(), null);
//        if(guestRepository.findById(user.getEmailId()).isPresent()){
//            throw new UserAlreadyExistsException();
//        }
//        else {
//            guestRepository.save(user);
//
//        }
//        return guestRepository.save(user);
//    }

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

//    @Override
//    public User deleteCategoryFromList(String emailId, int categoryId) throws UserNotFoundException, CategoryNotFoundException {
//        boolean categoryIdIsPresent = false;
//        if(guestRepository.findById(emailId).isEmpty())
//        {
//            throw new UserNotFoundException();
//        }
//        User user = guestRepository.findById(emailId).get();
//        List<Category> categories = user.getCategories();
//        categoryIdIsPresent = categories.removeIf(x->x.getCategoryId() == categoryId);
//        if(!categoryIdIsPresent)
//        {
//            throw new CategoryNotFoundException();
//        }
//        user.setCategories(categories);
//        return guestRepository.save(user);
//    }
//
//    @Override
//    public List<Category> getAllCategories(String emailId) throws UserNotFoundException {
//        if(guestRepository.findById(emailId).isEmpty())
//        {
//            throw new UserNotFoundException();
//        }
//        return guestRepository.findById(emailId).get().getCategories();
//    }
//
//    @Override
//    public User updateCategory(Category category, String emailId) throws UserNotFoundException {
//        int index = 0;
//        if (guestRepository.findById(emailId).isEmpty()) {
//            throw new UserNotFoundException();
//        }
//        User user = guestRepository.findByEmailId(emailId);
//        List<Category> categories = user.getCategories();
//        for(Category category1: categories){
//            if(category1.getCategoryId() == category.getCategoryId()){
//                index = categories.indexOf(category1);
//            }
//        }
//        categories.remove(index);
//        categories.add(index, category);
//        user.setCategories(categories);
////        return guestRepository.save(user);
//        return null;
//
//    }

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

//    @Override
//    public User deleteTodoFromList(int todoId, int categoryId, String emailId) throws UserNotFoundException {
//        boolean categoryIdIsPresent = false;
//        if(guestRepository.findById(emailId).isEmpty())
//        {
//            throw new UserNotFoundException();
//        }
//        User user = guestRepository.findById(emailId).get();
//        for (Category category: user.getCategories()) {
//                    if(category.getCategoryId() == categoryId) {
//                        category.getTodos().removeIf(x -> x.getTodoId() == todoId);
//                        return guestRepository.save(user);
//                    }
//                };
//        return null;
//    }
//
//    @Override
//    public User updateTodoToList(Todo todo, int categoryId, String emailId) throws UserNotFoundException {
//        if(guestRepository.findById(emailId).isEmpty())
//        {
//            throw new UserNotFoundException();
//        }
//        User user1 = new User();
//        User user = guestRepository.findByEmailId(emailId);
//        List<Category> categories = user.getCategories();
//        for (Category category: categories) {
//            if(category.getCategoryId() == categoryId){
//                        List<Todo> todos = category.getTodos();
//                        int index = 0;
//                        for (Todo todo1: todos) {
//                            if(todo1.getTodoId() == todo.getTodoId()){
//                                index = todos.indexOf(todo1);
//                            }
//                        }
//                        todos.removeIf(x-> todo.getTodoId() == x.getTodoId());
//                        todos.add(index, todo);
//                        category.setTodos(todos);
//                        user.setCategories(categories);
//                    }
//                    return guestRepository.save(user);
//            }
//        return null;
//    }
//
//    @Override
//    public List<Todo> getAllTodo( int categoryId, String emailId) throws UserNotFoundException {
//        List<Todo> todoList = new ArrayList<>();
//        if(guestRepository.findById(emailId).isEmpty())
//        {
//            throw new UserNotFoundException();
//        }
//        User user = guestRepository.findByEmailId(emailId);
//        for (Category category: user.getCategories()) {
//            if(category.getCategoryId() == categoryId) {
//                todoList = category.getTodos();
//            }
//        }
//
//        return todoList;
//    }

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

//    @Override
//    public Todo addGuest(Guest guest, int todoId, int categoryId, String emailId) throws UserNotFoundException {
//        Todo todo =  findTodoById(todoId, categoryId, emailId);
//        List<Guest> guests = new ArrayList<>();
//        if(todo.getGuests()!=null) {
//            guests = todo.getGuests();
//        }
//        System.out.println(guests.size());
//        guests.add(guest);
//        todo.setGuests(guests);
//        updateTodoToList(todo,categoryId,emailId);
//        return todo;
//    }
}
