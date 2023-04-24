package banking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;


import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class AccountCreate extends JInternalFrame {

	private final JPanel contentPanel = new JPanel();
	List<String> staffIDList = new ArrayList<>();
	List<String> customerIDList = new ArrayList<>();
	List<String> accountTypeIDList = new ArrayList<>();
	private JButton btnClose;
	private JComboBox cboAccountType;
	private JComboBox cboStaff;
	private JComboBox cboCustomer;
	private JLabel lblForID;
	private JLabel lblNewLabel;
	private JTextField txtBalance;
	private String depositID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AccountCreate dialog = new AccountCreate();
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
	public AccountCreate() throws ClassNotFoundException {
		setTitle("Account Entry");
//		setBounds(100, 100, 632, 572);
		setBounds(0, 0, Constants.c_width, Constants.c_height);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, new Color(51, 102, 255), new Color(0, 0, 204)));
		panel.setBounds(86, 15, 519, 335);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblAccID_1_1 = new JLabel("ID:");
		lblAccID_1_1.setBounds(131, 91, 86, 14);
		panel.add(lblAccID_1_1);
		
		lblForID = new JLabel("AC-0000004");
		lblForID.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblForID.setBounds(268, 85, 186, 27);
		panel.add(lblForID);
	
		
	
		
		JLabel lblCustomer_1_1 = new JLabel("Customer:");
		lblCustomer_1_1.setBounds(-96, 91, 69, 14);
		panel.add(lblCustomer_1_1);
		
		JLabel lblCustomer_1_2 = new JLabel("Customer:");
		lblCustomer_1_2.setBounds(131, 149, 86, 14);
		panel.add(lblCustomer_1_2);
		
		cboCustomer = new JComboBox();
		cboCustomer.setBounds(268, 145, 186, 22);
		panel.add(cboCustomer);
		
		JLabel lblAccountType_1_1 = new JLabel("Account Type:");
		lblAccountType_1_1.setBounds(131, 199, 86, 14);
		panel.add(lblAccountType_1_1);
		
		cboAccountType = new JComboBox();
		cboAccountType.setBounds(268, 195, 186, 22);
		panel.add(cboAccountType);
		
		cboStaff = new JComboBox();
		cboStaff.setBounds(268, 250, 186, 22);
		panel.add(cboStaff);
		
		lblNewLabel = new JLabel("Create New Account");
		lblNewLabel.setFont(new Font("Pyidaungsu", Font.BOLD, 17));
		lblNewLabel.setBounds(196, 31, 186, 19);
		panel.add(lblNewLabel);
		
		JLabel lblAccountType_1_1_1 = new JLabel("Staff :");
		lblAccountType_1_1_1.setBounds(131, 254, 86, 14);
		panel.add(lblAccountType_1_1_1);
		
		JLabel lblAccountType_1_1_1_1 = new JLabel("Balance:");
		lblAccountType_1_1_1_1.setBounds(131, 290, 86, 14);
		panel.add(lblAccountType_1_1_1_1);
		
		txtBalance = new JTextField();
		txtBalance.setBounds(268, 287, 116, 20);
		panel.add(txtBalance);
		txtBalance.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Minimum initial balance: 10,000 ks");
		lblNewLabel_1.setBounds(268, 310, 205, 14);
		panel.add(lblNewLabel_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(183, 349, 338, 82);
			contentPanel.add(buttonPane);
			{
				JButton btnSave = new JButton("Save");
				btnSave.setForeground(Color.WHITE);
				btnSave.setBackground(new Color(0, 153, 0));
				btnSave.setBounds(30, 20, 90, 35);
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
				     if(cboAccountType.getSelectedIndex()==0){
				        	JOptionPane.showMessageDialog(null, "Please choose Account Type");						
				        }
				        else if(cboCustomer.getSelectedIndex()==0){
				        	JOptionPane.showMessageDialog(null, "Please choose Customer");						
				        }
				        else if(cboStaff.getSelectedIndex()==0){
				        	JOptionPane.showMessageDialog(null, "Please choose Staff");						
				        }
				        else if(Checking.IsNull(txtBalance.getText())){
				        	JOptionPane.showMessageDialog(null, "Please add Balance");		
				        	txtBalance.requestFocus();
				        	txtBalance.selectAll();
				        }
				        else if(!Checking.IsAllDigit(txtBalance.getText())){
				        	JOptionPane.showMessageDialog(null, "You can only input digits for balance.");		
				        	txtBalance.requestFocus();
				        	txtBalance.selectAll();
				        }
				        else if(Integer.parseInt( txtBalance.getText())<10000 ){
				        	JOptionPane.showMessageDialog(null, "Minimum Balance is 10000");		
				        	txtBalance.requestFocus();
				        	txtBalance.selectAll();
				        }
				        
				        else {	
				        	boolean ee = mySQLQueries.isduplicateCustomerID(customerIDList.get(cboCustomer.getSelectedIndex()-1), 
				        			accountTypeIDList.get(cboAccountType.getSelectedIndex()-1));
				        	if(ee) {
						    	 JOptionPane.showMessageDialog(null,"A customer can open the same account type only once. Please select other Account Type.","Duplicate customer",JOptionPane.INFORMATION_MESSAGE);

				        	}else {
				        		String str[] = new String[8];
					            str[0] = (String)lblForID.getText();	
					            str[1] = customerIDList.get(cboCustomer.getSelectedIndex()-1); 
					            str[2] = accountTypeIDList.get(cboAccountType.getSelectedIndex()-1); 
					            str[3] = staffIDList.get(cboStaff.getSelectedIndex()-1); 
					            
					            boolean save = mySQLQueries.insertData("account", str);
						        if(save)
						        {
						        	//id,amount,accountno,staffno
						        	String strDepo[] = new String[4];
						        	str[0] = depositID;
						        	str[1] = (String)txtBalance.getText();
						        	str[2] = accountTypeIDList.get(cboAccountType.getSelectedIndex()-1);
						        	str[3] = staffIDList.get(cboStaff.getSelectedIndex()-1); 
						        	 boolean depositSave = mySQLQueries.insertData("deposit", strDepo);
						        	if(depositSave) {
						        		JOptionPane.showMessageDialog(null, "Successfully saved record!","Save Record.",JOptionPane.INFORMATION_MESSAGE);
						        		
						        	}else {
								    	 JOptionPane.showMessageDialog(null,"Deposit create problem.","Cannot Save",JOptionPane.INFORMATION_MESSAGE);

						        	}
						        		
						            try {
									AutoID();
								} catch (ClassNotFoundException e1) {
									e1.printStackTrace();
								}
							        clear();
							     }
							     else
							     {
							    	 JOptionPane.showMessageDialog(null,"Failed to save new record","Cannot Save",JOptionPane.INFORMATION_MESSAGE);
							         try {
							        	 AutoID();
							         } catch (ClassNotFoundException e1) {
										e1.printStackTrace();
							         }
							     }
				        	}
				            
					            
				        }
					}
				});
				buttonPane.setLayout(null);
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
				
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setForeground(new Color(255, 255, 255));
				btnCancel.setBackground(new Color(255, 215, 0));
				btnCancel.setBounds(133, 20, 90, 35);
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						clear();
					}
				});
				buttonPane.add(btnCancel);
			}
			{
				btnClose = new JButton("Close");
				btnClose.setBounds(232, 20, 90, 35);
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confrim",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
						{	
							dispose();
						}
					}
				});
				btnClose.setActionCommand("Cancel");
				buttonPane.add(btnClose);
			}
		}
		fillCustomer();
		AutoID();
		fillAccountType();
		fillStaff();
	}
	public void fillCustomer()
    {
		cboCustomer.addItem("----Select----");
        String str[]=mySQLQueries.getNameForChoice("customer");
        String temp[]=mySQLQueries.getIDForChoice("customer");
        customerIDList = Arrays.asList(temp);
        for(int i=0;i<str.length;i++)
        	cboCustomer.addItem(str[i].toString());
    }
	public void fillStaff()
    {
		cboStaff.addItem("----Select----");
        String str[]=mySQLQueries.getNameForChoice("staff");
        String temp[]=mySQLQueries.getIDForChoice("staff");
        staffIDList = Arrays.asList(temp);
        for(int i=0;i<str.length;i++)
        	cboStaff.addItem(str[i].toString());
    }
	public void fillAccountType()
    {
		cboAccountType.addItem("----Select----");
        String str[]=mySQLQueries.getNameForChoice("accounttype");
        String temp[]=mySQLQueries.getIDForChoice("accounttype");
        accountTypeIDList = Arrays.asList(temp);
        for(int i=0;i<str.length;i++)
        	cboAccountType.addItem(str[i].toString());
    }
	public void clear()
    {      
        cboAccountType.setSelectedIndex(0);
        cboCustomer.setSelectedIndex(0);
        cboStaff.setSelectedIndex(0);
        
    }
	public void AutoID() throws ClassNotFoundException
	{
		lblForID.setText((String.valueOf(mySQLQueries.getAutoid("id", "account", "AC-"))));
		depositID = String.valueOf(mySQLQueries.getAutoid("id", "deposit", "DE-"));		
	}
}


