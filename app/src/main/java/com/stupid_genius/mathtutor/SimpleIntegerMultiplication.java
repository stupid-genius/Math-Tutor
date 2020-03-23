package com.stupid_genius.mathtutor;

class SimpleIntegerMultiplication extends SimpleInteger {
	SimpleIntegerMultiplication(int level) {
		firstNumber = (int) (Math.random() * level);
		secondNumber = (int) (Math.random() * level);
	}

	public String getOperator(){
		return "*";
	}

	@Override
	public boolean checkAnswer(int input) {
		return (firstNumber * secondNumber) == input;
	}

	public String toString() {
		return String.format("%d * %d = ", firstNumber, secondNumber);
	}
}
