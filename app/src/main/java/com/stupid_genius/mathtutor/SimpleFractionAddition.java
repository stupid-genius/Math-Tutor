package com.stupid_genius.mathtutor;

public class SimpleFractionAddition extends SimpleFractionProblem {
	public SimpleFractionAddition(SimpleFraction first, SimpleFraction second) {
		firstNumber = first;
		secondNumber = second;
		operation = OperationEnum.ADDITION;
	}

	public SimpleFractionAddition(int level) {
		firstNumber = new SimpleFraction((int) (Math.random() * level), (int) (Math.random() * level) + 1);
		secondNumber = new SimpleFraction((int) (Math.random() * level), (int) (Math.random() * level) + 1);
		operation = OperationEnum.ADDITION;
	}

	@Override
	public boolean checkAnswer(Number input) {
		return firstNumber.add(secondNumber).equals(input);
	}
}
