package com.stupid_genius.mathtutor;

import java.util.Map;

public class SimpleFractionMultiplication extends SimpleFractionProblem {
	public SimpleFractionMultiplication(SimpleFraction first, SimpleFraction second) {
		firstNumber = first;
		secondNumber = second;
		operation = OperationEnum.MULTIPLICATION;
	}

	public SimpleFractionMultiplication(Map config) {
		int level = Integer.parseInt(((Map<String, String>)config).get("level"));
		firstNumber = new SimpleFraction((int) (Math.random() * level), (int) (Math.random() * level) + 1);
		secondNumber = new SimpleFraction((int) (Math.random() * level), (int) (Math.random() * level) + 1);
		operation = OperationEnum.MULTIPLICATION;
	}

	@Override
	public Number getAnswer(){
		return firstNumber.multiply(secondNumber);
	}

	@Override
	public boolean checkAnswer(Number input) {
		return getAnswer().equals(input);
	}
}
