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
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class DepositCreate extends JInternalFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtAmount;
	private JLabel lblForID;
	private boolean isDeposit = true;
	JComboBox cboAccount;
	JComboBox cboStaff; 
	JLabel lblforaccid;
	JLabel lblforbalance;
	JLabel lblforcusname;
	JLabel lblforacctype;
	JLabel lblforbalanceNew;
	JLabel lblforbalanceTotal;
	List<String> staffIdList = new ArrayList<String>();  
	List<String> AccountIdList = new ArrayList<String>(); 
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DepositCreate dialog = new DepositCreate();
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
	public DepositCreate() throws ClassNotFoundException {
		setTitle("Transaction Entry");
//		setBounds(100, 100, 487, 456);
		setBounds(0, 0, Constants.c_width, Constants.c_height);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblDepositID = new JLabel("ID:");
		lblDepositID.setBounds(232, 113, 46, 14);
		lblDepositID.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPanel.add(lblDepositID);
		
		lblForID = new JLabel("");
		lblForID.setBounds(332, 113, 86, 20);
		lblForID.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.add(lblForID);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(213, 142, 69, 14);
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPanel.add(lblAmount);
		
		txtAmount = new JTextField();
		txtAmount.setBounds(332, 139, 86, 20);
		txtAmount.setColumns(10);
		contentPanel.add(txtAmount);
		
		cboAccount = new JComboBox();
		cboAccount.setBounds(332, 173, 186, 22);
		cboAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboAccount.getSelectedIndex()> 0) {	
					String[] result = mySQLQueries.getAccountDataFordeposit((String)cboAccount.getSelectedItem());
					int[] totalBalance = CalculateIntrest.something(result[0]);
					lblforaccid.setText(result[0]);
					lblforbalance.setText(result[1]);
					lblforacctype.setText(result[2]);
					lblforcusname.setText(result[3]);
					lblforbalanceTotal.setText(""+totalBalance[0]);
					lblforbalanceNew.setText(""+totalBalance[1]);
					//lblfor
					
				}
			}
		});
		contentPanel.add(cboAccount);
		
		JLabel lblAccount = new JLabel("Account:");
		lblAccount.setBounds(213, 177, 69, 14);
		lblAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPanel.add(lblAccount);
		
		JLabel lblStaff = new JLabel("Staff:");
		lblStaff.setBounds(210, 210, 69, 14);
		lblStaff.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPanel.add(lblStaff);
		
		cboStaff = new JComboBox();
		cboStaff.setBounds(332, 206, 186, 22);
		contentPanel.add(cboStaff);
		
	
		lblforbalance = new JLabel("");
		lblforbalance.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblforbalance.setBounds(224, 56, 86, 14);
		
		JRadioButton rdoDeposit = new JRadioButton("Deposit");
		rdoDeposit.setBounds(213, 77, 109, 23);
		rdoDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Deposit Entry", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				isDeposit = true;
				try {
					AutoID();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		rdoDeposit.setSelected(true);
		buttonGroup.add(rdoDeposit);
		contentPanel.add(rdoDeposit);
		
		JRadioButton rdoWithdraw = new JRadioButton("Withdraw");
		rdoWithdraw.setBounds(325, 77, 109, 23);
		rdoWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Withdraw Entry", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				isDeposit = false;
				try {
					AutoID();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		buttonGroup.add(rdoWithdraw);
		contentPanel.add(rdoWithdraw);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, new Color(51, 102, 255), new Color(0, 0, 204)));
		panel.setBounds(86, 15, 519, 379);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel contentPanel_1 = new JPanel();
		contentPanel_1.setBounds(21, 223, 474, 144);
		panel.add(contentPanel_1);
		contentPanel_1.setLayout(null);
		contentPanel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Account Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblAccID = new JLabel("ID:");
		lblAccID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccID.setBounds(105, 22, 74, 23);
		contentPanel_1.add(lblAccID);
		
		lblforaccid = new JLabel("");
		lblforaccid.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblforaccid.setBounds(218, 22, 86, 23);
		contentPanel_1.add(lblforaccid);
		
		JLabel lblbalance = new JLabel("Withdrawable Balance:");
		lblbalance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblbalance.setBounds(33, 52, 146, 21);
		contentPanel_1.add(lblbalance);
		//contentPanel_1.add(lblforbalance);
		
		JLabel lblCust = new JLabel("Account type:");
		lblCust.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCust.setBounds(93, 81, 86, 21);
		contentPanel_1.add(lblCust);
		
		lblforacctype = new JLabel("");
		lblforacctype.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblforacctype.setBounds(218, 81, 164, 21);
		contentPanel_1.add(lblforacctype);
		
		JLabel lblCustomerName = new JLabel("Customer Name:");
		lblCustomerName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustomerName.setBounds(49, 110, 130, 20);
		contentPanel_1.add(lblCustomerName);
		
			lblforcusname = new JLabel("");
			lblforcusname.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblforcusname.setBounds(218, 110, 172, 20);
			contentPanel_1.add(lblforcusname);
			
			lblforbalanceNew = new JLabel("");
			lblforbalanceNew.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblforbalanceNew.setBounds(218, 51, 86, 22);
			contentPanel_1.add(lblforbalanceNew);
			
			lblforbalanceTotal = new JLabel("");
			lblforbalanceTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblforbalanceTotal.setBounds(365, 52, 86, 21);
			contentPanel_1.add(lblforbalanceTotal);
			
			JLabel lblTotal = new JLabel("total:");
			lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTotal.setBounds(307, 52, 48, 21);
			contentPanel_1.add(lblTotal);
			
			JLabel lblNewLabel = new JLabel("Deposit Or Withdraw Operation");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Pyidaungsu", Font.BOLD, 17));
			lblNewLabel.setBounds(88, 15, 319, 19);
			panel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(182, 396, 346, 50);
			contentPanel.add(buttonPane);
			{
				JButton btnSave = new JButton("Save");
				btnSave.setForeground(new Color(255, 255, 255));
				btnSave.setBackground(new Color(0, 128, 0));
				btnSave.setBounds(10, 5, 90, 35);
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
				        if(Checking.IsNull(txtAmount.getText()))
				        {
				            JOptionPane.showMessageDialog(null, "Please enter Amount.");
				            txtAmount.requestFocus();
				            txtAmount.selectAll();
				        }
				        else if(cboStaff.getSelectedIndex()<1)
				        {
				            JOptionPane.showMessageDialog(null, "Please select staff.");
				        }
				        else if(cboAccount.getSelectedIndex()<1)
				        {
				            JOptionPane.showMessageDialog(null, "Please select account.");
				        }
				        else if(!Checking.IsAllDigit(txtAmount.getText()))
				        {
				            JOptionPane.showMessageDialog(null,"Please enter valid number.");
				            txtAmount.requestFocus();
				            txtAmount.selectAll();
				        }
				        else if(Integer.parseInt((String)txtAmount.getText()) <= 0){
				        	JOptionPane.showMessageDialog(null,"Please enter valid amount");
				            txtAmount.requestFocus();
				            txtAmount.selectAll();
				        }
				       
				        else {							
							
				            	String str[] = new String[8];
				            	str[0] = (String)lblForID.getText();						
				            	str[1] = (String)txtAmount.getText();

				            	str[2] =  (String) cboAccount.getSelectedItem();
				            	str[3] = staffIdList.get(cboStaff.getSelectedIndex()-1);
				            	//System.out.println("inserting into deposit " + str[0] + str[1] + str[3]);
				            	boolean save = false;
				            	if(isDeposit) {
				            		save = mySQLQueries.insertData("deposit", str);
				            		JOptionPane.showMessageDialog(null, "Successfully saved record!","Save Record.",JOptionPane.INFORMATION_MESSAGE);
				            	}else {
				            		if(Integer.parseInt((String)txtAmount.getText()) > Integer.parseInt(lblforbalanceNew.getText()) ){
							        	JOptionPane.showMessageDialog(null,"You don't have enough balance");
							            txtAmount.requestFocus();
							            txtAmount.selectAll();
							        }else {
							        	save = mySQLQueries.insertData("withdraw", str);
							        	JOptionPane.showMessageDialog(null, "Successfully saved record!","Save Record.",JOptionPane.INFORMATION_MESSAGE);
							        }
				            	}
					            if(save)
					            {					            	
					                try {
										AutoID();
									} catch (ClassNotFoundException e1) {
										// TODO Auto-generated catch block
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
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
					            }
					            
				        }
					}
				});
				buttonPane.setLayout(null);
				
				JButton btnCancle = new JButton("Cancel");
				btnCancle.setBackground(new Color(255, 215, 0));
				btnCancle.setForeground(new Color(255, 255, 255));
				btnCancle.setBounds(131, 5, 90, 35);
				buttonPane.add(btnCancle);
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				JButton cancelButton = new JButton("Close");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confrim",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
						{	
							dispose();
						}
					}
				});
				cancelButton.setBounds(246, 5, 90, 35);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		fillAccount();
		fillStaff();
		AutoID();
	}
	public void fillAccount()
    {
        String str[]=mySQLQueries.getIDForChoice("account");
        cboAccount.addItem("-Select-");
        for(int i=0;i<str.length;i++)
        	cboAccount.addItem(str[i].toString());				
    }
	public void fillStaff()
    {
        String str[]=mySQLQueries.getNameForChoice("staff");
        String temp[] = mySQLQueries.getIDForChoice("staff");
        staffIdList = Arrays.asList(temp);
        cboStaff.addItem("-Select-");
        for(int i=0;i<str.length;i++)
        	cboStaff.addItem(str[i].toString());				
    }
	public void clear()
    {      
		lblforaccid.setText("");
		lblforacctype.setText("");
		lblforbalance.setText("");
		lblforcusname.setText("");
		lblforbalanceNew.setText("");
		lblforbalanceTotal.setText("");
        txtAmount.setText("");
        cboAccount.setSelectedIndex(0);
        cboStaff.setSelectedIndex(0);
    }
	public void AutoID() throws ClassNotFoundException
	{
		if(isDeposit) {
			lblForID.setText((String.valueOf(mySQLQueries.getAutoid("id", "deposit", "DE-"))));			
		}else {
			lblForID.setText((String.valueOf(mySQLQueries.getAutoid("id", "withdraw", "WD-"))));		
		}
	}
}


