package com.stupid_genius.mathtutor;

import com.google.common.math.LongMath;

/**
 * Represents a fraction
 *
 * Mixed numbers are not supported; improper fractions are
 */
public class SimpleFraction extends Number {
    Integer numerator;
    Integer denominator;

	SimpleFraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public SimpleFraction add(SimpleFraction that) {
		int commonDenominator = denominator*that.denominator;
		int thisNumerator = numerator*that.denominator;
		int thatNumerator = that.numerator*denominator;
		return (new SimpleFraction(thisNumerator+thatNumerator, commonDenominator)).reduce();
	}

	public SimpleFraction subtract(SimpleFraction that) {
		int commonDenominator = denominator*that.denominator;
		int thisNumerator = numerator*that.denominator;
		int thatNumerator = that.numerator*denominator;
		return (new SimpleFraction(thisNumerator-thatNumerator, commonDenominator)).reduce();
	}

	public SimpleFraction multiply(SimpleFraction that) {
		return (new SimpleFraction(numerator*that.numerator, denominator*that.denominator)).reduce();
	}

	public SimpleFraction divide(SimpleFraction that) {
		SimpleFraction flip = new SimpleFraction(that.denominator, that.numerator);
		return multiply(flip).reduce();
	}

	@Override
	public boolean equals(Object that) {
		if (!(that instanceof SimpleFraction)) {
			return false;
		}
		return this.numerator==((SimpleFraction) that).numerator
			&& this.denominator==((SimpleFraction) that).denominator;
	}

	public boolean equivalent(SimpleFraction that) {
		return reduce().equals(that.reduce());
	}

	public SimpleFraction reduce(){
		long gcd = LongMath.gcd(numerator, denominator);
		return new SimpleFraction((int) (numerator / gcd), (int) (denominator / gcd));
	}

	public Integer getNumerator(){
		return numerator;
	}

	public Integer getDenominator(){
		return denominator;
	}

	public String toString() {
		return String.format("(%s / %s)", numerator, denominator);
	}

	@Override
	public int intValue() {
		return numerator/denominator;
	}

	@Override
	public long longValue() {
		return numerator/denominator;
	}

	@Override
	public float floatValue() {
		return (float)numerator / (float)denominator;
	}

	@Override
	public double doubleValue() {
		return (double)numerator / (double)denominator;
	}
}
