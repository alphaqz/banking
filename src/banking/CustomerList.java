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
		panel.setBounds(10, 11, 790, 415);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 108, 770, 252);
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
		btnClose.setBounds(673, 371, 89, 33);
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
		btnPrint.setBounds(560, 371, 89, 33);
		panel.add(btnPrint);
		
		cboAddress = new JComboBox();
		cboAddress.setBounds(80, 62, 103, 22);
		panel.add(cboAddress);
		
		cboJob = new JComboBox();
		cboJob.setBounds(310, 62, 109, 22);
		panel.add(cboJob);
		
		rdoAddress = new JRadioButton("Address");
		rdoAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cboAddress.setVisible(true);
		        cboJob.setVisible(false);
			}
		});
		rdoAddress.setBounds(76, 21, 109, 23);
		panel.add(rdoAddress);
		
		rdoJob = new JRadioButton("Job");
		rdoJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cboAddress.setVisible(false);
		        cboJob.setVisible(true);
			}
		});
		rdoJob.setBounds(310, 21, 109, 23);
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
//		        } else if (rdoBoth.isSelected()) {
//		        	
//		            if(cboAddress.getSelectedIndex()==0) {
//		                JOptionPane.showMessageDialog(null,"Please choose Month");
//		                cboAddress.requestFocus();
//		            } else if(cboJob.getSelectedIndex()==0) {
//		            	JOptionPane.showMessageDialog(null,"Please choose Year");
//		                cboJob.requestFocus();
//		            } else {
//		            	String str = "select * from transfer where Month(date)="+cbomonth.getSelectedIndex()+" and Year(date)="+cboyear.getSelectedItem().toString()+"";
//		                fillData(str);
//		            }
		        }
			}
		});
		btnSearch.setBounds(560, 61, 89, 23);
		panel.add(btnSearch);
		
		JButton btnShow = new JButton("Show All");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cboAddress.setVisible(true);
		        cboJob.setVisible(true);
			}
		});
		btnShow.setBounds(560, 21, 89, 23);
		panel.add(btnShow);
		
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