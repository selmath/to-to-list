/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.service;

import com.todo.dao.MongoDao;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Service implementation that process the business logic required before
 * updating the information to data base
 *
 * @author Lulu-Devs
 */
@Service
public class ToDoServiceImpl implements ToDoService {

    private static final Logger log = LoggerFactory.getLogger(ToDoServiceImpl.class);

    private final Marker marker = MarkerFactory.getMarker("ToDoServiceImpl");

    @Autowired
    private MongoDao mongoDao;

    @Autowired
    private Environment env;  

    @Override
    public void saveToDo(net.sf.json.JSONObject jsonObject) {
         mongoDao.saveToDo(jsonObject);
    }

    @Override
    public void getToDoList(net.sf.json.JSONObject payload) {
        mongoDao.getToDoList(payload);
    }

}
