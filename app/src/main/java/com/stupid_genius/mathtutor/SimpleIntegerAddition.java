package com.stupid_genius.mathtutor;

public class SimpleIntegerAddition extends SimpleInteger {
	SimpleIntegerAddition(int level) {
		firstNumber = (int) (Math.random() * level);
		secondNumber = (int) (Math.random() * level);
	}

	@Override
	public boolean checkAnswer(int input) {
		return (firstNumber + secondNumber) == input;
	}

	public String toString() {
		return String.format("%d + %d =", firstNumber, secondNumber);
	}
}
