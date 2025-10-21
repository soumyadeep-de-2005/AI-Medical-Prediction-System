package com.medpred.model;

import org.bson.Document;

public class Patient {
    private String name;
    private int age;
    private String sex;
    private double bmi;
    private double glucose;
    private double bloodPressure;
    private double lastRisk = -1;  // stores last prediction risk probability (0-1)
    private String timestamp = ""; // stores the instant of last prediction

    public Patient() {}

    public Patient(String name, int age, String sex, double bmi, double glucose, double bloodPressure) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.bmi = bmi;
        this.glucose = glucose;
        this.bloodPressure = bloodPressure;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }
    public double getBmi() { return bmi; }
    public void setBmi(double bmi) { this.bmi = bmi; }
    public double getGlucose() { return glucose; }
    public void setGlucose(double glucose) { this.glucose = glucose; }
    public double getBloodPressure() { return bloodPressure; }
    public void setBloodPressure(double bloodPressure) { this.bloodPressure = bloodPressure; }

    // For storing latest prediction and save/load from db
    public double getLastRisk() { return lastRisk; }
    public void setLastRisk(double lastRisk) { this.lastRisk = lastRisk; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public Document toDocument() {
        return new Document("name", name)
            .append("age", age)
            .append("sex", sex)
            .append("bmi", bmi)
            .append("glucose", glucose)
            .append("bloodPressure", bloodPressure)
            .append("lastRisk", lastRisk)
            .append("timestamp", timestamp);
    }

    public static Patient fromDocument(Document d) {
        Patient p = new Patient(
            d.getString("name"),
            d.getInteger("age", 0),
            d.getString("sex"),
            d.getDouble("bmi"),
            d.getDouble("glucose"),
            d.getDouble("bloodPressure")
        );
        if (d.containsKey("lastRisk")) p.setLastRisk(d.getDouble("lastRisk"));
        if (d.containsKey("timestamp")) p.setTimestamp(d.getString("timestamp"));
        return p;
    }
}
