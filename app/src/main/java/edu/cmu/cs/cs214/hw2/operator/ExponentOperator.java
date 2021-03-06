package edu.cmu.cs.cs214.hw2.operator;
/**
 * Arithmetic exponentiation Operator
 * @author Hubert-Acer
 *
 */
public class ExponentOperator implements BinaryOperator {
	/**
	 * Applies exponentiation on the two numbers given.
	 * 
	 * @param arg1 the first number before the operator
	 * @param arg2 the second number after the operator
	 * @return arg1**arg2
	 */
	@Override
	public double apply(double arg1, double arg2) {
		return Math.pow(arg1, arg2);
	}
	@Override
	public String toString(){
		return "^";
	}
}
