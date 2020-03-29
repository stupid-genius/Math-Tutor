package com.stupid_genius.mathtutor;

public class SimpleFractionDivision extends SimpleFractionProblem {
	public SimpleFractionDivision(SimpleFraction first, SimpleFraction second) {
		firstNumber = first;
		secondNumber = second;
		operation = OperationEnum.DIVISION;
	}

	public SimpleFractionDivision(int level) {
		firstNumber = new SimpleFraction((int) (Math.random() * level), (int) (Math.random() * level) + 1);
		secondNumber = new SimpleFraction((int) (Math.random() * level)+1, (int) (Math.random() * level) + 1);
		operation = OperationEnum.DIVISION;
	}

	@Override
	public boolean checkAnswer(Number input) {
		return firstNumber.divide(secondNumber).equals(input);
	}
}
