package com.stupid_genius.mathtutor;

import java.util.Map;

public class SimpleFractionSubtraction extends SimpleFractionProblem {
	public SimpleFractionSubtraction(SimpleFraction first, SimpleFraction second) {
		firstNumber = first;
		secondNumber = second;
		operation = OperationEnum.SUBTRACTION;
	}

	public SimpleFractionSubtraction(Map config) {
		int level = Integer.parseInt(((Map<String, String>)config).get("level"));
		firstNumber = new SimpleFraction((int) (Math.random() * level), (int) (Math.random() * level) + 1);
		secondNumber = new SimpleFraction((int) (Math.random() * level), (int) (Math.random() * level) + 1);
		operation = OperationEnum.SUBTRACTION;
	}

	@Override
	public Number getAnswer(){
		return firstNumber.subtract(secondNumber);
	}

	@Override
	public boolean checkAnswer(Number input) {
		return getAnswer().equals(input);
	}
}
