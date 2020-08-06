package com.stupid_genius.mathtutor;

import java.util.Map;

class SimpleIntegerDivision extends SimpleIntegerProblem {
	public SimpleIntegerDivision(Map<MathTutorConfiguration, String> config) {
		int level = Integer.parseInt(config.get(MathTutorConfiguration.LEVEL));
		boolean allowNegatives = Boolean.parseBoolean((String) config.get(MathTutorConfiguration.NEGATIVE));
		if(allowNegatives){
			do{
				secondNumber = (int) (Math.random() * (level*2)) - level;
			}while(secondNumber.equals(0));
			firstNumber = ((int) (Math.random() * (level*2)) - level) * secondNumber;
		}else{
			secondNumber = (int) (Math.random() * (level-1)) + 1;
			firstNumber = ((int) (Math.random() * level)) * secondNumber;
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
