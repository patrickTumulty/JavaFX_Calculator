package com.pt.interpreter;

public class MathExpEvalUtil {
    public static boolean isMathSymbol(String element) {
        switch (element) {
            case "+", "-", "*", "÷" -> {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumeric(String element) {
        return element.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
