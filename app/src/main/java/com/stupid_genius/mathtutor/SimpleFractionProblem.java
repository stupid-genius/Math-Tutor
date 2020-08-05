package com.stupid_genius.mathtutor;

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
		return null;
	}

	public String toString() {
		return String.format("%s %s %s =", firstNumber.toString(), operation.getOperator(), secondNumber.toString());
	}
}
