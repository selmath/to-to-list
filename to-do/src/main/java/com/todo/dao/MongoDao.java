/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.dao;

import java.util.Collection;

/**
 *
 * @author Vineetha
 */
public interface MongoDao{

   
    public void saveToDo(net.sf.json.JSONObject jsonObject);
    
    public Collection getToDoList(net.sf.json.JSONObject jsonObject);

    public void removeToDo(net.sf.json.JSONObject jsonObject);
    
}
