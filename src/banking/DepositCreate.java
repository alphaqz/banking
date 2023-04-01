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

public class DepositCreate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtAmount;
	private JLabel lblForID;
	JComboBox cboAccount;
	JComboBox cboStaff; 
	JLabel lblforaccid;
	JLabel lblforbalance;
	JLabel lblforcusname;
	JLabel lblforacctype;
	JLabel lblforbalanceNew;
	List<String> staffIdList = new ArrayList<String>();  
	List<String> AccountIdList = new ArrayList<String>(); 
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
		setTitle("Deposit Entry");
		setBounds(100, 100, 450, 456);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Deposit Entry", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblDepositID = new JLabel("ID:");
		lblDepositID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepositID.setBounds(126, 57, 46, 14);
		contentPanel.add(lblDepositID);
		
		lblForID = new JLabel("");
		lblForID.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblForID.setBounds(222, 57, 86, 14);
		contentPanel.add(lblForID);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setBounds(103, 104, 69, 14);
		contentPanel.add(lblAmount);
		
		txtAmount = new JTextField();
		txtAmount.setColumns(10);
		txtAmount.setBounds(222, 101, 86, 20);
		contentPanel.add(txtAmount);
		
		cboAccount = new JComboBox();
		cboAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboAccount.getSelectedIndex()> 0) {	
					String[] result = mySQLQueries.getAccountDataFordeposit((String)cboAccount.getSelectedItem());
					lblforaccid.setText(result[0]);
					lblforbalance.setText(result[1]);
					lblforacctype.setText(result[2]);
					lblforcusname.setText(result[3]);
					lblforbalanceNew.setText(""+CalculateIntrest.something(result[0]));
					
				}
			}
		});
		cboAccount.setBounds(222, 143, 186, 22);
		contentPanel.add(cboAccount);
		
		JLabel lblAccount = new JLabel("Account:");
		lblAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccount.setBounds(103, 147, 69, 14);
		contentPanel.add(lblAccount);
		
		JLabel lblStaff = new JLabel("Staff:");
		lblStaff.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStaff.setBounds(103, 180, 69, 14);
		contentPanel.add(lblStaff);
		
		cboStaff = new JComboBox();
		cboStaff.setBounds(222, 176, 186, 22);
		contentPanel.add(cboStaff);
		
		JPanel contentPanel_1 = new JPanel();
		contentPanel_1.setLayout(null);
		contentPanel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Account Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPanel_1.setBounds(0, 240, 434, 133);
		contentPanel.add(contentPanel_1);
		
		JLabel lblAccID = new JLabel("ID:");
		lblAccID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccID.setBounds(105, 30, 74, 14);
		contentPanel_1.add(lblAccID);
		
		lblforaccid = new JLabel("");
		lblforaccid.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblforaccid.setBounds(224, 30, 86, 14);
		contentPanel_1.add(lblforaccid);
		
		JLabel lblbalance = new JLabel("Balance:");
		lblbalance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblbalance.setBounds(105, 56, 74, 14);
		contentPanel_1.add(lblbalance);
		
	
		lblforbalance = new JLabel("");
		lblforbalance.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblforbalance.setBounds(224, 56, 86, 14);
		//contentPanel_1.add(lblforbalance);
		
		JLabel lblCust = new JLabel("Account type:");
		lblCust.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCust.setBounds(93, 81, 86, 14);
		contentPanel_1.add(lblCust);
		
		lblforacctype = new JLabel("");
		lblforacctype.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblforacctype.setBounds(224, 81, 164, 14);
		contentPanel_1.add(lblforacctype);
		
		JLabel lblCustomerName = new JLabel("Customer Name:");
		lblCustomerName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustomerName.setBounds(49, 106, 130, 14);
		contentPanel_1.add(lblCustomerName);
	
		lblforcusname = new JLabel("");
		lblforcusname.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblforcusname.setBounds(224, 106, 172, 14);
		contentPanel_1.add(lblforcusname);
		
		lblforbalanceNew = new JLabel("");
		lblforbalanceNew.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblforbalanceNew.setBounds(224, 55, 86, 14);
		contentPanel_1.add(lblforbalanceNew);
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
				        }else {							
							
				            	String str[] = new String[8];
				            	str[0] = (String)lblForID.getText();						
				            	str[1] = (String)txtAmount.getText();

				            	str[2] =  (String) cboAccount.getSelectedItem();
				            	str[3] = staffIdList.get(cboStaff.getSelectedIndex()-1);
				            	//System.out.println("inserting into deposit " + str[0] + str[1] + str[3]);
				            	boolean save = mySQLQueries.insertData("deposit", str);
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
				
				JButton btnClear = new JButton("Clear");
				buttonPane.add(btnClear);
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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
        txtAmount.setText("");
        cboAccount.setSelectedIndex(0);
        cboStaff.setSelectedIndex(0);
    }
	public void AutoID() throws ClassNotFoundException
	{
		 lblForID.setText((String.valueOf(mySQLQueries.getAutoid("id", "deposit", "DE-"))));
	}
}


