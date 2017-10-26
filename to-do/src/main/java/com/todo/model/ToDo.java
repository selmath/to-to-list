/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author lulu-Devps
 */
@Document(collection = "todo")
public class ToDo {
    String id;
    String title;
    String description;
    String status;
    String remarks;
    User user;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public String getRemarks() {
        return remarks;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "ToDo{" + "id=" + id + ", title=" + title + ", description=" + description + ", status=" + status + ", remarks=" + remarks + ", user=" + user + '}';
    }      
    
    
}
