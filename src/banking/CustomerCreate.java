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
	private String gender = "";
	private JRadioButton rdoMale;
	private JRadioButton rdoFemale;

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
		
		JLabel lblPhoneNp = new JLabel("Phone no:");
		lblPhoneNp.setBounds(38, 177, 79, 14);
		panel.add(lblPhoneNp);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(60, 216, 46, 14);
		panel.add(lblEmail);
		
		lblcusid = new JLabel("");
		lblcusid.setBounds(111, 23, 151, 14);
		panel.add(lblcusid);
		
		txtcusname = new JTextField("asdfsa");
		txtcusname.setBounds(111, 59, 167, 20);
		panel.add(txtcusname);
		txtcusname.setColumns(10);
		
		txtaddress = new JTextField("asdf");
		txtaddress.setColumns(10);
		txtaddress.setBounds(109, 130, 169, 20);
		panel.add(txtaddress);
		
		txtphone = new JTextField("09876543212");
		txtphone.setColumns(10);
		txtphone.setBounds(109, 174, 169, 20);
		panel.add(txtphone);
		
		txtEmail = new JTextField("kaung@gmail.com");
		txtEmail.setColumns(10);
		txtEmail.setBounds(111, 213, 167, 20);
		panel.add(txtEmail);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(45, 87, 46, 14);
		panel.add(lblGender);
		
		JLabel lblNrc = new JLabel("Nrc:");
		lblNrc.setBounds(55, 257, 46, 14);
		panel.add(lblNrc);
		
		rdoMale = new JRadioButton("Male");
		rdoMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "male";
			}
		});
		buttonGroup.add(rdoMale);
		rdoMale.setBounds(101, 83, 81, 23);
		panel.add(rdoMale);
		
		rdoFemale = new JRadioButton("Female");
		rdoFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "female";
			}
		});
		buttonGroup.add(rdoFemale);
		rdoFemale.setBounds(192, 83, 109, 23);
		panel.add(rdoFemale);
		
		txtNrc = new JTextField("");
		txtNrc.setColumns(10);
		txtNrc.setBounds(111, 254, 167, 20);
		panel.add(txtNrc);
		
		txtJob = new JTextField("");
		txtJob.setColumns(10);
		txtJob.setBounds(111, 299, 167, 20);
		panel.add(txtJob);
		
		JLabel lblJob = new JLabel("Job:");
		lblJob.setBounds(55, 302, 46, 14);
		panel.add(lblJob);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

		         if(Checking.IsValidName(txtcusname.getText()) || Checking.IsNull(txtcusname.getText()))
		        {
		            JOptionPane.showMessageDialog(null, "Please enter VALID Name.");
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
		        else if(!Checking.IsPhoneNoformat(txtphone.getText()))
		        {
		            JOptionPane.showMessageDialog(null,"Please enter valid Phone Number. You must start with 09 and entered number after 09 must not be zero!");
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
		         else if(rdoMale.isSelected()==false && rdoFemale.isSelected()==false)
		         {
		            JOptionPane.showMessageDialog(null, "Please select gender");
		         }
		         else if(Checking.IsNull(txtNrc.getText()) || !Checking.IsNRCformat(txtNrc.getText())) {
		        	 System.out.println(txtNrc.getText());
		        	 JOptionPane.showMessageDialog(null, "Invalid NRC No");
		        	 
		         }
		         else if(Checking.IsNull(txtJob.getText()))
			        {
			            JOptionPane.showMessageDialog(null, "Please enter Job.");
			            txtJob.requestFocus();
			            txtJob.selectAll();
			        }
		         
		         else{
		        	 String st[] = new String[8];
						st[0] = (String)txtcusname.getText();
						st[1] = gender;
						st[2] = (String)txtphone.getText();
						st[3] = (String)txtaddress.getText();
						st[4] = (String)txtJob.getText();
						st[5] = (String)txtNrc.getText();
						st[6] = (String)txtEmail.getText();
						
			            boolean ee = mySQLQueries.isduplicate("customer", st);
			            boolean ee1 = mySQLQueries.isduplicate1("customer", st);
			            boolean ee2= mySQLQueries.isduplicate2("customer", st);
			            if(!(ee && ee1 && ee2))
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
	        txtNrc.setText("");
	        txtJob.setText("");
	        txtcusname.requestFocus();
	    }

		public void AutoID() throws ClassNotFoundException
	    {
	    	 lblcusid.setText((String.valueOf(mySQLQueries.getAutoid("id", "customer", "CU-"))));
	    }
	}
