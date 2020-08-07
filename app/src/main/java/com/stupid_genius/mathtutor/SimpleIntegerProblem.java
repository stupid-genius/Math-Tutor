package com.stupid_genius.mathtutor;

import com.google.common.collect.Maps;

import java.util.Map;

public abstract class SimpleIntegerProblem implements SimpleProblem {
    protected Integer firstNumber;
    protected Integer secondNumber;
    protected OperationEnum operation;

    public Integer getFirstNumber() {
        return firstNumber;
    }

    public Integer getSecondNumber(){
        return secondNumber;
    }

    public String getOperator() {
    	return operation.getOperator();
	}

	public Map<String, String> getModel(){
		Map<String, String> newProblem = Maps.newHashMap();
		newProblem.put("firstNum", String.valueOf(firstNumber));
		newProblem.put("secNum", String.valueOf(secondNumber));
		newProblem.put("op", getOperator());
		newProblem.put("answer", String.valueOf(getAnswer()));
		return newProblem;
	}

	protected int random(int min, int max){
		return (int) (Math.random() * (max-min)) + min;
	}

	public String toString() {
    	return String.format("%d %s %d =", firstNumber, operation.getOperator(), secondNumber);
	}
}
