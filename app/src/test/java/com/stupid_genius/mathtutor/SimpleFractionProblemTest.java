package com.stupid_genius.mathtutor;

import com.google.common.collect.Maps;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class SimpleFractionProblemTest {
	@Test
	public void addition() {
		SimpleFraction a = new SimpleFraction(1,2);
		SimpleFraction b = new SimpleFraction(3,2);
		SimpleFraction expected = new SimpleFraction(2,1);
		SimpleFractionProblem problem = new SimpleFractionAddition(a, b);
		assertTrue(problem.checkAnswer(expected));
	}

	@Test
	public void subtraction() {
		SimpleFraction a = new SimpleFraction(4,2);
		SimpleFraction b = new SimpleFraction(3,2);
		SimpleFraction expected = new SimpleFraction(1,2);
		SimpleFractionProblem problem = new SimpleFractionSubtraction(a, b);
		assertTrue(problem.checkAnswer(expected));

		a = new SimpleFraction(3,2);
		b = new SimpleFraction(4,2);
		expected = new SimpleFraction(-1,2);
		problem = new SimpleFractionSubtraction(a, b);
		assertTrue(problem.checkAnswer(expected));

		a = new SimpleFraction(5, 5);
		b = new SimpleFraction(0, 4);
		expected = new SimpleFraction( 1,1);
		problem = new SimpleFractionSubtraction(a, b);
		assertTrue(problem.checkAnswer(expected));
	}

	@Test
	public void multiplication() {
		SimpleFraction a = new SimpleFraction(0, 8);
		SimpleFraction b = new SimpleFraction(2, 2);
		SimpleFraction expected = new SimpleFraction(0, 1);
		SimpleFractionProblem problem = new SimpleFractionMultiplication(a, b);
		assertTrue(problem.checkAnswer(expected));
	}

	@Test
	public void division() {
		SimpleFraction a = new SimpleFraction(2, 3);
		SimpleFraction b = new SimpleFraction(4, 5);
		SimpleFraction expected = new SimpleFraction(5, 6);
		SimpleFractionProblem problem = new SimpleFractionDivision(a, b);
		assertTrue(problem.checkAnswer(expected));
	}

	@Test
	public void fuzz() {
		Map<MathTutorConfiguration, String> config = Maps.newHashMap();
		config.put(MathTutorConfiguration.LEVEL, "100");

		for (int i=0; i<100; ++i) {
			SimpleFractionAddition problem = new SimpleFractionAddition(config);
			SimpleFraction a = problem.getFirstNumber();
			SimpleFraction b = problem.getSecondNumber();
			assertTrue(problem.checkAnswer(a.add(b)));
		}
		for (int i=0; i<100; ++i) {
			SimpleFractionSubtraction problem = new SimpleFractionSubtraction(config);
			SimpleFraction a = problem.getFirstNumber();
			SimpleFraction b = problem.getSecondNumber();
			assertTrue(problem.checkAnswer(a.subtract(b)));
		}
		for (int i=0; i<100; ++i) {
			SimpleFractionMultiplication problem = new SimpleFractionMultiplication(config);
			SimpleFraction a = problem.getFirstNumber();
			SimpleFraction b = problem.getSecondNumber();
			assertTrue(problem.checkAnswer(a.multiply(b)));
		}
		for (int i=0; i<100; ++i) {
			SimpleFractionDivision problem = new SimpleFractionDivision(config);
			SimpleFraction a = problem.getFirstNumber();
			SimpleFraction b = problem.getSecondNumber();
			assertTrue(problem.checkAnswer(a.divide(b)));
		}
	}
}
