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
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.SwingConstants;

public class StaffEntry extends JInternalFrame {
	private JLabel lblstaid;
	private JTextField txtstaname;
	private JTextField txtphone;
	private JButton btnSave;
	private JButton btnclose;
	private JButton btnCancel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String gender = "male";
	private JTextField txtEmail;
	private JRadioButton rdoMale;
	private JRadioButton rdoFemale;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			StaffEntry dialog = new StaffEntry();
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
	public StaffEntry() throws ClassNotFoundException {
		setTitle("Staff Entry");
//		setBounds(100, 100, 392, 454);
		setBounds(0, 0, Constants.c_width, Constants.c_height);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, new Color(51, 102, 255), new Color(0, 0, 204)));
		panel.setBounds(75, 15, 519, 335);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Staff ID:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(101, 89, 81, 14);
		panel.add(lblNewLabel);
		
		JLabel lblCusto = new JLabel("Staff Name:");
		lblCusto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCusto.setBounds(91, 128, 91, 14);
		panel.add(lblCusto);
		
		JLabel lblPhoneNp = new JLabel("Phone no:");
		lblPhoneNp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhoneNp.setBounds(100, 221, 79, 14);
		panel.add(lblPhoneNp);
		
		lblstaid = new JLabel("");
		lblstaid.setBounds(221, 89, 151, 14);
		panel.add(lblstaid);
		
		txtstaname = new JTextField("Kaung Thu Han");
		txtstaname.setBounds(221, 125, 167, 20);
		panel.add(txtstaname);
		txtstaname.setColumns(10);
		
		txtphone = new JTextField("09797246971");
		txtphone.setBounds(219, 218, 169, 20);
		txtphone.setColumns(10);
		panel.add(txtphone);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGender.setBounds(102, 176, 75, 14);
		panel.add(lblGender);
		
		rdoMale = new JRadioButton("Male");
		rdoMale.setBounds(219, 169, 81, 23);
		rdoMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "male";
			}
		});
		buttonGroup.add(rdoMale);
		panel.add(rdoMale);
		
		rdoFemale = new JRadioButton("Female");
		rdoFemale.setBounds(302, 169, 109, 23);
		rdoFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "female";
			}
		});
		buttonGroup.add(rdoFemale);
		panel.add(rdoFemale);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(99, 277, 79, 14);
		panel.add(lblEmail);
		
		txtEmail = new JTextField("");
		txtEmail.setBounds(221, 272, 169, 20);
		txtEmail.setColumns(10);
		panel.add(txtEmail);
		
		lblNewLabel_1 = new JLabel("Create New Staff");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Pyidaungsu", Font.BOLD, 17));
		lblNewLabel_1.setBounds(120, 24, 268, 19);
		panel.add(lblNewLabel_1);
		
		btnSave = new JButton("Save");
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setBackground(new Color(0, 128, 0));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

		         if(Checking.IsValidName(txtstaname.getText()) || Checking.IsNull(txtstaname.getText()))
		        {
		            JOptionPane.showMessageDialog(null, "Please enter VALID Name.");
		            txtstaname.requestFocus();
		            txtstaname.selectAll();
		        }
		         
//		         Check Gender
		         else if(rdoMale.isSelected()==false && rdoFemale.isSelected()==false)
		         {
		            JOptionPane.showMessageDialog(null, "Please select gender");
		         }
		         
//		         Check Phone
		        else if(Checking.IsNull(txtphone.getText()))
		        {
		            JOptionPane.showMessageDialog(null, "Please enter Phone.");
		            txtphone.requestFocus();
		            txtphone.selectAll();
		        }   

		        else if(!Checking.IsPhoneNoformat(txtphone.getText()))
		        {
		            JOptionPane.showMessageDialog(null,"Phone Number must start with 09 and entered number after 09 must not be zero!");
		            txtphone.requestFocus();
		            txtphone.selectAll();
		        }
		         
//		         Check Email
		         else if(Checking.IsNull(txtEmail.getText()))
		        {
		            JOptionPane.showMessageDialog(null, "Please enter Email.");
		            txtEmail.requestFocus();
		            txtEmail.selectAll();
		        }
		         else if(!Checking.IsEformat(txtEmail.getText()))
		         {
		            JOptionPane.showMessageDialog(null, "Invalid Email Format.Please reenter!");
		            txtEmail.requestFocus();
		            txtEmail.selectAll();
		         }
		         else if(rdoMale.isSelected()==false && rdoFemale.isSelected()==false)
		         {
		            JOptionPane.showMessageDialog(null, "Please select gender");
		         }
		         
		         else{
		        	 String st[] = new String[5];
						st[0] = (String)txtstaname.getText();
						st[1] = gender;
						st[2] = (String) txtphone.getText();
						st[3] = (String)txtEmail.getText();					
						
			            boolean ee = mySQLQueries.isduplicate("staff", st);
			            boolean ee1= mySQLQueries.isduplicateEmailForStaff("staff", st);
			            if(!ee)
			            {
			                JOptionPane.showMessageDialog(null, "Duplicate Record!");
			                txtstaname.requestFocus();
			                txtstaname.selectAll();
			            }
			            else if(!ee1) {
			            	JOptionPane.showMessageDialog(null, "Duplicate Email!");
			            	txtEmail.requestFocus();
			            	txtEmail.selectAll();
			            }
			            else
						       {
			            	//id,name,gender,phone
					            String str[]=new String[5];
					            str[0]=lblstaid.getText();
					            str[1]=txtstaname.getText();
					            //gender
					            str[2]=gender;
					            
					            //phone
					            str[3]=txtphone.getText();
					            str[4]=txtEmail.getText();
					            
					            boolean save = mySQLQueries.insertData("staff", str);
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
		btnSave.setBounds(202, 393, 90, 35);
		getContentPane().add(btnSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(255, 215, 0));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnCancel.setBounds(310, 393, 90, 35);
		getContentPane().add(btnCancel);
		
		btnclose = new JButton("Close");
		btnclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confrim",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
				{	
					dispose();
				}
			}
		});
		btnclose.setBounds(420, 393, 90, 35);
		getContentPane().add(btnclose);
		AutoID();
		clear();
	}
		public void clear()
	    {
	        txtstaname.setText("");
	        txtphone.setText("");
	        txtEmail.setText("");
	        rdoMale.setSelected(false);
	        rdoFemale.setSelected(false);
	        
	        txtstaname.requestFocus();
	    }

		public void AutoID() throws ClassNotFoundException
	    {
	    	 lblstaid.setText((String.valueOf(mySQLQueries.getAutoid("id", "staff", "ST-"))));
	    }
}
