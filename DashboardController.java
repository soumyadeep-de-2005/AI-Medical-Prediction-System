package com.medpred.controllers;

import com.medpred.App;

import javafx.fxml.FXML;

public class DashboardController {

    @FXML
    private void openPatientForm() {
        try {
            App.setRoot("patient_form.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openPredictions() {
        try {
            App.setRoot("predictions.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void logout() {
        try {
            App.setRoot("login.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
