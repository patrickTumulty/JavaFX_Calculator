package com.pt.interpreter;

public class SimpleExpression {
    private double leftHandValue;
    private double rightHandValue;
    private char operator;

    public SimpleExpression(String left, String operator, String right) {
        checkInputValues(left, operator, right);
        castInputToProperType(left, operator, right);
    }

    private void checkInputValues(String left, String operator, String right) throws IllegalArgumentException {
        if (!MathExpEvalUtil.isNumeric(left) || !MathExpEvalUtil.isNumeric(right)) {
            throw new IllegalArgumentException();
        } else if (!MathExpEvalUtil.isMathSymbol(operator) && operator.length() != 1) {
            throw new IllegalArgumentException();
        }
    }

    private void castInputToProperType(String left, String operator, String right) {

        this.leftHandValue = Double.parseDouble(left);
        this.rightHandValue = Double.parseDouble(right);
        this.operator = operator.charAt(0);
    }

    public double getLeftHandValue() {
        return leftHandValue;
    }

    public double getRightHandValue() {
        return rightHandValue;
    }

    public char getOperator() {
        return operator;
    }

    public static double evaluateSimpleExpression(SimpleExpression expression) {
        double value = 0.0;
        switch (expression.getOperator()) {
            case '+' -> value = expression.leftHandValue + expression.rightHandValue;
            case '-' -> value = expression.leftHandValue - expression.rightHandValue;
            case '*' -> value = expression.leftHandValue * expression.rightHandValue;
            case '/' -> value = expression.leftHandValue / expression.rightHandValue;
        }
        return value;
    }
}

