package edu.cmu.cs.cs214.hw2.operator;
/**
 * Arithmetic multiplication Operator
 * @author Hubert-Acer
 *
 */
public class MultiplicationOperator implements BinaryOperator {

	@Override
	public double apply(double arg1, double arg2) {
		return arg1 * arg2;
	}
	@Override
	public String toString(){
		return "\u00D7";
	}
}
