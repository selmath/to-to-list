/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo;

import com.mongodb.MongoClient;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

@Configuration
public class DataSourceConfig {
    
//    @Bean(name = "dataSource")
//    @Primary
//    @ConfigurationProperties(prefix="spring.datasource")
//    public DataSource dataSource(){
//        return DataSourceBuilder.create().build();
//    }
//    
//    @Bean(name = "mssqlDataSource")
//    @ConfigurationProperties(prefix="spring.mssql.datasource")
//    public DataSource mssqlDataSource() {
//        return DataSourceBuilder.create().build();
//    }

    @Autowired
    Environment env;

    @Bean(name = "mongoClient")
    public MongoClient mongoClient() {
        String host = env.getRequiredProperty("spring.data.mongodb.host", String.class);
        Integer port = env.getRequiredProperty("spring.data.mongodb.port", Integer.class);
        MongoClient mongoClient = new MongoClient(host, port);

        return mongoClient;
    }

}
