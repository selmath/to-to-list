/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.common.ToDoCommons;
import static com.todo.common.ToDoConstant.SUCCESS;
import static com.todo.common.ToDoConstant.SUCCESS_MSG;
import com.todo.service.ToDoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author geethu
 */
@RestController
@RequestMapping("/todo/")
public class ToDoWeb extends ToDoCommons {
    
    @Autowired
    private ToDoService toDoService;
    
    @ApiOperation(value = "API for to get TO-DO list", notes = "This API will to do list based on user")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "A constant string is returned")})
    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public String getToDoList(@RequestParam(required = false, name = "payload") String payload) {
        JSONObject responsePayload = new JSONObject();
        try {
            JSONObject inParamsJson = (JSONObject) JSONSerializer.toJSON(payload);
            toDoService.getToDoList(inParamsJson);
            System.out.println("com.todo.web.ToDoWeb.getProfessionList()"+ inParamsJson);
        } catch (RuntimeException re) {
            return re.getMessage();
        }

        return buildResponse(SUCCESS, SUCCESS_MSG, null);
    }
    
    @ApiOperation(value = "API for to save TO-DO list", notes = "This method save TO-DO")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "A constant string is returned")})

    @RequestMapping(method = RequestMethod.POST, path = "/save")
    public String saveToDoItem(@RequestBody(required = true) LinkedHashMap<String, Object> inParamsMap) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            net.sf.json.JSONObject payload = (net.sf.json.JSONObject) JSONSerializer.toJSON(mapper.writeValueAsString(inParamsMap));
            toDoService.saveToDo(payload);
            System.out.println("com.todo.web.ToDoWeb.saveToDoItem()" + inParamsMap);
            return buildResponse(SUCCESS, SUCCESS_MSG, null);
        } catch (RuntimeException re) {
            return re.getMessage();
        } catch (JsonProcessingException ex) {
            return ex.getMessage();
        }
    } 
    
}
