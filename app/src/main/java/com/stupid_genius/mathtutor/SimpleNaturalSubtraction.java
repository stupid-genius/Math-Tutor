package com.stupid_genius.mathtutor;

public class SimpleNaturalSubtraction extends SimpleIntegerProblem {
	public SimpleNaturalSubtraction(int level) {
		firstNumber = (int) (Math.random() * level);
		secondNumber = (int) (Math.random() * level);
		if (firstNumber < secondNumber) {
			Integer temp = firstNumber;
			firstNumber = secondNumber;
			secondNumber = temp;
		}
		operation = OperationEnum.SUBTRACTION;
	}

	@Override
	public boolean checkAnswer(Number input) {
		int userAnswer = (int) input;
		return (firstNumber - secondNumber) == userAnswer;
	}
}
