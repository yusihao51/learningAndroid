package edu.cmu.cs.cs214.hw2.operator;
/**
 * Arithmetic division Operator
 * @author Hubert-Acer
 *
 */
public class DivisionOperator implements BinaryOperator {
	/**
	 * Applies divisions on the two numbers given.
	 * 
	 * @param arg1 the first number before the operator
	 * @param arg2 the second number after the operator
	 * @return arg1 / arg2
	 */
	@Override
	public double apply(double arg1, double arg2) {
		return arg1 / arg2;
	}
	@Override
	public String toString(){
		return "\u00F7";
	}
}
