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

public class CustomerUpdate extends JInternalFrame {
	private JTextField txtname;
	private JTextField txtaddress;
	private JTextField txtphone;
	private JTextField txtemail;
	private JComboBox cbocustomerid;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnclose;
	private JTextField txtJob;
	private JTextField txtNrc;
	private JRadioButton rdbtnMale = new JRadioButton("male");;
	private JRadioButton rdbtnFemale = new JRadioButton("Female");;
	private String gender = "";
	private final ButtonGroup buttonGroup = new ButtonGroup();

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
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Customer Update Info:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 11, 325, 320);
			getContentPane().add(panel);
			{
				JLabel lblNewLabel = new JLabel("Customer ID:");
				lblNewLabel.setBounds(20, 23, 81, 14);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblCusto = new JLabel("Customer Name:");
				lblCusto.setBounds(10, 62, 109, 14);
				panel.add(lblCusto);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Address:");
				lblNewLabel_2.setBounds(38, 167, 56, 14);
				panel.add(lblNewLabel_2);
			}
			{
				JLabel lblPhoneNp = new JLabel("Phone No:");
				lblPhoneNp.setBounds(38, 132, 79, 14);
				panel.add(lblPhoneNp);
			}
			{
				JLabel lblEmail = new JLabel("Email:");
				lblEmail.setBounds(60, 271, 46, 14);
				panel.add(lblEmail);
			}
			{
				txtname = new JTextField();
				txtname.setColumns(10);
				txtname.setBounds(111, 59, 167, 20);
				panel.add(txtname);
			}
			{
				txtaddress = new JTextField();
				txtaddress.setColumns(10);
				txtaddress.setBounds(109, 164, 169, 20);
				panel.add(txtaddress);
			}
			{
				txtphone = new JTextField();
				txtphone.setColumns(10);
				txtphone.setBounds(109, 129, 169, 20);
				panel.add(txtphone);
			}
			{
				txtemail = new JTextField();
				txtemail.setColumns(10);
				txtemail.setBounds(111, 268, 167, 20);
				panel.add(txtemail);
			}
			{
				cbocustomerid = new JComboBox();
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
				cbocustomerid.setBounds(111, 19, 167, 22);
				panel.add(cbocustomerid);
			}
			
			JLabel lblJob = new JLabel("Job:");
			lblJob.setHorizontalAlignment(SwingConstants.RIGHT);
			lblJob.setBounds(38, 198, 51, 14);
			panel.add(lblJob);
			
			txtJob = new JTextField();
			txtJob.setColumns(10);
			txtJob.setBounds(109, 192, 169, 20);
			panel.add(txtJob);
			
			txtNrc = new JTextField();
			txtNrc.setColumns(10);
			txtNrc.setBounds(109, 230, 169, 20);
			panel.add(txtNrc);
			
			JLabel lblNewLabel_2 = new JLabel("NRC:");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_2.setBounds(38, 230, 56, 14);
			panel.add(lblNewLabel_2);
			
			JLabel lblGender = new JLabel("Gender:");
			lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
			lblGender.setBounds(10, 97, 79, 14);
			panel.add(lblGender);
			
			rdbtnMale = new JRadioButton("Male");
			rdbtnFemale.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gender = "male";
				}
			});
			buttonGroup.add(rdbtnMale);
			rdbtnMale.setBounds(121, 86, 56, 23);
			panel.add(rdbtnMale);
			
			rdbtnFemale = new JRadioButton("Female");
			rdbtnFemale.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gender = "female";
				}
			});
			buttonGroup.add(rdbtnFemale);
			rdbtnFemale.setBounds(195, 86, 83, 23);
			panel.add(rdbtnFemale);
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
			btnclose.setBounds(250, 342, 85, 23);
			getContentPane().add(btnclose);
		}
		{
			btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
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
			btnDelete.setBounds(134, 342, 85, 23);
			getContentPane().add(btnDelete);
		}
		{
			btnUpdate = new JButton("Update");
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
			btnUpdate.setBounds(24, 342, 79, 23);
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