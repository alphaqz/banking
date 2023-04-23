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
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class CustomerCreate extends JInternalFrame {
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
	private JLabel lblNewLabel_1;

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
//		setBounds(100, 100, 392, 454);
		setBounds(0, 0, Constants.c_width, Constants.c_height);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, new Color(51, 102, 255), new Color(0, 0, 204)));
		panel.setBounds(86, 15, 519, 372);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer ID:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(120, 95, 81, 14);
		panel.add(lblNewLabel);
		
		JLabel lblCusto = new JLabel("Customer Name:");
		lblCusto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCusto.setBounds(82, 130, 120, 14);
		panel.add(lblCusto);
		
		JLabel lblNewLabel_2 = new JLabel("Address:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(144, 196, 56, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblPhoneNp = new JLabel("Phone no:");
		lblPhoneNp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhoneNp.setBounds(136, 231, 63, 14);
		panel.add(lblPhoneNp);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(149, 263, 46, 14);
		panel.add(lblEmail);
		
		lblcusid = new JLabel("");
		lblcusid.setBounds(230, 95, 151, 14);
		panel.add(lblcusid);
		
		txtcusname = new JTextField("asdfsa");
		txtcusname.setBounds(230, 127, 167, 20);
		panel.add(txtcusname);
		txtcusname.setColumns(10);
		
		txtaddress = new JTextField("asdf");
		txtaddress.setBounds(228, 190, 169, 20);
		txtaddress.setColumns(10);
		panel.add(txtaddress);
		
		txtphone = new JTextField("09797246971");
		txtphone.setBounds(228, 227, 169, 20);
		txtphone.setColumns(10);
		panel.add(txtphone);
		
		txtEmail = new JTextField("eieihtay@gmail.com");
		txtEmail.setBounds(230, 260, 167, 20);
		txtEmail.setColumns(10);
		panel.add(txtEmail);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGender.setBounds(150, 163, 46, 14);
		panel.add(lblGender);
		
		JLabel lblNrc = new JLabel("Nrc:");
		lblNrc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNrc.setBounds(148, 300, 46, 14);
		panel.add(lblNrc);
		
		rdoMale = new JRadioButton("Male");
		rdoMale.setBounds(230, 157, 81, 23);
		rdoMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "male";
			}
		});
		buttonGroup.add(rdoMale);
		panel.add(rdoMale);
		
		rdoFemale = new JRadioButton("Female");
		rdoFemale.setBounds(337, 157, 109, 23);
		rdoFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "female";
			}
		});
		buttonGroup.add(rdoFemale);
		panel.add(rdoFemale);
		
		txtNrc = new JTextField("9/test(N)000000");
		txtNrc.setBounds(230, 295, 167, 20);
		txtNrc.setColumns(10);
		panel.add(txtNrc);
		
		txtJob = new JTextField("dev");
		txtJob.setBounds(230, 333, 167, 20);
		txtJob.setColumns(10);
		panel.add(txtJob);
		
		JLabel lblJob = new JLabel("Job:");
		lblJob.setHorizontalAlignment(SwingConstants.RIGHT);
		lblJob.setBounds(148, 336, 46, 14);
		panel.add(lblJob);
		
		lblNewLabel_1 = new JLabel("Create New Customer");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Pyidaungsu", Font.BOLD, 17));
		lblNewLabel_1.setBounds(120, 34, 289, 19);
		panel.add(lblNewLabel_1);
		
		btnSave = new JButton("Save");
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setBackground(new Color(0, 128, 0));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

		         if(Checking.IsValidName(txtcusname.getText()) || Checking.IsNull(txtcusname.getText()))
		        {
		            JOptionPane.showMessageDialog(null, "Please enter VALID Name.");
		            txtcusname.requestFocus();
		            txtcusname.selectAll();
		        }
		         else if(rdoMale.isSelected()==false && rdoFemale.isSelected()==false)
		         {
		            JOptionPane.showMessageDialog(null, "Please select gender");
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
		         else if(Checking.IsNull(txtNrc.getText())) {
		        	 JOptionPane.showMessageDialog(null, "Please enter NRC No.");
			         txtNrc.requestFocus();
			         txtNrc.selectAll();
		         }
		         else if(!Checking.IsNRCformat(txtNrc.getText())) {
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
			            boolean ee1 = mySQLQueries.isduplicateNRC("customer", st);
			            boolean ee2= mySQLQueries.isduplicateEmail("customer", st);
			            if(!ee)
			            {
			                JOptionPane.showMessageDialog(null, "Duplicate Phone No!");
			                txtphone.requestFocus();
			                txtphone.selectAll();
			            }else if(!ee1) {
			            	JOptionPane.showMessageDialog(null, "Duplicate NRC!");
			                txtNrc.requestFocus();
			                txtNrc.selectAll();
			            }else if(!ee2) {
			            	JOptionPane.showMessageDialog(null, "Duplicate Email!");
			                txtEmail.requestFocus();
			                txtEmail.selectAll();
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
		btnSave.setBounds(199, 402, 90, 35);
		getContentPane().add(btnSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setBackground(new Color(255, 215, 0));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnCancel.setBounds(315, 402, 90, 35);
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
		btnclose.setBounds(431, 402, 90, 35);
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
	        buttonGroup.clearSelection();
	    }

		public void AutoID() throws ClassNotFoundException
	    {
	    	 lblcusid.setText((String.valueOf(mySQLQueries.getAutoid("id", "customer", "CU-"))));
	    }
	}
