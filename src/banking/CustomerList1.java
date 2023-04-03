package banking;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.mysql.jdbc.Statement;

public class CustomerList1 extends JInternalFrame {

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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerList1 frame = new CustomerList1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerList1() {
		setBounds(335, 121, 765, 555);

		setTitle("Customer List");
		setBounds(100, 100, 719, 476);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Customer List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 683, 415);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 108, 663, 252);
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
		btnClose.setBounds(584, 371, 89, 33);
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
		btnPrint.setBounds(471, 371, 89, 33);
		panel.add(btnPrint);
		
		cboAddress = new JComboBox();
		cboAddress.setBounds(10, 63, 103, 22);
		panel.add(cboAddress);
		
		cboJob = new JComboBox();
		cboJob.setBounds(150, 63, 109, 22);
		panel.add(cboJob);
		
		rdoAddress = new JRadioButton("Address");
		rdoAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cboAddress.setVisible(true);
		        cboJob.setVisible(false);
			}
		});
		rdoAddress.setBounds(6, 22, 109, 23);
		panel.add(rdoAddress);
		
		rdoJob = new JRadioButton("Job");
		rdoJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cboAddress.setVisible(false);
		        cboJob.setVisible(true);
			}
		});
		rdoJob.setBounds(150, 22, 109, 23);
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
		btnSearch.setBounds(296, 62, 89, 23);
		panel.add(btnSearch);
		
		JButton btnShow = new JButton("Show All");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cboAddress.setVisible(true);
		        cboJob.setVisible(true);
			}
		});
		btnShow.setBounds(296, 22, 89, 23);
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
       setColumnWidth(0,40);
       setColumnWidth(1,50);
       setColumnWidth(2,100);
       setColumnWidth(3,40);
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
