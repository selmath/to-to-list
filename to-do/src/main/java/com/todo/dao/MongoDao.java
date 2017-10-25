/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.dao;

import org.json.JSONObject;

/**
 *
 * @author Vineetha
 */
public interface MongoDao{

  
    public void updateAmlResponseTime(JSONObject jsonObject);

    public void updateAmlResponse(String responseString);
    
    public void saveToDo(net.sf.json.JSONObject jsonObject);
    
    public void getToDoList(net.sf.json.JSONObject jsonObject);
    
}
