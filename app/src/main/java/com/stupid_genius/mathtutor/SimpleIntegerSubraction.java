package com.stupid_genius.mathtutor;

public class SimpleIntegerSubraction extends SimpleIntegerProblem {
	public SimpleIntegerSubraction(int level) {
		firstNumber = (int) (Math.random() * level);
		secondNumber = (int) (Math.random() * level);
		operation = OperationEnum.SUBTRACTION;
	}

	@Override
	public boolean checkAnswer(Number input) {
		int userAnswer = (int) input;
		return (firstNumber - secondNumber) == userAnswer;
	}
}
