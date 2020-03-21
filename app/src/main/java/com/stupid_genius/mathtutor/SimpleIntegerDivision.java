package com.stupid_genius.mathtutor;

class SimpleIntegerDivision extends SimpleInteger {
	SimpleIntegerDivision(int level) {
		int factor = (int) (Math.random() * level) + 1;
		firstNumber = ((int) (Math.random() * level) + 1) * factor;
		secondNumber = factor;
	}

	@Override
	public boolean checkAnswer(int input) {
		return (firstNumber / secondNumber) == input;
	}

	public String toString() {
		return String.format("%d / %d = ", firstNumber, secondNumber);
	}
}
