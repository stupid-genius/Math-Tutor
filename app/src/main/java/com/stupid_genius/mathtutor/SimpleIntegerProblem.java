package com.stupid_genius.mathtutor;

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

    public String toString() {
    	return String.format("%d %s %d =", firstNumber, operation.getOperator(), secondNumber);
	}
}
