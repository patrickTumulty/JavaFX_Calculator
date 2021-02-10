package com.pt.app;

import com.pt.controllers.CalculatorController;
import com.pt.interpreter.MathExpEvalUtil;
import com.pt.interpreter.MathExpressionEvaluator;
import com.pt.logic.NumberProcessor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
//        MathExpressionEvaluator mathExpressionEvaluator = new MathExpressionEvaluator();
//        double v1 = mathExpressionEvaluator.evaluate("23 + 2 * 6");
//        System.out.println(v1);
//        double v2 = mathExpressionEvaluator.evaluate("12 - 6 * 8 + 6 ÷ 2");
//        System.out.println(v2);
//        double v3 = mathExpressionEvaluator.evaluate("25 + 6 ÷ 4 * 5 - 1");
//        System.out.println(v3);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainpanel.fxml"));

        Parent root = fxmlLoader.load();
        CalculatorController calculatorController = fxmlLoader.getController();

        Scene scene = new Scene(root);
        primaryStage.setTitle("FX-84");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);

//        NumberProcessor numberProcessor = new NumberProcessor(calculatorController);

        primaryStage.show();



    }
}