package com.pt.tests.interpreter;

import com.pt.interpreter.MathExpressionEvaluator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class InterpreterTest {
    @Test
    void expressionReturnsCorrectAnswer() {
        Map<String, Double> expressionAndAnswer = new HashMap<String, Double>();
        expressionAndAnswer.put("3 + 4", 7.0);
        expressionAndAnswer.put("23 * 7 - 4 / 8", 160.5);
        expressionAndAnswer.put("25 + 6 / 4 * 5 - 1", 31.5);
        expressionAndAnswer.put("5 * 6 * 8 / 3 + 4 / 2 - 8 / 9", 81.11);

        MathExpressionEvaluator evaluator = new MathExpressionEvaluator();
        for (String expression : expressionAndAnswer.keySet()) {
            Assertions.assertEquals(expressionAndAnswer.get(expression), evaluator.evaluate(expression), 0.01);
        }
    }
}
