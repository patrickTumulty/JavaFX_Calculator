package com.pt.interpreter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MathExpressionEvaluator {
    private List<String> expressionList;

    public MathExpressionEvaluator() {

    }

    public double evaluate(String expression) throws IllegalArgumentException {
        expressionList = splitExpressionStringToList(expression);
        if (!expressionListIsLegal()) {
            throw new IllegalArgumentException();
        }
        if (expressionList.size() == 1) {
            return Double.parseDouble(expressionList.get(0));
        }
        return evaluateExpressionList();
    }

    private boolean expressionListIsLegal() {
        if (expressionListIsSingleNumberEntry()) return true;
        if (expressionList.size() % 2 == 0) return false;
        return !expressionContainsDoubleOperator();
    }

    private boolean expressionListIsSingleNumberEntry() {
        if (expressionList.size() == 1) {
            return MathExpEvalUtil.isNumeric(expressionList.get(0));
        }
        return false;
    }

    private List<String> splitExpressionStringToList(String expression) {
        return new LinkedList<>(Arrays.asList(expression.split("\\s")));
    }

    private double evaluateExpressionList() {
        SimpleExpression simpleExpression;
        double value = 0.0;
        while (expressionList.size() != 0) {
            simpleExpression = getNextSimpleExpression();
            value = SimpleExpression.evaluateSimpleExpression(simpleExpression);
            if (expressionList.size() != 0) {
                addValueBackToExpressionList(value);
            }
        }
        return value;
    }

    private void addValueBackToExpressionList(double value) {
        String valueAsString = Double.toString(value);
        String firstElement = expressionList.get(0);
        String lastElement = expressionList.get(expressionList.size() - 1);
        if (MathExpEvalUtil.isMathSymbol(firstElement)) {
            expressionList.add(0, valueAsString);
        } else if (MathExpEvalUtil.isMathSymbol(lastElement)) {
            expressionList.add(valueAsString);
        } else {
            insertValueBetweenDoubleOperator(valueAsString);
        }
    }

    private void insertValueBetweenDoubleOperator(String valueAsString) {
        for (int i = 0; i < expressionList.size() - 1; i++) {
            String first = expressionList.get(i);
            String second = expressionList.get(i + 1);
            if (MathExpEvalUtil.isMathSymbol(first) && MathExpEvalUtil.isMathSymbol(second)) {
                expressionList.add(i + 1, valueAsString);
            }
        }
    }

    private boolean expressionContainsDoubleOperator() {
        for (int i = 0; i < expressionList.size() - 1; i++) {
            String first = expressionList.get(i);
            String second = expressionList.get(i + 1);
            if (MathExpEvalUtil.isMathSymbol(first) && MathExpEvalUtil.isMathSymbol(second)) {
                return true;
            }
        }
        return false;
    }

    private SimpleExpression getNextSimpleExpression() {
        String lhv, rhv, operator;
        int indexOfOperator = -1;
        for (String op : Arrays.asList("/", "*", "-", "+")) {
            indexOfOperator = expressionList.indexOf(op);
            if (indexOfOperator != -1) break;
        }
        lhv = getAndRemoveFromExpressionList(indexOfOperator - 1);
        operator = getAndRemoveFromExpressionList(indexOfOperator - 1);
        rhv = getAndRemoveFromExpressionList(indexOfOperator - 1);
        return new SimpleExpression(lhv, operator, rhv);
    }

    private String getAndRemoveFromExpressionList(int index) {
        String value = expressionList.get(index);
        expressionList.remove(index);
        return value;
    }

}