package ua.com.foxminded;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class OutputFormatterTest {
	
	OutputFormatter outputFormatter;
	DivisionData algorithmPayload;

	@BeforeEach
	void setUp() throws Exception {
		this.outputFormatter = new OutputFormatter();
	}

	
	@Nested
	class TestFormat {
		
		@Test
		@DisplayName("negative integers handling")
		void negativeIntegers() {
			algorithmPayload = new DivisionData("-5", "-8");
			algorithmPayload.setRemainder("5");
			algorithmPayload.setResult("0");
			
			String ouputString = outputFormatter.format(algorithmPayload);

			String expectedString = "" +
					" _-5|-8\n" + 
					"   0|--\n" + 
					"   -|0\n" + 
					"   5";
			
			int expectedHash = expectedString.hashCode();
			int actualHash = ouputString.hashCode();
			
			assertEquals(expectedHash, actualHash, "passed " + algorithmPayload.toString());
		}
		
		@Test
		@DisplayName("repeating digits sequence handling")
		void repeatingSequence() {
			algorithmPayload = new DivisionData("808080", "8");
			algorithmPayload.setRemainder("0");
			algorithmPayload.setResult("101010");
			algorithmPayload.setNextOperand("8");
			algorithmPayload.setNextOperand("8");
			algorithmPayload.setNextOperand("08");
			algorithmPayload.setNextOperand("8");
			algorithmPayload.setNextOperand("08");
			algorithmPayload.setNextOperand("8");
			
			String ouputString = outputFormatter.format(algorithmPayload);

			String expectedString = "" +
					" _808080|8\n" + 
					"  8     |------\n" + 
					"  -     |101010\n" + 
					"  _08\n" + 
					"    8\n" + 
					"    -\n" + 
					"    _08\n" + 
					"      8\n" + 
					"      -\n" + 
					"       0";
			
			int expectedHash = expectedString.hashCode();
			int actualHash = ouputString.hashCode();
			
			assertEquals(expectedHash, actualHash, "passed " + algorithmPayload.toString());
		}
		
		
		@Test
		@DisplayName("divisor as subsequence of divider")
		void divisorSubsequenceOfDividend() {
			algorithmPayload = new DivisionData("125125125", "125");
			algorithmPayload.setRemainder("0");
			algorithmPayload.setResult("1001001");
			algorithmPayload.setNextOperand("125");
			algorithmPayload.setNextOperand("125");
			algorithmPayload.setNextOperand("125");
			algorithmPayload.setNextOperand("125");
			algorithmPayload.setNextOperand("125");
			algorithmPayload.setNextOperand("125");
			
			String ouputString = outputFormatter.format(algorithmPayload);

			String expectedString = "" +
					" _125125125|125\n" + 
					"  125      |-------\n" + 
					"  ---      |1001001\n" + 
					"    _125\n" + 
					"     125\n" + 
					"     ---\n" + 
					"       _125\n" + 
					"        125\n" + 
					"        ---\n" + 
					"          0";
			
			int expectedHash = expectedString.hashCode();
			int actualHash = ouputString.hashCode();
			
			assertEquals(expectedHash, actualHash, "passed " + algorithmPayload.toString());
		}
	}
	
}
