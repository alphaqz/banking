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
	static String id = "";
	static double currAmount=0.0;
	static double intrest = 0;
	public static void main(String[] args) {
	 int output =	something("ac-0000001")[0];
	 System.out.println(output);
	}
	
	static int changeTotal(String accid,int input) {
		 if(id.startsWith("D")) {
    		 //deposit
    		 input+=currAmount;
    	 }else if(id.startsWith("T")) {
    		 
    		try {
				if(rs.getString("received").toLowerCase().equals(accid.toLowerCase())) {
					 input+=currAmount;           			
				}else {
					input -=currAmount;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 }else {
    		 //withdraw
    		 input -=currAmount;
    	 }
		 return input;
	}
	
	public static int[] something(String accid) {
		
		String str[] ;
		  str = new String[3];
		Date prevDate = null;
		int currMonth = 0;
		int todayTotal = 0;
		double intrestTotal = 0.0;
		double prevAmount = 0.0;
		int total = 0;
		int withdrawableAmount = 0;
		String accountData[] = mySQLQueries.getAccountData(accid);
		String accountTypeID = accountData[2];
		int fixedPeriod = Integer.parseInt( mySQLQueries.getAccountTypeData(accountTypeID)[2]);
//		
//		System.out.println("xxxxxxx");
//		System.out.println(mySQLQueries.getAccountTypeData(accountTypeID)[0]);
		intrest = Double.parseDouble( mySQLQueries.getAccountTypeData(accountTypeID)[1] );
//		System.out.println("intrest is "+intrest );
//		System.out.println(mySQLQueries.getAccountTypeData(accountTypeID)[2]);
//		System.out.println("xxxxxxx");
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
            	 
            	  id = rs.getString(1);
            	 LocalDate currLocalDate = currDate.toLocalDate();
            	  currAmount = Integer.parseInt(rs.getString(2));
            	 double currIntrest = getIntrest(currAmount);
            	 System.out.println(currLocalDate);
            	 System.out.println(currLocalDate.isBefore( LocalDate.now().minusMonths(fixedPeriod)));
            	if(accountTypeID.equals("AT-0000001") || currLocalDate.isBefore( LocalDate.now().minusMonths(fixedPeriod))) {
            		System.out.println("this is "+ fixedPeriod + "months before.");
            		withdrawableAmount = changeTotal(accid, withdrawableAmount);
            	}
            	total = changeTotal(accid, total);
            	 
            
            	
            	 if(currDate !=null && prevDate != null) { 
            		 LocalDate prevLocalDate = prevDate.toLocalDate();
            		 //System.out.println("prev date " + prevDate.toLocalDate().plusDays(1));
            		 while(prevLocalDate.isBefore( currLocalDate)) {
            			 if(prevLocalDate.plusDays(1).getDayOfMonth() == 1) {
            				 System.out.println("@@@@@@   new month   @@@@@@");
            				 total += intrestTotal;
            				 withdrawableAmount += intrestTotal;
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
	    				 withdrawableAmount += intrestTotal;
	    				 intrestTotal = 0;
	    			 }
	    			 intrestTotal+= getIntrest(total);
	    			prevLocalDate =  prevLocalDate.plusDays(1);
	    			 System.out.println(prevLocalDate + " 	balance:" + total +" 	intrest:" +intrestTotal);
	    		 }
             }
             
            // System.out.println("total intrest is : "+intrestTotal);
             
         }catch(SQLException e)
         {
             JOptionPane.showMessageDialog(null,e.getMessage());
         }
		 int[] result = new int[2];
		 result[0] = total;
		 result[1] = withdrawableAmount;
		 System.out.println("total amount is : "+result[0]);
         System.out.println("withdrawable amount is : "+result[1]);
		 return result;
	}
	public static double getIntrest(double currAmount) {
		return Math.floor( (currAmount * intrest *  0.01)/365);
	}
	

}
