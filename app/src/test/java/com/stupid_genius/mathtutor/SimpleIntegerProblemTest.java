package com.stupid_genius.mathtutor;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SimpleIntegerProblemTest {
	@Test
	public void addition() {
		SimpleIntegerProblem problem = new SimpleIntegerAddition(1000);
		Integer a = problem.getFirstNumber();
		Integer b = problem.getSecondNumber();
		assertTrue(problem.checkAnswer(a + b));
	}

	@Test
	public void subtraction() {
		SimpleIntegerProblem problem = new SimpleIntegerSubraction(1000);
		Integer a = problem.getFirstNumber();
		Integer b = problem.getSecondNumber();
		assertTrue(problem.checkAnswer(a-b));
	}

	@Test
	public void multiplication() {
		SimpleIntegerProblem problem = new SimpleIntegerMultiplication(1000);
		Integer a = problem.getFirstNumber();
		Integer b = problem.getSecondNumber();
		assertTrue(problem.checkAnswer(a*b));
	}

	@Test
	public void division() {
		SimpleIntegerProblem problem = new SimpleIntegerDivision(1000);
		Integer a = problem.getFirstNumber();
		Integer b = problem.getSecondNumber();
		assertTrue(problem.checkAnswer(a/b));
	}
}
