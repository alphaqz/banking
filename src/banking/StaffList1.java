package banking;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.mysql.jdbc.Statement;

public class StaffList1 extends JInternalFrame {

	private JTable tblstaff;
	private JButton btnPrint;
	private JButton btnClose;
	DefaultTableModel dtm = new DefaultTableModel();
	Statement ste = null ;
	Connection con = null ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffList1 frame = new StaffList1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws PropertyVetoException 
	 */
	public StaffList1() throws PropertyVetoException {
		setMaximum(true);
		//setBounds(100, 100, 450, 300);

		setTitle("Staff List");
	
		setBounds(0, 0, Constants.c_width, Constants.c_height);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Staff List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 683, 428);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 128, 663, 232);
		panel.add(scrollPane);
		
		tblstaff = new JTable();
		scrollPane.setViewportView(tblstaff);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confrim",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
				{	
					dispose();
				}
			}
		});
		btnClose.setBounds(564, 371, 89, 33);
		panel.add(btnClose);
		
		btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
		            tblstaff.print();
		        }catch(Exception e1) {
		            JOptionPane.showMessageDialog(null, e1);
		        }
			}
		});
		btnPrint.setBounds(440, 371, 89, 33);
		panel.add(btnPrint);
		
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
        fillStaff();

	}
	public void createtable()
	   {
	       dtm.addColumn("Staff ID");
	       dtm.addColumn("Staff Name");
	       dtm.addColumn("Gender");
	       dtm.addColumn("Phone No");
	       dtm.addColumn("Email");
	       tblstaff.setModel(dtm);
	       setColumnWidth(0,40);
	       setColumnWidth(1,50);
	       setColumnWidth(2,15);
	       setColumnWidth(3,40);
	       setColumnWidth(4,120);

	   }

	    public void setColumnWidth(int index , int width)
	    {
	        DefaultTableColumnModel tcm = (DefaultTableColumnModel)tblstaff.getColumnModel();
	        TableColumn tc = tcm.getColumn(index);
	        tc.setPreferredWidth(width);
	    }


	    public void fillStaff()
	    {
	        String strdataitem[]=new String[5];
	        try{
	            Statement ste = (Statement) con.createStatement();
	            String str = "select * from staff";
	            ResultSet rs = ste.executeQuery(str);
	            while(rs.next())
	            {
	                strdataitem[0]=rs.getString(1);
	                strdataitem[1]=rs.getString(2);
	                strdataitem[2]=rs.getString(3);
	                strdataitem[3]=rs.getString(4);
	                strdataitem[4]=rs.getString(5);
	                dtm.addRow(strdataitem);
	            }
	            tblstaff.setModel(dtm);
	        }
	        catch(SQLException sqle)
	        {
	            System.out.println(sqle);
	        }
	    }


}
