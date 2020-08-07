package com.stupid_genius.mathtutor;

import java.util.Map;

public class SimpleIntegerAddition extends SimpleIntegerProblem {
	public SimpleIntegerAddition(Map<MathTutorConfiguration, String> config) {
		int level = Integer.parseInt(config.get(MathTutorConfiguration.LEVEL));
		boolean allowNegatives = Boolean.parseBoolean(config.get(MathTutorConfiguration.NEGATIVE));
		if(allowNegatives){
			firstNumber = random(-level, level);
			secondNumber = random(-level, level);
		}else{
			firstNumber = random(0, level);
			secondNumber = random(0, level);
		}
		operation = OperationEnum.Addition;
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
