package com.medpred.dao;

import java.io.InputStream;
import java.util.Properties;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    private static MongoDatabase db;
    static {
        try (InputStream in = MongoConnection.class.getResourceAsStream("/config/application.properties")) {
            Properties props = new Properties();
            props.load(in);
            String host = props.getProperty("mongo.host", "localhost");
            int port = Integer.parseInt(props.getProperty("mongo.port", "27017"));
            String database = props.getProperty("mongo.database", "medpred");

            MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(b -> b.hosts(java.util.List.of(new ServerAddress(host, port))))
                .build();
            MongoClient client = MongoClients.create(settings);
            db = client.getDatabase(database);
        } catch (Exception e) { throw new RuntimeException("DB setup failed", e); }
    }

    public static MongoDatabase database() { return db; }
}
