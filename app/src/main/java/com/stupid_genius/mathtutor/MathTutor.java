package com.stupid_genius.mathtutor;

import androidx.annotation.NonNull;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.File;
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

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class MathTutor implements Iterable<SimpleProblem> {
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
		report.append(problemFactory.getSessionStats());
		report.append(String.format("\nNumber correct: %d", numCorrect));
		return report.toString();
	}

	public String toString() {
		return problemFactory.toString();
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

		Map<String, Object> modelRoot = Maps.newHashMap();
		List<Map<String, String>> problems = Lists.newArrayList();
		modelRoot.put("problems", problems);
		SimpleDateFormat format = new SimpleDateFormat("MMMM d, y");
		modelRoot.put("date", format.format(new Date()));

		tutor.startSession(NumberEnum.INTEGER, OperationEnum.RANDOM,25, 10, false, false);
		for(SimpleProblem problem : tutor){
			Map<String, String> newProblem = Maps.newHashMap();
			newProblem.put("firstNum", String.valueOf(problem.getFirstNumber()));
			newProblem.put("secNum", String.valueOf(problem.getSecondNumber()));
			newProblem.put("op", problem.getOperator());
			newProblem.put("answer", String.valueOf(problem.getAnswer()));
			problems.add(newProblem);
		}

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
		StringWriter out = new StringWriter();
		try {
			worksheetTemplate.process(modelRoot, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (PrintStream ostrm = new PrintStream(new FileOutputStream("mathworksheet.html"))) {
			ostrm.print(out.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("Welcome to MathTutor CLI!");
		tutor.startSession(NumberEnum.INTEGER, OperationEnum.RANDOM,0, 10, false, false);
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
		System.out.println(tutor.toString());
		tutor.normalShutdown = true;
	}
}
