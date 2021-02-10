package com.pt.interpreter;

import com.pt.controllers.CalculatorController;
import com.pt.observer.Observer;

public class InputInterpreter implements Observer {
    CalculatorController controller;
    MathExpressionEvaluator evaluator;

    public InputInterpreter(CalculatorController controller) {
        evaluator = new MathExpressionEvaluator();
        this.controller = controller;
        this.controller.addObserver(this);
    }

    @Override
    public void update(String string) {
        double answer;
        try {
            answer = evaluator.evaluate(string);
        } catch (IllegalArgumentException ex) {
            controller.displayError();
            return;
        }
        controller.displayAnswer(answer);
    }
}
