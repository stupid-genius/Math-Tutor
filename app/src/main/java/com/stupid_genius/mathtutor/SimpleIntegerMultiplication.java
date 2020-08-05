package com.stupid_genius.mathtutor;

import java.util.Map;

class SimpleIntegerMultiplication extends SimpleIntegerProblem {
	public SimpleIntegerMultiplication(Map<MathTutorConfiguration, String> config) {
		int level = Integer.parseInt(config.get(MathTutorConfiguration.LEVEL));
		boolean allowNegatives = Boolean.parseBoolean((String) config.get(MathTutorConfiguration.NEGATIVE));
		if(allowNegatives){
			firstNumber = (int) (Math.random() * (level*2))-level;
			secondNumber = (int) (Math.random() * (level*2))-level;
		}else{
			firstNumber = (int) (Math.random() * level);
			secondNumber = (int) (Math.random() * level);
		}
		operation = OperationEnum.MULTIPLICATION;
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
