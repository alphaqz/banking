package banking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
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
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JSpinner;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class AccountTypeUpdate extends JInternalFrame {
	private JTextField txtTitle;
	private JComboBox cboaccounttypeid;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JRadioButton rdbtnMale = new JRadioButton("male");;
	private JRadioButton rdbtnFemale = new JRadioButton("Female");;
	private String gender = "";
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JSpinner spinner;
	
	Border backline=BorderFactory.createLineBorder(Color.black);
	private JTextField txtInterest;
	private JLabel lblNewLabel_1;
	private JButton btnClose;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AccountTypeUpdate dialog = new AccountTypeUpdate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AccountTypeUpdate() {
		setTitle("Account Type Update");
//		setBounds(100, 100, 579, 428);
		setBounds(0, 0, Constants.c_width, Constants.c_height);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, new Color(51, 102, 255), new Color(0, 0, 204)));
			panel.setBounds(86, 15, 519, 335);
			getContentPane().add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Account Type ID:");
				lblNewLabel.setBounds(102, 127, 113, 14);
				lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblCusto = new JLabel("Title :");
				lblCusto.setBounds(163, 183, 52, 14);
				lblCusto.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(lblCusto);
			}
			{
				txtTitle = new JTextField();
				txtTitle.setBounds(255, 180, 167, 20);
				txtTitle.setColumns(10);
				panel.add(txtTitle);
			}
			{
				cboaccounttypeid = new JComboBox();
				cboaccounttypeid.setBounds(255, 123, 167, 22);
				cboaccounttypeid.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cboaccounttypeid.getSelectedIndex()<=0)
						{
							txtTitle.setText("");
					        txtInterest.setText("");
						}
						else
						{
							showAccountType();
						}
					}
				});
				panel.add(cboaccounttypeid);
			}
			
			JLabel lblJob = new JLabel("Interest :");
			lblJob.setBounds(124, 238, 91, 14);
			lblJob.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(lblJob);
			
			txtInterest = new JTextField();
			txtInterest.setBounds(255, 235, 167, 20);
			txtInterest.setColumns(10);
			panel.add(txtInterest);
			
			JLabel lblFixedPeriod = new JLabel("Fixed period:");
			lblFixedPeriod.setBounds(124, 282, 91, 14);
			lblFixedPeriod.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(lblFixedPeriod);
			
			spinner = new JSpinner();
			spinner.setBounds(258, 279, 52, 20);
			panel.add(spinner);
			{
				lblNewLabel_1 = new JLabel("Modify Account Type Info:");
				lblNewLabel_1.setFont(new Font("Pyidaungsu", Font.BOLD, 17));
				lblNewLabel_1.setBounds(173, 52, 238, 19);
				panel.add(lblNewLabel_1);
			}
			rdbtnFemale.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gender = "male";
				}
			});
		}
		{
			btnDelete = new JButton("Cancel");
			btnDelete.setForeground(Color.WHITE);
			btnDelete.setBackground(new Color(255, 215, 0));
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clear();

				}
			});
			btnDelete.setBounds(314, 377, 90, 35);
			getContentPane().add(btnDelete);
		}
		{
			btnUpdate = new JButton("Update");
			btnUpdate.setForeground(Color.WHITE);
			btnUpdate.setBackground(new Color(0, 128, 0));
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

			        if(cboaccounttypeid.getSelectedIndex()==0) 
			        {
			            JOptionPane.showMessageDialog(null, "Please choose account id.");
			            cboaccounttypeid.requestFocus();
			        } 
			        else{
						 if(JOptionPane.showConfirmDialog(null, "Are you Sure Update?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION) 
						 {
							 String []st = new String[3];
						     String id = cboaccounttypeid.getSelectedItem().toString();						               
						               
						     st[0] = (String)txtTitle.getText();
						     st[1] = (String)txtInterest.getText();
						     st[2] = ""+spinner.getValue();
						     							
						     boolean save = mySQLQueries.updateRecord("accounttype", id, st);
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
			btnUpdate.setBounds(183, 377, 90, 35);
			getContentPane().add(btnUpdate);
		}
		{
			btnClose = new JButton("Close");
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confrim",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
					{	
						dispose();
					}
				}
			});
			btnClose.setBounds(436, 376, 90, 35);
			getContentPane().add(btnClose);
		}
		fillAccountType();
	}

   public void fillAccountType()
   {
       cboaccounttypeid.removeAllItems();
       cboaccounttypeid.addItem("-Selected-");
       String str[]=mySQLQueries.getIDForChoice("accounttype");
       for(int i = 0 ; i<str.length ; i++)
    	   cboaccounttypeid.addItem(str[i].toString());
       cboaccounttypeid.setSelectedIndex(0);
   }
    public void showAccountType()
    {
        String result[]= mySQLQueries.getAccountTypeData(cboaccounttypeid.getSelectedItem().toString());
        txtTitle.setText(result[0]);
        txtInterest.setText(result[1]);
        spinner.setValue(Integer.parseInt(result[2]));
    }
    public void clear()
    {
        txtTitle.setText("");
        txtInterest.setText("");
        spinner.setValue(0);
        cboaccounttypeid.requestFocus();
        cboaccounttypeid.setSelectedIndex(0);
    }
}