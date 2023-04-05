package banking;

import java.time.LocalDate;

public class test {
	public static void main(String[] args) {
		String str ="15/mamata(N)004039";
		String str1="09257111720";
		System.out.println(str.split("/")[0]);
		System.out.println(str.split("/")[1].split("\\(N\\)")[0]);
		System.out.println(str.split("/")[1].split("\\(N\\)")[1]);
		
		if(!checkNRC(str)) {
			System.out.println(false);
		}
		else {
			System.out.println(true);
		}
		
//		if((str1.charAt(0)=='0') && (str1.charAt(1)=='9') && (str1.length()==11) && (IsAllDigit(str1))) {
//			System.out.println(true);
//		}
//		else {
//			System.out.println(false);
//		}
	}
	public static boolean checkNRC(String str) {
		boolean b=false;
		
		String first=str.split("/")[0];
		String second=str.split("/")[1].split("\\(N\\)")[0];
		String third=str.split("/")[1].split("\\(N\\)")[1];
		
		System.out.println(Integer.parseInt(third));
//		first section
//		if ((!first.trim().equals("")) && (IsAllDigit(first)) && first.length()<=2) {
//			System.out.println("true");
//		} else {
//			System.out.println("false");
//		}
//		
////		second section
//		if ((!second.trim().equals(""))) {
//			System.out.println("true");
//		} else {
//			System.out.println("false");
//		}
//		
////		third section
//		if ((!third.trim().equals("")) && (IsAllDigit(third)) && third.length()==6) {
//			System.out.println("true");
//		} else {
//			System.out.println("false");
//		}
		
		if (((!first.trim().equals("")) && (IsAllDigit(first)) && first.length()<=2) && ((!second.trim().equals(""))) && ((!third.trim().equals("")) && (IsAllDigit(third)) && third.length()==6)) {
			b=true;
			return b;
		}
		 else {
			 return b;
		}
	}
	public static boolean IsAllDigit(String str) {
		boolean b = false;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i)))
				b = true;
			else {
				b = false;
				break;
			}

		}
		return b;
	}
}
