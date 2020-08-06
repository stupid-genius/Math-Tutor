package com.stupid_genius.mathtutor;

import java.util.Map;

public class SimpleFractionAddition extends SimpleFractionProblem {
	public SimpleFractionAddition(SimpleFraction first, SimpleFraction second) {
		firstNumber = first;
		secondNumber = second;
		operation = OperationEnum.Addition;
	}

	public SimpleFractionAddition(Map<MathTutorConfiguration, String> config) {
		int level = Integer.parseInt(config.get(MathTutorConfiguration.LEVEL));
		firstNumber = new SimpleFraction((int) (Math.random() * level), (int) (Math.random() * level) + 1);
		secondNumber = new SimpleFraction((int) (Math.random() * level), (int) (Math.random() * level) + 1);
		operation = OperationEnum.Addition;
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
