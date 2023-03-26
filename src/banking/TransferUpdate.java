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

public class TransferUpdate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtAmount;
	private JButton btnDelete;
	private JButton btnClose;
	private JButton btnUpdate;
	
	Border backline=BorderFactory.createLineBorder(Color.black);
	
	date d=new date();
	
	List<String> staffIdList = new ArrayList<String>();
	private JLabel lblRAcc;
	private JLabel lblTAcc;
	private JLabel lblStaff;
	private JComboBox cboTID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TransferUpdate dialog = new TransferUpdate();
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
	public TransferUpdate() throws ClassNotFoundException {
		setTitle("Transfer Update Transaction :");
		setBounds(100, 100, 450, 506);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Transfer Update Process Transaction :", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		txtAmount = new JTextField();
		txtAmount.setBounds(206, 93, 157, 25);
		panel.add(txtAmount);
		txtAmount.setColumns(10);
		
		Border backline=BorderFactory.createLineBorder(Color.black);
		JLabel lbldate = new JLabel("");
		lbldate.setBounds(206, 143, 157, 19);
		lbldate.setBorder(backline);
		panel.add(lbldate);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if(cboTID.getSelectedIndex()==0){
		        	JOptionPane.showMessageDialog(null, "Please choose Transfer ID");						
		        }
		        else {
		        	String r[]= mySQLQueries.getTransferData(cboTID.getSelectedItem().toString());
		        	int amt=Integer.parseInt(r[0]);//transfered amt
		        	System.out.println("previous transfer amt "+amt);
		        	
		            String str[] = new String[3];						
		            str[0] = (String)txtAmount.getText();//update amt
		            str[1] = (String)lblRAcc.getText();//Receive Account No
		            str[2] = (String)lblTAcc.getText();//Transfer Account No
		            
		            System.out.println("update amt "+str[0]);
		            if(Integer.parseInt(str[0])==amt) {
		            	JOptionPane.showMessageDialog(null, "Please change amount for update");
		            	txtAmount.requestFocus();
		            }
		            else {
		            	//get received && transfered amount from transfer table
			            int r_amt= Integer.parseInt(mySQLQueries.getReceiveAmount(str[1]));
			            int t_amt= Integer.parseInt(mySQLQueries.getAmount(str[2]));
			            
			            r_amt-=amt;
			            t_amt+=amt;
//			            System.out.println(r_amt);
//			            System.out.println(t_amt);
			            
			            if(t_amt<Integer.parseInt(str[0])) {
			            	JOptionPane.showMessageDialog(null, "Insufficient balance!");
							txtAmount.requestFocus();
			            }
			            else {
			            	//update amount
				            r_amt+=Integer.parseInt(str[0]);
				            t_amt-=Integer.parseInt(str[0]);
				            
//				            System.out.println(r_amt);
//				            System.out.println(t_amt);
				            
				            boolean success=mySQLQueries.updateAmount("transfer", str[1], String.valueOf(r_amt));						
							boolean success1=mySQLQueries.updateAmount("transfer", str[2],String.valueOf(t_amt));
							boolean update=mySQLQueries.updateAmount("transferUpdate", cboTID.getSelectedItem().toString(), String.valueOf(str[0]));
							
							if(update && success && success1) {
			            		JOptionPane.showMessageDialog(null, "Successfully transfered amount!","Save Record.",JOptionPane.INFORMATION_MESSAGE);					            		
			            	}  
							else
				            {
				                JOptionPane.showMessageDialog(null,"Failed to update account balance.","Cannot Save",JOptionPane.INFORMATION_MESSAGE);
				            }
			            }
			            
			            
		            }
	                     
		        }
			}

			
		});
		btnUpdate.setMnemonic('S');
		btnUpdate.setBounds(107, 390, 87, 27);
		contentPanel.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    String id = cboTID.getSelectedItem().toString();
                    if(JOptionPane.showConfirmDialog(null, "Are you Sure Delete?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
                    {
                    	mySQLQueries.deleteRecord("transfer", id);
                    	clear();
//                    	fillTransferID();
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
		btnDelete.setMnemonic('C');
		btnDelete.setBounds(221, 390, 87, 27);
		contentPanel.add(btnDelete);
		
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
		
		lblRAcc = new JLabel("");
		lblRAcc.setBounds(206, 187, 157, 19);
		lblRAcc.setBorder(backline);
		panel.add(lblRAcc);
		
		lblTAcc = new JLabel("");
		lblTAcc.setBounds(206, 233, 157, 19);
		lblTAcc.setBorder(backline);
		panel.add(lblTAcc);
		
		lblStaff = new JLabel("");
		lblStaff.setBounds(206, 283, 157, 19);
		lblStaff.setBorder(backline);
		panel.add(lblStaff);
		
		cboTID = new JComboBox();
		cboTID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowTransferDetail();
			}
		});
		cboTID.setBounds(206, 43, 157, 27);
		panel.add(cboTID);
		
		fillTransferID();
		lbldate.setText(d.getMySQLDateFormat());
	}
	private void fillTransferID() {
		String str[]=mySQLQueries.getIDForChoice("transfer");
		cboTID.addItem("- Select -");
		for(int i=0;i<str.length;i++) {
			cboTID.setSelectedIndex(0);
			cboTID.addItem(str[i].toString());
		}
		
	}
	public void ShowTransferDetail()
    {
        String result[]= mySQLQueries.getTransferData(cboTID.getSelectedItem().toString());
        txtAmount.setText(result[0]);
        lblRAcc.setText(result[2]);
        lblTAcc.setText(result[3]);
        lblStaff.setText(result[4]);
    }
	public void clear() {
		cboTID.setSelectedIndex(0);
		txtAmount.setText("");
		lblRAcc.setText("");
		lblTAcc.setText("");
	}
}
