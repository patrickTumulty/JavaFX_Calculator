package com.pt.logic;

import com.pt.controllers.CalculatorController;
import com.pt.observer.Observer;

public class NumberProcessor implements Observer {
    CalculatorController controller;
    MathExpressionEvaluator evaluator;

    public NumberProcessor(CalculatorController controller) {
        evaluator = new MathExpressionEvaluator();
        this.controller = controller;
        this.controller.addObserver(this);
    }

    @Override
    public void update(String string) {
        Double answer = evaluator.evaluate(string);
        if (answer == null) {
            controller.displayError();
        } else {
            controller.displayAnswer(answer);
        }
    }
}
