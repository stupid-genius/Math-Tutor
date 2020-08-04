package com.stupid_genius.mathtutor;

public interface SimpleProblem {
	Number getFirstNumber();
	Number getSecondNumber();
	String getOperator();
	Number getAnswer();
	boolean checkAnswer(Number input);
}
