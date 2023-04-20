package banking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private JButton btnTransaction;
	private JLabel mnuAccount;
	private JPanel navPanel;
	private JLabel mnuDepoAndWith;
	private JLabel mnuTransfer;
	private JLabel mnuCustomer;
	private JLabel mnuStaff;
	private JPanel customerPanel;
	private JButton btnCreateCustomer;
	private JPanel accountPanel;
	private JButton btnAccountTypeCreate;
	private JButton btnUpdateAccountTyep_1;
	private JButton btnAccountTypeList_1;
	private JButton btnAccountCreate_4;
	private JButton btnAccountCreate_5;
	private JPanel staffPanel;
	private JButton btnCreateStaff;
	private JButton btnUpdateStaff;
	private JButton btnStaffList;
	private JButton btnRemoveALl_2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setSize(1200,720);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 974, 531);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(348, 121, 719, 476);
		contentPane.add(desktopPane);
		
		
		setTitle("Bank Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 969, 744);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 39, 300, 693);
		panel.setBackground(new Color(0, 0, 128));
		getContentPane().add(panel);
		panel.setLayout(null);
		JToggleButton menu = new JToggleButton("");
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if(menu.isSelected()) {
					//panel.setSize(300,690);
//				}
//				else {
//					panel.setSize(71,690);
//				}
			}
		});
		menu.setIcon(new ImageIcon("D:\\MMDCR_Project\\project image\\menu-icon.png"));
		menu.setBounds(10, 28, 42, 34);
		
		btnTransaction = new JButton("Transaction");
		btnTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				desktopPane.removeAll();
				DepositCreate d;
				try {
					d = new DepositCreate();
					desktopPane.add(d).setVisible(true);
					desktopPane.updateUI();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnTransaction.setBorder(null);
		btnTransaction.setBounds(162, 587, 116, 53);
		panel.add(btnTransaction);
		
		customerPanel = new JPanel();
		customerPanel.hide();
		
		accountPanel = new JPanel();
		accountPanel.hide();
		
		staffPanel = new JPanel();
		staffPanel.hide();
		staffPanel.setLayout(null);
		staffPanel.setBackground(new Color(0, 0, 128));
		staffPanel.setBounds(0, 0, 268, 449);
		panel.add(staffPanel);
		
		btnCreateStaff = new JButton("Create Staff");
		btnCreateStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				StaffEntry d;
				try {
					d = new StaffEntry();
					desktopPane.add(d).setVisible(true);
					desktopPane.updateUI();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCreateStaff.setBorder(null);
		btnCreateStaff.setBounds(63, 29, 140, 53);
		staffPanel.add(btnCreateStaff);
		
		btnUpdateStaff = new JButton("Update Staff");
		btnUpdateStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				StaffUpdate d;
				d = new StaffUpdate();
				desktopPane.add(d).setVisible(true);
				desktopPane.updateUI();
			}
		});
		btnUpdateStaff.setBorder(null);
		btnUpdateStaff.setBounds(64, 108, 140, 53);
		staffPanel.add(btnUpdateStaff);
		
		btnStaffList = new JButton("Staff List");
		btnStaffList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				StaffList d;
				try {
					d = new StaffList();
					desktopPane.add(d).setVisible(true);
					desktopPane.updateUI();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStaffList.setBorder(null);
		btnStaffList.setBounds(64, 176, 140, 53);
		staffPanel.add(btnStaffList);
		
		btnRemoveALl_2 = new JButton("Remove All");
		btnRemoveALl_2.setBorder(null);
		btnRemoveALl_2.setBounds(63, 257, 140, 53);
		staffPanel.add(btnRemoveALl_2);
		accountPanel.setLayout(null);
		accountPanel.setBorder(null);
		accountPanel.setBackground(new Color(0, 0, 128));
		accountPanel.setBounds(0, 0, 268, 461);
		panel.add(accountPanel);
		
		btnAccountTypeCreate = new JButton("Create Account Type");
		btnAccountTypeCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountTypeCreate g;
				try {
					g = new AccountTypeCreate();
					desktopPane.removeAll();
					desktopPane.add(g).setVisible(true);
					desktopPane.updateUI();
				} catch ( ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAccountTypeCreate.setBorder(null);
		btnAccountTypeCreate.setBounds(74, 37, 140, 53);
		accountPanel.add(btnAccountTypeCreate);
		
		btnUpdateAccountTyep_1 = new JButton("Update Account Type");
		btnUpdateAccountTyep_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountTypeUpdate g;
				g = new AccountTypeUpdate();
				desktopPane.removeAll();
				desktopPane.add(g).setVisible(true);
				desktopPane.updateUI();
			}
		});
		btnUpdateAccountTyep_1.setBorder(null);
		btnUpdateAccountTyep_1.setBounds(74, 104, 140, 53);
		accountPanel.add(btnUpdateAccountTyep_1);
		
		btnAccountTypeList_1 = new JButton("Account Type List");
		btnAccountTypeList_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountTypeList g;
				try {
					g = new AccountTypeList();
					desktopPane.removeAll();
					desktopPane.add(g).setVisible(true);
					desktopPane.updateUI();
				} catch ( ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAccountTypeList_1.setBorder(null);
		btnAccountTypeList_1.setBounds(74, 170, 140, 53);
		accountPanel.add(btnAccountTypeList_1);
		
		btnAccountCreate_4 = new JButton("Open Account");
		btnAccountCreate_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountCreate g;
				try {
					g = new AccountCreate();
					desktopPane.removeAll();
					desktopPane.add(g).setVisible(true);
					desktopPane.updateUI();
				} catch ( ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAccountCreate_4.setBorder(null);
		btnAccountCreate_4.setBounds(74, 238, 140, 53);
		accountPanel.add(btnAccountCreate_4);
		
		btnAccountCreate_5 = new JButton("Account List");
		btnAccountCreate_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountList g;
				try {
					g = new AccountList();
					desktopPane.removeAll();
					desktopPane.add(g).setVisible(true);
					desktopPane.updateUI();
				} catch ( ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAccountCreate_5.setBorder(null);
		btnAccountCreate_5.setBounds(74, 313, 140, 53);
		accountPanel.add(btnAccountCreate_5);
		customerPanel.setBackground(new Color(0, 0, 128));
		customerPanel.setBounds(16, 27, 268, 449);
		panel.add(customerPanel);
		customerPanel.setLayout(null);
		
		btnCreateCustomer = new JButton("Create Customer");
		btnCreateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerCreate g;
				try {
					g = new CustomerCreate();
					desktopPane.removeAll();
					desktopPane.add(g).setVisible(true);
					desktopPane.updateUI();
				} catch ( ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCreateCustomer.setBounds(63, 29, 140, 53);
		customerPanel.add(btnCreateCustomer);
		btnCreateCustomer.setBorder(null);
		
		JButton btnUpdateCustomer = new JButton("Update Customer");
		btnUpdateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerUpdate g;
				g = new CustomerUpdate();
				desktopPane.removeAll();
				desktopPane.add(g).setVisible(true);
				desktopPane.updateUI();
			}
		});
		btnUpdateCustomer.setBorder(null);
		btnUpdateCustomer.setBounds(64, 108, 140, 53);
		customerPanel.add(btnUpdateCustomer);
		
		JButton btnCustomerList_1_1 = new JButton("Customer List");
		btnCustomerList_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerList g;
				try {
					g = new CustomerList();
					desktopPane.removeAll();
					desktopPane.add(g).setVisible(true);
					desktopPane.updateUI();
				} catch ( ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCustomerList_1_1.setBorder(null);
		btnCustomerList_1_1.setBounds(64, 176, 140, 53);
		customerPanel.add(btnCustomerList_1_1);
		
		JButton btnRemoveALl = new JButton("Remove All");
		btnRemoveALl.setBounds(63, 257, 140, 53);
		customerPanel.add(btnRemoveALl);
		btnRemoveALl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				desktopPane.updateUI();
				
			}
		});
		btnRemoveALl.setBorder(null);
		
		JButton btnRemoveALl_1 = new JButton("Remove All");
		btnRemoveALl_1.setBorder(null);
		btnRemoveALl_1.setBounds(-14, 600, 140, 53);
		panel.add(btnRemoveALl_1);
		
		JLabel lblNewLabel = new JLabel("Welcome To Bank Management System");
		lblNewLabel.setBounds(335, 60, 590, 32);
		lblNewLabel.setFont(new Font("Pyidaungsu", Font.BOLD, 24));
		contentPane.add(lblNewLabel);
		
		mnuAccount = new JLabel("Account");
		mnuAccount.setFont(new Font("Tahoma", Font.BOLD, 13));
		mnuAccount.setForeground(Color.WHITE);
		mnuAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				accountPanel.show();
				customerPanel.hide();
				staffPanel.hide();
			}
		});
		mnuAccount.setBounds(10, 12, 85, 19);
		contentPane.add(mnuAccount);
		
		navPanel = new JPanel();
		navPanel.setBackground(Color.BLUE);
		navPanel.setBounds(0, 0, 10000, 39);
		contentPane.add(navPanel);
		navPanel.setLayout(null);
		
		mnuDepoAndWith = new JLabel("Deposit & Withdraw");
		mnuDepoAndWith.setForeground(Color.WHITE);
		mnuDepoAndWith.setFont(new Font("Tahoma", Font.BOLD, 13));
		mnuDepoAndWith.setBounds(100, 13, 152, 19);
		navPanel.add(mnuDepoAndWith);
		
		mnuTransfer = new JLabel("Transfer");
		mnuTransfer.setForeground(Color.WHITE);
		mnuTransfer.setFont(new Font("Tahoma", Font.BOLD, 13));
		mnuTransfer.setBounds(268, 13, 85, 19);
		navPanel.add(mnuTransfer);
		
		mnuCustomer = new JLabel("Customer");
		mnuCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				customerPanel.show();
				accountPanel.hide();
				staffPanel.hide();
			}
		});
		mnuCustomer.setForeground(Color.WHITE);
		mnuCustomer.setFont(new Font("Tahoma", Font.BOLD, 13));
		mnuCustomer.setBounds(355, 13, 85, 19);
		navPanel.add(mnuCustomer);
		
		mnuStaff = new JLabel("Staff");
		mnuStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				staffPanel.show();
				accountPanel.hide();
				customerPanel.hide();
			}
		});
		mnuStaff.setForeground(Color.WHITE);
		mnuStaff.setFont(new Font("Tahoma", Font.BOLD, 13));
		mnuStaff.setBounds(450, 12, 85, 19);
		navPanel.add(mnuStaff);
	}
}
