package com.udemy.multischema.service;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.udemy.multischema.tenant.TenantAwareService;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;


@Configuration
public class MongoConfig implements MongoDbFactory {

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;


    @Override
    public MongoDatabase getDb() throws DataAccessException {
        String databaseName = TenantAwareService.getTenant();
        if (databaseName == null)
            databaseName = "ADMIN";
        MongoDatabase database = new MongoClient(host, port).getDatabase(databaseName);
        return database;
    }

    @Override
    public MongoDatabase getDb(String s) throws DataAccessException {
        String databaseName = TenantAwareService.getTenant();
        if (databaseName == null)
            databaseName = "ADMIN";
        MongoDatabase database = new MongoClient("localhost").getDatabase(databaseName);
        return database;
    }

    @Override
    public PersistenceExceptionTranslator getExceptionTranslator() {
        return null;
    }

    @Override
    public DB getLegacyDb() {
        return null;
    }
}

