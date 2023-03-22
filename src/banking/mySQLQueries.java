package banking;
import banking.clsDBConnection;
import java.sql.*;
import java.sql.Connection;
import javax.swing.*;


public class mySQLQueries {
	
	static Connection con = null;
	static Statement stmt;
	static String query , query1;
	static ResultSet rs;
	static clsDBConnection connect = new clsDBConnection();
	public mySQLQueries() throws ClassNotFoundException
	{
	    try{
	        con=connect.getConnection();
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
	        query = "insert into account(id,balance,cusID,accTypeID,staffID)values ('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"'  )";
	    }

	    else if(tbName.equals("staff"))
	    {
	        query = "insert into staff(id,name,gender,phone,email)values ('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"')";
	    }
	    else if(tbName.equals("deposit"))
	    {
	        query = "insert into deposit(id,amount,accountno,staffno)values ('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"')";
	    }

	    else if(tbName.equals("merchandise"))
	    {
	        query = "insert into merchandise(merID,brandID,typeID)values('"+data[0]+"','"+data[1]+"','"+data[2]+"')";
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
	        stmt = con.createStatement();
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
	        stmt = con.createStatement();
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
            stmt = con.createStatement();
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
            stmt = con.createStatement();
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
            stmt = con.createStatement();
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
            stmt = con.createStatement();
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
             else if(tbName.equals("type"))
                 rs=connect.SQLSelect("typeID", "type");
             else if(tbName.equals("customer"))
                 rs=connect.SQLSelect("id", "customer");
             else if(tbName.equals("staff"))
                 rs=connect.SQLSelect("id", "staff");
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

     public static String []getMerchandise(String merid)
     {
         try{
        	 con=connect.getConnection();
             String[]value= new String[2];
             stmt=con.createStatement();
             query = "select * from merchandise where merid='"+merid+"'";
             rs=stmt.executeQuery(query);
             rs.next();
             value[0]=rs.getString(2);
             value[1]=rs.getString(3);
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
         if(tbName.equals("supplier"))
             query = "update supplier set Name='"+data[0]+"',Address='"+data[1]+"',PhoneNo='"+data[2]+"',Email='"+data[3]+"'where supplierID='"+id+"'";
         else  if(tbName.equals("customer"))
             query = "update customer set Name='"+data[0]+"',Address='"+data[1]+"',PhoneNo='"+data[2]+"',Email='"+data[3]+"'where customerID='"+id+"'";
         else if(tbName.equals("itemdetail"))
             query = "update itemdetail set itemname='"+data[0]+"',cursaleprice="+data[1]+",remark='"+data[2]+"'where itemid='"+id+"'";
         else if(tbName.equals("brand"))
              query = "update brand set brandname='"+data[0]+"' where brandid='"+id+"'";
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
                 stmt = con.createStatement();
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

     public static String []getSupplierData(String id)
     {
         try
         {
        	 con=connect.getConnection();
             stmt = con.createStatement();
             String str[];
             query = "select * from supplier where supplierID='"+id+"'";
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
     





}
