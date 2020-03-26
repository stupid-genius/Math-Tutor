package com.stupid_genius.mathtutor;

public abstract class SimpleIntegerProblem implements SimpleProblem {
    protected Integer firstNumber;
    protected Integer secondNumber;

    public Integer getFirstNumber() {
        return firstNumber;
    }

    public Integer getSecondNumber(){
        return secondNumber;
    }

    public String toString() {
    	return String.format("%d %s %d =", firstNumber, getOperator(), secondNumber);
	}
}
