package banking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class CustomerList extends JDialog {
	private JTable tblcustomer;
	private JButton btnPrint;
	private JButton btnClose;
	DefaultTableModel dtm = new DefaultTableModel();
	Statement ste = null ;
	Connection con = null ;
	private JRadioButton rdoAddress;
	private JRadioButton rdoJob;
	private JComboBox cboJob;
	private JComboBox cboAddress;
	private JTextField txtName;
	private JTextField txtEmail;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CustomerList dialog = new CustomerList();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CustomerList() throws ClassNotFoundException {
		setTitle("Customer List");
		setBounds(100, 100, 826, 476);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Customer List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 26, 790, 400);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 135, 770, 191);
		panel.add(scrollPane);
		
		tblcustomer = new JTable();
		scrollPane.setViewportView(tblcustomer);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confrim",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
				{	
					dispose();
				}
			}
		});
		btnClose.setBounds(671, 352, 89, 33);
		panel.add(btnClose);
		
		btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
		            tblcustomer.print();
		        }catch(Exception e1) {
		            JOptionPane.showMessageDialog(null, e1);
		        }
			}
		});
		btnPrint.setBounds(560, 352, 89, 33);
		panel.add(btnPrint);
		
		cboAddress = new JComboBox();
		cboAddress.setBounds(298, 98, 103, 22);
		panel.add(cboAddress);
		
		cboJob = new JComboBox();
		cboJob.setBounds(411, 98, 109, 22);
		panel.add(cboJob);
		
		rdoAddress = new JRadioButton("Address");
		rdoAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdoAddress.isSelected()==false) {
					cboAddress.setVisible(false);
			        cboJob.setVisible(false);
				}
				else {
					cboAddress.setVisible(true);
			        cboJob.setVisible(false);
				}
				rdoJob.setSelected(false);
			}
		});
		rdoAddress.setBounds(298, 61, 109, 23);
		panel.add(rdoAddress);
		
		rdoJob = new JRadioButton("Job");
		rdoJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if(rdoJob.isSelected()==false) {
					cboAddress.setVisible(false);
			        cboJob.setVisible(false);
				}
				else {
					cboAddress.setVisible(false);
			        cboJob.setVisible(true);
				}
		        rdoAddress.setSelected(false);
			}
		});
		rdoJob.setBounds(411, 58, 109, 23);
		panel.add(rdoJob);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdoAddress.isSelected()) {
		            if(cboAddress.getSelectedIndex()==0) {
		                JOptionPane.showMessageDialog(null,"Please choose Address");
		                cboAddress.requestFocus();
		            } else {
		            	String str = "select * from customer where address='"+cboAddress.getSelectedItem().toString()+"'";
		            	fillData(str);
		            }
		        } else if(rdoJob.isSelected()) {
		        	
		            if(cboJob.getSelectedIndex()==0) {
		                JOptionPane.showMessageDialog(null,"Please choose Job");
		                cboJob.requestFocus();
		            } else {
//		            	dtm.removeRow(0);
//		            	tbltransfer.removeAll();
		            	String str = "select * from customer where job='"+cboJob.getSelectedItem().toString()+"'";
		            	fillData(str);
		            }
		        } else if (rdoAddress.isSelected() && rdoJob.isSelected()) {
		        	
		            if(cboAddress.getSelectedIndex()==0) {
		                JOptionPane.showMessageDialog(null,"Please choose Address");
		                cboAddress.requestFocus();
		            } else if(cboJob.getSelectedIndex()==0) {
		            	JOptionPane.showMessageDialog(null,"Please choose Job");
		                cboJob.requestFocus();
		            } else {
		            	String str = "select * from customer where address='"+cboAddress.getSelectedItem().toString()+"' and job='"+cboJob.getSelectedItem().toString()+"'";
		                fillData(str);
		            }
		            
		        }
		        else if(txtName.getText()!=null) {
		        	String str = "select * from customer where name LIKE '"+txtName.getText().toString()+"%'";
	            	fillData(str);
	            }
		        else if(txtEmail.getText()!=null) {
		        	String str = "select * from customer where email LIKE '"+txtEmail.getText().toString()+"%'";
	            	fillData(str);
	            }
			}
		});
		btnSearch.setBounds(560, 97, 89, 23);
		panel.add(btnSearch);
		
		JButton btnShow = new JButton("Show All");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdoAddress.setSelected(true);
				rdoJob.setSelected(true);
				
				cboAddress.setVisible(true);
		        cboJob.setVisible(true);
		        
