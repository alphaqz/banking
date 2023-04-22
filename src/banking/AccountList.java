package banking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.mysql.jdbc.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AccountList extends JInternalFrame {
	private JTable tblaccount;
	private JButton btnPrint;
	private JButton btnClose;
	DefaultTableModel dtm = new DefaultTableModel();
	Statement ste = null ;
	Connection con = null ;
	private JComboBox cboAccNo;
	private JButton btnSearch;
	private JButton btnShowAll;
	private String selectAll = "select a.id as id,c.name,at.title as accTypeID ,staffID from account a\r\n"
    		+ "        		inner join accounttype at\r\n"
    		+ "        		on a.accTypeID = at.id \r\n"
    		+ "        		inner join customer c\r\n"
    		+ "        		on a.cusID = c.id";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AccountList dialog = new AccountList();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AccountList() throws ClassNotFoundException {
		setTitle("Account List");
//		setBounds(100, 100, 719, 476);
		setBounds(0, 0, Constants.c_width, Constants.c_height);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Account List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 683, 415);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 125, 663, 235);
		panel.add(scrollPane);
		
		tblaccount = new JTable();
		scrollPane.setViewportView(tblaccount);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confrim",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
				{	
					dispose();
				}
			}
		});
		btnClose.setBounds(584, 371, 89, 33);
		panel.add(btnClose);
		
		btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
		            tblaccount.print();
		        }catch(Exception e1) {
		            JOptionPane.showMessageDialog(null, e1);
		        }
			}
		});
		btnPrint.setBounds(471, 371, 89, 33);
		panel.add(btnPrint);
		
		cboAccNo = new JComboBox();
		cboAccNo.setBounds(10, 42, 124, 27);
		panel.add(cboAccNo);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboAccNo.getSelectedIndex()==0) {
	                JOptionPane.showMessageDialog(null,"Please choose Account No");
	                cboAccNo.requestFocus();
	            } else {
//	            	tblaccount.removeAll();
	            	String str = "select a.id,c.name,a.accTypeID,a.staffID from account a "
	            			+ "inner join customer c on a.cusID = c.id "
	            			+ "where a.id='"+cboAccNo.getSelectedItem().toString()+"'";
//	            	fillAccountForSelected();
	            	fillAccount(str);
	            }
			}
		});
		btnSearch.setBounds(171, 42, 87, 27);
		panel.add(btnSearch);
		
		btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillAccount(selectAll);
			}
		});
		btnShowAll.setBounds(297, 42, 87, 27);
		panel.add(btnShowAll);
		
		try{
			clsDBConnection c=new clsDBConnection();
            con=c.getConnection();
        }catch(SQLException sqle)
        {
            System.out.println(sqle);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        createtable();
        fillAccount(selectAll);
        fillAccountData();
	}

    public void createtable()
   {
       dtm.addColumn("Account No");
//       dtm.addColumn("Balance");
       dtm.addColumn("Withdrawable");
       dtm.addColumn("Customer");
       dtm.addColumn("Account Type");
       dtm.addColumn("Staff ID");
//       dtm.addColumn("Balance new");
       tblaccount.setModel(dtm);
       setColumnWidth(0,40);
       setColumnWidth(1,50);
       setColumnWidth(1,50);
       setColumnWidth(2,130);
       setColumnWidth(3,60);
       setColumnWidth(4,60);
//       setColumnWidth(1,50);

   }

    public void setColumnWidth(int index , int width)
    {
        DefaultTableColumnModel tcm = (DefaultTableColumnModel)tblaccount.getColumnModel();
        TableColumn tc = tcm.getColumn(index);
        tc.setPreferredWidth(width);
    }


    public void fillAccount(String sql)
    {
        String strdataitem[]=new String[5];
        try{
            Statement ste = (Statement) con.createStatement();
            while(dtm.getRowCount()>0) {
            	dtm.removeRow(0);
            }
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next())
            {
            	int[] result = CalculateIntrest.something(rs.getString(1));
                strdataitem[0]=rs.getString(1);
                strdataitem[1]=result[0]+""; //total
                strdataitem[2]=result[1]+"";// withdrawable total
                strdataitem[3]=rs.getString(3);
                strdataitem[4]=rs.getString(5);
//                strdataitem[4]=rs.getString(4);
                strdataitem[4] = ""+CalculateIntrest.something(rs.getString(1));
                dtm.addRow(strdataitem);
            }
            tblaccount.setModel(dtm);
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
        }
    }
    public void fillAccountForSelected()
    {
        String strdataitem[]=new String[8];
        try{
            Statement ste = (Statement) con.createStatement();
            String str = "select id,cusID,accTypeID,staffID from account where id='"+cboAccNo.getSelectedItem().toString()+"'";
            ResultSet rs = ste.executeQuery(str);
            while(rs.next())
            {
                strdataitem[0]=rs.getString(1);
                strdataitem[1]=rs.getString(2);
                strdataitem[2]=rs.getString(3);
                strdataitem[3]=rs.getString(4);
                strdataitem[4]=rs.getString(5);
                strdataitem[5] = ""+CalculateIntrest.something(rs.getString(1));
                dtm.addRow(strdataitem);
            }
            tblaccount.setModel(dtm);
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
        }
    }
    public void fillAccountData()
    {
        String str[]=mySQLQueries.getIDForChoice("account");
        cboAccNo.addItem("-Select-");
        for(int i=0;i<str.length;i++)
        	cboAccNo.addItem(str[i].toString());				
    }
}