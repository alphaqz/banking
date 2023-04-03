package banking;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

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
	 int output =	something("ac-0000002");
	 System.out.println(output);
	}
	public static int something(String accid) {
		
		int total = 0; 
		int withDrawable = 0;
		String str[] ;
		  str = new String[3];
		Date prevDate = null;
		int currMonth = 0;
		int todayTotal = 0;
		double intrestTotal = 0.0;
		double prevAmount = 0.0;
		String accountData[] = mySQLQueries.getAccountData(accid);
		String accountTypeID = accountData[2];
		int fixedPeriod = Integer.parseInt( mySQLQueries.getAccountTypeData("AT-0000002")[2]);
		//AT-0000001
		if(accountTypeID.equals("AT-0000001")) {
			System.out.println("normal saving acc");
		}else {
			System.out.println("fixed period: " + fixedPeriod);
		}
		 try
         {
        	 con=connect.getConnection();
             stmt = (Statement) con.createStatement();
             
             String query  =  "(select w.id,w.amount,w.date,w.accountno as received,w.accountno as transfered"
             		+ "     				 from withdraw w "
             		+ "     				where w.accountno = '"+accid+"' )	"
             		+ "     			"
             		+ "     				UNION"
             		+ "     				(select d.id, d.amount, d.date,d.accountno,d.accountno  from deposit d "
             		+ "     				 where d.accountno = '"+accid+"'  order by date)"
             		+ "                     "
             		+ "                     UNION"
             		+ "(select t.id,t.amount,t.date,t.receivedAccount,t.transferedAccount "
             		+ "from transfer t where receivedAccount = '"+accid+"' or transferedAccount = '"+accid+"')";
             
             rs = stmt.executeQuery(query);
           
             while(rs.next())
             {
            	 Date currDate = rs.getDate(3);
            	 
            	 String id = rs.getString(1);
            	 LocalDate currLocalDate = currDate.toLocalDate();
            	 double currAmount = Integer.parseInt(rs.getString(2));
            	 double currIntrest = getIntrest(currAmount);
            	if(accountTypeID.equals("AT-0000001") || currLocalDate.isBefore( LocalDate.now().minusMonths(fixedPeriod))) {
            		System.out.println("this is "+ fixedPeriod + "months before.");
            		 if(id.startsWith("D")) {
                		 //deposit
                		 total+=currAmount;
                	 }else if(id.startsWith("T")) {
                		 
                		if(rs.getString("received").toLowerCase().equals(accid.toLowerCase())) {
                			 total+=currAmount;           			
                		}else {
                			total -=currAmount;
                		}
                	 }else {
                		 //withdraw
                		 total -=currAmount;
                	 }
            	}
            	 
            
            	
            	 if(currDate !=null && prevDate != null) { 
            		 LocalDate prevLocalDate = prevDate.toLocalDate();
            		 //System.out.println("prev date " + prevDate.toLocalDate().plusDays(1));
            		 while(prevLocalDate.isBefore( currLocalDate)) {
            			 if(prevLocalDate.plusDays(1).getDayOfMonth() == 1) {
            				 System.out.println("@@@@@@   new month   @@@@@@");
            				 total += intrestTotal;
            				 intrestTotal = 0;
            			 }
            			 intrestTotal+= getIntrest(total);
            			prevLocalDate =  prevLocalDate.plusDays(1);
            			 System.out.println(prevLocalDate + " 	balance:" + total +" 	intrest:" +intrestTotal);
            		 }
            	 }
            	    
            	
            
            	 
            	 intrestTotal+= currIntrest;
            	 //total+=currAmount;
            	 System.out.println("---------------added--------------- "+ currAmount);
            	 prevDate = currDate;
            	 prevAmount = currAmount;            	 
            	// System.out.println(prevDate + " intrest is " + intrestTotal);
             }
             LocalDate today = LocalDate.now();
             if(prevDate != null) {            	 
             
	             LocalDate prevLocalDate = prevDate.toLocalDate();
	             while(!prevLocalDate.equals( today)) {
	    			 if(prevLocalDate.plusDays(1).getDayOfMonth() == 1) {
	    				 System.out.println("@@@@@@   new month   @@@@@@");
	    				 total += intrestTotal;
	    				 intrestTotal = 0;
	    			 }
	    			 intrestTotal+= getIntrest(total);
	    			prevLocalDate =  prevLocalDate.plusDays(1);
	    			 System.out.println(prevLocalDate + " 	balance:" + total +" 	intrest:" +intrestTotal);
	    		 }
             }
             System.out.println("total amount is : "+total);
            // System.out.println("total intrest is : "+intrestTotal);
             
         }catch(SQLException e)
         {
             JOptionPane.showMessageDialog(null,e.getMessage());
         }
		 return total;
	}
	public static double getIntrest(double currAmount) {
		return Math.floor( currAmount * 0.0001);
	}
	

}
