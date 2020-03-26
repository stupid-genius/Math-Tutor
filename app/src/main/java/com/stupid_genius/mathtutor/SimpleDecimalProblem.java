package com.stupid_genius.mathtutor;

public abstract class SimpleDecimalProblem implements SimpleProblem {
	protected SimpleDecimal firstNumber;
	protected SimpleDecimal secondNumber;

	public SimpleDecimal getFirstNumber() {
		return firstNumber;
	}

	public SimpleDecimal getSecondNumber() {
		return secondNumber;
	}

	public String toString(){
		return String.format("%f %s %f =", firstNumber, getOperator(), secondNumber);
	}
}
