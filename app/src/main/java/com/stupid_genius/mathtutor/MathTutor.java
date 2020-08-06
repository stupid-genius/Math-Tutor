package com.stupid_genius.mathtutor;

import androidx.annotation.NonNull;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import freemarker.template.TemplateException;

public class MathTutor implements Iterable<SimpleProblem> {
	private ProblemFactory problemFactory;
	private int numCorrect;
	private boolean normalShutdown = false;

	private WorksheetTemplateLoader template;

	/**
	 * Configurations:
	 * - number class
	 * - operation
	 * - difficulty level
	 * - allow negatives
	 * - allow improper fractions
	 *
	 * @param op
	 * @param difficulty
	 * @param allowNegatives
	 */
	public void startSession(NumberEnum num, OperationEnum op, int count, int difficulty, boolean allowNegatives, boolean allowImproper) {
		problemFactory = new ProblemFactory(num, op, count, difficulty, allowNegatives, allowImproper);
		numCorrect = 0;
	}

	public void killSession() {
	}

	@NonNull
	@Override
	public Iterator<SimpleProblem> iterator() {
		return problemFactory;
	}


	public boolean hasNext(){
		return problemFactory.hasNext();
	}

	public SimpleProblem next(){
		return problemFactory.next();
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
		return problemFactory.getSessionCreated();
	}

	public String getSessionStats(){
		StringBuilder report = new StringBuilder();
		report.append(problemFactory.toString());
		report.append(problemFactory.getSessionStats());
		report.append(String.format("\nNumber correct: %d", numCorrect));
		return report.toString();
	}

	public void setLoader(WorksheetTemplateLoader worksheetTemplateLoader){
		template = worksheetTemplateLoader;
	}

	public String toString() {
		if(template==null){
			throw new RuntimeException("no WorksheetTemplate");
		}
		Map<String, Object> modelRoot = Maps.newHashMap();
		List<Map<String, String>> problems = Lists.newArrayList();
		modelRoot.put("problems", problems);
		SimpleDateFormat format = new SimpleDateFormat("MMMM d, y");
		modelRoot.put("date", format.format(new Date()));

		for(SimpleProblem problem : this){
			problems.add(problem.getModel());
		}

		StringWriter out = new StringWriter();
		try {
			template.loadTemplate().process(modelRoot, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}
		return out.toString();
	}

	public static void main(String[] args) {
		final MathTutor tutor = new MathTutor();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				if (tutor.normalShutdown) {
					return;
				}
				tutor.killSession();
				System.out.println(tutor.getSessionStats());
			}
		});

		WorksheetTemplateLoader templateLoader = new CLIWorksheetTemplateLoader();
		templateLoader.setTemplate("fractionworksheet.html");
		tutor.setLoader(templateLoader);
		tutor.startSession(NumberEnum.Fraction, OperationEnum.Random, 25, 30, true, false);
		try (PrintStream ostrm = new PrintStream(new FileOutputStream("mathworksheet.html"))) {
			ostrm.print(tutor.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("Welcome to MathTutor CLI!");
		tutor.startSession(NumberEnum.Integer, OperationEnum.Random,0, 10, true, false);
		Scanner stdin = new Scanner(System.in);
		for(SimpleProblem problem : tutor){
			System.out.println(problem.toString());
			int answer = stdin.nextInt();
//			String input = stdin.nextLine();
//			String[] ints = input.split("/");
//			SimpleFraction answer;
			boolean isCorrect;
//			if (ints.length == 2) {
//				answer = new SimpleFraction(Integer.valueOf(ints[0]), Integer.valueOf(ints[1]));
//			} else {
//				answer = new SimpleFraction(Integer.valueOf(ints[0]), 1);
//			}
			isCorrect = problem.checkAnswer(answer);
			tutor.recordAnswer(isCorrect);
			if (isCorrect) {
				System.out.println("Correct!");
			} else {
				System.out.printf("Incorrect: %s %s\n", problem.toString(), answer);
			}
		}
		tutor.killSession();
		tutor.normalShutdown = true;
	}
}