//		        if(cboAddress.getSelectedIndex()==0) {
////	                JOptionPane.showMessageDialog(null,"Please choose Address");
////	                cboAddress.requestFocus();
//	            } else if(cboJob.getSelectedIndex()==0) {
////	            	JOptionPane.showMessageDialog(null,"Please choose Job");
////	                cboJob.requestFocus();
//	            } else {
//	            	String str = "select * from customer where address='"+cboAddress.getSelectedItem().toString()+"' and job='"+cboJob.getSelectedItem().toString()+"'";
//	                fillData(str);
//	            }
//		        String str = "select * from customer";
//            	fillData(str);
			}
		});
		btnShow.setBounds(560, 58, 89, 23);
		panel.add(btnShow);
		
		txtName = new JTextField();
		txtName.setBounds(10, 95, 96, 25);
		panel.add(txtName);
		txtName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(139, 95, 96, 25);
		panel.add(txtEmail);
		
		JLabel lblNewLabel = new JLabel("Search Name");
		lblNewLabel.setBounds(10, 62, 96, 19);
		panel.add(lblNewLabel);
		
		JLabel lblSearchEmail = new JLabel("Search Email");
		lblSearchEmail.setBounds(139, 62, 96, 19);
		panel.add(lblSearchEmail);
		
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
        fillCustomer();
        fillAddress();
        fillJob();
        cboAddress.setVisible(false);
        cboJob.setVisible(false);
	}

    public void createtable()
   {
       dtm.addColumn("Customer ID");
       dtm.addColumn("Customer Name");
       dtm.addColumn("Gender");
       dtm.addColumn("Phone");
       dtm.addColumn("Address");
       dtm.addColumn("Job");
       dtm.addColumn("NRC");
       dtm.addColumn("Email");
       tblcustomer.setModel(dtm);
       setColumnWidth(0,50);
       setColumnWidth(1,100);
       setColumnWidth(2,35);
       setColumnWidth(3,50);
       setColumnWidth(4,60);

   }

    public void setColumnWidth(int index , int width)
    {
        DefaultTableColumnModel tcm = (DefaultTableColumnModel)tblcustomer.getColumnModel();
        TableColumn tc = tcm.getColumn(index);
        tc.setPreferredWidth(width);
    }


    public void fillCustomer()
    {
        String strdataitem[]=new String[8];
        try{
            Statement ste = (Statement) con.createStatement();
            String str = "select * from Customer";
            ResultSet rs = ste.executeQuery(str);
            while(rs.next())
            {
                strdataitem[0]=rs.getString(1);
                strdataitem[1]=rs.getString(2);
                strdataitem[2]=rs.getString(3);
                strdataitem[3]=rs.getString(4);
                strdataitem[4]=rs.getString(5);
                strdataitem[5]=rs.getString(6);
                strdataitem[6]=rs.getString(7);
                strdataitem[7]=rs.getString(8);
                dtm.addRow(strdataitem);
            }
            tblcustomer.setModel(dtm);
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
        }
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
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next())
            {
            	 strdataitem[0]=rs.getString(1);
                 strdataitem[1]=rs.getString(2);
                 strdataitem[2]=rs.getString(3);
                 strdataitem[3]=rs.getString(4);
                 strdataitem[4]=rs.getString(5);
                 strdataitem[5]=rs.getString(6);
                 strdataitem[6]=rs.getString(7);
                 strdataitem[7]=rs.getString(8);
                 dtm.addRow(strdataitem);
            }
            tblcustomer.setModel(dtm);
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
        }
    }
    private void fillAddress() {
		String str[]=mySQLQueries.FilterAddress("customer");
		cboAddress.addItem("- Select -");
		for(int i=0;i<str.length;i++) {
			cboAddress.addItem(str[i].toString());
		}
	}
    private void fillJob() {
		String str[]=mySQLQueries.FilterJob("customer");
		cboJob.addItem("- Select -");
		for(int i=0;i<str.length;i++) {
			cboJob.addItem(str[i].toString());
		}
	}
}