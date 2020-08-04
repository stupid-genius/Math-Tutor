package com.stupid_genius.mathtutor;

import com.google.common.collect.Maps;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public enum NumberEnum {
	INTEGER(SimpleIntegerAddition.class, SimpleIntegerSubraction.class, SimpleIntegerMultiplication.class, SimpleIntegerDivision.class),
	FRACTION(SimpleFractionAddition.class, SimpleFractionSubtraction.class, SimpleFractionMultiplication.class, SimpleFractionDivision.class);
//	DECIMAL,
//	EXPONENT,

	Map<OperationEnum, Constructor<? extends SimpleProblem>> constructors = Maps.newHashMap();

	NumberEnum(Class<? extends SimpleProblem> additionClass,
			Class<? extends SimpleProblem> subtractionClass,
			Class<? extends SimpleProblem> multiplicationClass,
			Class<? extends SimpleProblem> divisionClass){
		try {
			constructors.put(OperationEnum.ADDITION, additionClass.getConstructor(Map.class));
			constructors.put(OperationEnum.SUBTRACTION, subtractionClass.getConstructor(Map.class));
			constructors.put(OperationEnum.MULTIPLICATION, multiplicationClass.getConstructor(Map.class));
			constructors.put(OperationEnum.DIVISION, divisionClass.getConstructor(Map.class));
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
}
