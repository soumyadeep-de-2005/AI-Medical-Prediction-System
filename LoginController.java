package com.medpred.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.medpred.App;
import com.medpred.service.AuthService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private final AuthService authService = new AuthService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        authService.ensureDefaultAdmin();
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        errorLabel.setText("");
        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please enter username and password");
            return;
        }
        if (authService.login(username, password)) {
            try { App.setRoot("dashboard.fxml"); }
            catch (Exception e) { errorLabel.setText("Failed to load dashboard."); }
        } else {
            errorLabel.setText("Invalid username or password");
        }
    }
}
