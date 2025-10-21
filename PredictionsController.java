package com.medpred.controllers;

import java.util.List;

import com.medpred.App;
import com.medpred.dao.PatientRepository;
import com.medpred.model.Patient;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PredictionsController {

    @FXML private TableView<Row> table;
    @FXML private TableColumn<Row, String> nameCol;
    @FXML private TableColumn<Row, Integer> ageCol;
    @FXML private TableColumn<Row, String> sexCol;
    @FXML private TableColumn<Row, String> riskCol;
    @FXML private TableColumn<Row, String> dateCol;

    private final PatientRepository patientRepo = new PatientRepository();

    public static class Row {
        private final String name;
        private final int age;
        private final String sex;
        private final String risk;
        private final String date;

        public Row(String name, int age, String sex, String risk, String date) {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.risk = risk;
            this.date = date;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
        public String getSex() { return sex; }
        public String getRisk() { return risk; }
        public String getDate() { return date; }
    }

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        sexCol.setCellValueFactory(new PropertyValueFactory<>("sex"));
        riskCol.setCellValueFactory(new PropertyValueFactory<>("risk"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        List<Patient> patients = patientRepo.findAll();
        List<Row> rows = patients.stream()
                .map(p -> new Row(
                        p.getName(),
                        p.getAge(),
                        p.getSex(),
                        String.format("%.1f%%", p.getLastRisk() * 100),
                        p.getTimestamp() == null ? "" : p.getTimestamp()
                ))
                .toList();

        table.setItems(FXCollections.observableArrayList(rows));
    }

    @FXML
    @SuppressWarnings("CallToPrintStackTrace")
    public void back() {
        try {
            App.setRoot("dashboard.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
