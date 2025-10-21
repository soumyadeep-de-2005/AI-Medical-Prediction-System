package com.medpred.model;
import org.bson.Document;
public class User {
    private String username, hash, role;
    public User(){}
    public User(String username, String hash, String role){this.username=username;this.hash=hash;this.role=role;}
    public String getUsername(){return username;}
    public String getHash(){return hash;}
    public String getRole(){return role;}
    public Document toDocument(){return new Document("username",username).append("hash",hash).append("role",role);}
    public static User fromDocument(Document d) { return new User(d.getString("username"), d.getString("hash"), d.getString("role")); }
}
