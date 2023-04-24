package banking;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.lang.String.*;
import banking.mySQLQueries;

public class Checking {
	public static boolean IsValidName(String str) {
		if (IsNull(str) || str.startsWith(""))
			return false;
		if (!IsLetter(str))
			return false;
		return true;
	}

	public static boolean IsNull(String str) {
		if (str.trim().equals("") || str.trim().equals(null))
			return true;
		else
			return false;
	}

//	Check Letter
	public static boolean IsLetter(String str) {
		boolean b = false;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isLetter(str.charAt(i)))
				b = true;
			else {
				b = false;
				break;
			}
		}
		return b;
	}

//	Check Email
	public static boolean IsEformat(String str) {
		boolean b = false;
		int t = str.indexOf(".");
		int a = str.indexOf("@");
		if ((t < 0) || (a < 0) || (str.indexOf(" ") > 0)) {
			return b;
		}
		String st1 = str.substring(0, a);
		String st = str.substring(t + 1);
		if ((!st1.trim().equals("")) && (st.equals("com"))) {
			b = true;
			return b;
		} else
			return b;
	}
	
//	Check NRC
	public static boolean IsNRCformat(String str) {
		boolean b = false;
		if(!str.contains("/") || !str.contains("(N)") ) {
			return b;
		}
		String first=str.split("/")[0];
		String second=str.split("/")[1].split("\\(N\\)")[0];
		String third=str.split("/")[1].split("\\(N\\)")[1];
		
//		System.out.println(str.split("/")[0]);
//		System.out.println(str.split("/")[1].split("\\(N\\)")[0]);
//		System.out.println(str.split("/")[1].split("\\(N\\)")[1]);
		
		if (((!first.trim().equals("")) && (IsAllDigit(first)) && first.length()<=2) && ((!second.trim().equals(""))) && ((!third.trim().equals("")) && (IsAllDigit(third)) && third.length()==6)) {
			b = true;
			return b;
		}
		 else {
			return b;
		}
	}
	
//	Check Phone No
	public static boolean IsPhoneNoformat(String str) {
		boolean b=false;
		
		if((str.length()==11) && (str.charAt(0)=='0') && (str.charAt(1)=='9') && (str.charAt(2)=='2' || str.charAt(2)=='4' || str.charAt(2)=='6' || str.charAt(2)=='7' || str.charAt(2)=='8' || str.charAt(2)=='9')) {
			b=true;
			return b;
		}
		else {
			return b;
		}
	}

//	Check Digit
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

	public static boolean IsContain(char c, String str) {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == c)
				return true;
		}
		return false;
	}

	public static boolean IsContain(String s, Vector str) {
		for (int i = 0; i < str.size(); i++) {
			if (s.equals((String) str.elementAt(i)))
				return true;
		}
		return false;
	}

	

	public static String[] fromtable(JTable mtable, int size, int column) {
		String[] str = new String[size];
		for (int i = 0; i < size; i++) {
			if (IsContain(':', ((String) mtable.getValueAt(i, column)))) {
				int j = ((String) mtable.getValueAt(i, column)).indexOf("::");
				str[i] = ((String) mtable.getValueAt(i, column)).substring(0, j);

			} else {
				str[i] = ((String) mtable.getValueAt(i, column));
			}
		}
		return str;
	}
}
