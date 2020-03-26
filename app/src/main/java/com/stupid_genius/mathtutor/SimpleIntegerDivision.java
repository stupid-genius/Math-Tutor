package com.stupid_genius.mathtutor;

class SimpleIntegerDivision extends SimpleIntegerProblem {
	SimpleIntegerDivision(int level) {
		int factor = (int) (Math.random() * level) + 1;
		firstNumber = ((int) (Math.random() * level) + 1) * factor;
		secondNumber = factor;
	}

	public String getOperator(){
		return "/";
	}

	@Override
	public boolean checkAnswer(Number input) {
		int userAnswer = (int) input;
		return (firstNumber / secondNumber) == userAnswer;
	}
}
