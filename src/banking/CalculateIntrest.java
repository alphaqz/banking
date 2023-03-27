package banking;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

public class CalculateIntrest {
	static Connection con = null;
	static Statement stmt;
	static String query , query1;
	static ResultSet rs;
	static clsDBConnection connect = new clsDBConnection();
	
	public static void main(String[] args) {
		System.out.println("calculating intrest");		
		String str[] ;
		  str = new String[3];
		int total = 0;  
		Date currDate = null;
		int todayTotal = 0;
		 try
         {
        	 con=connect.getConnection();
             stmt = (Statement) con.createStatement();
             
             String query  = "(select w.id,w.amount,w.date "
     				+ " from withdraw w "
     				+ "where w.accountno = 'AC-0000003')	"
     				//;
     				+"UNION"
     				+ "(select d.id, d.amount, d.date  from deposit d "
     				+ "where d.accountno = 'AC-0000003' ) order by date;";
             
             rs = stmt.executeQuery(query);
             
             while(rs.next())
             {
            	 String id = rs.getString(1);
            	 Date temp = rs.getDate(3);
            	 int currAmount = Integer.parseInt(rs.getString(2));
            	 if(currDate == null) {
            		 currDate = temp;            		 
            		 System.out.print(currDate + " income is " + currAmount);
            	 }else {
            		
            	 }
            	 
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
                 System.out.println("");
             }
             
             System.out.println("total amount is : "+total);
         }catch(SQLException e)
         {
             JOptionPane.showMessageDialog(null,e.getMessage());
         }
	}

}
