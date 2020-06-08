package ua.com.foxminded;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

import java.util.ArrayList;

public class ColumnDivision {
	
	
	public DivisionData divide(int dividend, int divisor) {
		
		validateDivisor(divisor);

		DivisionData output = 
				new DivisionData(
						valueOf(dividend).toString(), 
						valueOf(divisor).toString()
						);
		
		String result = Integer.toString(dividend / divisor);
		output.setResult(result);
		
		String remainder = Integer.toString(Math.abs(dividend % divisor));
		output.setRemainder(remainder);
		
		ArrayList<Integer> dividendDigits = tokenizeNumber(dividend);
		
		String minuend = "";
		int subtrahend = 0;
			
		for (Integer dividendDigit : dividendDigits) {
			minuend += dividendDigit;
			subtrahend = getPartialQuotient(parseInt(minuend), divisor);
			
			if (subtrahend/divisor == 0) {
				continue;
			}
			
			output.setNextOperand(minuend);
			output.setNextOperand(Integer.toString(subtrahend));
			
			int difference = parseInt(minuend) - subtrahend;
			
			if (difference != 0) {
				minuend = "" + difference;
			} else {
				minuend = "";
			}
		}

		return output;
	}
	
	
	private void validateDivisor (int divisor) throws IllegalArgumentException {
		if (divisor == 0) {
			throw new IllegalArgumentException("divisor can't be zero");
		}
	}
	
	
	private ArrayList<Integer> tokenizeNumber(int input) {
		ArrayList<Integer> outputList = new ArrayList<>();
		String inputAsString = valueOf(input).toString();
		inputAsString = inputAsString.replace("-", "");
		
		for (int i = 0; i < inputAsString.length(); i++) {
			outputList.add(parseInt(inputAsString.substring(i, i + 1)));
		}
		
		return outputList;
	}
	
	
	private int getPartialQuotient(int divident, int divisor) {
		return (int) divident - (divident % divisor);
	}
}


