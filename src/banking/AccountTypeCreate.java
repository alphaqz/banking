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

public class AccountTypeCreate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;
	private JTextField txtIntrest;
	private JLabel lblForID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AccountTypeCreate dialog = new AccountTypeCreate();
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
	public AccountTypeCreate() throws ClassNotFoundException {
		setTitle("Account type Entry");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Account Type Entry", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTitle = new JLabel("Title:");
			lblTitle.setBounds(103, 99, 46, 14);
			contentPanel.add(lblTitle);
		}
		
		txtTitle = new JTextField();
		txtTitle.setBounds(222, 96, 86, 20);
		contentPanel.add(txtTitle);
		txtTitle.setColumns(10);
		
		JLabel lblAccTypeID = new JLabel("ID:");
		lblAccTypeID.setBounds(103, 57, 46, 14);
		contentPanel.add(lblAccTypeID);
		
		lblForID = new JLabel("");
		lblForID.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblForID.setBounds(222, 57, 86, 14);
		contentPanel.add(lblForID);
		
		JLabel lblIntrest = new JLabel("Intrest Rate:");
		lblIntrest.setBounds(103, 152, 69, 14);
		contentPanel.add(lblIntrest);
		
		txtIntrest = new JTextField();
		txtIntrest.setColumns(10);
		txtIntrest.setBounds(225, 149, 86, 20);
		contentPanel.add(txtIntrest);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(Checking.IsNull(txtTitle.getText()))
				        {
				            JOptionPane.showMessageDialog(null,"Please enter Title.");;
				            txtTitle.requestFocus();
				            txtTitle.selectAll();
				        }
				        else if(Checking.IsNull(txtIntrest.getText()))
				        {
				            JOptionPane.showMessageDialog(null, "Please enter Intrest.");
				            txtIntrest.requestFocus();
				            txtIntrest.selectAll();
				        }
				        else if(!Checking.IsAllDigit(txtIntrest.getText()))
				        {
				            JOptionPane.showMessageDialog(null,"Please enter valid number.");
				            txtIntrest.requestFocus();
				            txtIntrest.selectAll();
				        }else {
				        	String st[] = new String[8];
							st[0] = (String)txtTitle.getText();							
							st[1] = (String)txtIntrest.getText();
							
							boolean ee = mySQLQueries.isduplicate("accounttype", st);
					            if(!ee)
					            {
					                JOptionPane.showMessageDialog(null, "Duplicate Record!");
					                txtTitle.requestFocus();
					                txtTitle.selectAll();
					            }else {
					            	String str[] = new String[8];
					            	str[0] = (String)lblForID.getText();
					            	str[1] = (String)txtTitle.getText();							
					            	str[2] = (String)txtIntrest.getText();
					            	
					            	boolean save = mySQLQueries.insertData("accounttype", str);
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
		AutoID();
	}
	public void clear()
    {
        txtTitle.setText("");
        txtIntrest.setText("");
    }
	public void AutoID() throws ClassNotFoundException
	{
		 lblForID.setText((String.valueOf(mySQLQueries.getAutoid("id", "accounttype", "AT-"))));
	}
}

