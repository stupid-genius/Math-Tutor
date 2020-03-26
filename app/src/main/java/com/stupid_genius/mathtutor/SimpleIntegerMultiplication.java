package com.stupid_genius.mathtutor;

class SimpleIntegerMultiplication extends SimpleIntegerProblem {
	SimpleIntegerMultiplication(int level) {
		firstNumber = (int) (Math.random() * level);
		secondNumber = (int) (Math.random() * level);
		operation = OperationEnum.MULTIPLICATION;
	}

	@Override
	public boolean checkAnswer(Number input) {
		int userAnswer = (int) input;
		return (firstNumber * secondNumber) == userAnswer;
	}
}
