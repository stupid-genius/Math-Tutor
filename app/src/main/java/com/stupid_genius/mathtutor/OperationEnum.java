package com.stupid_genius.mathtutor;

public enum OperationEnum {
    ADDITION("+"), SUBTRACTION("-"), MULTIPLICATION("*"), DIVISION("/"), RANDOM("");

	private String operator;

    OperationEnum(String operator) {
    	this.operator = operator;
	}

	public String getOperator() {
    	return operator;
	}
}
