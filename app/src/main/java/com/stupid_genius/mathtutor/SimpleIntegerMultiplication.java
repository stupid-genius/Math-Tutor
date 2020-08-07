package com.stupid_genius.mathtutor;

import java.util.Map;

class SimpleIntegerMultiplication extends SimpleIntegerProblem {
	public SimpleIntegerMultiplication(Map<MathTutorConfiguration, String> config) {
		int level = Integer.parseInt(config.get(MathTutorConfiguration.LEVEL));
		boolean allowNegatives = Boolean.parseBoolean((String) config.get(MathTutorConfiguration.NEGATIVE));
		if(allowNegatives){
			firstNumber = random(-level, level);
			secondNumber = random(-level, level);
		}else{
			firstNumber = random(0, level);
			secondNumber = random(0, level);
		}
		operation = OperationEnum.Multiplication;
	}

	@Override
	public Number getAnswer() {
		return firstNumber * secondNumber;
	}

	@Override
	public boolean checkAnswer(Number input) {
		return getAnswer().equals(input);
	}
}
