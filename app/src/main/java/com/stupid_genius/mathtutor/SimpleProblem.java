package com.stupid_genius.mathtutor;

public interface SimpleProblem {
	Number getFirstNumber();
	Number getSecondNumber();
	String getOperator();
    boolean checkAnswer(int input);
}
