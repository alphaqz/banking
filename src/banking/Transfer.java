package banking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import banking.date;
import banking.mySQLQueries;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Transfer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtAmount;
	private JComboBox cboTransfer;
	private JComboBox cboReceive;
	private JComboBox cboStaff;
	private JButton btnCancel;
	private JButton btnClose;
	private JButton btnSave;
	private JLabel lblTID;
	
	date d=new date();
	
	List<String> staffIdList = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Transfer dialog = new Transfer();
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
	public Transfer() throws ClassNotFoundException {
		setTitle("Transfer Transaction :");
		setBounds(100, 100, 450, 506);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Transfer Process Transaction :", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 15, 414, 332);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Transfer ID :");
		lblNewLabel.setBounds(10, 47, 90, 19);
		panel.add(lblNewLabel);
		
		JLabel lblDate = new JLabel("Amount :");
		lblDate.setBounds(10, 96, 90, 19);
		panel.add(lblDate);
		
		JLabel lblDatelabel = new JLabel("Date :");
		lblDatelabel.setBounds(10, 143, 90, 19);
		panel.add(lblDatelabel);
		
		JLabel lblReceivedAccountNo = new JLabel("Received Account No :");
		lblReceivedAccountNo.setBounds(10, 187, 148, 19);
		panel.add(lblReceivedAccountNo);
		
		JLabel lblTransferedAccountNo = new JLabel("Transfered Account No :");
		lblTransferedAccountNo.setBounds(10, 233, 148, 19);
		panel.add(lblTransferedAccountNo);
		
		JLabel lblStaffId = new JLabel("Staff ID :");
		lblStaffId.setBounds(10, 283, 90, 19);
		panel.add(lblStaffId);
		
		lblTID = new JLabel("");
		lblTID.setBounds(206, 47, 96, 19);
		panel.add(lblTID);
		
		txtAmount = new JTextField();
		txtAmount.setBounds(206, 93, 157, 25);
		panel.add(txtAmount);
		txtAmount.setColumns(10);
		
		cboReceive = new JComboBox();
		cboReceive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
			}
		});
		cboReceive.setBounds(206, 183, 157, 27);
		panel.add(cboReceive);
		
		cboTransfer = new JComboBox();
		cboTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboTransfer.getSelectedIndex()==0) {
//					JOptionPane.showMessageDialog(null, "Please select Transfered Account.");
//					cboTransfer.requestFocus();
				}
				else {
					cboReceive.removeAllItems();
					filterReceiveAccount();
				}
			}
		});
		cboTransfer.setBounds(206, 229, 157, 27);
		panel.add(cboTransfer);
		
		cboStaff = new JComboBox();
		cboStaff.setBounds(206, 279, 157, 27);
		panel.add(cboStaff);
		
		Border backline=BorderFactory.createLineBorder(Color.black);
		JLabel lbldate = new JLabel("");
		lbldate.setBounds(206, 143, 157, 19);
		lbldate.setBorder(backline);
		panel.add(lbldate);
		
		btnSave = new JButton("Save");
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
		        else if(cboTransfer.getSelectedIndex()==0){
		        	JOptionPane.showMessageDialog(null, "Please choose Transfer Account No");						
		        }
		        else if(cboReceive.getSelectedIndex()==0){
		        	JOptionPane.showMessageDialog(null, "Please choose Received Account No");						
		        }
		        else if(cboStaff.getSelectedIndex()==0){
		        	JOptionPane.showMessageDialog(null, "Please choose Staff");						
		        }
		        else {
		        	
		            String str[] = new String[5];
		            str[0] = (String)lblTID.getText();						
		            str[1] = (String)txtAmount.getText();
//		            str[2] = (String)lbldate.getText();
		             
		            str[3] = (String) cboTransfer.getSelectedItem(); 
		            
		            str[2] = (String) cboReceive.getSelectedItem();
		            str[4] = staffIdList.get(cboStaff.getSelectedIndex()-1); 
		            	
		            int saveAmount=0;
		            int t_amount=Integer.parseInt(str[1]);
		            
		            String t_id=cboTransfer.getSelectedItem().toString();
					String r_id=cboReceive.getSelectedItem().toString();
					
					int total_amount= CalculateIntrest.something(t_id);

//					System.out.println(total_amount);
						
		            if(t_amount > total_amount) {
		            	JOptionPane.showMessageDialog(null, "Insufficient balance!");
		            	txtAmount.setText("");
						txtAmount.requestFocus();
		            }
		            else {
		            	boolean save = mySQLQueries.insertData("transfer", str);
		            	saveAmount+=total_amount-t_amount;
		            	int r_amount=Integer.parseInt(mySQLQueries.getAmount(r_id));	
						r_amount+=t_amount;
						boolean success=mySQLQueries.updateAmount("transfer", r_id, String.valueOf(r_amount));						
						boolean success1=mySQLQueries.updateAmount("transfer", t_id,String.valueOf(saveAmount));
						
						if(save && success && success1) {
			           		JOptionPane.showMessageDialog(null, "Successfully transfered amount!","Save Record.",JOptionPane.INFORMATION_MESSAGE);	
			           		try {
								AutoID();
								clear();
							} catch (ClassNotFoundException e1) {
								e1.printStackTrace();
							}
			            }  
						else
				        {
				            JOptionPane.showMessageDialog(null,"Failed to update account balance.","Cannot Save",JOptionPane.INFORMATION_MESSAGE);
				        }
		            }
			            
		        }
			}	
		});
		btnSave.setMnemonic('S');
		btnSave.setBounds(107, 390, 87, 27);
		contentPanel.add(btnSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnCancel.setMnemonic('C');
		btnCancel.setBounds(221, 390, 87, 27);
		contentPanel.add(btnCancel);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confrim",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
				{	
					dispose();
				}
			}
		});
		btnClose.setMnemonic('L');
		btnClose.setBounds(337, 390, 87, 27);
		contentPanel.add(btnClose);
		
		AutoID();
		fillReceivedAccountNo();
		fillTransferedAccountNo();
		fillStaff();
		lbldate.setText(d.getMySQLDateFormat());
	}
	private void fillTransferedAccountNo() {
		String str[]=mySQLQueries.getIDForChoice("account");
		cboTransfer.addItem("- Select -");
		for(int i=0;i<str.length;i++) {
			cboTransfer.addItem(str[i].toString());
		}
	}
	private void clear() {
		cboTransfer.setSelectedIndex(0);
		cboReceive.setSelectedIndex(0);
		cboStaff.setSelectedIndex(0);
		txtAmount.setText("");
	}
	private void fillReceivedAccountNo() {
		String str[]=mySQLQueries.getIDForChoice("account");
		cboReceive.addItem("- Select -");
		for(int i=0;i<str.length;i++) {
			cboReceive.addItem(str[i].toString());
		}
	}
	private void filterReceiveAccount() {
		String str[]=mySQLQueries.getIDForFilter(cboTransfer.getSelectedItem().toString());
		
		cboReceive.addItem("- Select -");
		for(int i=0;i<str.length;i++) {
			cboReceive.addItem(str[i].toString());
		}
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
	
	
	public void AutoID() throws ClassNotFoundException
    {
    	 lblTID.setText((String.valueOf(mySQLQueries.getAutoid("id", "transfer", "TR-"))));
    }
}
