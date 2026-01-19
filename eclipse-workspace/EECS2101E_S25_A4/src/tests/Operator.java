package tests;

/*
 * A class used by the StarterTests.
 * Operators (e.g., `+` for addition) manipulate integer operands.
 * You must use this class as is without modifying it.
 */

public class Operator extends Expression {
	private char op;
	
	public Operator(char op) {
		this.op = op;
	}
	
	public void setOperator(char op) {
		this.op = op;
	}
	
	public char getOperator() {
		return this.op;
	}
}
