package ua.com.foxminded;

import java.util.ArrayList;

class DivisionData {
	
	private String dividend;
	private String divisor;
	private String result;
	private String remainder;
	private String dividendPrefix = " _";
	
	private ArrayList<String> negationOperands = new ArrayList<>();

	DivisionData (String dividend, String divisor) {
		this.dividend = dividend;
		this.divisor = divisor;
	}
	
	String operandAt(int index) {
		return negationOperands.get(index);
	}
	
	void setNextOperand(String operand) {
		negationOperands.add(operand);
	}
	
	int getNumberOfOperands() {
		return negationOperands.size();
	}
	
	String getResult() {
		return result;
	}

	void setResult(String result) {
		this.result = result;
	}

	String getRemainder() {
		return remainder;
	}

	void setRemainder(String remainder) {
		this.remainder = remainder;
	}

	String getDividend() {
		return dividend;
	}

	String getDivisor() {
		return divisor;
	}

	String getDividendPrefix() {
		return dividendPrefix;
	}
	
	@Override
	public String toString() {
		StringBuilder representation = new StringBuilder();
		representation.append(this.dividend + " " + this.divisor + " ");
		
		for (String operand : negationOperands) {
			representation.append(operand + " ");
		}
		
		representation.append(this.remainder + " " + this.result);
		
		return representation.toString();
	}
}
