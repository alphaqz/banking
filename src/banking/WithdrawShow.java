package banking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
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
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class WithdrawShow extends JInternalFrame {
	private JLabel lblforWithdrawID;
	private JButton btndelete;
	private JButton btnupdate;
	private JComboBox cboStaffID;
	String strdataitem[]=new String[9];
	String strquery[]=new String[5];
	private JTable tblwithdraw;
	private JScrollPane scrollPane;
	DefaultTableModel dtm = new DefaultTableModel();
	Vector vid = new Vector();
	Vector vamount = new Vector();
	String str[],stri[];
	Statement ste = null ;
	Connection con = null ;
	List<String> staffIdList = new ArrayList<String>();  
	boolean b = false ;
   date d=new date();
   int selectedRow;
   private JLabel lblAccountId;
   private JComboBox cboAccountID;
   private JLabel lblForDate;
   private JLabel lblDate;
   private JLabel txtamount;
   private JComboBox cboyear;
   private JRadioButton rboyear;
   private JRadioButton rbomonth;
   private JRadioButton rboBoth;
   private JComboBox cbomonth;
   private JButton btnBoth;
   private JButton btnSearch;




	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WithdrawShow dialog = new WithdrawShow();
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
	public WithdrawShow() throws ClassNotFoundException {
		setTitle("Withdraw Process");
		Border blackline = BorderFactory.createLineBorder(Color.black);
//		setBounds(0, 0, 708, 484);
		setBounds(0, 0, 719, 462);

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		 int centerX = (int) (screenDimension.getWidth() - getWidth()) / 2;
	     int centerY = (int) (screenDimension.getHeight() - getHeight()) / 2;
	     setLocation(centerX, centerY);
	     
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(10, 11, 680, 419);
			getContentPane().add(panel);
			panel.setLayout(null);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Withdraw Info:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_1.setBounds(20, 11, 725, 148);
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					JLabel lblstaffId = new JLabel("Staff ID:");
					lblstaffId.setHorizontalAlignment(SwingConstants.RIGHT);
					lblstaffId.setBounds(10, 70, 58, 14);
					panel_1.add(lblstaffId);
				}
				{
					JLabel lblDepositID = new JLabel("Withdraw ID:");
					lblDepositID.setBounds(10, 25, 85, 14);
					panel_1.add(lblDepositID);
				}
				{
					cboStaffID = new JComboBox();
					cboStaffID.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(cboStaffID.getSelectedIndex()<=0)
			                    clearItem();
			                else
			                {
//			                    strquery = mySQLQueries.getItemData(cboitemid.getSelectedItem().toString());
//			                    strdataitem[1]=strquery[0];//itemid
//			                    strdataitem[2]=strquery[1];//itemname
//			                    lblitemname.setText(strdataitem[2]);
//			                    strdataitem[8]=(!strquery[4].equals(""))?strquery[4]:"-";//remark
//			                    strquery = mySQLQueries.getMerchandise(strquery[3]);//merid to brandid,typeid
//			                    strdataitem[6]=mySQLQueries.getBrandName(strquery[0]);//brandid to brandname
//			                    strdataitem[7]=mySQLQueries.getTypeName(strquery[1]);
//			                    lblitemtype.setText(strdataitem[7]);
//			                    txtprice.requestFocus();
			                }

						}
					});
					cboStaffID.setBounds(87, 66, 197, 22);
					panel_1.add(cboStaffID);
				}
				{
					lblforWithdrawID = new JLabel("");
					lblforWithdrawID.setBounds(87, 25, 197, 22);
					lblforWithdrawID.setBorder(blackline);
					panel_1.add(lblforWithdrawID);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Amount:");
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
					lblNewLabel_2.setBounds(338, 33, 85, 14);
					panel_1.add(lblNewLabel_2);
				}
				{
					btndelete = new JButton("Delete");
					btndelete.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

			                if(tblwithdraw.getSelectedRow()<0)
			                    JOptionPane.showMessageDialog(null, "Please select row to delete.");
			                else
			                {
			                    deleteRow();
			                    try {
			                        String id = lblforWithdrawID.getText().toString();
			                        if(JOptionPane.showConfirmDialog(null, "Are you Sure Delete?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
			                        {
			                        	mySQLQueries.deleteRecord("withdraw", id);
			                        }
			                        else
			                        {
			                        	JOptionPane.showMessageDialog(null, "Fail to delete record","Cannot Update",JOptionPane.INFORMATION_MESSAGE);
			                        }
			                    } catch(Exception sqle) {
			                        sqle.printStackTrace();
			                    }
			                    clearItem();
			                    cboStaffID.setSelectedIndex(0);
			                }

						}
					});
					btndelete.setBounds(436, 114, 89, 23);
					panel_1.add(btndelete);
				}
				{
					btnupdate = new JButton("Update");
					btnupdate.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(tblwithdraw.getSelectedRow()<0)
			                {
			                    JOptionPane.showMessageDialog(null, "Please select row to update");
			                }
			                else
			                {
			                    deleteRow();//delete selected row
			                    //itemaddmethod();//update selected row			                   
			                    clearItem();//clear labels
			                    cboStaffID.setSelectedIndex(0);//focus at cboitemid
			                }
						}
					});
					btnupdate.setBounds(544, 114, 89, 23);
					panel_1.add(btnupdate);
				}
				{
					lblAccountId = new JLabel("Account ID:");
					lblAccountId.setBounds(10, 119, 58, 14);
					panel_1.add(lblAccountId);
				}
				{
					cboAccountID = new JComboBox();
					cboAccountID.setBounds(87, 115, 197, 22);
					panel_1.add(cboAccountID);
				}
				{
					lblForDate = new JLabel("");
					lblForDate.setBorder(new LineBorder(new Color(0, 0, 0)));
					lblForDate.setBounds(436, 66, 197, 22);
					panel_1.add(lblForDate);
				}
				{
					lblDate = new JLabel("Date:");
					lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
					lblDate.setBounds(338, 70, 85, 14);
					panel_1.add(lblDate);
				}
				
				txtamount = new JLabel("");
				txtamount.setBorder(new LineBorder(new Color(0, 0, 0)));
				txtamount.setBounds(436, 25, 197, 22);
				panel_1.add(txtamount);
			}
			
			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBounds(194, 365, 290, 49);
			panel.add(panel_1);
			
			JButton btnSave = new JButton("Save");
		
			btnSave.setBounds(31, 11, 89, 27);
			panel_1.add(btnSave);
			
			JButton btnClose = new JButton("Close");
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confrim",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
					{	
						dispose();
					}
				}
			});
			btnClose.setBounds(159, 11, 89, 27);
			panel_1.add(btnClose);
			
			scrollPane = new JScrollPane();
			scrollPane.setToolTipText("");
			scrollPane.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

	                

				}
			});
			scrollPane.setBounds(20, 230, 648, 112);
			panel.add(scrollPane);
			
			tblwithdraw = new JTable();
			tblwithdraw.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int r = tblwithdraw.getSelectedRow();
					selectedRow = r;
					lblforWithdrawID.setText(tblwithdraw.getValueAt(r, 0).toString());
					txtamount.setText(tblwithdraw.getValueAt(r, 1).toString());
	                cboAccountID.setSelectedItem(tblwithdraw.getValueAt(r, 2));
	                cboStaffID.setSelectedItem(tblwithdraw.getValueAt(r, 3));
	                lblForDate.setText(tblwithdraw.getValueAt(r, 4).toString());
				}
			});
			scrollPane.setViewportView(tblwithdraw);
			
			rboyear = new JRadioButton("Year");
			rboyear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cbomonth.setVisible(false);
			        cboyear.setVisible(true);
				}
			});
			rboyear.setBounds(156, 164, 109, 27);
			panel.add(rboyear);
			
			rbomonth = new JRadioButton("Month");
			rbomonth.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cbomonth.setVisible(true);
			        cboyear.setVisible(false);
				}
			});
			rbomonth.setBounds(20, 164, 109, 27);
			panel.add(rbomonth);
			
			rboBoth = new JRadioButton("Year & Month");
			rboBoth.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cbomonth.setVisible(true);
			        cboyear.setVisible(true);
				}
			});
			rboBoth.setBounds(290, 164, 109, 27);
			panel.add(rboBoth);
			
			cboyear = new JComboBox();
			cboyear.setBounds(156, 191, 109, 27);
			panel.add(cboyear);
			
			cbomonth = new JComboBox();
			cbomonth.setModel(new DefaultComboBoxModel(new String[] { "-Selected-", "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
			cbomonth.setBounds(20, 191, 109, 27);
			panel.add(cbomonth);
			
			btnSearch = new JButton("Search");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rbomonth.isSelected()) {
			            if(cbomonth.getSelectedIndex()==0) {
			                JOptionPane.showMessageDialog(null,"Please choose Month");
			                cbomonth.requestFocus();
			            } else {
			            	String str = "select * from withdraw where Month(date)="+cbomonth.getSelectedIndex();
			            	fillWithdraw(str);
			            }
			        } else if(rboyear.isSelected()) {
			        	
			            if(cboyear.getSelectedIndex()==0) {
			                JOptionPane.showMessageDialog(null,"Please choose Year");
			                cboyear.requestFocus();
			            } else {
//			            	dtm.removeRow(0);
//			            	tbltransfer.removeAll();
			            	String str = "select * from withdraw where Year(date)="+cboyear.getSelectedItem().toString();
			            	fillWithdraw(str);
			            }
			        } else if (rboBoth.isSelected()) {
			        	
			            if(cbomonth.getSelectedIndex()==0) {
			                JOptionPane.showMessageDialog(null,"Please choose Month");
			                cbomonth.requestFocus();
			            } else if(cboyear.getSelectedIndex()==0) {
			            	JOptionPane.showMessageDialog(null,"Please choose Year");
			                cbomonth.requestFocus();
			            } else {
			            	String str = "select * from withdraw where Month(date)="+cbomonth.getSelectedIndex()+" and Year(date)="+cboyear.getSelectedItem().toString()+"";
			            	fillWithdraw(str);
			            }
			        }
				}
			});
			btnSearch.setMnemonic('S');
			btnSearch.setBounds(290, 191, 87, 27);
			panel.add(btnSearch);
			
			btnBoth = new JButton("Show All");
			btnBoth.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fillWithdraw("select * from withdraw");
				    cbomonth.setSelectedIndex(0);
				    cboyear.setSelectedIndex(0);
				}
			});
			btnBoth.setMnemonic('A');
			btnBoth.setBounds(398, 191, 87, 27);
			panel.add(btnBoth);
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
		fillStaff();
		createtable();
		fillWithdraw("select * from withdraw");
		fillYearData();
        cbomonth.setVisible(false);
        cboyear.setVisible(false);
	}
	
    public void clearItem()
    {
        lblforWithdrawID.setText("");
        lblForDate.setText("");
        txtamount.setText("");
        cboStaffID.setSelectedIndex(0);
        cboAccountID.setSelectedIndex(0);
    }

    public void setColumnWidth(int index , int width)
   {
       DefaultTableColumnModel tcm = (DefaultTableColumnModel)tblwithdraw.getColumnModel();
       TableColumn tc = tcm.getColumn(index);
       tc.setPreferredWidth(width);
   }
    public void createtable()
    {
         dtm.addColumn("Withdraw ID");
        dtm.addColumn("Amount");
        dtm.addColumn("Account No");
        dtm.addColumn("Staff");
        dtm.addColumn("Date");
        tblwithdraw.setModel(dtm);
        setColumnWidth(0,5);
         setColumnWidth(1,60);
         setColumnWidth(2,75);
          setColumnWidth(3,40);
           setColumnWidth(4,180);
    }
    public void deleteRow()
    {
    	
    	dtm.removeRow(selectedRow);

        
        tblwithdraw.setModel(dtm);
        
    }
    public void fillWithdraw(String sql)
    {
        String strdataitem[]=new String[5];
        try{
            Statement ste = (Statement) con.createStatement();
//            String str = "select * from withdraw";
            while(dtm.getRowCount()>0) {
            	dtm.removeRow(0);
            }
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next())
            {
                strdataitem[0]=rs.getString(1);
                strdataitem[1]=rs.getString(2);
                strdataitem[2]=rs.getString(3);
                strdataitem[3]=rs.getString(4);
                strdataitem[4]=rs.getString(5);
                dtm.addRow(strdataitem);
            }
            tblwithdraw.setModel(dtm);
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
	public void fillStaff()
    {
        String str[]=mySQLQueries.getNameForChoice("staff");
        String temp[] = mySQLQueries.getIDForChoice("staff");
        staffIdList = Arrays.asList(temp);
        cboStaffID.addItem("-Select-");
        for(int i=0;i<str.length;i++)
        	cboStaffID.addItem(temp[i].toString());				
    }
    public void clear()
    {
        cboStaffID.setSelectedIndex(0);
        while(dtm.getRowCount()>0)
            dtm.removeRow(0);
        tblwithdraw.setModel(dtm);
        vid.removeAllElements();
        vamount.removeAllElements();
    }
    public void fillYear()
    {
       String strdataitem[]=new String[7];
       try{
         Statement ste = (Statement) con.createStatement();
         String str = "select * from transfer where Year(date)="+cboyear.getSelectedItem().toString();
         ResultSet rs = ste.executeQuery(str);
         while(rs.next())
         {
           strdataitem[0]=rs.getString(1);
           strdataitem[1]=rs.getString(2);
           strdataitem[2]=rs.getString(3);
           strdataitem[3]=rs.getString(4);
           strdataitem[4]=rs.getString(5);
           strdataitem[5]=rs.getString(6);
                    
           dtm.addRow(strdataitem);
         }
         tblwithdraw.setModel(dtm);
       }
       catch(SQLException sqle)
       {
         System.out.println(sqle);
       }
    }

    public void fillMonth()
    {
       String strdataitem[]=new String[7];
       try{
         Statement ste = (Statement) con.createStatement();
         String str = "select * from transfer where Month(date)="+cbomonth.getSelectedIndex();
         ResultSet rs = ste.executeQuery(str);
         while(rs.next())
         {
           strdataitem[0]=rs.getString(1);
           strdataitem[1]=rs.getString(2);
           strdataitem[2]=rs.getString(3);
           strdataitem[3]=rs.getString(4);
           strdataitem[4]=rs.getString(5);
           strdataitem[5]=rs.getString(6);
                    
           dtm.addRow(strdataitem);
         }
         tblwithdraw.setModel(dtm);
       }
       catch(SQLException sqle)
       {
         System.out.println(sqle);
       }
    }
    
    public void fillMonthAndYear()
    {
       String strdataitem[]=new String[7];
       try{
         Statement ste = (Statement) con.createStatement();
         String str = "select * from transfer where Month(date)="+cbomonth.getSelectedIndex()+" and Year(date)="+cboyear.getSelectedItem().toString()+"";
         ResultSet rs = ste.executeQuery(str);
         while(rs.next())
         {
           strdataitem[0]=rs.getString(1);
           strdataitem[1]=rs.getString(2);
           strdataitem[2]=rs.getString(3);
           strdataitem[3]=rs.getString(4);
           strdataitem[4]=rs.getString(5);
           strdataitem[5]=rs.getString(6);
                    
           dtm.addRow(strdataitem);
         }
         tblwithdraw.setModel(dtm);
       }
       catch(SQLException sqle)
       {
         System.out.println(sqle);
       }
    }
    
    public void fillYearData()
    {
        cboyear.addItem("-Selected-");
        for(int i=2010 ; i<=2050 ; i++ )
            cboyear.addItem(i);
    }
}
