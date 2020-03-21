package com.stupid_genius.mathtutor;

public class SimpleNaturalSubtraction extends SimpleInteger {
	public SimpleNaturalSubtraction(int level) {
		firstNumber = (int) (Math.random() * level);
		secondNumber = (int) (Math.random() * level);
		if (firstNumber < secondNumber) {
			Integer temp = firstNumber;
			firstNumber = secondNumber;
			secondNumber = temp;
		}
	}

	@Override
	public boolean checkAnswer(int input) {
		return (firstNumber - secondNumber) == input;
	}

	public String toString() {
		return String.format("%d - %d = ", firstNumber, secondNumber);
	}
}
