package com.stupid_genius.mathtutor;

import java.util.Map;

public class SimpleIntegerSubraction extends SimpleIntegerProblem {
	public SimpleIntegerSubraction(Map<MathTutorConfiguration, String> config) {
		int level = Integer.parseInt(config.get(MathTutorConfiguration.LEVEL));
		boolean allowNegatives = Boolean.parseBoolean((String) config.get(MathTutorConfiguration.NEGATIVE));
		if(allowNegatives){
			firstNumber = random(-level, level);
			secondNumber = random(-level, level);
		}else{
			secondNumber = random(0, (int)(.8*level));
			firstNumber = random(1, (int)(.8*level)) + secondNumber;
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
