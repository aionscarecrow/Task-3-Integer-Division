package ua.com.foxminded;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ColumnDivisionTest {
	
	ColumnDivision columnDivision;
	
	@BeforeEach
	void setUp() throws Exception {
		this.columnDivision = new ColumnDivision();
	}
	
	
	@Nested
	class testDivide {
		
		@Test
		@DisplayName("positive integers")
		void positiveIntegers() {
			String expected = "512 8 51 48 32 32 0 64";
			String actual = columnDivision.divide(512, 8).toString();
			assertEquals(expected, actual, "passed (512, 8)");
		}
		
		@Test
		@DisplayName("negative dividend")
		void negativeDividend() {
			String expected = "-8 8 8 8 0 -1";
			String actual = columnDivision.divide(-8, 8).toString();
			assertEquals(expected, actual, "passed (-8, 8)");
		}
		
		@Test
		@DisplayName("negative divisor")
		void negativeDivisor() {
			String expected = "8 -8 8 8 0 -1";
			String actual = columnDivision.divide(8, -8).toString();
			assertEquals(expected, actual, "passed (8, -8)");
		}
		
		@Test
		@DisplayName("negative integers")
		void negativeIntegers() {
			String expected = "-5 -8 5 0";
			String actual = columnDivision.divide(-5, -8).toString();
			assertEquals(expected, actual, "passed (-5, -8)");
		}
		
		@Test
		@DisplayName("zero divisor")
		void zeroDivisor() {
			assertThrows(
					IllegalArgumentException.class,
					() -> columnDivision.divide(100, 0),
					"passed (100, 0)"
					);
		}
	}
	
}
