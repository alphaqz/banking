package banking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class CustomerUpdate extends JInternalFrame {
	private JTextField txtname;
	private JTextField txtaddress;
	private JTextField txtphone;
	private JTextField txtemail;
	private JComboBox cbocustomerid;
	private JButton btnCancel;
	private JButton btnUpdate;
	private JButton btnclose;
	private JTextField txtJob;
	private JTextField txtNrc;
	private JRadioButton rdbtnMale = new JRadioButton("male");;
	private JRadioButton rdbtnFemale = new JRadioButton("Female");;
	private String gender = "";
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CustomerUpdate dialog = new CustomerUpdate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CustomerUpdate() {
		setTitle("Customer Update");
//		setBounds(100, 100, 360, 428);
		setBounds(0, 0, Constants.c_width, Constants.c_height);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, new Color(51, 102, 255), new Color(0, 0, 204)));
			panel.setBounds(86, 15, 519, 369);
			getContentPane().add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Customer ID:");
				lblNewLabel.setBounds(125, 93, 81, 14);
				lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblCusto = new JLabel("Customer Name:");
				lblCusto.setBounds(97, 125, 109, 14);
				lblCusto.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(lblCusto);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Address:");
				lblNewLabel_2.setBounds(146, 218, 56, 14);
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(lblNewLabel_2);
			}
			{
				JLabel lblPhoneNp = new JLabel("Phone No:");
				lblPhoneNp.setBounds(127, 183, 79, 14);
				lblPhoneNp.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(lblPhoneNp);
			}
			{
				JLabel lblEmail = new JLabel("Email:");
				lblEmail.setBounds(159, 322, 46, 14);
				lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(lblEmail);
			}
			{
				txtname = new JTextField();
				txtname.setBounds(230, 122, 167, 20);
				txtname.setColumns(10);
				panel.add(txtname);
			}
			{
				txtaddress = new JTextField();
				txtaddress.setBounds(228, 215, 169, 20);
				txtaddress.setColumns(10);
				panel.add(txtaddress);
			}
			{
				txtphone = new JTextField();
				txtphone.setBounds(228, 180, 169, 20);
				txtphone.setColumns(10);
				panel.add(txtphone);
			}
			{
				txtemail = new JTextField();
				txtemail.setBounds(230, 319, 167, 20);
				txtemail.setColumns(10);
				panel.add(txtemail);
			}
			{
				cbocustomerid = new JComboBox();
				cbocustomerid.setBounds(230, 87, 167, 22);
				cbocustomerid.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cbocustomerid.getSelectedIndex()<=0)
						{
							txtname.setText("");
					        txtaddress.setText("");
					        txtphone.setText("");
					        txtemail.setText("");
						}
						else
						{
							showCustomer();
						}
							
						
						
					}
				});
				panel.add(cbocustomerid);
			}
			
			JLabel lblJob = new JLabel("Job:");
			lblJob.setBounds(149, 249, 51, 14);
			lblJob.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(lblJob);
			
			txtJob = new JTextField();
			txtJob.setBounds(228, 247, 169, 20);
			txtJob.setColumns(10);
			panel.add(txtJob);
			
			txtNrc = new JTextField();
			txtNrc.setBounds(228, 283, 169, 20);
			txtNrc.setColumns(10);
			panel.add(txtNrc);
			
			JLabel lblNewLabel_2 = new JLabel("NRC:");
			lblNewLabel_2.setBounds(145, 286, 56, 14);
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(lblNewLabel_2);
			
			JLabel lblGender = new JLabel("Gender:");
			lblGender.setBounds(125, 152, 79, 14);
			lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(lblGender);
			
			rdbtnMale = new JRadioButton("Male");
			rdbtnMale.setBounds(229, 147, 56, 23);
			rdbtnFemale.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gender = "male";
				}
			});
			buttonGroup.add(rdbtnMale);
			panel.add(rdbtnMale);
			
			rdbtnFemale = new JRadioButton("Female");
			rdbtnFemale.setBounds(314, 147, 83, 23);
			rdbtnFemale.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gender = "female";
				}
			});
			buttonGroup.add(rdbtnFemale);
			panel.add(rdbtnFemale);
			{
				lblNewLabel_1 = new JLabel("Update Customer Info:");
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_1.setFont(new Font("Pyidaungsu", Font.BOLD, 17));
				lblNewLabel_1.setBounds(152, 33, 246, 19);
				panel.add(lblNewLabel_1);
			}
		}
		{
			btnclose = new JButton("Close");
			btnclose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confrim",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
					{	
						dispose();
					}
				}
			});
			btnclose.setBounds(424, 399, 90, 35);
			getContentPane().add(btnclose);
		}
		{
			btnCancel = new JButton("Cancel");
			btnCancel.setBackground(new Color(255, 215, 0));
			btnCancel.setForeground(new Color(255, 255, 255));
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
	                    String id = cbocustomerid.getSelectedItem().toString();
	                    if(JOptionPane.showConfirmDialog(null, "Are you Sure Delete?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
	                    {
	                    	mySQLQueries.deleteRecord("customer", id);
	                    	fillCustomer();
	                    }
	                    else
	                    {
	                    	JOptionPane.showMessageDialog(null, "Fail to delete record","Cannot Update",JOptionPane.INFORMATION_MESSAGE);
	                    }
	                } catch(Exception sqle) {
	                    sqle.printStackTrace();
	                }

				}
			});
			btnCancel.setBounds(308, 399, 90, 35);
			getContentPane().add(btnCancel);
		}
		{
			btnUpdate = new JButton("Update");
			btnUpdate.setForeground(new Color(255, 255, 255));
			btnUpdate.setBackground(new Color(0, 128, 0));
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

			        if(cbocustomerid.getSelectedIndex()==0) 
			        {
			            JOptionPane.showMessageDialog(null, "Please choose customer id.");
			            cbocustomerid.requestFocus();
			        } else if(Checking.IsValidName(txtname.getText())) 
			        {
			            JOptionPane.showMessageDialog(null, "Please enter valid Name.");
			            txtname.requestFocus();
			            txtname.selectAll();
			        } else if(Checking.IsNull(txtaddress.getText())) 
			        {
			            JOptionPane.showMessageDialog(null, "Please enter Address.");
			            txtaddress.requestFocus();
			            txtaddress.selectAll();
			        } else if(Checking.IsNull(txtphone.getText())) {
			            JOptionPane.showMessageDialog(null, "Please enter Phone.");
			            txtphone.requestFocus();
			            txtphone.selectAll();
			        }
			        else if(!Checking.IsAllDigit(txtphone.getText().trim())) 
			        {
			            JOptionPane.showMessageDialog(null, "Please enter valid Phone Number.");
			            txtphone.requestFocus();
			            txtphone.selectAll();
			        } 
			        else if(Checking.IsNull(txtemail.getText())) 
			        {
			            JOptionPane.showMessageDialog(null, "Please enter Email.");
			            txtemail.requestFocus();
			            txtemail.selectAll();
			        }
			        else{
							
							
				            
				           
						            if(JOptionPane.showConfirmDialog(null, "Are you Sure Update?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION) 
						            {
						                String []st = new String[7];
						                String id = cbocustomerid.getSelectedItem().toString();						               
						                
						                st[0] = (String)txtname.getText();
										st[1] = gender;
										 //id name gender phone address job nrc email
										st[2] = (String)txtphone.getText();
										st[3] = (String)txtaddress.getText();
										st[4] = (String)txtJob.getText();
										st[5] = (String)txtNrc.getText();
										st[6] = (String)txtemail.getText();
						                boolean save = mySQLQueries.updateRecord("customer", id, st);
						                if(save) 
						                {
						                    JOptionPane.showMessageDialog(null, "Successfully update record!","Update Record.",JOptionPane.INFORMATION_MESSAGE);
						                    clear();
						                }
						                else
						                {
						                	JOptionPane.showMessageDialog(null, "Fail to update record","Cannot Update",JOptionPane.INFORMATION_MESSAGE);
						                }
						            }
				            
			        }
				}
			});
			btnUpdate.setBounds(198, 399, 90, 35);
			getContentPane().add(btnUpdate);
		}
		fillCustomer();
	}

    public void fillCustomer()
   {
       cbocustomerid.removeAllItems();
       cbocustomerid.addItem("-Selected-");
       String str[]=mySQLQueries.getIDForChoice("customer");
       for(int i = 0 ; i<str.length ; i++)
    	   cbocustomerid.addItem(str[i].toString());
       cbocustomerid.setSelectedIndex(0);
   }
    public void showCustomer()
    {
        String result[]= mySQLQueries.getCustomerData(cbocustomerid.getSelectedItem().toString());
        txtname.setText(result[0]);
        //gender
        if(result[1].equals("male")) {
        	rdbtnMale.setSelected(true);
        	gender="male";
        }else {
        	rdbtnFemale.setSelected(true);
        	gender="female";
        }
        txtphone.setText(result[2]);
        txtaddress.setText(result[3]);
        txtJob.setText(result[4]);
        txtNrc.setText(result[5]);
        txtemail.setText(result[6]);
        //id name gender phone address job nrc email
    }
    public void clear()
    {
        txtname.setText("");
        txtaddress.setText("");   
        txtphone.setText("");
        txtemail.setText("");
        cbocustomerid.requestFocus();
        cbocustomerid.setSelectedIndex(0);
    }
}