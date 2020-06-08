package ua.com.foxminded;

import static java.lang.Integer.parseInt;

public class OutputFormatter {
	
	
	String format(DivisionData input) {
		
		StringBuilder outputBuilder = new StringBuilder();
		
		outputBuilder.append(buildBoilerplateLines(input));
		outputBuilder.append(buildInnerLines(input));
		outputBuilder.append(buildRemainderLine(input));
		
		return outputBuilder.toString();
	}
	
	
	private String buildBoilerplateLines(DivisionData input) {
		
		StringBuilder outputBuilder = new StringBuilder();
		
		String dividend = input.getDividend();
		String divisor = input.getDivisor();
		
		String subtrahend;
		if (input.getNumberOfOperands() == 0) {
			subtrahend = "0";
		} else {
			subtrahend = input.operandAt(1);
		}
		
		String dividendPrefix = input.getDividendPrefix();

		int dividendLength = dividend.length();
		int subtrahendLength = subtrahend.length();
		int leftIndent = dividendPrefix.length();
		
		if (dividend.charAt(0) == '-') {
			leftIndent++;
			dividendLength--;
		}
		
		int spacesBeforePipe = dividendLength - subtrahendLength;
		int hyphensAboveResult = Math.max(
				input.getResult().length(), 
				input.getDivisor().length()
				);
		
		outputBuilder.append(dividendPrefix + dividend + "|" + divisor + "\n");
		
		outputBuilder.append(replicateChar(' ', leftIndent) + subtrahend);
		outputBuilder.append(replicateChar(' ', spacesBeforePipe) + "|");
		outputBuilder.append(replicateChar('-', hyphensAboveResult) + "\n");
		
		outputBuilder.append(replicateChar(' ', leftIndent)); 
		outputBuilder.append(replicateChar('-', subtrahendLength));
		outputBuilder.append(replicateChar(' ', spacesBeforePipe) + "|");
		outputBuilder.append(input.getResult() + "\n");
		
		return outputBuilder.toString();
	}
	
	
	private String buildInnerLines(DivisionData input) {
		
		StringBuilder outputBuilder = new StringBuilder();
		
		int listLength = input.getNumberOfOperands();
		String previousMinuend;
		String previousSubtrahend;
		
		if(input.getNumberOfOperands() == 0) {
			previousMinuend = "0";
			previousSubtrahend = "0";
		} else {
			previousMinuend = input.operandAt(0);
			previousSubtrahend = input.operandAt(1);
		}

		Integer previousDifference = parseInt(previousMinuend) - parseInt(previousSubtrahend);

		int leftIndent = input.getDividendPrefix().length();
		if(input.getDividend().charAt(0) == '-') {
			leftIndent++;
		}
		
		for (int i = 2; i <= listLength - 2; i = i + 2) {
			
			previousDifference = parseInt(previousMinuend) - parseInt(previousSubtrahend);
			int adjustIndent = 0;
			
			if (previousDifference == 0) {
				leftIndent += previousSubtrahend.length();
			} else {
				adjustIndent = previousSubtrahend.length() - 
						previousDifference.toString().length();
				leftIndent += adjustIndent;
			}
			
			outputBuilder.append(replicateChar(' ', leftIndent - 1));
			outputBuilder.append("_" + input.operandAt(i) + "\n");
			
			adjustIndent = (input.operandAt(i).length() - input.operandAt(i + 1).length());
			leftIndent += adjustIndent;
			
			outputBuilder.append(replicateChar(' ', leftIndent));
			outputBuilder.append(input.operandAt(i + 1) + "\n");
			
			outputBuilder.append(replicateChar(' ', leftIndent));
			outputBuilder.append(replicateChar('-', input.operandAt(i + 1).length()) + "\n");
			
			previousMinuend = input.operandAt(i);
			previousSubtrahend = input.operandAt(i + 1);
		}
		
		return outputBuilder.toString();
	}
	
	
	private String buildRemainderLine(DivisionData input) {
		String remainder = input.getRemainder();
		int pipeCharIndex = input.getDividendPrefix().length() + input.getDividend().length();
		int remainderIndent = pipeCharIndex - input.getRemainder().length();
		
		String remainderLine = replicateChar(' ', remainderIndent) + remainder;
		
		return remainderLine;
	}
	
	
	private String replicateChar(char character, int quantity) {
		StringBuilder outputBuilder = new StringBuilder();
		
		for(int i = 0; i < quantity; i++) {
			outputBuilder.append(character);
		}

		return outputBuilder.toString();
	}

}
