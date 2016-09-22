package edu.cmu.cs.cs214.hw2.operator;
/**
 * Absolute  value Operator
 * @author Hubert-Acer
 */
public class AbsoluteValueOperator implements UnaryOperator{

	@Override
	public double apply(double arg) {
		return Math.abs(arg);
	}
	@Override
	public String toString(){
		return "Abs";
	}
}
