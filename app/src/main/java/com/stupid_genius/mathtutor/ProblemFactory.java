package com.stupid_genius.mathtutor;

import com.google.common.collect.Maps;

import java.util.Iterator;
import java.util.Map;

class ProblemFactory implements Iterator<SimpleProblem>{
	private NumberEnum numberClass;
	private OperationEnum operation;
	private Map config = Maps.newHashMap();
	private int sessionCount;
	private int sessionCreated = 0;

	private OperationEnum[] ops = OperationEnum.values();

	ProblemFactory(NumberEnum num, OperationEnum op, int count, int difficulty, boolean negatives, boolean improper) {
		numberClass = num;
		operation = op;
		sessionCount = count;
		config.put(MathTutorConfiguration.LEVEL, String.valueOf(difficulty));
		config.put(MathTutorConfiguration.NEGATIVE, String.valueOf(negatives));
		config.put(MathTutorConfiguration.IMPROPER, String.valueOf(improper));
	}

	@Override
	public boolean hasNext() {
		return sessionCount==0 || sessionCreated < sessionCount;
	}

	@Override
	public SimpleProblem next() {
		++sessionCreated;
		OperationEnum op = operation;
		if(OperationEnum.RANDOM.equals(operation)){
			op = ops[(int)(Math.random()*(ops.length-1))];
		}
		return numberClass.newProblem(op, config);
	}

	public int getSessionCreated() {
		return sessionCreated;
	}

	public String getSessionStats(){
		String batchSession = sessionCount>0?"\nsession max: %d":"";
		return String.format("Session:\nproblems created: %d%s", sessionCreated, batchSession);
	}

	public String toString() {
		return String.format("Problem factory:\n%s\n%s\n%s", numberClass, operation, config);
	}
}
