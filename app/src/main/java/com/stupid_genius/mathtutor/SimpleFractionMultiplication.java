package com.stupid_genius.mathtutor;

public class SimpleFractionMultiplication extends SimpleFractionProblem {
	public SimpleFractionMultiplication(int level) {
		firstNumber = new SimpleFraction((int) (Math.random() * level), (int) (Math.random() * level) + 1);
		secondNumber = new SimpleFraction((int) (Math.random() * level), (int) (Math.random() * level) + 1);
	}
	@Override
	public String getOperator() {
		return "*";
	}

	@Override
	public boolean checkAnswer(Number input) {
		return firstNumber.multiply(secondNumber).equals(input);
	}
}
