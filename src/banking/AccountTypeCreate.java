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
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;

public class AccountTypeCreate extends JInternalFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;
	private JTextField txtIntrest;
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, new Color(51, 102, 255), new Color(0, 0, 204)));
		panel.setBounds(86, 15, 519, 335);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		lblTitle = new JLabel("Title:");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle.setBounds(137, 140, 69, 19);
		panel.add(lblTitle);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(248, 137, 96, 25);
		txtTitle.setColumns(10);
		panel.add(txtTitle);
		
		JLabel lblAccTypeID = new JLabel("ID:");
		lblAccTypeID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccTypeID.setBounds(157, 97, 48, 19);
		panel.add(lblAccTypeID);
		
		JLabel lblForID = new JLabel("AT-0000004");
		lblForID.setBounds(248, 96, 96, 21);
		lblForID.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lblForID);
		
		JLabel lblIntrest = new JLabel("Intrest Rate:");
		lblIntrest.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIntrest.setBounds(132, 191, 74, 19);
		panel.add(lblIntrest);
		
		txtIntrest = new JTextField();
		txtIntrest.setBounds(248, 188, 96, 25);
		txtIntrest.setColumns(10);
		panel.add(txtIntrest);
		
		JLabel lblKsPer = new JLabel("kyat per 1 year");
		lblKsPer.setBounds(349, 191, 96, 19);
		panel.add(lblKsPer);
		
		JLabel lblTitle_1 = new JLabel("Create Account Type");
		lblTitle_1.setBounds(185, 35, 149, 30);
		lblTitle_1.setFont(new Font("Pyidaungsu", Font.BOLD, 17));
		panel.add(lblTitle_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 365, 703, 66);
			contentPanel.add(buttonPane);
			{
				JButton btnSave = new JButton("Save");
				btnSave.setForeground(Color.WHITE);
				btnSave.setBackground(new Color(0, 128, 0));
				btnSave.setBounds(418, 5, 79, 39);
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
				cancelButton.setBackground(new Color(255, 204, 51));
				cancelButton.setForeground(Color.WHITE);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				cancelButton.setBounds(518, 5, 79, 39);
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
	}
}


