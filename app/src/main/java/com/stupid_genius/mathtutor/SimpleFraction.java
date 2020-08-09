package com.stupid_genius.mathtutor;

import com.google.common.math.LongMath;

/**
 * Represents a fraction: a number which can be expressed as the ratio of two integers
 */
public class SimpleFraction extends Number implements Comparable<SimpleFraction>{
	private Integer numerator;
	private Integer denominator;
	private static final int precision = 2;

	SimpleFraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	SimpleFraction(double decimal){
		int whole = (int) decimal;
		decimal -= whole;
		int digits = String.valueOf(decimal).length()-2;
		if(digits>SimpleFraction.precision){
			digits = SimpleFraction.precision;
		}
		denominator = Double.valueOf(Math.pow(10, digits)).intValue();
		int fraction = Double.valueOf(decimal * denominator).intValue();
		numerator = (whole*denominator)+fraction;
	}

	public SimpleFraction add(SimpleFraction that) {
		int commonDenominator = denominator * that.denominator;
		int thisNumerator = numerator * that.denominator;
		int thatNumerator = that.numerator * denominator;
		return (new SimpleFraction(thisNumerator + thatNumerator, commonDenominator)).reduce();
	}

	public SimpleFraction subtract(SimpleFraction that) {
		int commonDenominator = denominator * that.denominator;
		int thisNumerator = numerator * that.denominator;
		int thatNumerator = that.numerator * denominator;
		return (new SimpleFraction(thisNumerator - thatNumerator, commonDenominator)).reduce();
	}

	public SimpleFraction multiply(SimpleFraction that) {
		return (new SimpleFraction(numerator * that.numerator, denominator * that.denominator)).reduce();
	}

	public SimpleFraction divide(SimpleFraction that) {
		SimpleFraction flip = new SimpleFraction(that.denominator, that.numerator);
		return multiply(flip).reduce();
	}

	@Override
	public int compareTo(SimpleFraction o) {
		int result;
		if(this.equivalent(o)){
			result = 0;
		}else {
			result = this.doubleValue() < o.doubleValue() ? -1 : 1;
		}
		return result;
	}

	@Override
	public boolean equals(Object that) {
		if (!(that instanceof SimpleFraction)) {
			return false;
		}
		return this.numerator.equals(((SimpleFraction) that).numerator)
			&& this.denominator.equals(((SimpleFraction) that).denominator);
	}

	public boolean equivalent(SimpleFraction that) {
		return reduce().equals(that.reduce());
	}

	/**
	 * reduce() potentially changes the numerator and denominator (compare with getCanonical)
	 * @return a new, cannoncialized SimpleFraction with numerator and denominator reduced
	 */
	public SimpleFraction reduce() {
		if (denominator == 0) {
			throw new ArithmeticException("denominator cannot be zero");
		}
		if (numerator == 0) {
			return new SimpleFraction(0,1);
		}
		long gcd = LongMath.gcd(Math.abs(numerator), Math.abs(denominator));
		return new SimpleFraction((int) (numerator / gcd), (int) (denominator / gcd)).getCanonical();
	}

	public boolean isReduced() {
		return reduce().equals(this);
	}

	public boolean isWholeNumber() {
		return denominator.equals(1);
	}

	public boolean isProper(){
		return Math.abs(numerator)<Math.abs(denominator);
	}

	public boolean isNegative() {
		return numerator < 0 || denominator < 0;
	}

	public Integer getNumerator() {
		return numerator;
	}

	public Integer getDenominator() {
		return denominator;
	}

	/**
	 * getCanonical() does not change the absolute value of the numerator or denominator
	 * @return a new SimpleFraction that has at most one negative value, and that in the numerator
	 */
	public SimpleFraction getCanonical(){
		Integer canonicalNumerator = numerator;
		Integer canonicalDenominator = denominator;
		if(canonicalDenominator < 0){	// (numerator<0 && denominator<0) || denominator<0
			canonicalNumerator *= -1;
			canonicalDenominator *= -1;
		}
		return new SimpleFraction(canonicalNumerator, canonicalDenominator);
	}

	public String toString() {
		String s;
		if (isWholeNumber()) {
			s = String.format("%d", numerator);
		} else {
			s = String.format("(%d / %d)", numerator, denominator);
		}
		return s;
	}

	/**
	 * Hack alert
	 * if int (or long) equals float (or doubld) then this fraction is equivalent to a whole number because this
	 * indicates that the denominator is 1
	 *
	 * @return the numerator of the fraction
	 */
	@Override
	public int intValue() {
		return numerator;
	}

	@Override
	public long longValue() {
		return numerator;
	}

	/**
	 * @return the quotient of the number
	 */
	@Override
	public float floatValue() {
		return (float) numerator / (float) denominator;
	}

	/**
	 * @return the quotient of the number
	 */
	@Override
	public double doubleValue() {
		return (double) numerator / (double) denominator;
	}
}
