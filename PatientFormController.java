package com.medpred.controllers;

import java.time.Instant;

import com.medpred.App;
import com.medpred.dao.PatientRepository;
import com.medpred.model.Patient;
import com.medpred.service.PredictionService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PatientFormController {

    @FXML private TextField nameField, ageField, sexField, bmiField, glucoseField, bpField;
    @FXML private Label resultLabel;

    private final PatientRepository patientRepo = new PatientRepository();
    private final PredictionService predictionService = new PredictionService();

    @FXML
    @SuppressWarnings("UseSpecificCatch")
    public void predict() {
        System.out.println("Predict method called");
        try {
            Patient p = new Patient(
                nameField.getText(),
                Integer.parseInt(ageField.getText()),
                sexField.getText(),
                Double.parseDouble(bmiField.getText()),
                Double.parseDouble(glucoseField.getText()),
                Double.parseDouble(bpField.getText())
            );

            double probability = predictionService.predictRisk(p);

            p.setLastRisk(probability);
            p.setTimestamp(Instant.now().toString());

            patientRepo.save(p); // save with prediction info

            boolean highRisk = probability >= 0.5;
            resultLabel.setText(String.format(
                "Risk: %.1f%% (%s)",
                probability * 100,
                highRisk ? "POSITIVE/High Risk" : "NEGATIVE/Low Risk"
            ));
        } catch (Exception ex) {
            resultLabel.setText("Invalid input! Fill all fields correctly.");
            ex.printStackTrace();
        }
    }

    @FXML
    public void back() {
        System.out.println("Back method called");
        try {
            App.setRoot("dashboard.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
