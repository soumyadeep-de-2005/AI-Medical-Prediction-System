package com.medpred.dao;
import org.bson.Document;

import com.medpred.model.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class UserRepository {
    private final MongoCollection<Document> collection;
    public UserRepository() { this.collection = MongoConnection.database().getCollection("users"); }
    public User findByUsername(String username) {
        Document doc = collection.find(Filters.eq("username", username)).first();
        return doc != null ? User.fromDocument(doc) : null;
    }
    public void save(User user){collection.insertOne(user.toDocument());}
}
