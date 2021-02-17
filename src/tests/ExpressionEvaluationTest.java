package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import main.MathExpressionEvaluator;

class ExpressionEvaluationTest {
	String evaluatedMathExpression;

	@BeforeEach
	void setUp() throws Exception {
		evaluatedMathExpression = MathExpressionEvaluator.evaluateExpression("1+2*4/(16+8+8)");
	}

	@SuppressWarnings("deprecation")
	@Test
	void testExpressionEvaluation() {
		Assert.assertEquals("1.25", evaluatedMathExpression);
	}

}
