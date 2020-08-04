package com.stupid_genius.mathtutor;

import java.util.Map;

public class SimpleFractionDivision extends SimpleFractionProblem {
	public SimpleFractionDivision(SimpleFraction first, SimpleFraction second) {
		firstNumber = first;
		secondNumber = second;
		operation = OperationEnum.DIVISION;
	}

	public SimpleFractionDivision(Map config) {
		int level = Integer.parseInt(((Map<String, String>)config).get("level"));
		firstNumber = new SimpleFraction((int) (Math.random() * level), (int) (Math.random() * level) + 1);
		secondNumber = new SimpleFraction((int) (Math.random() * level)+1, (int) (Math.random() * level) + 1);
		operation = OperationEnum.DIVISION;
	}

	@Override
	public Number getAnswer(){
		return firstNumber.divide(secondNumber);
	}

	@Override
	public boolean checkAnswer(Number input) {
		return getAnswer().equals(input);
	}
}
