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
	private JPanel transactionPanel;
	private JPanel accountPanel;
	private JButton btnAccountTypeCreate;
	private JButton btnUpdateAccountTyep;
	private JButton btnAccountTypeList;
	private JButton btnAccountCreate;
	private JButton btnAccountCreate_1;
	private JPanel staffPanel;
	private JButton btnCreateStaff;
	private JButton btnUpdateStaff;
	private JButton btnStaffList;
	private JButton btnRemoveAll;
	

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
		
		customerPanel = new JPanel();
		customerPanel.hide();
		
		transactionPanel = new JPanel();
		transactionPanel.hide();
		transactionPanel.setLayout(null);
		transactionPanel.setBackground(new Color(0, 0, 128));
		transactionPanel.setBounds(0, 0, 268, 449);
		panel.add(transactionPanel);
		
		JButton btnDeposit = new JButton("Deposit List");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				DepositShow d;
				try {
					d = new DepositShow();
					desktopPane.add(d).setVisible(true);
					desktopPane.updateUI();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDeposit.setBorder(null);
		btnDeposit.setBounds(64, 108, 140, 53);
		transactionPanel.add(btnDeposit);
		
		JButton btnWithdraw = new JButton("Withdrawl List");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				WithdrawShow d;
				try {
					d = new WithdrawShow();
					desktopPane.add(d).setVisible(true);
					desktopPane.updateUI();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnWithdraw.setBorder(null);
		btnWithdraw.setBounds(64, 176, 140, 53);
		transactionPanel.add(btnWithdraw);
		
		btnTransaction = new JButton("Transaction");
		btnTransaction.setBounds(64, 35, 140, 53);
		transactionPanel.add(btnTransaction);
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
		
		JButton btnRemoveALl_1 = new JButton("Remove All");
		btnRemoveALl_1.setBounds(64, 244, 140, 53);
		transactionPanel.add(btnRemoveALl_1);
		btnRemoveALl_1.setBorder(null);
		
		accountPanel = new JPanel();
		accountPanel.hide();
		accountPanel.setLayout(null);
		accountPanel.setBorder(null);
		accountPanel.setBackground(new Color(0, 0, 128));
		accountPanel.setBounds(0, 0, 268, 461);
		panel.add(accountPanel);
		
		btnAccountTypeCreate = new JButton("Create Account Type");
		btnAccountTypeCreate.setBorder(null);
		btnAccountTypeCreate.setBounds(74, 37, 140, 53);
		accountPanel.add(btnAccountTypeCreate);
		
		btnUpdateAccountTyep = new JButton("Update Account Type");
		btnUpdateAccountTyep.setBorder(null);
		btnUpdateAccountTyep.setBounds(74, 104, 140, 53);
		accountPanel.add(btnUpdateAccountTyep);
		
		btnAccountTypeList = new JButton("Account Type List");
		btnAccountTypeList.setBorder(null);
		btnAccountTypeList.setBounds(74, 170, 140, 53);
		accountPanel.add(btnAccountTypeList);
		
		btnAccountCreate = new JButton("Open Account");
		btnAccountCreate.setBorder(null);
		btnAccountCreate.setBounds(74, 238, 140, 53);
		accountPanel.add(btnAccountCreate);
		
		btnAccountCreate_1 = new JButton("Account List");
		btnAccountCreate_1.setBorder(null);
		btnAccountCreate_1.setBounds(74, 313, 140, 53);
		accountPanel.add(btnAccountCreate_1);
		customerPanel.setBackground(new Color(0, 0, 128));
		customerPanel.setBounds(22, 0, 268, 449);
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
		
		JButton btnCustomerList = new JButton("Customer List");
		btnCustomerList.addActionListener(new ActionListener() {
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
		btnCustomerList.setBorder(null);
		btnCustomerList.setBounds(64, 176, 140, 53);
		customerPanel.add(btnCustomerList);
		
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
		
		staffPanel = new JPanel();
		staffPanel.hide();
		staffPanel.setLayout(null);
		staffPanel.setBackground(new Color(0, 0, 128));
		staffPanel.setBounds(0, 0, 268, 449);
		panel.add(staffPanel);
		
		btnCreateStaff = new JButton("Create Staff");
		btnCreateStaff.setBorder(null);
		btnCreateStaff.setBounds(63, 29, 140, 53);
		staffPanel.add(btnCreateStaff);
		
		btnUpdateStaff = new JButton("Update Staff");
		btnUpdateStaff.setBorder(null);
		btnUpdateStaff.setBounds(64, 108, 140, 53);
		staffPanel.add(btnUpdateStaff);
		
		btnStaffList = new JButton("Staff List");
		btnStaffList.setBorder(null);
		btnStaffList.setBounds(64, 176, 140, 53);
		staffPanel.add(btnStaffList);
		
		btnRemoveAll = new JButton("Remove All");
		btnRemoveAll.setBorder(null);
		btnRemoveAll.setBounds(63, 257, 140, 53);
		staffPanel.add(btnRemoveAll);
		
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
				transactionPanel.hide();
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
		mnuDepoAndWith.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				transactionPanel.show();
				accountPanel.hide();
				customerPanel.hide();
				staffPanel.hide();
			}
		});
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
				transactionPanel.hide();
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
				transactionPanel.hide();
			}
		});
		mnuStaff.setForeground(Color.WHITE);
		mnuStaff.setFont(new Font("Tahoma", Font.BOLD, 13));
		mnuStaff.setBounds(450, 12, 85, 19);
		navPanel.add(mnuStaff);
	}
}
