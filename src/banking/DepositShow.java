 package banking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
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
import javax.swing.DefaultComboBoxModel;

public class DepositShow extends JInternalFrame {
	private JLabel lblforDepositID;
	private JButton btndelete;
	private JButton btnupdate;
	private JComboBox cboStaffID;
	String strdataitem[]=new String[9];
	String strquery[]=new String[5];
	private JTable tbldeposit;
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
   private JRadioButton rdoMonth;
   private JRadioButton rdoYear;
   private JRadioButton rdoBoth;
   private JButton btnSearch;
   private JButton btnShowAll;
   private JComboBox cboYear;
   private JComboBox cboMonth;




	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DepositShow dialog = new DepositShow();
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
	public DepositShow() throws ClassNotFoundException {
		setTitle("Deposit Process");
		Border blackline = BorderFactory.createLineBorder(Color.black);
//		setBounds(-100, -100, 791, 685);
		setBounds(0, 0, Constants.c_width, Constants.c_height);

//		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
//		 int centerX = (int) (screenDimension.getWidth() - getWidth()) / 2;
//	     int centerY = (int) (screenDimension.getHeight() - getHeight()) / 2;
//	     setLocation(centerX, centerY);
	     
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(10, 11, 683, 435);
			getContentPane().add(panel);
			panel.setLayout(null);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Deposit Info:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_1.setBounds(20, 11, 658, 148);
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					JLabel lblstaffId = new JLabel("Staff ID:");
					lblstaffId.setHorizontalAlignment(SwingConstants.LEFT);
					lblstaffId.setBounds(10, 70, 85, 14);
					panel_1.add(lblstaffId);
				}
				{
					JLabel lblDepositID = new JLabel("Deposit ID:");
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
					cboStaffID.setBounds(120, 66, 197, 22);
					panel_1.add(cboStaffID);
				}
				{
					lblforDepositID = new JLabel("");
					lblforDepositID.setBounds(120, 25, 197, 22);
					lblforDepositID.setBorder(blackline);
					panel_1.add(lblforDepositID);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Amount:");
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
					lblNewLabel_2.setBounds(353, 33, 64, 14);
					panel_1.add(lblNewLabel_2);
				}
				{
					btndelete = new JButton("Delete");
					btndelete.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

			                if(tbldeposit.getSelectedRow()<0)
			                    JOptionPane.showMessageDialog(null, "Please select row to delete.");
			                else
			                {
			                    deleteRow();
			                    try {
			                        String id = lblforDepositID.getText().toString();
			                        if(JOptionPane.showConfirmDialog(null, "Are you Sure Delete?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
			                        {
			                        	mySQLQueries.deleteRecord("deposit", id);
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
					btndelete.setBounds(417, 114, 89, 23);
					panel_1.add(btndelete);
				}
				{
					btnupdate = new JButton("Update");
					btnupdate.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(tbldeposit.getSelectedRow()<0)
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
					btnupdate.setBounds(538, 114, 89, 23);
					panel_1.add(btnupdate);
				}
				{
					lblAccountId = new JLabel("Account ID:");
					lblAccountId.setBounds(10, 119, 85, 14);
					panel_1.add(lblAccountId);
				}
				{
					cboAccountID = new JComboBox();
					cboAccountID.setBounds(120, 115, 197, 22);
					panel_1.add(cboAccountID);
				}
				{
					lblForDate = new JLabel("");
					lblForDate.setBorder(new LineBorder(new Color(0, 0, 0)));
					lblForDate.setBounds(430, 66, 197, 22);
					panel_1.add(lblForDate);
				}
				{
					lblDate = new JLabel("Date:");
					lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
					lblDate.setBounds(332, 70, 85, 14);
					panel_1.add(lblDate);
				}
				
				txtamount = new JLabel("");
				txtamount.setBorder(new LineBorder(new Color(0, 0, 0)));
				txtamount.setBounds(430, 25, 197, 22);
				panel_1.add(txtamount);
			}
			
			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBounds(219, 386, 290, 49);
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
			scrollPane.setBounds(20, 227, 653, 148);
			panel.add(scrollPane);
			
			tbldeposit = new JTable();
			tbldeposit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int r = tbldeposit.getSelectedRow();
					selectedRow = r;
					lblforDepositID.setText(tbldeposit.getValueAt(r, 0).toString());
					txtamount.setText(tbldeposit.getValueAt(r, 1).toString());
	                cboAccountID.setSelectedItem(tbldeposit.getValueAt(r, 2));
	                cboStaffID.setSelectedItem(tbldeposit.getValueAt(r, 3));
	                lblForDate.setText(tbldeposit.getValueAt(r, 4).toString());
				}
			});
			scrollPane.setViewportView(tbldeposit);
			
			rdoMonth = new JRadioButton("Month");
			rdoMonth.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					if(rdoYear.isSelected() && rdoMonth.isSelected() && rdoBoth.isSelected()) {
//						cboMonth.setVisible(true);
//				        cboYear.setVisible(true);
//					}
					if(rdoMonth.isSelected()) {
						cboMonth.setVisible(true);
				        cboYear.setVisible(false);
					}
//					else if(rdoYear.isSelected() && rdoBoth.isSelected()) {
//						cboMonth.setVisible(false);
//				        cboYear.setVisible(true);
//					}
//					
					else {
						cboMonth.setVisible(false);
				        cboYear.setVisible(false);
					}
				}
			});
			rdoMonth.setBounds(20, 160, 109, 27);
			panel.add(rdoMonth);
			
			rdoYear = new JRadioButton("Year");
			rdoYear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					if(rdoYear.isSelected() && rdoMonth.isSelected() && rdoBoth.isSelected()) {
//						cboMonth.setVisible(true);
//				        cboYear.setVisible(true);
//					}
					if(rdoYear.isSelected()) {
						cboMonth.setVisible(false);
				        cboYear.setVisible(true);
					}
//					else if(rdoMonth.isSelected() && rdoBoth.isSelected()) {
//						cboMonth.setVisible(true);
//				        cboYear.setVisible(false);
//					}
					
					else {
						cboMonth.setVisible(false);
				        cboYear.setVisible(false);
					}
				}
			});
			rdoYear.setBounds(131, 160, 109, 27);
			panel.add(rdoYear);
			
			rdoBoth = new JRadioButton("Year & Month");
			rdoBoth.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rdoBoth.isSelected()) {
						cboMonth.setVisible(true);
				        cboYear.setVisible(true);
				        rdoMonth.setSelected(true);
				        rdoYear.setSelected(true);
					}
					else {
						cboMonth.setVisible(false);
				        cboYear.setVisible(false);
				        rdoMonth.setSelected(false);
				        rdoYear.setSelected(false);
					}
				}
			});
			rdoBoth.setBounds(271, 160, 109, 27);
			panel.add(rdoBoth);
			
			btnSearch = new JButton("Search");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rdoMonth.isSelected()) {
			            if(cboMonth.getSelectedIndex()==0) {
			                JOptionPane.showMessageDialog(null,"Please choose Month");
			                cboMonth.requestFocus();
			            } else {
			            	String str = "select * from deposit where Month(date)="+cboMonth.getSelectedIndex();
			            	fillDeposit(str);
			            }
			        } else if(rdoYear.isSelected()) {
			        	
			            if(cboYear.getSelectedIndex()==0) {
			                JOptionPane.showMessageDialog(null,"Please choose Year");
			                cboYear.requestFocus();
			            } else {
//			            	dtm.removeRow(0);
//			            	tbltransfer.removeAll();
			            	String str = "select * from withdraw where Year(date)="+cboYear.getSelectedItem().toString();
			            	fillDeposit(str);
			            }
			        } else if (rdoBoth.isSelected()) {
			        	
			            if(cboMonth.getSelectedIndex()==0) {
			                JOptionPane.showMessageDialog(null,"Please choose Month");
			                cboMonth.requestFocus();
			            } else if(cboYear.getSelectedIndex()==0) {
			            	JOptionPane.showMessageDialog(null,"Please choose Year");
			                cboMonth.requestFocus();
			            } else {
			            	String str = "select * from deposit where Month(date)="+cboMonth.getSelectedIndex()+" and Year(date)="+cboYear.getSelectedItem().toString()+"";
			            	fillDeposit(str);
			            }
			        }
				}
			});
			btnSearch.setBounds(278, 191, 87, 27);
			panel.add(btnSearch);
			
			btnShowAll = new JButton("Show All");
			btnShowAll.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fillDeposit("select * from deposit");
				    cboMonth.setSelectedIndex(0);
				    cboYear.setSelectedIndex(0);
				}
			});
			btnShowAll.setBounds(391, 191, 87, 27);
			panel.add(btnShowAll);
			
			cboMonth = new JComboBox();
			cboMonth.setModel(new DefaultComboBoxModel(new String[] {"-Selected-", "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
			cboMonth.setBounds(25, 191, 96, 27);
			panel.add(cboMonth);
			
			cboYear = new JComboBox();
			cboYear.setModel(new DefaultComboBoxModel(new String[] {"-Selected-"}));
			cboYear.setBounds(131, 191, 109, 27);
			panel.add(cboYear);
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
		fillDeposit("select * from deposit");
		fillYearData();
		cboMonth.setVisible(false);
        cboYear.setVisible(false);
	}
	
    public void clearItem()
    {
        lblforDepositID.setText("");
        lblForDate.setText("");
        txtamount.setText("");
        cboStaffID.setSelectedIndex(0);
        cboAccountID.setSelectedIndex(0);
    }

    public void setColumnWidth(int index , int width)
   {
       DefaultTableColumnModel tcm = (DefaultTableColumnModel)tbldeposit.getColumnModel();
       TableColumn tc = tcm.getColumn(index);
       tc.setPreferredWidth(width);
   }
    public void createtable()
    {
         dtm.addColumn("Deposit ID");
        dtm.addColumn("Amount");
        dtm.addColumn("Account No");
        dtm.addColumn("Staff");
        dtm.addColumn("Date");
        tbldeposit.setModel(dtm);
        setColumnWidth(0,5);
         setColumnWidth(1,60);
         setColumnWidth(2,75);
          setColumnWidth(3,40);
           setColumnWidth(4,180);
    }
    public void deleteRow()
    {
    	
    	dtm.removeRow(selectedRow);

        
        tbldeposit.setModel(dtm);
        
    }
    public void fillDeposit(String sql)
    {
        String strdataitem[]=new String[5];
//        String strdataitem[]=new String[5];
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
            tbldeposit.setModel(dtm);
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
        tbldeposit.setModel(dtm);
        vid.removeAllElements();
        vamount.removeAllElements();
    }
    public void fillYear()
    {
       String strdataitem[]=new String[7];
       try{
         Statement ste = (Statement) con.createStatement();
         String str = "select * from transfer where Year(date)="+cboYear.getSelectedItem().toString();
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
         tbldeposit.setModel(dtm);
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
         String str = "select * from transfer where Month(date)="+cboMonth.getSelectedIndex();
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
         tbldeposit.setModel(dtm);
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
         String str = "select * from transfer where Month(date)="+cboMonth.getSelectedIndex()+" and Year(date)="+cboYear.getSelectedItem().toString()+"";
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
         tbldeposit.setModel(dtm);
       }
       catch(SQLException sqle)
       {
         System.out.println(sqle);
       }
    }
    
    public void fillYearData()
    {
        cboYear.addItem("-Selected-");
        for(int i=2010 ; i<=2050 ; i++ )
            cboYear.addItem(i);
    }
}
