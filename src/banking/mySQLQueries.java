package banking;
import banking.clsDBConnection;
import java.sql.*;

import javax.swing.*;

import com.mysql.jdbc.Statement;


public class mySQLQueries {
	
	static Connection con = null;
	static Statement stmt;
	static String query , query1;
	static ResultSet rs;
	static clsDBConnection connect = new clsDBConnection();
	public mySQLQueries() throws ClassNotFoundException
	{
		System.out.println("mysqlqueries initiated");
	    try{
	        con=connect.getConnection();
	        System.out.println("con: " + con);
	    }
	    catch(SQLException sqle)
	    {
	        System.out.println(sqle);
	    }catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	}
	
	public static boolean insertData(String tbName , String[]data)
	{
	    if(tbName.equals("brand"))
	    {
	        query = "insert into brand values ('"+data[0]+"','"+data[1]+"');";
	    }
	    else if(tbName.equals("accounttype"))
	    {
	        query = "insert into accounttype values ('"+data[0]+"','"+data[1]+"' , " + Integer.parseInt(data[2]) + ")";
	    }
	    else if(tbName.equals("account"))
	    {
	        query = "insert into account(id,balance,cusID,accTypeID,staffID)values ('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"')";
	    }

	    else if(tbName.equals("staff"))
	    {
	        query = "insert into staff(id,name,gender,phone,email)values ('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"')";
	    }
	    else if(tbName.equals("deposit"))
	    {
	        query = "insert into deposit(id,amount,accountno,staffno)values ('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"')";
	    }
	    else if(tbName.equals("withdraw"))
	    {
	        query = "insert into withdraw(id,amount,accountno,staffno)values ('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"')";
	    }

	    else if(tbName.equals("transfer"))
	    {
	        query = "insert into transfer(id, amount, date, receivedAccount, transferedAccount, staffId)values('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"')";
	    }
	    else if(tbName.equals("customer"))
	    {
	        query = "insert into customer(id,name,gender,address,phone,email,nrc,job) values ('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"' ,'"+data[5]+"','"+data[6]+"'  ,'"+data[7]+"')";
	    }
	     else if(tbName.equals("itemdetail"))
	    {
	        query = "insert into itemdetail(itemid,merid,itemname,remark)values ('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"')";
	    }

	    try{
	    	con=connect.getConnection();
	        stmt = (Statement) con.createStatement();
	        boolean r = stmt.execute(query);
	       //System.out.println(query+r);
	        if(r)
	        {
	            return false;
	        }
	        else return true;

	    }catch(SQLException e)
	    {
	        JOptionPane.showMessageDialog(null,e.getMessage());
	        e.printStackTrace();
	        return true;
	    }
	}
	   public static String []getCustomerData(String id)
	     {
	         try
	         {
	        	 con=connect.getConnection();
	             stmt = (Statement) con.createStatement();
	             String str[];
	             query = "select * from customer where id='"+id+"'";
	             str = new String[7];
	             rs = stmt.executeQuery(query);
	             if(rs.next())
	             {
	                 for(int i = 0 ; i<str.length ; i++)
	                 {
	                     str[i]=rs.getString(i+2);
	                 }
	             }
	             return str;
	         }catch(SQLException e)
	         {
	             JOptionPane.showMessageDialog(null,e.getMessage());
	             return null;
	         }
	     }
	   
