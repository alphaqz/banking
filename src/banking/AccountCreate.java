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
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AccountCreate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBalance;
	private JLabel lblForID;
	JComboBox cboCustomer;

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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Account Type Entry", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAccID = new JLabel("ID:");
		lblAccID.setBounds(103, 57, 46, 14);
		contentPanel.add(lblAccID);
		
		lblForID = new JLabel("");
		lblForID.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblForID.setBounds(222, 57, 86, 14);
		contentPanel.add(lblForID);
		
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setBounds(103, 104, 69, 14);
		contentPanel.add(lblBalance);
		
		txtBalance = new JTextField();
		txtBalance.setColumns(10);
		txtBalance.setBounds(222, 101, 86, 20);
		contentPanel.add(txtBalance);
		
		cboCustomer = new JComboBox();
		cboCustomer.setBounds(222, 143, 186, 22);
		contentPanel.add(cboCustomer);
		
		JLabel lblCustomer = new JLabel("Customer:");
		lblCustomer.setBounds(80, 147, 69, 14);
		contentPanel.add(lblCustomer);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
				        if(Checking.IsNull(txtBalance.getText()))
				        {
				            JOptionPane.showMessageDialog(null, "Please enter Intrest.");
				            txtBalance.requestFocus();
				            txtBalance.selectAll();
				        }
				        else if(!Checking.IsAllDigit(txtBalance.getText()))
				        {
				            JOptionPane.showMessageDialog(null,"Please enter valid number.");
				            txtBalance.requestFocus();
				            txtBalance.selectAll();
				        }else {							
							
				            	String str[] = new String[8];
				            	str[0] = (String)lblForID.getText();						
				            	str[1] = (String)txtBalance.getText();
				            	
				            	boolean save = mySQLQueries.insertData("account", str);
					            if(save)
					            {
					                JOptionPane.showMessageDialog(null, "Successfully saved record!","Save Record.",JOptionPane.INFORMATION_MESSAGE);
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
		fillCustomer();
		AutoID();
	}
	public void fillCustomer()
    {
        String str[]=mySQLQueries.getIDForChoice("customer");
        cboCustomer.addItem("-Select-");
        for(int i=0;i<str.length;i++)
        	cboCustomer.addItem(str[i].toString());
    }
	public void clear()
    {      
        txtBalance.setText("");
    }
	public void AutoID() throws ClassNotFoundException
	{
		 lblForID.setText((String.valueOf(mySQLQueries.getAutoid("id", "account", "AT-"))));
	}
}


