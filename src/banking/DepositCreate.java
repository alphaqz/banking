package banking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;


import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class DepositCreate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtAmount;
	private JLabel lblForID;
	JComboBox cboAccount;
	JComboBox cboStaff; 
	List<String> staffIdList = new ArrayList<String>();  

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DepositCreate dialog = new DepositCreate();
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
	public DepositCreate() throws ClassNotFoundException {
		setTitle("Deposit Entry");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Deposit Entry", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAccID = new JLabel("ID:");
		lblAccID.setBounds(103, 57, 46, 14);
		contentPanel.add(lblAccID);
		
		lblForID = new JLabel("");
		lblForID.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblForID.setBounds(222, 57, 86, 14);
		contentPanel.add(lblForID);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(103, 104, 69, 14);
		contentPanel.add(lblAmount);
		
		txtAmount = new JTextField();
		txtAmount.setColumns(10);
		txtAmount.setBounds(222, 101, 86, 20);
		contentPanel.add(txtAmount);
		
		cboAccount = new JComboBox();
		cboAccount.setBounds(222, 143, 186, 22);
		contentPanel.add(cboAccount);
		
		JLabel lblAccount = new JLabel("Account:");
		lblAccount.setBounds(80, 147, 69, 14);
		contentPanel.add(lblAccount);
		
		JLabel lblStaff = new JLabel("Staff:");
		lblStaff.setBounds(80, 183, 69, 14);
		contentPanel.add(lblStaff);
		
		cboStaff = new JComboBox();
		cboStaff.setBounds(222, 176, 186, 22);
		contentPanel.add(cboStaff);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
				        if(Checking.IsNull(txtAmount.getText()))
				        {
				            JOptionPane.showMessageDialog(null, "Please enter Amount.");
				            txtAmount.requestFocus();
				            txtAmount.selectAll();
				        }
				        else if(!Checking.IsAllDigit(txtAmount.getText()))
				        {
				            JOptionPane.showMessageDialog(null,"Please enter valid number.");
				            txtAmount.requestFocus();
				            txtAmount.selectAll();
				        }else {							
							
				            	String str[] = new String[8];
				            	str[0] = (String)lblForID.getText();						
				            	str[1] = (String)txtAmount.getText();

				            	str[2] = "AC-0000001"; //change this later
				            	str[3] = staffIdList.get(cboStaff.getSelectedIndex()-1);
				            	System.out.println("inserting into deposit " + str[0] + str[1] + str[3]);
				            	boolean save = mySQLQueries.insertData("deposit", str);
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
				});
				
				JButton btnClear = new JButton("Clear");
				buttonPane.add(btnClear);
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		fillCustomer();
		fillStaff();
		AutoID();
	}
	public void fillCustomer()
    {
        String str[]=mySQLQueries.getNameForChoice("customer");
        cboAccount.addItem("-Select-");
        for(int i=0;i<str.length;i++)
        	cboAccount.addItem(str[i].toString());
    }
	public void fillStaff()
    {
        String str[]=mySQLQueries.getNameForChoice("staff");
        String temp[] = mySQLQueries.getIDForChoice("staff");
        staffIdList = Arrays.asList(temp);
        cboStaff.addItem("-Select-");
        for(int i=0;i<str.length;i++)
        	cboStaff.addItem(str[i].toString());
    }
	public void clear()
    {      
        txtAmount.setText("");
    }
	public void AutoID() throws ClassNotFoundException
	{
		 lblForID.setText((String.valueOf(mySQLQueries.getAutoid("id", "deposit", "DE-"))));
	}
}


