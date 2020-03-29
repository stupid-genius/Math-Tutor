package com.stupid_genius.mathtutor;

class ProblemFactory {
	private NumberEnum numberClass;
	private OperationEnum operation;
	private int level;
	private boolean allowNegatives;

	private OperationEnum[] ops = OperationEnum.values();
//	private Map<AbstractMap.SimpleEntry<NumberEnum, OperationEnum>, ?> problemClasses;

	private int problemsCreated = 0;

	ProblemFactory(NumberEnum num, OperationEnum op, int difficulty, boolean negatives) {
		numberClass = num;
		operation = op;
		level = difficulty;
		allowNegatives = negatives;
	}

	SimpleProblem getProblem() {
		++problemsCreated;
		SimpleProblem problem;

		switch (operation) {
			case ADDITION:
				switch (numberClass) {
					case INTEGER:
						problem = new SimpleIntegerAddition(level);
						break;
					case FRACTION:
						problem = new SimpleFractionAddition(level);
						break;
					default:
						throw new UnsupportedOperationException(String.format("Unrecognized number class: %s", numberClass));
				}
				break;
			case SUBTRACTION:
				if (allowNegatives) {
					switch (numberClass) {
						case INTEGER:
							problem = new SimpleIntegerSubraction(level);
							break;
						case FRACTION:
							problem = new SimpleFractionSubtraction(level);
							break;
						default:
							throw new UnsupportedOperationException(String.format("Unrecognized number class: %s", numberClass));
					}
				} else {
					switch (numberClass) {
						case INTEGER:
							problem = new SimpleNaturalSubtraction(level);
							break;
						case FRACTION:
							problem = new SimpleFractionSubtractionPositive(level);
							break;
						default:
							throw new UnsupportedOperationException(String.format("Unrecognized number class: %s", numberClass));
					}
				}
				break;
			case MULTIPLICATION:
				switch (numberClass) {
					case INTEGER:
						problem = new SimpleIntegerMultiplication(level);
						break;
					case FRACTION:
						problem = new SimpleFractionMultiplication(level);
						break;
					default:
						throw new UnsupportedOperationException(String.format("Unrecognized number class: %s", numberClass));
				}
				break;
			case DIVISION:
				switch (numberClass) {
					case INTEGER:
						problem = new SimpleIntegerDivision(level);
						break;
					case FRACTION:
						problem = new SimpleFractionDivision(level);
						break;
					default:
						throw new UnsupportedOperationException(String.format("Unrecognized number class: %s", numberClass));
				}
				break;
			case RANDOM:
				OperationEnum op = ops[(int) (Math.random() * (ops.length-1))];
				switch (op) {
					case ADDITION:
						if (NumberEnum.INTEGER.equals(numberClass)) {
							problem = new SimpleIntegerAddition(level);
						} else {
							problem = new SimpleFractionAddition(level);
						}
						break;
					case SUBTRACTION:
						if (allowNegatives) {
							if (NumberEnum.INTEGER.equals(numberClass)) {
								problem = new SimpleIntegerSubraction(level);
							} else{
								problem = new SimpleFractionSubtraction(level);
							}
						} else {
							if(NumberEnum.INTEGER.equals(numberClass)){
								problem = new SimpleNaturalSubtraction(level);
							} else {
								problem = new SimpleFractionSubtractionPositive(level);
							}
						}
						break;
					case MULTIPLICATION:
						if (NumberEnum.INTEGER.equals(numberClass)) {
							problem = new SimpleIntegerMultiplication(level);
						} else {
							problem = new SimpleFractionMultiplication(level);
						}
						break;
					case DIVISION:
						if (NumberEnum.INTEGER.equals(numberClass)) {
							problem = new SimpleIntegerDivision(level);
						} else{
							problem = new SimpleFractionDivision(level);
						}
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
