package edu.cmu.cs.cs214.hw2.operator;
/**
 * Arithmetic negation Operator
 * @author Hubert-Acer
 */
public class NegationOperator implements UnaryOperator {

	@Override
	public double apply(double arg) {
		return -arg;
	}
	@Override
	public String toString(){
		return "Neg";
	}
}
