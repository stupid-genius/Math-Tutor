package com.stupid_genius.mathtutor;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
		SimpleFraction minuend = new SimpleFraction(1, 2);
		SimpleFraction subtrahend = new SimpleFraction(3, 4);
		SimpleFraction expected = new SimpleFraction(-1, 4);
		assertEquals(expected, minuend.subtract(subtrahend));
		assertTrue(expected.isNegative());
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

		fraction = new SimpleFraction(9, 3);
		reduced = fraction.reduce();
		assertFalse(fraction.equals(reduced));
	}

	@Test
	public void equivalent() {
		SimpleFraction fraction = new SimpleFraction(2, 4);
		SimpleFraction reduced = new SimpleFraction(1, 2);
		assertTrue(fraction.equivalent(reduced));

		fraction = new SimpleFraction(9, 3);
		reduced = fraction.reduce();
		assertTrue(fraction.equivalent(reduced));
	}

	@Test
	public void reduce() {
		SimpleFraction fraction = new SimpleFraction(2, 4);
		assertFalse(fraction.isReduced());
		SimpleFraction reduced = fraction.reduce();
		assertTrue(reduced.isReduced());
		SimpleFraction expected = new SimpleFraction(1, 2);
		assertEquals(expected, reduced);

		fraction = new SimpleFraction(0, 2);
		reduced = fraction.reduce();
		expected = new SimpleFraction(0, 1);
		assertEquals(expected, reduced);

		fraction = new SimpleFraction(4, 2);
		reduced = fraction.reduce();
		expected = new SimpleFraction(2, 1);
		assertEquals(expected, reduced);

		fraction = new SimpleFraction(1, 1);
		reduced = fraction.reduce();
		expected = new SimpleFraction(1,1);
		assertEquals(expected, reduced);
	}

	@Test
	public void wholeNumbers() {
		for (int i = 1; i < 1000; ++i) {
			SimpleFraction fraction = new SimpleFraction(i, 1);
			assertTrue(fraction.isWholeNumber());
			assertTrue(fraction.intValue() == fraction.floatValue());
			assertTrue(fraction.intValue() == fraction.doubleValue());
			assertTrue(fraction.longValue() == fraction.floatValue());
			assertTrue(fraction.longValue() == fraction.doubleValue());
			fraction = new SimpleFraction(i, 2);
			assertFalse(fraction.isWholeNumber());
			assertFalse(fraction.intValue() == fraction.floatValue());
			assertFalse(fraction.intValue() == fraction.doubleValue());
			assertFalse(fraction.longValue() == fraction.floatValue());
			assertFalse(fraction.longValue() == fraction.doubleValue());
		}
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

	@Test
	public void cannonicalEqual(){
		SimpleFraction canonical = new SimpleFraction(-1, 2);
		SimpleFraction nonCannonical = new SimpleFraction(1, -2);
		assertTrue(canonical.equivalent(nonCannonical));
		assertFalse(canonical.equals(nonCannonical));
		assertTrue(canonical.equals(nonCannonical.getCanonical()));
	}

	public void createFromDecimal(){
		SimpleFraction a = new SimpleFraction(1, 2);
		SimpleFraction factor = new SimpleFraction(Math.random());
		SimpleFraction scaled = a.multiply(factor);
		System.out.println(a.reduce().toString());
		System.out.println(factor.toString());
		System.out.println(scaled.reduce().toString());
	}
}
