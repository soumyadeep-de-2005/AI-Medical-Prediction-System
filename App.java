package com.medpred;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

        // Load the initial login UI
        Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
        Scene scene = new Scene(root, 900, 600);

        // Optionally: Load global stylesheet
        scene.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());

        stage.setTitle("AI Medical Prediction System");
        stage.setScene(scene);
        stage.show();
    }

    // Utility for switching screens
    public static void setRoot(String fxml) throws Exception {
        Parent root = FXMLLoader.load(App.class.getResource("/views/" + fxml));
        primaryStage.getScene().setRoot(root);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
