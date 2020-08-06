package com.stupid_genius.mathtutor;

public enum OperationEnum {
    Addition("+"), Subtraction("-"), Multiplication("×"), Division("÷"), Random("");

	private String operator;

    OperationEnum(String operator) {
    	this.operator = operator;
	}

	public String getOperator() {
    	return operator;
	}
}
