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

	public String toString() {
		return String.format("%s %s %s =", firstNumber.toString(), operation.getOperator(), secondNumber.toString());
	}
}
