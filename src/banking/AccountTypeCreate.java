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
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class AccountTypeCreate extends JInternalFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;
	private JTextField txtIntrest;
	private JLabel lblForID;
	private JLabel lblTitle;

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
//		setBounds(100, 100, 450, 300);
		setBounds(0, 0, Constants.c_width, Constants.c_height);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTitle = new JLabel("Title:");
			lblTitle.setBounds(229, 197, 46, 14);
			contentPanel.add(lblTitle);
		}
		
		txtTitle = new JTextField();
		txtTitle.setBounds(348, 194, 86, 20);
		contentPanel.add(txtTitle);
		txtTitle.setColumns(10);
		
		JLabel lblAccTypeID = new JLabel("ID:");
		lblAccTypeID.setBounds(229, 155, 46, 14);
		contentPanel.add(lblAccTypeID);
		
		lblForID = new JLabel("");
		lblForID.setBounds(348, 148, 86, 21);
		lblForID.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.add(lblForID);
		
		JLabel lblIntrest = new JLabel("Intrest Rate:");
		lblIntrest.setBounds(229, 250, 69, 14);
		contentPanel.add(lblIntrest);
		
		txtIntrest = new JTextField();
		txtIntrest.setBounds(351, 247, 86, 20);
		txtIntrest.setColumns(10);
		contentPanel.add(txtIntrest);
		
		JLabel lblKsPer = new JLabel("kyat per 1 year");
		lblKsPer.setBounds(447, 250, 86, 14);
		contentPanel.add(lblKsPer);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, new Color(51, 102, 255), new Color(0, 0, 204)));
		panel.setBounds(86, 15, 519, 335);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		lblTitle = new JLabel("Create Account Type");
		lblTitle.setFont(new Font("Pyidaungsu", Font.BOLD, 17));
		lblTitle.setBounds(169, 50, 237, 19);
		panel.add(lblTitle);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(178, 372, 364, 59);
			contentPanel.add(buttonPane);
			{
				JButton btnSave = new JButton("Save");
				btnSave.setForeground(new Color(0, 0, 0));
				btnSave.setBackground(new Color(245, 245, 245));
				btnSave.setBounds(25, 13, 90, 35);
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
				buttonPane.setLayout(null);
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						clear();
					}
				});
				cancelButton.setBackground(new Color(245, 245, 245));
				cancelButton.setForeground(new Color(0, 0, 0));
				cancelButton.setBounds(143, 13, 90, 35);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			
			JButton btnClose = new JButton("Close");
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confrim",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
					{	
						dispose();
					}
				}
			});
			btnClose.setActionCommand("Cancel");
			btnClose.setBounds(252, 13, 90, 35);
			buttonPane.add(btnClose);
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


