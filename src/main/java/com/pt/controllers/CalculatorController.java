package com.pt.controllers;


import com.pt.observer.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import com.pt.observer.Subject;


import java.util.*;


public class CalculatorController implements Subject {

    @FXML public Button button9;
    @FXML public Button button8;
    @FXML public Button button7;
    @FXML public Button button6;
    @FXML public Button button5;
    @FXML public Button button4;
    @FXML public Button button3;
    @FXML public Button button2;
    @FXML public Button button1;
    @FXML public Button button0;

    @FXML public Button buttonTimes;
    @FXML public Button buttonMinus;
    @FXML public Button buttonPlus;
    @FXML public Button buttonEquals;
    @FXML public Button buttonClear;
    @FXML public Button buttonDivide;

    @FXML private TextArea textField;

    List<Button> buttonList = new ArrayList<>();
    List<Observer> observerList;
    StringBuilder mathExpressionString;
    Set<String> mathSymbols;

    public CalculatorController() {
        observerList = new ArrayList<Observer>();
        mathExpressionString = new StringBuilder("");
        mathSymbols = new HashSet<>(4);
        mathSymbols.addAll(Arrays.asList("+", "-", "*", "/"));
    }

    @FXML
    public void initialize() {
        buttonList.add(button0);
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);
        buttonList.add(button5);
        buttonList.add(button6);
        buttonList.add(button7);
        buttonList.add(button8);
        buttonList.add(button9);
        buttonList.add(buttonPlus);
        buttonList.add(buttonMinus);
        buttonList.add(buttonDivide);
        buttonList.add(buttonTimes);
        buttonList.add(buttonEquals);
        buttonList.add(buttonClear);


    }

    private void clearDisplay() {
        textField.clear();
    }

    public void displayAnswer(double value) {
        clearDisplay();
        textField.setText(Double.toString(value));
    }

    public void displayError() {
        clearDisplay();
        textField.setText("ERROR");
    }


    public void onClickEvent(MouseEvent mouseEvent) {
        Button clickedButton = (Button)mouseEvent.getSource();
        if (clickedButton.getText().equals("=")) {
            notifyObservers(mathExpressionString.toString());
        }
        updateDisplay(clickedButton);
    }

    private void updateDisplay(Button button) {
        if (button.getText().equals("C")) {
            clearDisplay();
            mathExpressionString.delete(0, mathExpressionString.length());
        } else if (button.getText().equals("=")) {
            mathExpressionString.delete(0, mathExpressionString.length());
        } else {
            updateExpressionString(button);
            textField.setText(mathExpressionString.toString());
        }
    }

    private void updateExpressionString(Button button) {
        if (mathSymbols.contains(button.getText())) {
            mathExpressionString.append(" ").append(button.getText()).append(" ");
        } else {
            mathExpressionString.append(button.getText());
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers(String string) {
        for (Observer observer : observerList) {
            observer.update(string);
        }
    }
}
