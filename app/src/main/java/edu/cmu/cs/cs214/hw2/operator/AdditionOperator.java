/**
 * 
 */
package edu.cmu.cs.cs214.hw2.operator;

/**
 * Arithmetic addition Operator
 * @author Hubert-Acer
 *
 */
public class AdditionOperator implements BinaryOperator {

	/**
	 * Applies addition on the two numbers given.
	 * 
	 * @param arg1 the first number before the operator
	 * @param arg2 the second number after the operator
	 * @return the output of the addition given inputs arg1 and arg2
	 */
	@Override
	public double apply(double arg1, double arg2) {
		return arg1 + arg2;
	}
	@Override
	public String toString(){
		return "+";
	}
}
