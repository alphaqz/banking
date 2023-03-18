package banking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
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

public class CustomerCreate extends JDialog {
	private JLabel lblcusid;
	private JTextField txtcusname;
	private JTextField txtaddress;
	private JTextField txtphone;
	private JTextField txtEmail;
	private JButton btnSave;
	private JButton btnclose;
	private JButton btnCancel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtNrc;
	private JTextField txtJob;
	private String gender = "male";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CustomerCreate dialog = new CustomerCreate();
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
	public CustomerCreate() throws ClassNotFoundException {
		setTitle("Customer Entry");
		setBounds(100, 100, 392, 454);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Customer Info:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 317, 359);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer ID:");
		lblNewLabel.setBounds(20, 23, 81, 14);
		panel.add(lblNewLabel);
		
		JLabel lblCusto = new JLabel("Customer Name:");
		lblCusto.setBounds(10, 62, 109, 14);
		panel.add(lblCusto);
		
		JLabel lblNewLabel_2 = new JLabel("Address:");
		lblNewLabel_2.setBounds(43, 133, 56, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblPhoneNp = new JLabel("Phone No:");
		lblPhoneNp.setBounds(38, 177, 79, 14);
		panel.add(lblPhoneNp);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(60, 216, 46, 14);
		panel.add(lblEmail);
		
		lblcusid = new JLabel("");
		lblcusid.setBounds(111, 23, 151, 14);
		panel.add(lblcusid);
		
		txtcusname = new JTextField("Kaung Thu Han");
		txtcusname.setBounds(111, 59, 167, 20);
		panel.add(txtcusname);
		txtcusname.setColumns(10);
		
		txtaddress = new JTextField("Yangon");
		txtaddress.setColumns(10);
		txtaddress.setBounds(109, 130, 169, 20);
		panel.add(txtaddress);
		
		txtphone = new JTextField("09797246971");
		txtphone.setColumns(10);
		txtphone.setBounds(109, 174, 169, 20);
		panel.add(txtphone);
		
		txtEmail = new JTextField("kaungthuhan@gmail.com");
		txtEmail.setColumns(10);
		txtEmail.setBounds(111, 213, 167, 20);
		panel.add(txtEmail);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(45, 87, 46, 14);
		panel.add(lblGender);
		
		JLabel lblNrc = new JLabel("Nrc:");
		lblNrc.setBounds(55, 257, 46, 14);
		panel.add(lblNrc);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Male");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "male";
			}
		});
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(101, 83, 81, 23);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "female";
			}
		});
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setBounds(192, 83, 109, 23);
		panel.add(rdbtnFemale);
		
		txtNrc = new JTextField("9/khamasa(n)0440252");
		txtNrc.setColumns(10);
		txtNrc.setBounds(111, 254, 167, 20);
		panel.add(txtNrc);
		
		txtJob = new JTextField("Developer");
		txtJob.setColumns(10);
		txtJob.setBounds(111, 299, 167, 20);
		panel.add(txtJob);
		
		JLabel lblJob = new JLabel("Job:");
		lblJob.setBounds(55, 302, 46, 14);
		panel.add(lblJob);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

		         if(Checking.IsValidName(txtcusname.getText()))
		        {
		            JOptionPane.showMessageDialog(null, "Please enter VALID Name!");
		            txtcusname.requestFocus();
		            txtcusname.selectAll();
		        }
		        else if(Checking.IsNull(txtaddress.getText()))
		        {
		            JOptionPane.showMessageDialog(null,"Please enter Address.");;
		            txtaddress.requestFocus();
		            txtaddress.selectAll();
		        }
		        else if(Checking.IsNull(txtphone.getText()))
		        {
		            JOptionPane.showMessageDialog(null, "Please enter Phone.");
		            txtphone.requestFocus();
		            txtphone.selectAll();
		        }
		        else if(!Checking.IsAllDigit(txtphone.getText()))
		        {
		            JOptionPane.showMessageDialog(null,"Please enter valid Phone Number.");
		            txtphone.requestFocus();
		            txtphone.selectAll();
		        }
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
		         
		         else{
		        	 String st[] = new String[8];
						st[0] = (String)txtcusname.getText();
						st[1] = gender;
						
						st[2] = (String)txtaddress.getText();
						st[3] = (String)txtphone.getText();
						st[4] = (String)txtEmail.getText();
						st[5] = (String)txtNrc.getText();
						st[6] = (String)txtEmail.getText();
						st[7] = (String)txtJob.getText();
						
			            boolean ee = mySQLQueries.isduplicate("customer", st);
			            if(!ee)
			            {
			                JOptionPane.showMessageDialog(null, "Duplicate Record!");
			                txtcusname.requestFocus();
			                txtcusname.selectAll();
			            }
			            else
						       {
			            	//id,name,gender,phone,address, job, nrc
					            String str[]=new String[8];
					            str[0]=lblcusid.getText();
					            str[1]=txtcusname.getText();
					            //gender
					            str[2]=gender;
					            str[3]=txtaddress.getText();
					            //job
					            str[4]=txtphone.getText();
					            //nrc
					            str[5]=txtEmail.getText();
					            str[6]=txtNrc.getText();
					            str[7]=txtJob.getText();
					            boolean save = mySQLQueries.insertData("customer", str);
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
		btnSave.setBounds(10, 381, 79, 23);
		getContentPane().add(btnSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnCancel.setBounds(126, 381, 85, 23);
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
		btnclose.setBounds(242, 381, 85, 23);
		getContentPane().add(btnclose);
		AutoID();
	}
public void clear()
	    {
	        txtcusname.setText("");
	        txtaddress.setText("");
	        txtphone.setText("");
	        txtEmail.setText("");
	        txtcusname.requestFocus();
	    }

public void AutoID() throws ClassNotFoundException
	    {
	    	 lblcusid.setText((String.valueOf(mySQLQueries.getAutoid("id", "customer", "CU-"))));
	    }
}
