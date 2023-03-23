package banking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import banking.mySQLQueries;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class Transfer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtAmount;
	private JTextField txtDate;
	private JComboBox cboTAcc;
	private JComboBox cboRAcc;
	private JComboBox cboStaff;
	private JButton btnCancel;
	private JButton btnClose;
	private JButton btnSave;
	private JLabel lblTID;
	
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
		
		JLabel lblDate_1 = new JLabel("Date :");
		lblDate_1.setBounds(10, 143, 90, 19);
		panel.add(lblDate_1);
		
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
		
		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBounds(206, 140, 157, 25);
		panel.add(txtDate);
		
		cboRAcc = new JComboBox();
		cboRAcc.setBounds(206, 183, 157, 27);
		panel.add(cboRAcc);
		
		cboTAcc = new JComboBox();
		cboTAcc.setBounds(206, 229, 157, 27);
		panel.add(cboTAcc);
		
		cboStaff = new JComboBox();
		cboStaff.setBounds(206, 279, 157, 27);
		panel.add(cboStaff);
		
		btnSave = new JButton("Save");
		btnSave.setMnemonic('S');
		btnSave.setBounds(107, 390, 87, 27);
		contentPanel.add(btnSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setMnemonic('C');
		btnCancel.setBounds(221, 390, 87, 27);
		contentPanel.add(btnCancel);
		
		btnClose = new JButton("Close");
		btnClose.setMnemonic('L');
		btnClose.setBounds(337, 390, 87, 27);
		contentPanel.add(btnClose);
		
		AutoID();
		fillReceivedAccountNo();
		fillTransferedAccountNo();
		fillStaff();
	}
	private void fillTransferedAccountNo() {
		String str[]=mySQLQueries.getIDForChoice("account");
		for(int i=0;i<str.length;i++) {
			cboTAcc.addItem(str[i].toString());
		}
	}

	private void fillReceivedAccountNo() {
		String str[]=mySQLQueries.getIDForChoice("account");
		for(int i=0;i<str.length;i++) {
			cboRAcc.addItem(str[i].toString());
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
