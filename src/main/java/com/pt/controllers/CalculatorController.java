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

    @FXML private Button button9;
    @FXML private Button button8;
    @FXML private Button button7;
    @FXML private Button button6;
    @FXML private Button button5;
    @FXML private Button button4;
    @FXML private Button button3;
    @FXML private Button button2;
    @FXML private Button button1;
    @FXML private Button button0;

    @FXML private Button buttonTimes;
    @FXML private Button buttonMinus;
    @FXML private Button buttonPlus;
    @FXML private Button buttonEquals;
    @FXML private Button buttonClear;
    @FXML private Button buttonDivide;

    @FXML private Button buttonOpenParenth;
    @FXML private Button buttonClosedParenth;
    @FXML private Button buttonPoint;
    @FXML private Button buttonNeg;

    @FXML private TextArea textField;

    private List<Button> buttonList;
    private List<Observer> observerList;
    private StringBuilder mathExpressionString;
    private final Set<String> mathSymbols;

    public CalculatorController() {
        observerList = new ArrayList<>();
        buttonList = new ArrayList<>();
        mathExpressionString = new StringBuilder();
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
        buttonList.add(buttonPoint);


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
        String buttonText = button.getText();
        if (mathSymbols.contains(buttonText)) {
            mathExpressionString.append(" ").append(button.getText()).append(" ");
        } else if (buttonText.equals("(-)")) {
            mathExpressionString.append("-");
        } else {
            mathExpressionString.append(buttonText);
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
