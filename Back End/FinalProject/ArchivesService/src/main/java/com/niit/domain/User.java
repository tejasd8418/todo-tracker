package com.niit.domain;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Document
public class User {

    @Id
    private String emailId;
    private String userName;
    private String password;
    private String phoneNo;
    private Category category;
    private List<Category> categories;

    public User() {
    }

    public User(String emailId, String userName, String password, String phoneNo, Category category, List<Category> categories) {
        this.emailId = emailId;
        this.userName = userName;
        this.password = password;
        this.phoneNo = phoneNo;
        this.category = category;
        this.categories = categories;
    }

    public User(String emailId, String userName, String password, String phoneNo, List<Category> categories) {
        this.emailId = emailId;
        this.userName = userName;
        this.password = password;
        this.phoneNo = phoneNo;
        this.category = category;
        this.categories = categories;
    }



    public User(String emailId, String userName, String password, String phoneNo, Category category) {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
