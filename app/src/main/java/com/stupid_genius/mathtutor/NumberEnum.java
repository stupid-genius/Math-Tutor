package com.stupid_genius.mathtutor;

import com.google.common.collect.Maps;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public enum NumberEnum {
	Integer(R.raw.worksheet, SimpleIntegerAddition.class, SimpleIntegerSubraction.class, SimpleIntegerMultiplication.class, SimpleIntegerDivision.class),
	Fraction(R.raw.fractionworksheet, SimpleFractionAddition.class, SimpleFractionSubtraction.class, SimpleFractionMultiplication.class, SimpleFractionDivision.class);
//	DECIMAL,
//	EXPONENT,

	private String resourceID;
	private Map<OperationEnum, Constructor<? extends SimpleProblem>> constructors = Maps.newHashMap();

	NumberEnum(int id,
			Class<? extends SimpleProblem> additionClass,
			Class<? extends SimpleProblem> subtractionClass,
			Class<? extends SimpleProblem> multiplicationClass,
			Class<? extends SimpleProblem> divisionClass){
		resourceID = String.valueOf(id);
		try {
			constructors.put(OperationEnum.Addition, additionClass.getConstructor(Map.class));
			constructors.put(OperationEnum.Subtraction, subtractionClass.getConstructor(Map.class));
			constructors.put(OperationEnum.Multiplication, multiplicationClass.getConstructor(Map.class));
			constructors.put(OperationEnum.Division, divisionClass.getConstructor(Map.class));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public SimpleProblem newProblem(OperationEnum op, Map config){
		try {
			return constructors.get(op).newInstance(config);
		} catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getResourceID(){
		return resourceID;
	}
}
