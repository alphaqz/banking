 package banking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLDataException;
import com.toedter.calendar.JCalendar;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;

public class Report extends JDialog {
	private JButton btnSearch;
	private JButton btnShowAll;
	String strdataitem[]=new String[9];
	String strquery[]=new String[5];
	private JTable tblReport;
	private JScrollPane scrollPane;
	private JCheckBox chkDeposit;
	private JCheckBox chkWithdraw;
	DefaultTableModel dtm = new DefaultTableModel();
	Vector vid = new Vector();
	Vector vamount = new Vector();
	String str[],stri[];
	Statement ste = null ;
	Connection con = null ;
	List<String> staffIdList = new ArrayList<String>();  
	List<String> accIdList = new ArrayList<String>();  
	boolean b = false ;
   date d=new date();
   int selectedRow;
   private JLabel lblAccountId;
   private JComboBox cboAccountID;
   private JDateChooser dateChooserStart;
   private JDateChooser dateChooserEnd; 
   private JCheckBox chkTransfer;
   private JCheckBox chkReceived;
   private JLabel lblforName;
   private JLabel lblForEmail;
   private JLabel lblforGender;




	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Report dialog = new Report();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws ClassNotFoundException 
	 */
	public Report() throws ClassNotFoundException {
		setTitle("Deposit Process");
		Border blackline = BorderFactory.createLineBorder(Color.black);
		setBounds(100, 100, 791, 685);
		

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		 int centerX = (int) (screenDimension.getWidth() - getWidth()) / 2;
	     int centerY = (int) (screenDimension.getHeight() - getHeight()) / 2;
	     setLocation(centerX, centerY);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(10, 11, 755, 600);
			getContentPane().add(panel);
			panel.setLayout(null);
			{
				JPanel search = new JPanel();
				search.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Report filter:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				search.setBounds(20, 11, 725, 204);
				panel.add(search);
				search.setLayout(null);
				{
					btnSearch = new JButton("Search");
					btnSearch.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String sql;
						if(cboAccountID.getSelectedIndex() < 1) {
	                    	JOptionPane.showMessageDialog(null, "Please select an account.","Cannot search",JOptionPane.INFORMATION_MESSAGE);

						}
						else if(!chkDeposit.isSelected() && !chkWithdraw.isSelected() && !chkTransfer.isSelected() && !chkReceived.isSelected()){
	                    	JOptionPane.showMessageDialog(null, "Please select at least one check box.","Cannot search",JOptionPane.INFORMATION_MESSAGE);

						}						
						else {
							if(dateChooserStart.getDate() == null) {
						
							
								  sql =  "select d.id, d.amount, d.date,d.accountno,d.accountno as filter,d.staffno from Deposit d where d.accountno = '" + cboAccountID.getSelectedItem() 
								  + "' UNION " +
								 "select w.id,w.amount,w.date,w.accountno as received,w.accountno as transfered,w.staffno from withdraw w where w.accountno = '" + cboAccountID.getSelectedItem() + "'" 
								  + "             UNION"
				             		+ "(select * "
				             		+ "from transfer t where receivedAccount = '"+cboAccountID.getSelectedItem()+"' or transferedAccount = '"+cboAccountID.getSelectedItem()+"')";
							}else {
								sql =  "select d.id, d.amount, d.date,d.accountno,d.accountno as filter,d.staffno from Deposit d  where accountno = '" + cboAccountID.getSelectedItem() 
								 + "' and  date between '" + dateFormat.format(dateChooserStart.getDate()) 
								 + "' and '" + dateFormat.format(dateChooserEnd.getDate())  + "' UNION "
								 + "select  w.id,w.amount,w.date,w.accountno as received,w.accountno as transfered,w.staffno from withdraw w where accountno = '" + cboAccountID.getSelectedItem() 
								 + "' and  date between '" + dateFormat.format(dateChooserStart.getDate()) 
								 + "' and '" + dateFormat.format(dateChooserEnd.getDate())  + "'" 
								  + "  UNION"
				             		+ "(select * "
				             		+ "from transfer t where receivedAccount = '"+cboAccountID.getSelectedItem()+"' or transferedAccount = '"+cboAccountID.getSelectedItem()+"'"
				             		+ " and  date between '" + dateFormat.format(dateChooserStart.getDate()) 
									 + "' and '" + dateFormat.format(dateChooserEnd.getDate())  + "')" ;
							}
				 
								fillData(sql);
							}
						}
						
					});
					btnSearch.setBounds(34, 170, 89, 23);
					search.add(btnSearch);
				}
				{
					btnShowAll = new JButton("Show All");
					btnShowAll.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
				            	fillDeposit();
						}
					});
					btnShowAll.setBounds(185, 170, 89, 23);
					search.add(btnShowAll);
				}
				{
					lblAccountId = new JLabel("Account ID:");
					lblAccountId.setBounds(20, 29, 58, 14);
					search.add(lblAccountId);
				}
				{
					cboAccountID = new JComboBox();
					cboAccountID.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//cbo
						}
					});
					cboAccountID.setBounds(97, 25, 197, 22);
					search.add(cboAccountID);
				}
				
				 dateChooserStart = new JDateChooser();
				dateChooserStart.setBounds(20, 96, 197, 20);
				search.add(dateChooserStart);
				
				dateChooserEnd = new JDateChooser();
				dateChooserEnd.setBounds(20, 127, 197, 20);
				search.add(dateChooserEnd);
				
				chkDeposit = new JCheckBox("Deposit");
				chkDeposit.setSelected(true);
				chkDeposit.setBounds(20, 66, 73, 23);
				search.add(chkDeposit);
				
				chkWithdraw = new JCheckBox("Withdraw");
				chkWithdraw.setSelected(true);
				chkWithdraw.setBounds(106, 66, 83, 23);
				search.add(chkWithdraw);
				
				chkTransfer = new JCheckBox("Transfer");
				chkTransfer.setSelected(true);
				chkTransfer.setBounds(201, 66, 89, 23);
				search.add(chkTransfer);
				
				JLabel lblNewLabel = new JLabel("Account Information");
				lblNewLabel.setBounds(368, 29, 135, 14);
				search.add(lblNewLabel);
				
				JLabel lblCustomerName = new JLabel("Customer name:");
				lblCustomerName.setHorizontalAlignment(SwingConstants.RIGHT);
				lblCustomerName.setBounds(364, 57, 83, 14);
				search.add(lblCustomerName);
				
				JLabel lblNewLabel_2 = new JLabel("email:");
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_2.setBounds(401, 93, 46, 14);
				search.add(lblNewLabel_2);
				
				JLabel lblNewLabel_3 = new JLabel("Gender:");
				lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_3.setBounds(401, 133, 46, 14);
				search.add(lblNewLabel_3);
				
				lblforName = new JLabel("");
				lblforName.setBorder(new LineBorder(new Color(0, 0, 0)));
				lblforName.setBounds(487, 57, 102, 14);
				search.add(lblforName);
				
				lblForEmail = new JLabel("");
				lblForEmail.setBorder(new LineBorder(new Color(0, 0, 0)));
				lblForEmail.setBounds(487, 93, 141, 14);
				search.add(lblForEmail);
				
				lblforGender = new JLabel("");
				lblforGender.setBorder(new LineBorder(new Color(0, 0, 0)));
				lblforGender.setBounds(487, 133, 67, 14);
				search.add(lblforGender);
				
				chkReceived = new JCheckBox("Received");
				chkReceived.setSelected(true);
				chkReceived.setBounds(157, 103, 97, 23);
				//search.add(chkReceived);

			}
			scrollPane = new JScrollPane();
			scrollPane.setToolTipText("");
			scrollPane.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

	                

				}
			});
			scrollPane.setBounds(20, 238, 703, 305);
			panel.add(scrollPane);
			
			tblReport = new JTable();
			tblReport.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
				}
			});
			scrollPane.setViewportView(tblReport);
		}
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
		fillAccount();
		createtable();
		fillDeposit();
	}
	
	
	



 
    public void clearItem()
    {
        cboAccountID.setSelectedIndex(0);
    }

    public void setColumnWidth(int index , int width)
   {
       DefaultTableColumnModel tcm = (DefaultTableColumnModel)tblReport.getColumnModel();
       TableColumn tc = tcm.getColumn(index);
       tc.setPreferredWidth(width);
   }
    public void createtable()
    {
         dtm.addColumn("Deposit ID");
        dtm.addColumn("Amount");
        dtm.addColumn("Date");
        dtm.addColumn("Account");
        dtm.addColumn("Account");
        dtm.addColumn("Staff");
        dtm.addColumn("Type");
        tblReport.setModel(dtm);
        setColumnWidth(0,40);
         setColumnWidth(1,40);
         setColumnWidth(2,80);
          setColumnWidth(3,40);
           setColumnWidth(4,60);
           setColumnWidth(5,40);
           setColumnWidth(6,40);
    }
    public void deleteRow()
    {
    	
    	dtm.removeRow(selectedRow);

        
        tblReport.setModel(dtm);
        
    }
    public void fillDeposit()
    {
    	clear();
        String strdataitem[]=new String[7];
        try{
            Statement ste = (Statement) con.createStatement();
            String str = "select  d.id, d.amount, d.date,d.accountno,d.accountno,d.staffno from Deposit d union "
            		+ "select w.id,w.amount,w.date,w.accountno as received,w.accountno,w.staffno from withdraw w"
            		+ "  UNION "
             		+ "(select * "
             		+ "from transfer)"
            		;
            ResultSet rs = ste.executeQuery(str);
            while(rs.next())
            {
                strdataitem[0]=rs.getString(1);
                strdataitem[1]=rs.getString(2);
                strdataitem[2]=rs.getString(3);
                strdataitem[3]=rs.getString(4);
                strdataitem[4]=rs.getString(5);
                strdataitem[5]=rs.getString(6);
                if(rs.getString(1).startsWith("D")) {
                    strdataitem[6]= "Deposit" ;       
                }else if(rs.getString(1).startsWith("W")) {
                	 strdataitem[6]= "Withdraw" ;  
                }else {
                	 strdataitem[6]= "Transfer" ;  
                }
         
                dtm.addRow(strdataitem);
            }
            tblReport.setModel(dtm);
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
        }
    }
    public void fillAccount()
    {
        String str[]=mySQLQueries.getIDForChoice("account");
        cboAccountID.addItem("-Select-");
        for(int i=0;i<str.length;i++)
        	cboAccountID.addItem(str[i].toString());				
    }
    public void fillData(String sql)
    {
    	
        String strdataitem[]=new String[8];
        try{
            Statement ste = (Statement) con.createStatement();
            while(dtm.getRowCount()>0) {
            	dtm.removeRow(0);
            }
//            String str = "select * from transfer";
            System.out.println("sql :" + sql);
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next())
            {
            	  strdataitem[0]=rs.getString(1);
                  strdataitem[1]=rs.getString(2);
                  strdataitem[2]=rs.getString(3);
                  strdataitem[3]=rs.getString(4);
                  strdataitem[4]=rs.getString(5);
                  strdataitem[5]=rs.getString(6);
                  if(rs.getString(1).startsWith("D")) {
                      strdataitem[6]= "Deposit" ;       
                  }else if(rs.getString(1).startsWith("W")) {
                  	 strdataitem[6]= "Withdraw" ;  
                  }else {
                  	 strdataitem[6]= "Transfer" ;  
                  }
                  if(chkDeposit.isSelected() && rs.getString(1).startsWith("D")) {
                	  dtm.addRow(strdataitem);                	  
                  }
                  if(chkWithdraw.isSelected() && rs.getString(1).startsWith("W") ) {
                	  dtm.addRow(strdataitem);         
                  }
                  if(chkTransfer.isSelected() && rs.getString(1).startsWith("T") ) {
                	  dtm.addRow(strdataitem);         
                  }
//                  if(chkSend.isSelected() && rs.getString(4).equals(cboAccountID.getSelectedItem())) {
//                	  dtm.addRow(strdataitem);    
//                  }
//                  if(chkReceived.isSelected() && rs.getString(5).equals(cboAccountID.getSelectedItem())) {
//                	  dtm.addRow(strdataitem);    
//                  }
            }
            tblReport.setModel(dtm);
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
        }
    }
    public void clear()
    {
        while(dtm.getRowCount()>0)
            dtm.removeRow(0);
        tblReport.setModel(dtm);
        vid.removeAllElements();
        vamount.removeAllElements();
    }
}
