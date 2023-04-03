package banking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
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

public class AccountTypeUpdate extends JDialog {
	private JTextField txtTitle;
	private JComboBox cboaccounttypeid;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnclose;
	private JRadioButton rdbtnMale = new JRadioButton("male");;
	private JRadioButton rdbtnFemale = new JRadioButton("Female");;
	private String gender = "";
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JSpinner spinner;
	
	Border backline=BorderFactory.createLineBorder(Color.black);
	private JTextField txtInterest;

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
		setBounds(100, 100, 579, 428);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Account Type Update Info:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(37, 63, 472, 246);
			getContentPane().add(panel);
			{
				JLabel lblNewLabel = new JLabel("Account Type ID:");
				lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel.setBounds(102, 47, 113, 14);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblCusto = new JLabel("Title :");
				lblCusto.setHorizontalAlignment(SwingConstants.RIGHT);
				lblCusto.setBounds(163, 103, 52, 14);
				panel.add(lblCusto);
			}
			{
				txtTitle = new JTextField();
				txtTitle.setColumns(10);
				txtTitle.setBounds(255, 100, 167, 20);
				panel.add(txtTitle);
			}
			{
				cboaccounttypeid = new JComboBox();
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
				cboaccounttypeid.setBounds(255, 43, 167, 22);
				panel.add(cboaccounttypeid);
			}
			
			JLabel lblJob = new JLabel("Interest :");
			lblJob.setHorizontalAlignment(SwingConstants.RIGHT);
			lblJob.setBounds(124, 158, 91, 14);
			panel.add(lblJob);
			
			txtInterest = new JTextField();
			txtInterest.setColumns(10);
			txtInterest.setBounds(255, 155, 167, 20);
			panel.add(txtInterest);
			
			JLabel lblFixedPeriod = new JLabel("Fixed period:");
			lblFixedPeriod.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFixedPeriod.setBounds(124, 202, 91, 14);
			panel.add(lblFixedPeriod);
			
			spinner = new JSpinner();
			spinner.setBounds(258, 199, 52, 20);
			panel.add(spinner);
			rdbtnFemale.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gender = "male";
				}
			});
		}
		{
			btnclose = new JButton("Close");
			btnclose.setForeground(new Color(255, 255, 255));
			btnclose.setBackground(new Color(105, 105, 105));
			btnclose.setMnemonic('C');
			btnclose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confrim",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
					{	
						dispose();
					}
				}
			});
			btnclose.setBounds(424, 326, 85, 39);
			getContentPane().add(btnclose);
		}
		{
			btnDelete = new JButton("Delete");
			btnDelete.setMnemonic('D');
			btnDelete.setForeground(Color.WHITE);
			btnDelete.setBackground(Color.RED);
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
	                    String id = cboaccounttypeid.getSelectedItem().toString();
	                    if(JOptionPane.showConfirmDialog(null, "Are you Sure Delete?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
	                    {
	                    	mySQLQueries.deleteRecord("accounttype", id);
	                    	fillAccountType();
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
			btnDelete.setBounds(329, 326, 85, 39);
			getContentPane().add(btnDelete);
		}
		{
			btnUpdate = new JButton("Update");
			btnUpdate.setMnemonic('U');
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
			btnUpdate.setBounds(240, 326, 79, 39);
			getContentPane().add(btnUpdate);
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