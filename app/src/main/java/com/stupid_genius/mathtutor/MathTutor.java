package com.stupid_genius.mathtutor;

import com.google.common.collect.Maps;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

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
		tutor.startSession(NumberEnum.FRACTION, OperationEnum.SUBTRACTION, 10, false);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				if (tutor.normalShutdown) {
					return;
				}
				tutor.killSession();
				System.out.println(tutor.toString());
			}
		});

		Map<String, Object> modelRoot = Maps.newHashMap();
		Configuration config = new Configuration();
		try {
			config.setDirectoryForTemplateLoading(new File("app/src/main/res/raw"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		config.setDefaultEncoding("UTF-8");
		config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		config.setLogTemplateExceptions(false);
		Template worksheetTemplate = null;
		try {
			worksheetTemplate = config.getTemplate("worksheet.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Writer out = new OutputStreamWriter(System.out);
		try {
			worksheetTemplate.process(modelRoot, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Welcome to MathTutor CLI!");
		Scanner stdin = new Scanner(System.in);
		while (tutor.hasNext()) {
			SimpleProblem problem = tutor.next();
			System.out.println(problem.toString());
//			int answer = stdin.nextInt();
			String input = stdin.nextLine();
			String[] ints = input.split("/");
			SimpleFraction answer;
			boolean isCorrect;
			if (ints.length == 2) {
				answer = new SimpleFraction(Integer.valueOf(ints[0]), Integer.valueOf(ints[1]));
				isCorrect = problem.checkAnswer(answer);
			} else {
				answer = new SimpleFraction(Integer.valueOf(ints[0]), 1);
				isCorrect = problem.checkAnswer(answer);
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
