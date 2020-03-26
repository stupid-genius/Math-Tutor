package com.stupid_genius.mathtutor;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleFractionTest {

	@Test
	public void add() {
		SimpleFraction addend1 = new SimpleFraction(1, 2);
		SimpleFraction addend2 = new SimpleFraction(3, 4);
		SimpleFraction expected = new SimpleFraction(5, 4);
		assertEquals(expected, addend1.add(addend2));
	}

	@Test
	public void subtract() {
		SimpleFraction minuend = new SimpleFraction(3, 4);
		SimpleFraction subtrahend = new SimpleFraction(1, 2);
		SimpleFraction expected = new SimpleFraction(1, 4);
		assertEquals(expected, minuend.subtract(subtrahend));
	}

	@Test
	public void multiply() {
		SimpleFraction factor1 = new SimpleFraction(1, 2);
		SimpleFraction factor2 = new SimpleFraction(3, 4);
		SimpleFraction expected = new SimpleFraction(3, 8);
		assertEquals(expected, factor1.multiply(factor2));
	}

	@Test
	public void divide() {
		SimpleFraction dividend = new SimpleFraction(1, 2);
		SimpleFraction divisor = new SimpleFraction(3, 4);
		SimpleFraction expected = new SimpleFraction(2, 3);
		assertEquals(expected, dividend.divide(divisor));
	}

	@Test
	public void equals() {
		SimpleFraction fraction = new SimpleFraction(2, 4);
		SimpleFraction reduced = new SimpleFraction(1, 2);
		assertFalse(fraction.equals(reduced));

		fraction = new SimpleFraction(0, 2);
		reduced = fraction.reduce();
		assertFalse(fraction.equals(reduced));
	}

	@Test
	public void equivalent() {
		SimpleFraction fraction = new SimpleFraction(2, 4);
		SimpleFraction reduced = new SimpleFraction(1, 2);
		assert (fraction.equivalent(reduced));

		fraction = new SimpleFraction(0, 2);
		reduced = fraction.reduce();
		assert (fraction.equivalent(reduced));
	}

	@Test
	public void reduce() {
		SimpleFraction fraction = new SimpleFraction(2, 4);
		SimpleFraction reduced = fraction.reduce();
		SimpleFraction expected = new SimpleFraction(1, 2);
		assertEquals(expected, reduced);

		fraction = new SimpleFraction(0, 2);
		reduced = fraction.reduce();
		expected = new SimpleFraction(0, 1);
		assertEquals(expected, reduced);
	}

	@Test
	public void floatValue() {
		SimpleFraction fraction = new SimpleFraction(1, 2);
		assertEquals(0.5F, fraction.floatValue(), 0);

		fraction = new SimpleFraction(0, 2);
		assertEquals(0F, fraction.floatValue(), 0);
	}

	@Test
	public void doubleValue() {
		SimpleFraction fraction = new SimpleFraction(1, 2);
		assertEquals(0.5, fraction.doubleValue(), 0);

		fraction = new SimpleFraction(0, 2);
		assertEquals(0.0, fraction.doubleValue(), 0);
	}
}
