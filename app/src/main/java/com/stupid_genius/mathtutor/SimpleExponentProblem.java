package com.stupid_genius.mathtutor;

public abstract class SimpleExponentProblem implements SimpleProblem {
	protected SimpleExponent firstNumber;
	protected SimpleExponent secondNumber;

	public SimpleExponent getFirstNumber() {
		return firstNumber;
	}

	public SimpleExponent getSecondNumber() {
		return secondNumber;
	}

	public String toString(){
		return String.format("%f %s %f =", firstNumber, getOperator(), secondNumber);
	}
}
