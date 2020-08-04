package com.stupid_genius.mathtutor;

import java.util.Map;

public class SimpleFractionAddition extends SimpleFractionProblem {
	public SimpleFractionAddition(SimpleFraction first, SimpleFraction second) {
		firstNumber = first;
		secondNumber = second;
		operation = OperationEnum.ADDITION;
	}

	public SimpleFractionAddition(Map config) {
		int level = Integer.parseInt(((Map<String, String>)config).get("level"));
		firstNumber = new SimpleFraction((int) (Math.random() * level), (int) (Math.random() * level) + 1);
		secondNumber = new SimpleFraction((int) (Math.random() * level), (int) (Math.random() * level) + 1);
		operation = OperationEnum.ADDITION;
	}

	@Override
	public Number getAnswer(){
		return firstNumber.add(secondNumber);
	}

	@Override
	public boolean checkAnswer(Number input) {
		return getAnswer().equals(input);
	}
}
