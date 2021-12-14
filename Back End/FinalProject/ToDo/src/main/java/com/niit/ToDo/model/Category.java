package com.niit.ToDo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


public class Category {

    private int categoryId;
    private String categoryName;
    //    private List<Status> statuses;
    private List<Todo> todos;
    private String emailId;

    public Category() {
    }

    public Category(int categoryId, String categoryName, List<Todo> todos, String emailId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.todos = todos;
        this.emailId = emailId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}