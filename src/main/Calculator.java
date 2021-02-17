package main;

import java.util.Scanner;

public class Calculator {
	
	private Scanner scan;
	private String expression;
	private String result;
	
	public void start() {
		
		System.out.println("Starting calculator!");
		
		scan = new Scanner(System.in);
		
		System.out.println("Please type a LEGAL math expression using: 0-9, +, -, *, /, (, ) \n "
				+ "and hit ENTER to evaluate the expression\n Enter q to exit");
		
		try {
            while (scan.hasNextLine()){

                expression = scan.nextLine();
                
                if ( expression.equals("q") ) {
                	System.out.println("Good bye!");
                	System.exit(0);
                	scan.close();
                } else if ( expression.equals("") ) {
                	System.out.println("An empty expression is not valid!");
                	continue;
                }
                
                result = MathExpressionEvaluator.evaluateExpression(expression);
            	System.out.println(result);
            }

        } finally {
            scan.close();
        }

	}

}
