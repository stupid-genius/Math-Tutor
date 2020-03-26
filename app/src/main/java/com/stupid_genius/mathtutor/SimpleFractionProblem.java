package com.stupid_genius.mathtutor;

public abstract class SimpleFractionProblem implements SimpleProblem {
	protected SimpleFraction firstNumber;
	protected SimpleFraction secondNumber;

	public SimpleFraction getFirstNumber() {
		return firstNumber;
	}

	public SimpleFraction getSecondNumber() {
		return secondNumber;
	}

	public String toString() {
		return String.format("%s %s %s =", firstNumber.toString(), getOperator(), secondNumber.toString());
	}
}
