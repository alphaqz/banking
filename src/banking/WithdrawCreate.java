package banking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
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

public class WithdrawCreate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtAmount;
	private JLabel lblForID;
	private JLabel lblForBalance;
	JComboBox cboAccount;
	JComboBox cboStaff; 
	List<String> staffIdList = new ArrayList<String>();
	List<String> AccountIdList = new ArrayList<String>(); 
	static String balance;
	private JButton btnclose;
	private JButton btnClear;
	private JLabel lblWithdrawableBalance;
	private JLabel lblForBalanceTotal;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WithdrawCreate dialog = new WithdrawCreate();
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
	public WithdrawCreate() throws ClassNotFoundException {
		setTitle("Withdraw Entry");
		setBounds(100, 100, 450, 357);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Withdraw Entry", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAccID = new JLabel("ID:");
		lblAccID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccID.setBounds(126, 32, 46, 14);
		contentPanel.add(lblAccID);
		
		lblForID = new JLabel("");
		lblForID.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblForID.setBounds(222, 32, 86, 14);
		contentPanel.add(lblForID);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setBounds(102, 117, 69, 14);
		contentPanel.add(lblAmount);
		
		txtAmount = new JTextField();
		txtAmount.setColumns(10);
		txtAmount.setBounds(221, 114, 86, 20);
		contentPanel.add(txtAmount);
		
		cboAccount = new JComboBox();
		cboAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboAccount.getSelectedIndex() > 0) {
					try {
						int[] arr = CalculateIntrest.something((String)cboAccount.getSelectedItem());
						String result = ""+arr[1];

						lblForBalance.setText(result);
						lblForBalanceTotal.setText(arr[0]+"");
						
						System.out.println("balance is "+ result);		
					} catch (Exception e2) {
						// TODO: handle exception
						System.out.println(e2);
					}
								
				}
			}
		});
		cboAccount.setBounds(221, 156, 186, 22);
		contentPanel.add(cboAccount);
		
		JLabel lblAccount = new JLabel("Account:");
		lblAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccount.setBounds(102, 160, 69, 14);
		contentPanel.add(lblAccount);
		
		JLabel lblStaff = new JLabel("Staff:");
		lblStaff.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStaff.setBounds(102, 193, 69, 14);
		contentPanel.add(lblStaff);
		
		cboStaff = new JComboBox();
		cboStaff.setBounds(221, 189, 186, 22);
		contentPanel.add(cboStaff);
		
		JLabel lblBalance = new JLabel("Withdrawable Balance:");
		lblBalance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBalance.setBounds(60, 57, 112, 14);
		contentPanel.add(lblBalance);
		
		lblForBalance = new JLabel("");
		lblForBalance.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblForBalance.setBounds(222, 57, 86, 14);
		contentPanel.add(lblForBalance);
		
		lblWithdrawableBalance = new JLabel("Total Balance:");
		lblWithdrawableBalance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWithdrawableBalance.setBounds(60, 92, 110, 14);
		contentPanel.add(lblWithdrawableBalance);
		
		lblForBalanceTotal = new JLabel("");
		lblForBalanceTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblForBalanceTotal.setBounds(220, 89, 86, 14);
		contentPanel.add(lblForBalanceTotal);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
				        if(Checking.IsNull(txtAmount.getText()))
				        {
				            JOptionPane.showMessageDialog(null, "Please enter Amount.");
				            txtAmount.requestFocus();
				            txtAmount.selectAll();
				        }
				        else if(!Checking.IsAllDigit(txtAmount.getText()))
				        {
				            JOptionPane.showMessageDialog(null,"Please enter valid number.");
				            txtAmount.requestFocus();
				            txtAmount.selectAll();
				        }
				        else if(Integer.parseInt((String)txtAmount.getText()) > Integer.parseInt(lblForBalance.getText()) ){
				        	JOptionPane.showMessageDialog(null,"You don't have enough balance");
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
				            	boolean save = mySQLQueries.insertData("withdraw", str);
					            if(save)
					            {
					            	//String action,String id , String amount)
					            	boolean updatesuccess = mySQLQueries.updateAmount("withdraw", (String)cboAccount.getSelectedItem() , (String)txtAmount.getText());
					            	if(updatesuccess) {
					            		JOptionPane.showMessageDialog(null, "Successfully saved record!","Save Record.",JOptionPane.INFORMATION_MESSAGE);					            		
					            	}  else
						            {
						                JOptionPane.showMessageDialog(null,"Failed to update account balance.","Cannot Save",JOptionPane.INFORMATION_MESSAGE);
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
				});
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			
			btnClear = new JButton("Clear");
			btnClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clear();
				}
			});
			buttonPane.add(btnClear);
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
				btnclose.setMnemonic('L');
				btnclose.setActionCommand("Cancel");
				buttonPane.add(btnclose);
			}
		}
		fillAccount();
		fillStaff();
		AutoID();
	}
	public void fillBalance() {
		String result = ""+CalculateIntrest.something((String)cboAccount.getSelectedItem() )[1];
		balance = result;
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
        txtAmount.setText("");
    }
	public void AutoID() throws ClassNotFoundException
	{
		 lblForID.setText((String.valueOf(mySQLQueries.getAutoid("id", "withdraw", "WD-"))));
	}
}


