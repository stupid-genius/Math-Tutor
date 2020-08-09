package com.stupid_genius.mathtutor;

import com.google.common.collect.Maps;

import java.util.Map;

public abstract class SimpleFractionProblem implements SimpleProblem {
	protected SimpleFraction firstNumber;
	protected SimpleFraction secondNumber;
	protected OperationEnum operation;

	public SimpleFraction getFirstNumber() {
		return firstNumber;
	}

	public SimpleFraction getSecondNumber() {
		return secondNumber;
	}

	public String getOperator(){
		return operation.getOperator();
	}

	public Map<String, String> getModel(){
		Map<String, String> newProblem = Maps.newHashMap();
		newProblem.put("firstNumerator", String.valueOf(firstNumber.getNumerator()));
		newProblem.put("firstDenominator", String.valueOf(firstNumber.getDenominator()));
		newProblem.put("secNumerator", String.valueOf(secondNumber.getNumerator()));
		newProblem.put("secDenominator", String.valueOf(secondNumber.getDenominator()));
		newProblem.put("op", getOperator());
		newProblem.put("answer", String.valueOf(getAnswer()));
		return newProblem;
	}

	@Override
	public boolean checkAnswer(Number input) {
		if(!(input instanceof SimpleFraction)){
			return false;
		}
		SimpleFraction userAnswer = (SimpleFraction) input;
		return getAnswer().equals(userAnswer.getCanonical());
	}

	protected int random(int min, int max){
		return (int) (Math.random() * (max-min)) + min;
	}

	public String toString() {
		return String.format("%s %s %s =", firstNumber.toString(), operation.getOperator(), secondNumber.toString());
	}
}
