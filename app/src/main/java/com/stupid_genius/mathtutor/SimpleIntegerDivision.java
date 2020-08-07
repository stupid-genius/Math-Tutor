package com.stupid_genius.mathtutor;

import java.util.Map;

class SimpleIntegerDivision extends SimpleIntegerProblem {
	public SimpleIntegerDivision(Map<MathTutorConfiguration, String> config) {
		int level = Integer.parseInt(config.get(MathTutorConfiguration.LEVEL));
		boolean allowNegatives = Boolean.parseBoolean((String) config.get(MathTutorConfiguration.NEGATIVE));
		if(allowNegatives){
			do{
				secondNumber = random(-level, level);
			}while(secondNumber.equals(0));
			firstNumber = random(-level, level) * secondNumber;
		}else{
			secondNumber = random(1, level);
			firstNumber = random(0, level) * secondNumber;
		}
		operation = OperationEnum.Division;
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
