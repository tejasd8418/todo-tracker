package com.niit.domain;

import java.time.LocalDate;
import java.util.List;

public class Todo {

    private int todoId;
    private String todoTitle;
    private String todoContent;
    private Image image;
    private LocalDate dueDate;
    private boolean isHighPriority;
    private boolean isCompleted;
    private boolean isGuest;
    private boolean isArchived;
    private LocalDate dateAdded;
    private List<Guest> guests;

    private int categoryId;
//    private int statusId;
    private String emailId;

    public Todo() {
    }

    public Todo(int todoId, String todoTitle, String todoContent, Image image, LocalDate dueDate, boolean isHighPriority, boolean isCompleted, boolean isGuest, boolean isArchived, LocalDate dateAdded, List<Guest> guests, int categoryId, String emailId) {
        this.todoId = todoId;
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.image = image;
        this.dueDate = dueDate;
        this.isHighPriority = isHighPriority;
        this.isCompleted = isCompleted;
        this.isGuest = isGuest;
        this.isArchived = isArchived;
        this.dateAdded = dateAdded;
        this.guests = guests;
        this.categoryId = categoryId;
        this.emailId = emailId;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoContent() {
        return todoContent;
    }

    public void setTodoContent(String todoContent) {
        this.todoContent = todoContent;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isHighPriority() {
        return isHighPriority;
    }

    public void setHighPriority(boolean highPriority) {
        isHighPriority = highPriority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isGuest() {
        return isGuest;
    }

    public void setGuest(boolean guest) {
        isGuest = guest;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
