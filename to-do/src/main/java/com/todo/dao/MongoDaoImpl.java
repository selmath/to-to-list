/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.dao;

import com.mongodb.MongoClient;
import java.util.List;
import net.sf.json.JSONArray;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vineetha
 */
@Repository
public class MongoDaoImpl implements MongoDao {

    private static final Logger log = LoggerFactory.getLogger(MongoDaoImpl.class);

    private MongoOperations amlStressLogMongoOperation;
    private MongoOperations amlMongoOperation;
    private MongoOperations mongoOperation;

    @Autowired
    @Qualifier("mongoClient")
    public void init(MongoClient mongoClient) {

        this.amlStressLogMongoOperation = new MongoTemplate(new SimpleMongoDbFactory(mongoClient, "aml-secondary"));
        this.amlMongoOperation = new MongoTemplate(new SimpleMongoDbFactory(mongoClient, "aml-secondary"));
        this.mongoOperation = new MongoTemplate(new SimpleMongoDbFactory(mongoClient, "to-do"));
    }

    @Override
    public void updateAmlResponse(String responseString) {
        try {

            amlStressLogMongoOperation.insert(responseString, "responselog");

        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new RuntimeException("Error updating information to database", e);
        } 
    }

    @Override
    public void updateAmlResponseTime(JSONObject jsonObject) {
        try {

            amlMongoOperation.insert(jsonObject, "aml_log_time");

        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new RuntimeException("Error updating information to database", e);
        } 
    }

    @Override
    public void saveToDo(net.sf.json.JSONObject jsonObject) {
        try {
            mongoOperation.save(jsonObject,"todo");
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new RuntimeException("Error updating information to database", e);
        }        
        
    }    

    @Override
    public void getToDoList(net.sf.json.JSONObject jsonObject) {
         try {
            List todoList = mongoOperation.find(
                    new Query(Criteria.where("status").is(jsonObject.getString("status"))), List.class);
             System.out.println("com.todo.dao.MongoDaoImpl.getToDoList()"+todoList);
           
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new RuntimeException("Error updating information to database", e);
        } 
    }
      

}
