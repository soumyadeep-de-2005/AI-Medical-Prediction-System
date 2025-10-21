package com.medpred.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.medpred.model.Patient;
import com.mongodb.client.MongoCollection;

public class PatientRepository {
    private final MongoCollection<Document> col;
    public PatientRepository() {
        this.col = MongoConnection.database().getCollection("patients");
    }

    public void save(Patient p) {
        col.insertOne(p.toDocument());
    }

    // ADD THIS METHOD:
    public List<Patient> findAll() {
        List<Patient> list = new ArrayList<>();
        for (Document d : col.find()) list.add(Patient.fromDocument(d));
        return list;
    }
}
