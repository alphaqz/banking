package banking;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

public class CalculateIntrest {
	static Connection con = null;
	static Statement stmt;
	static String query , query1;
	static ResultSet rs;
	static clsDBConnection connect = new clsDBConnection();
	//private static int total = 0;  
	//private static String id = "";
	public static void main(String[] args) {
	 int output =	something("ac-0000003");
	 System.out.println(output);
	}
	public static int something(String accid) {
		int total = 0;  
		System.out.println("calculating intrest");		
		String str[] ;
		  str = new String[3];
		Date currDate = null;
		int currMonth = 0;
		int todayTotal = 0;
		double intrestTotal = 0.0;
		
		 try
         {
        	 con=connect.getConnection();
             stmt = (Statement) con.createStatement();
             
             String query  = "(select w.id,w.amount,w.date "
     				+ " from withdraw w "
     				+ "where w.accountno = '"+accid +"')	"
     				//;
     				+"UNION"
     				+ "(select d.id, d.amount, d.date  from deposit d "
     				+ "where d.accountno = '"+accid +"' ) order by date;";
             
             rs = stmt.executeQuery(query);
             
             while(rs.next())
             {
            	 //System.out.println("new loop");
            	// System.out.println("wtf");
            	 String id = rs.getString(1);
            	 Date tempDate = rs.getDate(3);
            	 LocalDate localDate = tempDate.toLocalDate();
            	 int tempMonth = localDate.getMonthValue();
            	 int day = localDate.getDayOfMonth();
            	 if(currDate !=null && !currDate.equals(tempDate)) {
            		 //System.out.println("new date");
            		 intrestTotal+=getIntrest(todayTotal);
            		 
            		// System.out.println(intrestTotal + " today intrest: " +getIntrest(todayTotal));
            		// System.out.println("total amount is : "+total);
            	 }
            	 int currAmount = Integer.parseInt(rs.getString(2));
            	 
            	 if(currDate == null) {
            		// System.out.println("start date");   		 
            	 }
            	
            	 if(currMonth != 0 && currMonth != tempMonth) {
            		 System.out.println("new month");
            		 total += intrestTotal;
            		 intrestTotal = 0;
            	 }
            	 if(id.startsWith("D")) {
            		 //deposit
            		 todayTotal += currAmount;
            	 }else {
            		 //withdraw
            		 todayTotal -= currAmount;
            	 }
            	 
            	
            	 //System.out.print(""+tempMonth + "/" + day + " ");
            	// System.out.println("amount "+ currAmount + " intrest: " + getIntrest(todayTotal));
            	
            	
            	 
            	 currDate = tempDate; 
            	 currMonth = tempMonth;
            	 if(id.startsWith("D")) {
            		 //deposit
            		 total += Integer.parseInt(rs.getString(2));
            	 }else {
            		 //withdraw
            		 total -= Integer.parseInt(rs.getString(2));
            	 }
                 for(int i = 0 ; i<str.length ; i++)
                 {                	
                     str[i]=rs.getString(i+1);                     
                     //System.out.print( rs.getString(i+1) + " ");                    
                 }
             }
             intrestTotal+=getIntrest(todayTotal);
             System.out.println(currDate + " intrest is " + getIntrest(todayTotal));
             System.out.println("total amount is : "+total);
             System.out.println("total intrest is : "+intrestTotal);
             
         }catch(SQLException e)
         {
             JOptionPane.showMessageDialog(null,e.getMessage());
         }
		 return total;
	}
//	public static int getTotal(String idinput) {
//		id = idinput;
//		something();
//		return total;
//	}
	public static double getIntrest(int amount) {
		return Math.floor( amount * 0.0001);
	}

}
