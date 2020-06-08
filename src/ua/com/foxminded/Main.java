package ua.com.foxminded;

public class Main {
	
	public static void main(String[] args) {
		
		ColumnDivision columnDivision = new ColumnDivision();
		OutputFormatter outputFormatter = new OutputFormatter();
		
		DivisionData divisionValues = columnDivision.divide(-5, -8);
		String result = outputFormatter.format(divisionValues);

		System.out.println(result);
	}
}
