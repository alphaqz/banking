package banking;

import java.time.LocalDate;

public class test {
	public static void main(String[] args) {
		LocalDate aDate = LocalDate.parse("2017-01-01");
		aDate.isBefore( LocalDate.now().minusMonths(1));
		
		System.out.println("6months ago");
	}
}
