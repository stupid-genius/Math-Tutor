package com.stupid_genius.mathtutor;

public class SimpleFractionSubtractionPositive extends SimpleFractionProblem {
	public SimpleFractionSubtractionPositive(SimpleFraction first, SimpleFraction second) {
		firstNumber = first;
		secondNumber = second;
		operation = OperationEnum.SUBTRACTION;
	}

	public SimpleFractionSubtractionPositive(int level) {
		SimpleFraction addend = new SimpleFraction((int) (Math.random() * level), (int) (Math.random() * level) + 1);
		firstNumber = addend.reduce().add((new SimpleFraction((int) (Math.random() * level/2), (int) (Math.random() * level/2) + 1)).reduce());
		secondNumber = addend;
		operation = OperationEnum.SUBTRACTION;
	}

	@Override
	public boolean checkAnswer(Number input) {
		return firstNumber.subtract(secondNumber).equals(input);
	}
}