	   public static String []getAccountData(String id)
	     {
	         try
	         {
	        	 con=connect.getConnection();
	             stmt = (Statement) con.createStatement();
	             String str[];
	             query = "select balance,cusID,accTypeID,staffID from account where id='"+id+"'";
	             str = new String[4];
	             rs = stmt.executeQuery(query);
	             if(rs.next())
	             {
	                 for(int i = 0 ; i<str.length ; i++)
	                 {
	                     str[i]=rs.getString(i+1);
	                 }
	             }
	             return str;
	         }catch(SQLException e)
	         {
	             JOptionPane.showMessageDialog(null,e.getMessage());
	             return null;
	         }
	     }
	   public static String []getAccountTypeData(String id)
	     {
	         try
	         {
	        	 con=connect.getConnection();
	             stmt = (Statement) con.createStatement();
	             String str[];
	             query = "select * from accounttype where id='"+id+"'";
	             str = new String[2];
	             rs = stmt.executeQuery(query);
	             if(rs.next())
	             {
	                 for(int i = 0 ; i<str.length ; i++)
	                 {
	                     str[i]=rs.getString(i+2);
	                 }
	             }
	             return str;
	         }catch(SQLException e)
	         {
	             JOptionPane.showMessageDialog(null,e.getMessage());
	             return null;
	         }
	     }
    public static void deleteRecord(String tbName,String id)
    {
        int returnvalue = 0 ;
        String query = "";
        if(tbName.equals("customer"))
        {
            query = "delete from customer where id = '"+id+"' ";
        }
        if(tbName.equals("staff"))
        {
            query = "delete from staff where id = '"+id+"' ";
        }
        if(tbName.equals("deposit"))
        {
            query = "delete from deposit where id = '"+id+"' ";
        }
        if(tbName.equals("transfer"))
        {
            query = "delete from transfer where id = '"+id+"' ";
        }
        if(tbName.equals("account"))
        {
            query = "delete from account where id = '"+id+"' ";
        }
        if(tbName.equals("accounttype"))
        {
            query = "delete from accounttype where id = '"+id+"' ";
        }
        try{
        	 con=connect.getConnection();
            stmt = (Statement) con.createStatement();
            if(!query.equals("")&&stmt.executeUpdate(query)==1)
                JOptionPane.showMessageDialog(null, "The record is deleted successfully in"+tbName+"table.");
            else
                JOptionPane.showMessageDialog(null,"The specified ID does not found in the table .","Delete Fail",JOptionPane.ERROR_MESSAGE);
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"SQLException",JOptionPane.ERROR_MESSAGE);
        }

    }
	public static boolean isduplicate(String tbName , String []data)
	{
	    if(tbName.equals("brand"))
	    {
	        query = "select * from brand where brandname='"+data[0]+"'";
	    }
	    else if(tbName.equals("accounttype"))
	    {
	        query = "select * from accounttype where title='"+data[0]+"'";
	    }

	    else if(tbName.equals("staff"))
	    {
	        query = "select * from staff where name ='"+data[0]+"'and phone ='"+data[1]+"'and email ='"+data[1]+"'";
	    }
	    
	    else if(tbName.equals("merchandise"))
	    {
	        query = "select * from merchandise where brandid='"+data[0]+"'and typeid='"+data[1]+"'";
	    }
	    else if(tbName.equals("customer"))
	    {
	        query = "select * from customer where name ='"+data[0]+"'and address ='"+data[1]+"'and phone ='"+data[2]+"'and email='"+data[3]+"'";
	    }
	    else if(tbName.equals("itemdetail"))
	    {
	        query = "select * from itemdetail where itemname ='"+data[0]+"'and merid ='"+data[1]+"'";
	    }
	    

	    
	    try{
	    	con=connect.getConnection();
	        stmt = (Statement) con.createStatement();
	        rs = stmt.executeQuery(query);
	        if(rs.next())
	            return false;
	        else
	            return true;
	    }catch(SQLException e){
	        JOptionPane.showMessageDialog(null, e.getMessage(),"SQLException",JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	}

    public static String getTypeName(String typeid)
    {
        try
        {
            String typename;
            con=connect.getConnection();
            stmt = (Statement) con.createStatement();
            query = "select * from type where typeID ='"+typeid+"';";
            rs=stmt.executeQuery(query);
            rs.next();
            typename = rs.getString(2);
            return typename;
        }catch(SQLException sqle)
        {
            System.out.println(sqle);
            return null;
        }
    }
    public String getTypeID(String typename)
    {
        String typeid;
        try
        {
            stmt = (Statement) con.createStatement();
            query = "select typeid from type where typename='"+typename+"';";
            rs=stmt.executeQuery(query);
            rs.next();
            typeid=rs.getString(1);
            return typeid;
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
            return null;
        }
    }
    
    public String getBrandID(String brandname)
    {
        try{
            String brandid;
            stmt = (Statement) con.createStatement();
            query = "select brandid from brand where brandname='"+brandname+"';";
            rs=stmt.executeQuery(query);
            rs.next();
            brandid=rs.getString(1);
            return brandid;
        }catch(SQLException sqle)
        {
            System.out.println(sqle);
            return null;
        }
    }
     public static String getBrandName(String brandid)
    {
        try{
            String brandname;
            con=connect.getConnection();
            stmt = (Statement) con.createStatement();
            query = "select * from brand where brandid='"+brandid+"';";
            rs=stmt.executeQuery(query);
            rs.next();
            brandname=rs.getString(2);
            return brandname;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
     public static   String getAutoid(String field , String tabel , String prefix) throws ClassNotFoundException
     {
         if(tabel.equals("sale")||tabel.equals("purchase"))
         {
             return connect.getPrimaryKey(field, tabel, prefix);
         }
         else
         {
             return connect.getPrimaryKey2(field, tabel, prefix);
         }

     }
     public static String[]getIDForChoice(String tbName)
     {
         try{
             if((tbName.equals("staff")))
                 rs = connect.SQLSelect("id","staff");
             else if(tbName.equals("account"))
                 rs=connect.SQLSelect("id", "account");
             else if(tbName.equals("accounttype"))
                 rs = connect.SQLSelect("id","accounttype");
             else if(tbName.equals("transfer"))
                 rs=connect.SQLSelect("id", "transfer");
             else if(tbName.equals("customer"))
                 rs=connect.SQLSelect("id", "customer");
//             else if(tbName.equals("accounttype"))
//                 rs=connect.SQLSelect("id", "accounttype");
             
             /*
             else if(tbName.equals("merchandise"))
                 rs=connect.SQLSelect("merID", "merchandise");
             else if(tbName.equals("brand"))
                 rs=connect.SQLSelect("brandID", "brand");
             else if(tbName.equals("orders"))
                 rs=connect.SQLSelect("distinct orderid","orders");
             else if(tbName.equals("orderdetail"))
                 rs = connect.SQLSelect("orderid","orderdetail where remark !=0"); 
              */

             int rowcount = 0 ;
             while(rs.next())
             {
                 rowcount++;
             }
             String temp[]=new String[rowcount];
             rs.beforeFirst();
             int i = 0 ;
             while(rs.next())
             {
                 temp[i]=rs.getString(1);
                 i++;
             }
             return temp;
         }catch(SQLException sqle)
         {
             System.out.println(sqle);
             return null ;
         }
         catch(Exception e)
         {
             e.printStackTrace();
             return null;
         }
     }

     public static String []getTransferData(String tid)
     {
         try{
        	 con=connect.getConnection();
             String[]value= new String[5];
             stmt=(Statement) con.createStatement();
             query = "select * from transfer where id='"+tid+"'";
             rs=stmt.executeQuery(query);
             if(rs.next())
             {
                 for(int i = 0 ; i<value.length ; i++)
                 {
                     value[i]=rs.getString(i+2);
                 }
             }
             return value;
         }catch(SQLException e)
         {
             JOptionPane.showMessageDialog(null, e.getMessage());
             return null;
         }
     }
     public static String[]getNameForChoice(String tbName)
     {
         try{
             if(tbName.equals("type"))
                 rs=connect.SQLSelect("typeName", "type");
             else if(tbName.equals("brand"))
                 rs=connect.SQLSelect("brandname", "brand");
             else if(tbName.equals("customer"))
                 rs = connect.SQLSelect("email","customer");
             else if(tbName.equals("staff"))
                 rs = connect.SQLSelect("email","staff");
             else if(tbName.equals("accounttype"))
                 rs = connect.SQLSelect("title","accounttype");
              else if(tbName.equals("customeraddress"))
                 rs = connect.SQLSelect("distinct address","customer");
               else if(tbName.equals("supplieraddress"))
                 rs = connect.SQLSelect("distinct address","supplier");
             else if(tbName.equals("supplier"))
                 rs = connect.SQLSelect("name","supplier");

             int rowcount = 0 ;
             while(rs.next())
             {
                 rowcount++;
             }
             String []temp = new String[rowcount];
             rs.beforeFirst();
             int i = 0 ;
             while(rs.next())
             {
                 temp[i]=rs.getString(1);
                 i++;
             }
             return temp;
         }
         catch(SQLException sqle)
         {
             System.out.println(sqle);
             return null;
         }
         catch(Exception e)
         {
             e.printStackTrace();
             return null;
         }
     }
     public static boolean updateRecord(String tbName,String id , String []data)
     {
         if(tbName.equals("staff"))
             query = "update staff set name='"+data[0]+"',gender='"+data[1]+"',phone='"+data[2]+"',email='"+data[3]+"'where id='"+id+"'";
         else  if(tbName.equals("customer"))
        	 //id name gender phone address job nrc email
             query = "update customer set Name='"+data[0]+"',gender='"+data[1]+"',phone='"+data[2]+"',address='"+data[3]+"',job='"+data[4]+"',nrc='"+data[5]+"',Email='"+data[6]+"'where id='"+id+"'";
         else if(tbName.equals("account"))
             query = "update account set balance='"+data[0]+"' where id='"+id+"'";
         else if(tbName.equals("accounttype"))
              query = "update accounttype set title='"+data[0]+"',interest='"+data[1]+"' where id='"+id+"'";
         else if(tbName.equals("type"))
              query = "update type set typename='"+data[0]+"' where typeid='"+id+"'";
         else if(tbName.equals("orderdetail"))
              query = "update orderdetail set remark = "+Integer.parseInt(data[0])+" where orderid='"+id+"'";
         else if(tbName.equals("orders"))
         {
              int cat = data[0].indexOf("(");
              query = "update orders set orderdate='"+data[0].substring(0,cat)+"'where orderid='"+id+"'";
         }

             
             try{
            	 con=connect.getConnection();
                 stmt = (Statement) con.createStatement();
                 if(stmt.executeUpdate(query)==1)
                 {
                     return true;
                 }
                 else{
                     JOptionPane.showMessageDialog(null,"The table does not contain the specified ID.","Update Fail",JOptionPane.ERROR_MESSAGE);
                     return false;}
             }
             catch(SQLException e)
             {
                 JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException", JOptionPane.ERROR_MESSAGE);
                 return false;
             }
       }
     public static boolean updateAmount(String action,String id , String amount)
     {
         if(action.equals("deposit"))
             query = "update account set balance= balance +"+ Integer.parseInt(amount) +" where id='"+id+"'";
         else if(action.equals("withdraw"))
             query = "update account set balance= balance -"+ Integer.parseInt(amount) +" where id='"+id+"'";
         else  if(action.equals("transfer"))
             query = "update account set balance="+Integer.parseInt(amount)+" where id='"+id+"'";
         else if(action.equals("transferUpdate"))
        	 query = "update transfer set amount="+Integer.parseInt(amount)+" where id='"+id+"'";
             
             try{
            	 con=connect.getConnection();
                 stmt = (Statement) con.createStatement();
                 if(stmt.executeUpdate(query)==1)
                 {
                     return true;
                 }
                 else{
                     JOptionPane.showMessageDialog(null,"The table does not contain the specified ID.","Update Fail",JOptionPane.ERROR_MESSAGE);
                     return false;}
             }
             catch(SQLException e)
             {
                 JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException", JOptionPane.ERROR_MESSAGE);
                 return false;
             }
       }

    public static String []getStaffData(String id)
     {
         try
         {
        	 con=connect.getConnection();
             stmt = (Statement) con.createStatement();
             String str[];
             query = "select * from staff where id='"+id+"'";
             str = new String[4];
             rs = stmt.executeQuery(query);
             if(rs.next())
             {
                 for(int i = 0 ; i<str.length ; i++)
                 {
                     str[i]=rs.getString(i+2);
                 }
             }
             return str;
         }catch(SQLException e)
         {
             JOptionPane.showMessageDialog(null,e.getMessage());
             return null;
         }
     }
    
    public static String []getAccountDataFordeposit(String id)

    {
        try
        {
       	 con=connect.getConnection();
            stmt = (Statement) con.createStatement();
            String str[];
            query = "select a.id, a.balance, t.title as 'account type title', c.name as 'customer name'\r\n"
            		+ "from account a \r\n"
            		+ "join accounttype t\r\n"
            		+ "on a.accTypeID = t.id\r\n"
            		+ "join customer c\r\n"
            		+ "on a.cusID = c.id\r\n"
            		+ "where a.id = '"+id+"'";
            str = new String[4];
            rs = stmt.executeQuery(query);
            if(rs.next())
            {
                for(int i = 0 ; i<str.length ; i++)
                {
                    str[i]=rs.getString(i+1);
                }
            }
            return str;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) 
     {
    	 try {
    		 mySQLQueries q=new mySQLQueries();
    		 System.out.print(con);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }

	public static String getAccountBalance(String id) {
		 try{
             String result;
             con=connect.getConnection();
             stmt = (Statement) con.createStatement();
             query = "select balance from account where id='"+id+"';";
             rs=stmt.executeQuery(query);
             rs.next();
             result=rs.getString(1);
             return result;
         }catch(SQLException sqle)
         {
             System.out.println(sqle);
             return null;
         }
	}

	public static String getAmount(String transferID) {
		try{
			con=connect.getConnection();
            stmt = (Statement) con.createStatement();
            String amt = null;
            query = "select balance from account where id='"+transferID+"'";
            rs=stmt.executeQuery(query);
            if(rs.next())
            {
                amt=rs.getString(1);//id
            }
            return amt;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"SQLException",JOptionPane.ERROR_MESSAGE);
            return null;
        }

	}
	public static String getReceiveAmount(String receiveID) {
		try{
			con=connect.getConnection();
            stmt = (Statement) con.createStatement();
            String amt = null;
            query = "select balance from account where id='"+receiveID+"'";
            rs=stmt.executeQuery(query);
            if(rs.next())
            {
                amt=rs.getString(1);//id
            }
            return amt;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"SQLException",JOptionPane.ERROR_MESSAGE);
            return null;
        }

	}

	public static String[] getIDForFilter(String id) {
		try{
            con=connect.getConnection();
            stmt = (Statement) con.createStatement();
            query = "select id from account where id!='"+id+"';";
            rs=stmt.executeQuery(query);
            int rowcount = 0 ;
            while(rs.next())
            {
                rowcount++;
            }
            String []temp = new String[rowcount];
            rs.beforeFirst();
            int i = 0 ;
            while(rs.next())
            {
                temp[i]=rs.getString(1);
                i++;
            }
            return temp;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
	}

}
