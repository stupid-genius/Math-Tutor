package com.stupid_genius.mathtutor;

public class SimpleFractionMultiplication extends SimpleFractionProblem {
	public SimpleFractionMultiplication(SimpleFraction first, SimpleFraction second) {
		firstNumber = first;
		secondNumber = second;
		operation = OperationEnum.MULTIPLICATION;
	}

	public SimpleFractionMultiplication(int level) {
		firstNumber = new SimpleFraction((int) (Math.random() * level), (int) (Math.random() * level) + 1);
		secondNumber = new SimpleFraction((int) (Math.random() * level), (int) (Math.random() * level) + 1);
		operation = OperationEnum.MULTIPLICATION;
	}

	@Override
	public boolean checkAnswer(Number input) {
		return firstNumber.multiply(secondNumber).equals(input);
	}
}
