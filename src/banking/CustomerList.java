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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class CustomerList extends JInternalFrame {
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
	private JRadioButton rdoBoth;


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
//		setBounds(100, 100, 826, 476);
		setBounds(0, 0, Constants.c_width, Constants.c_height);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(0, 26, 703, 400);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 106, 657, 191);
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
		btnClose.setBounds(578, 312, 89, 33);
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
		btnPrint.setBounds(479, 312, 89, 33);
		panel.add(btnPrint);
		
		cboAddress = new JComboBox();
		cboAddress.setBounds(232, 49, 103, 22);
		panel.add(cboAddress);
		
		cboJob = new JComboBox();
		cboJob.setBounds(341, 49, 109, 22);
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
		rdoAddress.setBounds(222, 13, 109, 23);
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
		rdoJob.setBounds(341, 13, 109, 23);
		panel.add(rdoJob);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdoAddress.isSelected() && rdoJob.isSelected()) {
		        	System.out.println("both selected ");
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
				else if(rdoAddress.isSelected()) {
					System.out.println("address selected");
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
		        }
		        else if(!txtName.getText().isEmpty()) {
		        	String str = "select * from customer where name LIKE '"+txtName.getText().toString()+"%'";
	            	fillData(str);
	            }
		        else if(!txtEmail.getText().isEmpty()) {
		        	String str = "select * from customer where email LIKE '"+txtEmail.getText().toString()+"%'";
	            	fillData(str);
	            }
		        if(!(txtName.getText().isEmpty() && txtEmail.getText().isEmpty())) {
		        	String str = "select * from customer where name LIKE '"+txtName.getText().toString()+"%' and email LIKE '"+txtEmail.getText().toString()+"%'";
	            	fillData(str);
	            }
//		        if(!(txtName.getText().isEmpty() && txtEmail.getText().isEmpty()) && ) {
//		        	String str = "select * from customer where name LIKE '"+txtName.getText().toString()+"%' and email LIKE '"+txtEmail.getText().toString()+"%'";
//	            	fillData(str);
//	            }
			}
		});
		btnSearch.setBounds(479, 49, 89, 23);
		panel.add(btnSearch);
		
		JButton btnShow = new JButton("Show All");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String str = "select * from customer";
            	fillData(str);
			}
		});
		btnShow.setBounds(578, 49, 89, 23);
		panel.add(btnShow);
		
		txtName = new JTextField();
		txtName.setBounds(10, 48, 96, 25);
		panel.add(txtName);
		txtName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(116, 48, 96, 25);
		panel.add(txtEmail);
		
		JLabel lblNewLabel = new JLabel("Search Name");
		lblNewLabel.setBounds(10, 15, 96, 19);
		panel.add(lblNewLabel);
		
		JLabel lblSearchEmail = new JLabel("Search Email");
		lblSearchEmail.setBounds(116, 15, 96, 19);
		panel.add(lblSearchEmail);
		
		rdoBoth = new JRadioButton("Address & Job");
		rdoBoth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdoBoth.isSelected()==true) {
		        	cboAddress.setVisible(true);
			        cboJob.setVisible(true);
			        rdoAddress.setSelected(true);
			        rdoJob.setSelected(true);
		        }
		        else {
		        	cboAddress.setVisible(false);
			        cboJob.setVisible(false);
		        }
			}
		});
		rdoBoth.setBounds(479, 11, 109, 27);
		panel.add(rdoBoth);
		
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