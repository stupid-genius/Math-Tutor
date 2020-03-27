package com.stupid_genius.mathtutor;

public class SimpleFractionSubtraction extends SimpleFractionProblem {
	public SimpleFractionSubtraction(SimpleFraction first, SimpleFraction second) {
		firstNumber = first;
		secondNumber = second;
		operation = OperationEnum.SUBTRACTION;
	}

	public SimpleFractionSubtraction(int level) {
		firstNumber = new SimpleFraction((int) (Math.random() * level), (int) (Math.random() * level) + 1);
		secondNumber = new SimpleFraction((int) (Math.random() * level), (int) (Math.random() * level) + 1);
		operation = OperationEnum.SUBTRACTION;
	}

	@Override
	public boolean checkAnswer(Number input) {
		return firstNumber.subtract(secondNumber).equals(input);
	}
}
