package com.stupid_genius.mathtutor;

import java.util.Iterator;
import java.util.Scanner;

public class MathTutor implements Iterator<SimpleProblem> {
	private ProblemFactory problemFactory;
	private int numCorrect;
	private boolean normalShutdown = false;

//	private int sessionCount;

	/**
	 * Configurations:
	 * - number class
	 * - operation
	 * - difficulty level
	 * - allow negatives
	 * - allow impromper fractions
	 *
	 * @param op
	 * @param difficulty
	 * @param allowNegatives
	 */
	public void startSession(NumberEnum num, OperationEnum op, int difficulty, boolean allowNegatives) {
		problemFactory = new ProblemFactory(num, op, difficulty, allowNegatives);
		numCorrect = 0;
//		sessionCount = 0;
	}

	public void killSession() {

	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public SimpleProblem next() {
		return problemFactory.getProblem();
	}

	public void recordAnswer(boolean isCorrect) {
		if (isCorrect) {
			++numCorrect;
		}
	}

	public int getCorrectProblems() {
		return numCorrect;
	}

	public int getTotalProblems() {
		return problemFactory.getProblemsCreated();
	}

	public String toString() {
		StringBuilder report = new StringBuilder();
		report.append(problemFactory.toString());
		report.append(String.format("\nNumber correct: %d", numCorrect));
		return report.toString();
	}

	public static void main(String[] args) {
		final MathTutor tutor = new MathTutor();
		tutor.startSession(NumberEnum.FRACTION, OperationEnum.RANDOM, 10, true);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				if (tutor.normalShutdown) {
					return;
				}
				tutor.killSession();
				System.out.println(tutor.toString());
			}
		});

		System.out.println("Welcome to MathTutor CLI!");
		Scanner stdin = new Scanner(System.in);
		while (tutor.hasNext()) {
			SimpleProblem problem = tutor.next();
			System.out.println(problem.toString());
//			int answer = stdin.nextInt();
			String input  = stdin.nextLine();
			String[] ints = input.split("/");
			SimpleFraction answer;
			boolean isCorrect;
			if (ints.length == 2) {
				answer = new SimpleFraction(Integer.valueOf(ints[0]), Integer.valueOf(ints[1]));
				isCorrect = problem.checkAnswer(answer);
			} else {
				answer = new SimpleFraction(Integer.valueOf(ints[0]),1);
				isCorrect = false;
			}
			tutor.recordAnswer(isCorrect);
			if (isCorrect) {
				System.out.println("Correct!");
			} else {
				System.out.printf("Incorrect: %s %s\n", problem.toString(), answer.toString());
			}
		}
		tutor.killSession();
		System.out.println(tutor.toString());
		tutor.normalShutdown = true;
	}
}
