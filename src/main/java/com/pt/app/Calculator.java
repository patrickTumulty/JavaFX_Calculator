package com.pt.app;

import com.pt.controllers.CalculatorController;
import com.pt.interpreter.InputInterpreter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class Calculator extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainpanel.fxml"));

        Parent root = fxmlLoader.load();
        CalculatorController calculatorController = fxmlLoader.getController();

        Scene scene = new Scene(root);
        primaryStage.setTitle("FX-84");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);

        InputInterpreter interpreter = new InputInterpreter(calculatorController);

        primaryStage.show();

    }
}