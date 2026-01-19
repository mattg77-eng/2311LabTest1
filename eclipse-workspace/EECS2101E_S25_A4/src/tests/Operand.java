package tests;

/*
 * A class used by the StarterTests.
 * Operands store integer value.
 * You must use this class as is without modifying it.
 */

public class Operand extends Expression {
	private int value;

	public Operand(int value) {
		this.value = value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
