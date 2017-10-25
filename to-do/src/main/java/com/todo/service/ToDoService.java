/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.service;

import org.json.JSONObject;


/**
 * Definition of adapter proxies for yom update operation
 *
 * @author Lulu-Devs
 */
public interface ToDoService {

   

    public void saveToDo(net.sf.json.JSONObject jsonObject);

    public void getToDoList(net.sf.json.JSONObject payload);
    
   
}
