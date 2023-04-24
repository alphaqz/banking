package banking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
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
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TransferList extends JInternalFrame {
	private JTable tbltransfer;
	private JButton btnPrint;
	private JButton btnClose;
	DefaultTableModel dtm = new DefaultTableModel();
	Statement ste = null ;
	Connection con = null ;
	private JComboBox cboyear;
	private JComboBox cbomonth;
	private JRadioButton rdomonth;
	private JRadioButton rdoyear;
	private JRadioButton rdoAll;
	private JPanel panel_1;
	private JLabel lblTID;
	
	Border backline=BorderFactory.createLineBorder(Color.black);
	private JLabel lblAmount;
	private JLabel lblNew;
	private JLabel lblDate;
	private JLabel date;
	private JLabel lblTransferAccountNo;
	private JLabel lblTAcc;
	private JLabel lblReceiveAccountNo;
	private JLabel lblRAcc;
	private JButton btnDelete;
	
	int selectedRow;

//	LocalDate localDate = currDate.toLocalDate();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TransferList dialog = new TransferList();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TransferList() throws ClassNotFoundException {
		setTitle("Transfer Info List");
//		setBounds(100, 100, 642, 476);
		setBounds(0, 0, Constants.c_width, Constants.c_height);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(10, 11, 683, 415);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 241, 663, 115);
		panel.add(scrollPane);
		
		tbltransfer = new JTable();
		tbltransfer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int r = tbltransfer.getSelectedRow();
				selectedRow = r;
				lblTID.setText(tbltransfer.getValueAt(r, 0).toString());
				lblAmount.setText(tbltransfer.getValueAt(r, 1).toString());
                lblDate.setText(tbltransfer.getValueAt(r, 2).toString());
                lblRAcc.setText(tbltransfer.getValueAt(r, 3).toString());
                lblTAcc.setText(tbltransfer.getValueAt(r, 4).toString());
			}
		});
		scrollPane.setViewportView(tbltransfer);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confrim",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
				{	
					dispose();
				}
			}
		});
		btnClose.setBounds(345, 371, 90, 35);
		panel.add(btnClose);
		
		btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
		            tbltransfer.print();
		        }catch(Exception e1) {
		            JOptionPane.showMessageDialog(null, e1);
		        }
			}
		});
		btnPrint.setBounds(232, 371, 90, 35);
		panel.add(btnPrint);
		
		rdomonth = new JRadioButton("Monthly");
		rdomonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if(rdomonth.isSelected()==true) {
		        	cbomonth.setVisible(true);
			        cboyear.setVisible(false);
		        }
		        else {
		        	cbomonth.setVisible(false);
			        cboyear.setVisible(false);
		        }
			}
		});
		rdomonth.setBounds(10, 158, 109, 27);
		panel.add(rdomonth);
		
		rdoyear = new JRadioButton("Yearly");
		rdoyear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdoyear.isSelected()==true) {
					cbomonth.setVisible(false);
			        cboyear.setVisible(true);
		        }
		        else {
		        	cbomonth.setVisible(false);
			        cboyear.setVisible(false);
		        }
		        
			}
		});
		rdoyear.setBounds(121, 158, 109, 27);
		panel.add(rdoyear);
		
		rdoAll = new JRadioButton("Monthly & Yearly");
		rdoAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if(rdoAll.isSelected()==true) {
		        	cbomonth.setVisible(true);
			        cboyear.setVisible(true);
		        }
		        else {
		        	cbomonth.setVisible(false);
			        cboyear.setVisible(false);
		        }
			}
		});
		rdoAll.setBounds(232, 158, 137, 27);
		panel.add(rdoAll);
		
		cbomonth = new JComboBox();
		cbomonth.setModel(new DefaultComboBoxModel(new String[] { "-Selected-", "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
		cbomonth.setBounds(10, 194, 82, 27);
		panel.add(cbomonth);
		
		cboyear = new JComboBox();
		cboyear.setBounds(121, 194, 82, 27);
		panel.add(cboyear);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdomonth.isSelected()) {
		            if(cbomonth.getSelectedIndex()==0) {
		                JOptionPane.showMessageDialog(null,"Please choose Month");
		                cbomonth.requestFocus();
		            } else {
		            	String str = "select * from transfer where Month(date)="+cbomonth.getSelectedIndex();
		            	fillTransferData(str);
		            }
		        } else if(rdoyear.isSelected()) {
		        	
		            if(cboyear.getSelectedIndex()==0) {
		                JOptionPane.showMessageDialog(null,"Please choose Year");
		                cboyear.requestFocus();
		            } else {
//		            	dtm.removeRow(0);
//		            	tbltransfer.removeAll();
		            	String str = "select * from transfer where Year(date)="+cboyear.getSelectedItem().toString();
		            	fillTransferData(str);
		            }
		        } else if (rdoAll.isSelected()) {
		        	
		            if(cbomonth.getSelectedIndex()==0) {
		                JOptionPane.showMessageDialog(null,"Please choose Month");
		                cbomonth.requestFocus();
		            } else if(cboyear.getSelectedIndex()==0) {
		            	JOptionPane.showMessageDialog(null,"Please choose Year");
		                cbomonth.requestFocus();
		            } else {
		            	String str = "select * from transfer where Month(date)="+cbomonth.getSelectedIndex()+" and Year(date)="+cboyear.getSelectedItem().toString()+"";
		                fillTransferData(str);
		            }
		        }
			}
		});
		btnSearch.setBounds(243, 194, 87, 27);
		panel.add(btnSearch);
		
		JButton btnAll = new JButton("Show All");
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTransferData("select * from transfer");
			    cbomonth.setSelectedIndex(0);
			    cboyear.setSelectedIndex(0);
			}
		});
		btnAll.setBounds(361, 195, 87, 27);
		panel.add(btnAll);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Transfer Info:", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 0, 668, 159);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Transfer ID:");
		lblNewLabel.setBounds(10, 35, 137, 19);
		panel_1.add(lblNewLabel);
		
		lblTID = new JLabel("");
		
		lblTID.setBorder(backline);
		lblTID.setBounds(185, 29, 124, 29);
		panel_1.add(lblTID);
		
		lblAmount = new JLabel("");
		lblAmount.setBorder(backline);
		lblAmount.setBounds(510, 29, 124, 29);
		panel_1.add(lblAmount);
		
		lblNew = new JLabel("Amount :");
		lblNew.setBounds(417, 35, 71, 19);
		panel_1.add(lblNew);
		
		lblDate = new JLabel("");
		lblDate.setBorder(backline);
		lblDate.setBounds(510, 73, 124, 29);
		panel_1.add(lblDate);
		
		date = new JLabel("Date  :");
		date.setBounds(417, 79, 71, 19);
		panel_1.add(date);
		
		lblTransferAccountNo = new JLabel("Transfered Account No :");
		lblTransferAccountNo.setBounds(10, 79, 137, 19);
		panel_1.add(lblTransferAccountNo);
		
		lblTAcc = new JLabel("");
		lblTAcc.setBorder(backline);
		lblTAcc.setBounds(185, 73, 124, 29);
		panel_1.add(lblTAcc);
		
		lblReceiveAccountNo = new JLabel("Received Account No :");
		lblReceiveAccountNo.setBounds(10, 121, 137, 19);
		panel_1.add(lblReceiveAccountNo);
		
		lblRAcc = new JLabel("");
		lblRAcc.setBorder(backline);
		lblRAcc.setBounds(185, 115, 124, 29);
		panel_1.add(lblRAcc);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tbltransfer.getSelectedRow()<0)
                    JOptionPane.showMessageDialog(null, "Please select row to delete.");
                else
                {
                    deleteRow();
                    try {
                        String id = lblTID.getText().toString();
                        if(JOptionPane.showConfirmDialog(null, "Are you Sure Delete?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
                        {
                        	mySQLQueries.deleteRecord("transfer", id);
                        }
                        else
                        {
                        	JOptionPane.showMessageDialog(null, "Fail to delete record","Cannot Update",JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch(Exception sqle) {
                        sqle.printStackTrace();
                    }
                    clearItem();
                    
                }
			}
		});
		btnDelete.setBounds(544, 121, 87, 27);
		panel_1.add(btnDelete);
		
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
        fillTransferData("select * from transfer");
        fillYear();
        cbomonth.setVisible(false);
        cboyear.setVisible(false);
	}

    public void createtable()
   {
       dtm.addColumn("Transfer ID");
       dtm.addColumn("Amount");
       dtm.addColumn("Date");
       dtm.addColumn("Received Account");
       dtm.addColumn("Transfered Account");
       dtm.addColumn("Staff");
       
       tbltransfer.setModel(dtm);
       setColumnWidth(0,400);
       setColumnWidth(1,300);
       setColumnWidth(2,400);
       setColumnWidth(3,550);
       setColumnWidth(4,550);
       setColumnWidth(5,340);
   }

    public void setColumnWidth(int index , int width)
    {
        DefaultTableColumnModel tcm = (DefaultTableColumnModel)tbltransfer.getColumnModel();
        TableColumn tc = tcm.getColumn(index);
        tc.setPreferredWidth(width);
    }


    public void fillTransferData(String sql)
    {
    	
        String strdataitem[]=new String[7];
        try{
            Statement ste = (Statement) con.createStatement();
            while(dtm.getRowCount()>0) {
            	dtm.removeRow(0);
            }
//            String str = "select * from transfer";
            ResultSet rs = ste.executeQuery(sql);
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
            tbltransfer.setModel(dtm);
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
        }
    }   
    public void fillTransferForYear()
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
         tbltransfer.setModel(dtm);
       }
       catch(SQLException sqle)
       {
         System.out.println(sqle);
       }
    }

    public void fillTransferForMonth()
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
         tbltransfer.setModel(dtm);
       }
       catch(SQLException sqle)
       {
         System.out.println(sqle);
       }
    }
    
    public void fillTransferForMonthAndYear()
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
         tbltransfer.setModel(dtm);
       }
       catch(SQLException sqle)
       {
         System.out.println(sqle);
       }
    }
    public void fillYear()
    {
        cboyear.addItem("-Selected-");
        for(int i=2010 ; i<=2050 ; i++ )
            cboyear.addItem(i);
    }
    public void deleteRow()
    {
    	
    	dtm.removeRow(selectedRow);

        
        tbltransfer.setModel(dtm);
        
    }
    public void clearItem()
    {
        lblTID.setText("");
        lblDate.setText("");
        lblAmount.setText("");
        lblRAcc.setText("");
        lblTAcc.setText("");
    }
}