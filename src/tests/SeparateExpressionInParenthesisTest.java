package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import main.MathExpressionEvaluator;

class SeparateExpressionInParenthesisTest {
	
	String expressionFromParenthesis;

	@BeforeEach
	void setUp() throws Exception {
		expressionFromParenthesis = MathExpressionEvaluator.separateExpressionInRightParenthesis("1+2*4/(16+8+8)");
	}

	@Test
	void testSeparationFromParenthesis() {
		Assert.assertEquals("16+8+8", expressionFromParenthesis);
	}

}
