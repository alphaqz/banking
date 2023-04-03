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
import javax.swing.JTextField;
import javax.swing.JLabel;

public class StaffList extends JDialog {
	private JTable tblstaff;
	private JButton btnPrint;
	private JButton btnClose;
	DefaultTableModel dtm = new DefaultTableModel();
	Statement ste = null ;
	Connection con = null ;
	private JTextField txtName;
	private JTextField txtEmail;
	private JLabel lblNewLabel;
	private JLabel lblSearchEmail;
	private JButton btnSearch;
	private JButton btnAll;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			StaffList dialog = new StaffList();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public StaffList() throws ClassNotFoundException {
		setTitle("Staff List");
		setBounds(100, 100, 597, 476);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Staff List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 558, 415);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 128, 530, 232);
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
		btnClose.setBounds(451, 371, 89, 33);
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
		btnPrint.setBounds(338, 371, 89, 33);
		panel.add(btnPrint);
		
		txtName = new JTextField();
		txtName.setBounds(10, 74, 96, 25);
		panel.add(txtName);
		txtName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(146, 74, 96, 25);
		panel.add(txtEmail);
		
		lblNewLabel = new JLabel("Search Name");
		lblNewLabel.setBounds(10, 34, 96, 19);
		panel.add(lblNewLabel);
		
		lblSearchEmail = new JLabel("Search Email");
		lblSearchEmail.setBounds(146, 34, 96, 19);
		panel.add(lblSearchEmail);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtName.getText().isEmpty()) {
		        	String str = "select * from staff where name LIKE '"+txtName.getText().toString()+"%'";
	            	fillStaff(str);
	            }
		        else if(!txtEmail.getText().isEmpty()) {
		        	String str = "select * from staff where email LIKE '"+txtEmail.getText().toString()+"%'";
	            	fillStaff(str);
	            }
			}
		});
		btnSearch.setBounds(302, 73, 87, 27);
		panel.add(btnSearch);
		
		btnAll = new JButton("Show All");
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = "select * from staff";
            	fillStaff(str);
			}
		});
		btnAll.setBounds(302, 30, 87, 27);
		panel.add(btnAll);
		
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
        fillStaff("select * from staff");

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


    public void fillStaff(String sql)
    {
        String strdataitem[]=new String[5];
        try{
            Statement ste = (Statement) con.createStatement();
            while(dtm.getRowCount()>0) {
            	dtm.removeRow(0);
            }
//            String str = "select * from staff";
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
            tblstaff.setModel(dtm);
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
        }
    }
}