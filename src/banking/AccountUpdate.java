package banking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;

public class AccountUpdate extends JDialog {
	private JTextField txtBalance;
	private JComboBox cboaccountid;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnclose;
	private JRadioButton rdbtnMale = new JRadioButton("male");;
	private JRadioButton rdbtnFemale = new JRadioButton("Female");;
	private String gender = "";
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	Border backline=BorderFactory.createLineBorder(Color.black);
	private JLabel lblCustomer;
	private JLabel lblAccountType;
	private JLabel lblStaff;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AccountUpdate dialog = new AccountUpdate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AccountUpdate() {
		setTitle("Account Update");
		setBounds(100, 100, 360, 428);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Account Update Info:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 11, 325, 243);
			getContentPane().add(panel);
			{
				JLabel lblNewLabel = new JLabel("Account ID:");
				lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel.setBounds(10, 23, 62, 14);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblCusto = new JLabel("Balance :");
				lblCusto.setHorizontalAlignment(SwingConstants.RIGHT);
				lblCusto.setBounds(20, 62, 52, 14);
				panel.add(lblCusto);
			}
			{
				JLabel lblEmail = new JLabel("Staff ID :");
				lblEmail.setBounds(20, 207, 81, 14);
				panel.add(lblEmail);
			}
			{
				txtBalance = new JTextField();
				txtBalance.setColumns(10);
				txtBalance.setBounds(111, 59, 167, 20);
				panel.add(txtBalance);
			}
			{
				cboaccountid = new JComboBox();
				cboaccountid.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cboaccountid.getSelectedIndex()<=0)
						{
							txtBalance.setText("");
					        
						}
						else
						{
							showAccount();
						}
							
						
						
					}
				});
				cboaccountid.setBounds(111, 19, 167, 22);
				panel.add(cboaccountid);
			}
			
			JLabel lblJob = new JLabel("Account Type ID :");
			lblJob.setHorizontalAlignment(SwingConstants.RIGHT);
			lblJob.setBounds(10, 103, 91, 14);
			panel.add(lblJob);
			
			JLabel lblNewLabel_2 = new JLabel("Customer ID :");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_2.setBounds(20, 152, 81, 14);
			panel.add(lblNewLabel_2);
			rdbtnFemale.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gender = "male";
				}
			});
			
			lblAccountType = new JLabel("");
			lblAccountType.setBounds(111, 94, 167, 23);
			lblAccountType.setBorder(backline);
			panel.add(lblAccountType);
			
			lblCustomer = new JLabel("");
			lblCustomer.setBounds(111, 143, 167, 23);
			lblCustomer.setBorder(backline);
			panel.add(lblCustomer);
			
			lblStaff = new JLabel("");
			lblStaff.setBounds(111, 198, 167, 23);
			lblStaff.setBorder(backline);
			panel.add(lblStaff);
		}
		{
			btnclose = new JButton("Close");
			btnclose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confrim",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
					{	
						dispose();
					}
				}
			});
			btnclose.setBounds(249, 279, 85, 23);
			getContentPane().add(btnclose);
		}
		{
			btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
	                    String id = cboaccountid.getSelectedItem().toString();
	                    if(JOptionPane.showConfirmDialog(null, "Are you Sure Delete?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
	                    {
	                    	mySQLQueries.deleteRecord("account", id);
	                    	fillAccount();
	                    }
	                    else
	                    {
	                    	JOptionPane.showMessageDialog(null, "Fail to delete record","Cannot Update",JOptionPane.INFORMATION_MESSAGE);
	                    }
	                } catch(Exception sqle) {
	                    sqle.printStackTrace();
	                }

				}
			});
			btnDelete.setBounds(138, 279, 85, 23);
			getContentPane().add(btnDelete);
		}
		{
			btnUpdate = new JButton("Update");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

			        if(cboaccountid.getSelectedIndex()==0) 
			        {
			            JOptionPane.showMessageDialog(null, "Please choose account id.");
			            cboaccountid.requestFocus();
			        } 
			        else{
						 if(JOptionPane.showConfirmDialog(null, "Are you Sure Update?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION) 
						 {
							 String []st = new String[1];
						     String id = cboaccountid.getSelectedItem().toString();						               
						               
						     st[0] = (String)txtBalance.getText();
						     							
						     boolean save = mySQLQueries.updateRecord("account", id, st);
						     if(save) 
						     {
						        JOptionPane.showMessageDialog(null, "Successfully update record!","Update Record.",JOptionPane.INFORMATION_MESSAGE);
						        clear();
						     }
						     else
						     {
						      	JOptionPane.showMessageDialog(null, "Fail to update record","Cannot Update",JOptionPane.INFORMATION_MESSAGE);
						     }
						 }
				            
			        }
				}
			});
			btnUpdate.setBounds(20, 279, 79, 23);
			getContentPane().add(btnUpdate);
		}
		fillAccount();
	}

    public void fillAccount()
   {
       cboaccountid.removeAllItems();
       cboaccountid.addItem("-Selected-");
       String str[]=mySQLQueries.getIDForChoice("account");
       for(int i = 0 ; i<str.length ; i++)
    	   cboaccountid.addItem(str[i].toString());
       cboaccountid.setSelectedIndex(0);
   }
    public void showAccount()
    {
        String result[]= mySQLQueries.getAccountData(cboaccountid.getSelectedItem().toString());
        txtBalance.setText(result[0]);
        lblCustomer.setText(result[1]);
        lblAccountType.setText(result[2]);
        
        lblStaff.setText(result[3]);
    }
    public void clear()
    {
        txtBalance.setText("");
        lblCustomer.setText("");
        lblAccountType.setText("");
        lblStaff.setText("");
        cboaccountid.requestFocus();
        cboaccountid.setSelectedIndex(0);
    }
}