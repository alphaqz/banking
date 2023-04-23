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

public class StaffUpdate extends JInternalFrame {
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtemail;
	private JComboBox cboSID;
	private JButton btnCancel;
	private JButton btnUpdate;
	private JButton btnclose;
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
			StaffUpdate dialog = new StaffUpdate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public StaffUpdate() {
		setTitle("Staff Update");
//		setBounds(100, 100, 360, 428);
		setBounds(0, 0, Constants.c_width, Constants.c_height);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, new Color(51, 102, 255), new Color(0, 0, 204)));
			panel.setBounds(86, 15, 519, 335);
			getContentPane().add(panel);
			{
				JLabel lblNewLabel = new JLabel("Staff ID:");
				lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel.setBounds(111, 122, 81, 14);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblCusto = new JLabel("Staff Name:");
				lblCusto.setHorizontalAlignment(SwingConstants.RIGHT);
				lblCusto.setBounds(111, 161, 81, 14);
				panel.add(lblCusto);
			}
			{
				JLabel lblPhoneNp = new JLabel("Phone No:");
				lblPhoneNp.setHorizontalAlignment(SwingConstants.RIGHT);
				lblPhoneNp.setBounds(111, 231, 81, 14);
				panel.add(lblPhoneNp);
			}
			{
				JLabel lblEmail = new JLabel("Email:");
				lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
				lblEmail.setBounds(111, 272, 81, 14);
				panel.add(lblEmail);
			}
			{
				txtname = new JTextField();
				txtname.setColumns(10);
				txtname.setBounds(232, 158, 167, 20);
				panel.add(txtname);
			}
			{
				txtphone = new JTextField();
				txtphone.setColumns(10);
				txtphone.setBounds(230, 228, 169, 20);
				panel.add(txtphone);
			}
			{
				txtemail = new JTextField();
				txtemail.setColumns(10);
				txtemail.setBounds(232, 269, 167, 20);
				panel.add(txtemail);
			}
			{
				cboSID = new JComboBox();
				cboSID.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cboSID.getSelectedIndex()<=0)
						{
							txtname.setText("");
					        txtphone.setText("");
					        txtemail.setText("");
						}
						else
						{
							showStaff();
						}
							
						
						
					}
				});
				cboSID.setBounds(232, 118, 167, 22);
				panel.add(cboSID);
			}
			
			JLabel lblGender = new JLabel("Gender:");
			lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
			lblGender.setBounds(111, 189, 81, 14);
			panel.add(lblGender);
			
			rdbtnMale = new JRadioButton("Male");
			rdbtnFemale.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gender = "male";
				}
			});
			buttonGroup.add(rdbtnMale);
			rdbtnMale.setBounds(229, 185, 56, 23);
			panel.add(rdbtnMale);
			
			rdbtnFemale = new JRadioButton("Female");
			rdbtnFemale.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gender = "female";
				}
			});
			buttonGroup.add(rdbtnFemale);
			rdbtnFemale.setBounds(316, 185, 83, 23);
			panel.add(rdbtnFemale);
			{
				lblNewLabel_1 = new JLabel("Update Staff Info:");
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_1.setFont(new Font("Pyidaungsu", Font.BOLD, 17));
				lblNewLabel_1.setBounds(129, 40, 270, 19);
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
			btnclose.setBounds(429, 381, 90, 35);
			getContentPane().add(btnclose);
		}
		{
			btnCancel = new JButton("Cancel");
			btnCancel.setForeground(new Color(255, 255, 255));
			btnCancel.setBackground(new Color(255, 215, 0));
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clear();

				}
			});
			btnCancel.setBounds(327, 381, 90, 35);
			getContentPane().add(btnCancel);
		}
		{
			btnUpdate = new JButton("Update");
			btnUpdate.setForeground(new Color(255, 255, 255));
			btnUpdate.setBackground(new Color(0, 128, 0));
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

			        if(cboSID.getSelectedIndex()==0) 
			        {
			            JOptionPane.showMessageDialog(null, "Please choose staff id.");
			            cboSID.requestFocus();
			        } else if(Checking.IsValidName(txtname.getText())) 
			        {
			            JOptionPane.showMessageDialog(null, "Please enter valid Name.");
			            txtname.requestFocus();
			            txtname.selectAll();
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
						    String id = cboSID.getSelectedItem().toString();						               
						             
						    st[0] = (String)txtname.getText();
							st[1] = gender;
							//id name gender phone email
							st[2] = (String)txtphone.getText();
							st[3] = (String)txtemail.getText();
//							st[4] = (String)
							 boolean ee = mySQLQueries.isduplicate("staff", st);
					         boolean ee1= mySQLQueries.isduplicateEmailForStaff("staff", st);
					            
						    boolean save = mySQLQueries.updateRecord("staff", id, st);
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
			btnUpdate.setBounds(225, 381, 90, 35);
			getContentPane().add(btnUpdate);
		}
		fillStaff();
	}

    public void fillStaff()
   {
       cboSID.removeAllItems();
       cboSID.addItem("-Selected-");
       String str[]=mySQLQueries.getIDForChoice("staff");
       for(int i = 0 ; i<str.length ; i++)
    	   cboSID.addItem(str[i].toString());
       cboSID.setSelectedIndex(0);
   }
    public void showStaff()
    {
        String result[]= mySQLQueries.getStaffData(cboSID.getSelectedItem().toString());
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
        txtemail.setText(result[3]);
        //id name gender phone address job nrc email
    }
    public void clear()
    {
        txtname.setText("");
        txtphone.setText("");
        txtemail.setText("");
        cboSID.requestFocus();
        cboSID.setSelectedIndex(0);
    }
}