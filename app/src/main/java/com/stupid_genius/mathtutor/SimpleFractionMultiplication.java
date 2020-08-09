package com.stupid_genius.mathtutor;

import java.util.Map;

public class SimpleFractionMultiplication extends SimpleFractionProblem {
	public SimpleFractionMultiplication(SimpleFraction first, SimpleFraction second) {
		firstNumber = first;
		secondNumber = second;
		operation = OperationEnum.Multiplication;
	}

	public SimpleFractionMultiplication(Map<MathTutorConfiguration, String> config) {
		int level = Integer.parseInt(config.get(MathTutorConfiguration.LEVEL));
		boolean allowNegative = Boolean.parseBoolean(config.get(MathTutorConfiguration.NEGATIVE));
		boolean allowImproper = Boolean.parseBoolean(config.get(MathTutorConfiguration.IMPROPER));
		if(allowImproper){
			if(allowNegative){	// improper with negatives
				int numerator = random(-level, level);
				int denominator;
				do{
					denominator = random(-level, level);
				}while(denominator==0);
				if(numerator<0 && denominator<0){
					numerator *= -1;
					denominator *= -1;
				}
				secondNumber = new SimpleFraction(numerator, denominator);
				numerator = random(-level, level);
				do{
					denominator = random(-level, level);
				}while(denominator==0);
				firstNumber = new SimpleFraction(numerator, denominator);
			}else{	// improper no negatives
				secondNumber = new SimpleFraction(random(0, level), random(1, level));
				firstNumber = new SimpleFraction(random(0, level), random(1, level));
			}
		}else{
			if(allowNegative){	// proper with negatives
				int numerator = random((int)(.66*-level), (int)(.66*level));
				int denominator = random((int)(.66*-level), (int)(.66*level));
				if(denominator<0){
					denominator += numerator<0 ? numerator : -numerator;
				}else{
					denominator += numerator>0 ? numerator : -numerator;
				}
				if(denominator==0){
					denominator = 1;
				}
				if(numerator<0 && denominator<0){
					numerator *= -1;
					denominator *= -1;
				}
				secondNumber = new SimpleFraction(numerator, denominator);

				numerator = random((int)(.66*-level), (int)(.66*level));
				do{
					denominator = random((int)(.66*-level), (int)(.66*level))+numerator;
				}while(denominator==0);
				if(denominator<0){
					denominator += numerator<0 ? numerator : -numerator;
				}else{
					denominator += numerator>0 ? numerator : -numerator;
				}
				if(denominator==0){
					denominator = 1;
				}
				if(numerator<0 && denominator<0){
					numerator *= -1;
					denominator *= -1;
				}
				firstNumber = new SimpleFraction(numerator, denominator);
			}else{	// proper no negatives
				int numerator = random(0, (int)(.66*level));
				int denominator = random(1, (int)(.66*level))+numerator;
				secondNumber = new SimpleFraction(numerator, denominator);
				numerator = random(0, (int)(.66*level));
				denominator = random(1, (int)(.66*level))+numerator;
				firstNumber = new SimpleFraction(numerator, denominator);
			}
		}
		operation = OperationEnum.Multiplication;
	}

	@Override
	public Number getAnswer(){
		return firstNumber.multiply(secondNumber);
	}
}
