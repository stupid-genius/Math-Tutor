package com.stupid_genius.mathtutor;

class ProblemFactory {
	private OperationEnum operation;
	private int level;
	private boolean allowNegatives;

	private OperationEnum[] ops = OperationEnum.values();

	private int problemsCreated = 0;

	ProblemFactory(OperationEnum op, int difficulty, boolean negatives) {
		operation = op;
		level = difficulty;
		allowNegatives = negatives;
	}

	SimpleProblem getProblem() {
		++problemsCreated;
		SimpleProblem problem;

		switch (operation) {
			case ADDITION:
				problem = new SimpleIntegerAddition(level);
				break;
			case SUBTRACTION:
				if (allowNegatives) {
					problem = new SimpleIntegerSubraction(level);
				} else {
					problem = new SimpleNaturalSubtraction(level);
				}
				break;
			case MULTIPLICATION:
				problem = new SimpleIntegerMultiplication(level);
				break;
			case DIVISION:
				problem = new SimpleIntegerDivision(level);
				break;
			case RANDOM:
				OperationEnum op = ops[(int) (Math.random() * (ops.length-1))];
				switch (op) {
					case ADDITION:
						problem = new SimpleIntegerAddition(level);
						break;
					case SUBTRACTION:
						if (allowNegatives) {
							problem = new SimpleIntegerSubraction(level);
						} else {
							problem = new SimpleNaturalSubtraction(level);
						}
						break;
					case MULTIPLICATION:
						problem = new SimpleIntegerMultiplication(level);
						break;
					case DIVISION:
						problem = new SimpleIntegerDivision(level);
						break;
					default:
						throw new UnsupportedOperationException(String.format("Unrecognized operation: %s", op));
				}
				break;
			default:
				throw new UnsupportedOperationException(String.format("Unrecognized operation: %s", operation));
		}
		return problem;
	}

	int getProblemsCreated() {
		return problemsCreated;
	}

	public String toString() {
		return String.format("Total problems: %d", problemsCreated - 1);
	}
}
