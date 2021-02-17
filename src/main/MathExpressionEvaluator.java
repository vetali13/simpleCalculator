package main;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MathExpressionEvaluator {

	// takes a legal MATH expression as STRING and returns the result as STRING
	// the expression should contain only the following characters: 0-9, +, -, *, /, (, )
	public static String evaluateExpression(String expression) {
	
	String expressionFromParenthesis;
	String evaluatedExpression;
	String regEx;
		
	while ( hasParenthesis(expression) ) {
			expressionFromParenthesis = separateExpressionInRightParenthesis(expression);
			evaluatedExpression = evaluateMultiplicationDivision(expressionFromParenthesis);
			evaluatedExpression = evaluateAdditionSubstraction(evaluatedExpression);
			regEx = "(" + expressionFromParenthesis + ")";
			expression = expression.replaceAll(Pattern.quote(regEx), evaluatedExpression);
		}
		
		expression = evaluateAdditionSubstraction(
								evaluateMultiplicationDivision(expression)
								);
		return expression;
	}
	
	
	// takes a legal MATH expression as STRING and returns the expression from the parenthesis located mostly to the right
	// the expression should contain only the following characters: 0-9, +, -, *, /, (, )
	public static String separateExpressionInRightParenthesis(String expression) {
					
		if ( !hasParenthesis(expression) ) return expression;
		
		String expressionFromParenthesis = "";
		
		for (int i = 0; i < expression.length(); i++) {
			
			if (expression.charAt(i) == '(') expressionFromParenthesis = expression.substring(i+1);
		}
		
		expression = expressionFromParenthesis;
		
		for (int i = 0; i < expression.length(); i++) {
			
			if (expression.charAt(i) == ')') {
				
				expressionFromParenthesis = expression.substring(0, i);
				return expressionFromParenthesis;
			}
		}

		return expressionFromParenthesis;
	}


	// takes a legal MATH expression without parenthesis as STRING and evaluates all multiplications or divisions found
	// the expression should contain only the following characters: 0-9, +, -, *, /,
	public static String evaluateMultiplicationDivision(String expression) {
		
		if ( !hasMultiplicationOrDivision(expression) ) {
			return expression;
		}
		
		expression = markNegativeNumbers(expression);
		
		String[] additionSubstractionMembers = expression.replaceAll("[+-]", " ").split(" ");
		String additionSubstractionOperators = expression.replaceAll("[0-9.*/n]", "");
		List<String> multiplicationDivisionResults = new ArrayList<>();
		String[] multiplicationDivisionMembers;
		String multiplicationDivisionOperators;
		Float result;
		
		for (String s : additionSubstractionMembers) {
			
			multiplicationDivisionMembers = s.replaceAll("[*/]", " ").split(" ");
			multiplicationDivisionOperators = s.replaceAll("[0-9.n]", "");
			result = Float.valueOf(multiplicationDivisionMembers[0].replaceAll("n", "-"));
			
			for (int i = 0; i < multiplicationDivisionOperators.length(); i++) {
				if (multiplicationDivisionOperators.charAt(i) == '*') {
					result *= Float.valueOf(multiplicationDivisionMembers[i+1].replaceAll("n", "-"));
				} else if (multiplicationDivisionOperators.charAt(i) == '/') {
					result /= Float.valueOf(multiplicationDivisionMembers[i+1].replaceAll("n", "-"));
				}
			}
			
			multiplicationDivisionResults.add(result.toString());
			
		}
		
		String resultAsString = multiplicationDivisionResults.get(0);
		
		for (int i = 0; i < additionSubstractionOperators.length(); i++) {
			resultAsString += additionSubstractionOperators.charAt(i) + multiplicationDivisionResults.get(i+1);
		}
		return resultAsString;
	}
	
	
	// takes a legal MATH expression without parenthesis and without multiplications or divisions and evaluate it
	// the expression should contain only the following characters: 0-9, +, -
	public static String evaluateAdditionSubstraction(String expression) {
		
		expression = markNegativeNumbers(expression);
		
		String[] additionSubstrationMembers = expression.replaceAll("[+-]", " ").split(" ");
		String additionSubstrationOperators = expression.replaceAll("[0-9.n]", "");
		Float result = Float.valueOf(additionSubstrationMembers[0].replaceAll("n", "-"));
		
		for (int i = 0; i < additionSubstrationOperators.length(); i++) {
			if (additionSubstrationOperators.charAt(i) == '+') {
				result += Float.valueOf(additionSubstrationMembers[i+1].replaceAll("n", "-"));
			} else if (additionSubstrationOperators.charAt(i) == '-') {
				result -= Float.valueOf(additionSubstrationMembers[i+1].replaceAll("n", "-"));
			}
		}
		
		return result.toString();
	}
	
	// checks if a legal MATH expression has any opening parenthesis
	public static boolean hasParenthesis(String expression) {
		if (expression.indexOf('(') == -1) {
			return false;
		}
		return true;
	}
	
	// checks if a legal MATH expression has any multiplication or division
	public static boolean hasMultiplicationOrDivision(String expression) {
		if ( (expression.indexOf('*') == -1) && (expression.indexOf('/') == -1) ) {
			return false;
		}
		return true;
	}
	
	
	// checks for negative numbers in the expression and replace the sign "-" with an "n" flag
	public static String markNegativeNumbers(String expression) {
		if (expression.charAt(0) == '-') {
			expression = 'n' + expression.substring(1);
		}
		expression = expression.replaceAll(Pattern.quote("--"), "-n");
		expression = expression.replaceAll(Pattern.quote("+-"), "+n");
		expression = expression.replaceAll(Pattern.quote("*-"), "*n");
		expression = expression.replaceAll(Pattern.quote("/-"), "/n");
		
		return expression;	
	}
	

}
