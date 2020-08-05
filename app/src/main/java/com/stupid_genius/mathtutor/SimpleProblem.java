package com.stupid_genius.mathtutor;

import java.util.Map;

public interface SimpleProblem {
	Number getFirstNumber();
	Number getSecondNumber();
	String getOperator();
	Number getAnswer();
	boolean checkAnswer(Number input);
	Map<String, String> getModel();
}
