package com.stupid_genius.mathtutor;

import com.google.common.collect.Maps;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class SimpleIntegerProblemTest {
	static Map<MathTutorConfiguration, String> config = Maps.newHashMap();

	@BeforeClass
	public static void setupConfig(){
		config.put(MathTutorConfiguration.LEVEL, "100");
	}

	@Test
	public void addition() {
		SimpleIntegerProblem problem = new SimpleIntegerAddition(config);
		Integer a = problem.getFirstNumber();
		Integer b = problem.getSecondNumber();
		assertTrue(problem.checkAnswer(a + b));
	}

	@Test
	public void subtraction() {
		SimpleIntegerProblem problem = new SimpleIntegerSubraction(config);
		Integer a = problem.getFirstNumber();
		Integer b = problem.getSecondNumber();
		assertTrue(problem.checkAnswer(a-b));
	}

	@Test
	public void multiplication() {
		SimpleIntegerProblem problem = new SimpleIntegerMultiplication(config);
		Integer a = problem.getFirstNumber();
		Integer b = problem.getSecondNumber();
		assertTrue(problem.checkAnswer(a*b));
	}

	@Test
	public void division() {
		SimpleIntegerProblem problem = new SimpleIntegerDivision(config);
		Integer a = problem.getFirstNumber();
		Integer b = problem.getSecondNumber();
		assertTrue(problem.checkAnswer(a/b));
	}
}
