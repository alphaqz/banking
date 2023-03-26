package banking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
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

public class StaffUpdate extends JDialog {
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtemail;
	private JComboBox cboSID;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnclose;
	private JRadioButton rdbtnMale = new JRadioButton("male");;
	private JRadioButton rdbtnFemale = new JRadioButton("Female");;
	private String gender = "";
	private final ButtonGroup buttonGroup = new ButtonGroup();

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
		setBounds(100, 100, 360, 428);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Staff Update Info:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 11, 325, 218);
			getContentPane().add(panel);
			{
				JLabel lblNewLabel = new JLabel("Staff ID:");
				lblNewLabel.setBounds(10, 23, 81, 14);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblCusto = new JLabel("Staff Name:");
				lblCusto.setBounds(10, 62, 81, 14);
				panel.add(lblCusto);
			}
			{
				JLabel lblPhoneNp = new JLabel("Phone No:");
				lblPhoneNp.setBounds(10, 132, 81, 14);
				panel.add(lblPhoneNp);
			}
			{
				JLabel lblEmail = new JLabel("Email:");
				lblEmail.setBounds(10, 173, 81, 14);
				panel.add(lblEmail);
			}
			{
				txtname = new JTextField();
				txtname.setColumns(10);
				txtname.setBounds(111, 59, 167, 20);
				panel.add(txtname);
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
				txtemail.setBounds(111, 170, 167, 20);
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
				cboSID.setBounds(111, 19, 167, 22);
				panel.add(cboSID);
			}
			
			JLabel lblGender = new JLabel("Gender:");
			lblGender.setHorizontalAlignment(SwingConstants.LEFT);
			lblGender.setBounds(10, 90, 81, 14);
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
			btnclose.setBounds(218, 260, 85, 23);
			getContentPane().add(btnclose);
		}
		{
			btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
	                    String id = cboSID.getSelectedItem().toString();
	                    if(JOptionPane.showConfirmDialog(null, "Are you Sure Delete?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
	                    {
	                    	mySQLQueries.deleteRecord("staff", id);
	                    	fillStaff();
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
			btnDelete.setBounds(123, 260, 85, 23);
			getContentPane().add(btnDelete);
		}
		{
			btnUpdate = new JButton("Update");
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
			btnUpdate.setBounds(34, 260, 79, 23);
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