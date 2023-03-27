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

public class AccountTypeList extends JDialog {
	private JTable tblaccounttype;
	private JButton btnPrint;
	private JButton btnClose;
	DefaultTableModel dtm = new DefaultTableModel();
	Statement ste = null ;
	Connection con = null ;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AccountTypeList dialog = new AccountTypeList();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AccountTypeList() throws ClassNotFoundException {
		setTitle("Account List");
		setBounds(100, 100, 719, 476);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Account Type List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 683, 415);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 663, 310);
		panel.add(scrollPane);
		
		tblaccounttype = new JTable();
		scrollPane.setViewportView(tblaccounttype);
		
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
		            tblaccounttype.print();
		        }catch(Exception e1) {
		            JOptionPane.showMessageDialog(null, e1);
		        }
			}
		});
		btnPrint.setBounds(471, 371, 89, 33);
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
        fillAccountType();

	}

    public void createtable()
   {
       dtm.addColumn("Account Type ID");
       dtm.addColumn("Title");
       dtm.addColumn("Interest");
       tblaccounttype.setModel(dtm);
       setColumnWidth(0,40);
       setColumnWidth(1,50);
       setColumnWidth(2,100);
   }

    public void setColumnWidth(int index , int width)
    {
        DefaultTableColumnModel tcm = (DefaultTableColumnModel)tblaccounttype.getColumnModel();
        TableColumn tc = tcm.getColumn(index);
        tc.setPreferredWidth(width);
    }


    public void fillAccountType()
    {
        String strdataitem[]=new String[3];
        try{
            Statement ste = (Statement) con.createStatement();
            String str = "select * from accounttype";
            ResultSet rs = ste.executeQuery(str);
            while(rs.next())
            {
                strdataitem[0]=rs.getString(1);
                strdataitem[1]=rs.getString(2);
                strdataitem[2]=rs.getString(3);
                dtm.addRow(strdataitem);
            }
            tblaccounttype.setModel(dtm);
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
        }
    }


}