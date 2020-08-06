package com.stupid_genius.mathtutor;

import java.util.Map;

public class SimpleIntegerSubraction extends SimpleIntegerProblem {
	public SimpleIntegerSubraction(Map<MathTutorConfiguration, String> config) {
		int level = Integer.parseInt(config.get(MathTutorConfiguration.LEVEL));
		boolean allowNegatives = Boolean.parseBoolean((String) config.get(MathTutorConfiguration.NEGATIVE));
		if(allowNegatives){
			firstNumber = (int) (Math.random() * (level*2))-level;
			secondNumber = (int) (Math.random() * (level*2))-level;
		}else{
			secondNumber = (int) (Math.random() * level*.8);
			firstNumber = ((int) (Math.random() * level*.8) + 1) + secondNumber;
		}
		operation = OperationEnum.Subtraction;
	}

	@Override
	public Number getAnswer(){
		return firstNumber - secondNumber;
	}

	@Override
	public boolean checkAnswer(Number input) {
		return getAnswer().equals(input);
	}
}
