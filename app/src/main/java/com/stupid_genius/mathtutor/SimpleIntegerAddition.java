package com.stupid_genius.mathtutor;

import java.util.Map;

public class SimpleIntegerAddition extends SimpleIntegerProblem {
	public SimpleIntegerAddition(Map config) {
		int level = Integer.parseInt(((Map<String, String>)config).get("level"));
		firstNumber = (int) (Math.random() * level);
		secondNumber = (int) (Math.random() * level);
		operation = OperationEnum.ADDITION;
	}

	@Override
	public Number getAnswer() {
		return firstNumber + secondNumber;
	}

	@Override
	public boolean checkAnswer(Number input) {
		return getAnswer().equals(input);
	}
}
