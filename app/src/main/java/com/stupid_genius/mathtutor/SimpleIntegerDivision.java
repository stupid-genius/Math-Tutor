package com.stupid_genius.mathtutor;

import java.util.Map;

class SimpleIntegerDivision extends SimpleIntegerProblem {
	public SimpleIntegerDivision(Map config) {
		int level = Integer.parseInt(((Map<String, String>)config).get("level"));
		int factor = (int) (Math.random() * level) + 1;
		firstNumber = ((int) (Math.random() * level) + 1) * factor;
		secondNumber = factor;
		operation = OperationEnum.DIVISION;
	}

	@Override
	public Number getAnswer() {
		return firstNumber / secondNumber;
	}

	@Override
	public boolean checkAnswer(Number input) {
		return getAnswer().equals(input);
	}
}
