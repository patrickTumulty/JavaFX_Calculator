package com.pt.logic;
import com.pt.interpreter.MathExpEvalUtil;

import java.lang.reflect.Array;
import java.util.*;

public class MathExpressionEvaluator extends LinkedList<String> implements List<String> {
    private double total;

    public MathExpressionEvaluator() {
        total = 0.0;
    }

    public Double evaluate(String expression) {
        this.clear();
        parseExpressionStringToList(expression);
        if (!evaluateIfLegalExpression()) {
            return null;
        }
        evaluateMathExpression();
        return total;
    }


    private void evaluateMathExpression() {
        List<String> simpleExpression;
        while (this.size() != 1) {
            simpleExpression = getNextSimpleExpression();
//            simpleExpression.add(this.removeFirst());
            if (simpleExpression.size() == 3) {
                double value = evaluateSimpleExpression(simpleExpression);
                simpleExpression.clear();
                addValueBackToExpression(Double.toString(value));
//                this.addFirst(Double.toString(value));
            }
        }
        total = Double.parseDouble(this.removeFirst());
    }

    private void addValueBackToExpression(String value) {
        assert this.peekFirst() != null;
        if (MathExpEvalUtil.isMathSymbol(this.peekFirst())) {
            this.addFirst(value);
        } else if (MathExpEvalUtil.isMathSymbol(this.peekLast())) {
            this.addLast(value);
        } else {
            for (int i = 0; i < this.size() - 1; i++) {
                if (MathExpEvalUtil.isMathSymbol(this.get(i)) && MathExpEvalUtil.isMathSymbol(this.get(i+1))) {
                    this.add(i+1, value);
                }
            }
        }
    }

    private List<String> getNextSimpleExpression() {
        String left, right, operator;
        int indexOfOperator = -1;
        for (String op : Arrays.asList("*", "÷", "-", "+")) {
            indexOfOperator = this.indexOf(op);
            if (indexOfOperator != -1) {
                break;
            }
        }

        left = this.remove(indexOfOperator - 1);
        operator = this.remove(indexOfOperator - 1);
        right = this.remove(indexOfOperator - 1); // we don't need +1 because after we
        return new ArrayList<String>(Arrays.asList(left, operator, right));
    }

    private double evaluateSimpleExpression(List<String> simpleExpression) {
        double value = 0.0;
        switch (simpleExpression.get(1)) {
            case "+" -> value = Double.parseDouble(simpleExpression.get(0)) + Double.parseDouble(simpleExpression.get(2));
            case "-" -> value = Double.parseDouble(simpleExpression.get(0)) - Double.parseDouble(simpleExpression.get(2));
            case "*" -> value = Double.parseDouble(simpleExpression.get(0)) * Double.parseDouble(simpleExpression.get(2));
            case "÷" -> value = Double.parseDouble(simpleExpression.get(0)) / Double.parseDouble(simpleExpression.get(2));
        }
        return value;
    }

    private void parseExpressionStringToList(String expression) {
        String mathString = expression.replaceAll("\\s", ""); // remove white space
        String element = "";
        for (int i = 0; i < mathString.length(); i++) {
            char c = mathString.charAt(i);
            switch (c) {
                case '+' -> element = addNumAndOperator(element, c);
                case '-' -> element = addNumAndOperator(element, c);
                case '*' -> element = addNumAndOperator(element, c);
                case '÷' -> element = addNumAndOperator(element, c);
                default -> element += Character.toString(c);
            }
        }
        if (!element.equals("")) this.add(element);
    }

    private String addNumAndOperator(String element, char c) {
        if (!element.equals("")) this.add(element);
        this.add(Character.toString(c));
        element = "";
        return element;
    }

    private boolean evaluateIfLegalExpression() {
        if (isSingleNumberEntry()) return true;
        if (this.size() % 2 == 0) return false;
        return !exsistsInvalidDoubleMathSymbol();
    }

    private boolean isSingleNumberEntry() {
        if (this.size() == 1) {
            return !MathExpEvalUtil.isMathSymbol(this.peekFirst());
        }
        return false;
    }

    private boolean exsistsInvalidDoubleMathSymbol() {
        boolean nextShouldBeNumber = true;
        for (String element : this) {
            if (nextShouldBeNumber) {
                if (MathExpEvalUtil.isMathSymbol(element)) return true;
                nextShouldBeNumber = false;
            } else {
                if (!MathExpEvalUtil.isMathSymbol(element)) return true;
                nextShouldBeNumber = true;
            }
        }
        return false;
    }


}
