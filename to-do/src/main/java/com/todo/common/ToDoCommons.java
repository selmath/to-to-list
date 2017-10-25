/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.common;

import static com.todo.common.ToDoConstant.CODE;
import static com.todo.common.ToDoConstant.FAILURE;
import static com.todo.common.ToDoConstant.FAILURE_MSG;
import static com.todo.common.ToDoConstant.MESSAGE;
import static com.todo.common.ToDoConstant.PAYLOAD;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import static net.logstash.logback.marker.Markers.append;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ind-it-dev-shyam
 */
public abstract class ToDoCommons {

    @Autowired
    private Environment env;

    @Autowired
    private RestTemplate restTemplate;

    public static final String MONGO_DB_URI = "mongodb.persist.uri";
    public static final String LULUROUTE_PROCESS_URI = "luluroute.process.uri";
    private static final String MARKER_VALUE = "value";

    private static final Logger log = LoggerFactory.getLogger(ToDoCommons.class);

    public Environment getEnv() {
        return env;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

    public String getProp(String key) {
        return getEnv().getRequiredProperty(key);
    }

    public <T extends Object> T getProp(String key, Class<T> type) {
        return getEnv().getRequiredProperty(key, type);
    }

    public RestTemplate getREST() {
        return this.restTemplate;
    }

    protected static void loginfo(Object payload, String message, Object... objs) {
        log.debug(append(MARKER_VALUE, payload), message, objs);
    }

    /**
     *
     * @param apiUrl
     * @param inParamsMap
     * @return
     */
    public JSONObject postREST(final String apiUrl, final JSON inParamsMap) {
        loginfo(inParamsMap, "in payload - {}", apiUrl);

        try {
            JSONObject response = getREST().postForObject(apiUrl, inParamsMap, JSONObject.class);

            loginfo(response, " Response from - {}", apiUrl);

            return response;
        } catch (RestClientException rce) {
            throwFailure(FAILURE, FAILURE_MSG);
        }

        return null;
    }
    
     /**
     *
     * @param apiUrl
     * @param inParamsMap
     * @return
     */
    public JSONObject postRESTArray(final String apiUrl, final JSONArray inParamsMap) {
        loginfo(inParamsMap, "in payload - {}", apiUrl);

        try {
            JSONObject response = null;
            for (Iterator it = inParamsMap.iterator(); it.hasNext();) {
                JSON inparam = (JSON) it.next();
                response = getREST().postForObject(apiUrl, inparam, JSONObject.class);
                loginfo(response, " Response from - {}", apiUrl);           
            }
            return response;
        } catch (RestClientException rce) {
            throwFailure(FAILURE, FAILURE_MSG);
        }

        return null;
    }

    public JSONObject getREST(final String apiUrl, final JSON inParamsMap) {
        loginfo(inParamsMap, "in payload - {}", apiUrl);
        try {
            String response = getREST().getForObject(apiUrl, String.class);
            loginfo(response, " Response from - {}", apiUrl);
            JSONObject inParamsJson = (JSONObject) JSONSerializer.toJSON(response);
            return inParamsJson;
        } catch (RestClientException rce) {
            throwFailure(FAILURE, FAILURE_MSG);
        }

        return null;
    }

    public final void throwFailure(final int Code, final String message) {
        throw new RuntimeException(buildResponse(Code, message));
    }

    public final String buildResponse(final int code, final String message) {
        final JSONObject response = new JSONObject();
        response.put(CODE, code);
        response.put(MESSAGE, message);
        return response.toString();
    }

    public final String buildResponse(final int code, final String message, final JSONObject payoad) {
        final JSONObject response = new JSONObject();
        response.put(CODE, code);
        response.put(MESSAGE, message);
        response.put(PAYLOAD, payoad);
        return response.toString();
    }
    
     void parseXml(){
        try {
            JSONParser parser = new JSONParser();
            //Use JSONObject for simple JSON and JSONArray for array of JSON.
            JSONObject data = (JSONObject) parser.parse(
                  new FileReader("/resources/recon-json.json"));//path to the JSON file.
            System.out.println(data.toString());

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
     }
    
    
}
