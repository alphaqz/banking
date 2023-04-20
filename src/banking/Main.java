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
	private JButton btnStaffList;
	private JButton btnTransaction;
	private JButton btnAccountCreate;
	private JLabel mnuAccount;
	private JPanel panel_2;
	private JPanel accountPanel;
	private JButton btnCustomerList;
	private JLabel mnuDepoAndWith;
	private JLabel mnuTransfer;
	private JLabel mnuCustomer_3;
	private JLabel mnuStaff;
	private JButton btnUpdateAccountTyep;
	private JButton btnAccountTypeList;
	private JButton btnAccountCreate_3;
	private JButton btnAccountCreate_1;
	

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
		
		JButton btnRemoveALl = new JButton("Remove All");
		btnRemoveALl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				desktopPane.updateUI();
				
			}
		});
		btnRemoveALl.setBorder(null);
		btnRemoveALl.setBounds(10, 587, 116, 53);
		panel.add(btnRemoveALl);
		
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
		
		accountPanel = new JPanel();
		accountPanel.setBorder(null);
		accountPanel.setBackground(new Color(0, 0, 128));
		accountPanel.hide();
		accountPanel.setBounds(10, 27, 268, 461);
		panel.add(accountPanel);
		accountPanel.setLayout(null);
		
		btnAccountCreate = new JButton("Create Account Type");
		btnAccountCreate.setBounds(74, 37, 140, 53);
		accountPanel.add(btnAccountCreate);
		btnAccountCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				AccountTypeCreate d;
				try {
					d = new AccountTypeCreate();
					desktopPane.add(d).setVisible(true);
					desktopPane.updateUI();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAccountCreate.setBorder(null);
		
		btnUpdateAccountTyep = new JButton("Update Account Type");
		btnUpdateAccountTyep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				AccountTypeUpdate d;
				d = new AccountTypeUpdate();
				desktopPane.add(d).setVisible(true);
				desktopPane.updateUI();
			}
		});
		btnUpdateAccountTyep.setBorder(null);
		btnUpdateAccountTyep.setBounds(74, 104, 140, 53);
		accountPanel.add(btnUpdateAccountTyep);
		
		btnAccountTypeList = new JButton("Account Type List");
		btnAccountTypeList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				AccountTypeList d;
				try {
					d = new AccountTypeList();
					desktopPane.add(d).setVisible(true);
					desktopPane.updateUI();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAccountTypeList.setBorder(null);
		btnAccountTypeList.setBounds(74, 170, 140, 53);
		accountPanel.add(btnAccountTypeList);
		
		btnAccountCreate_3 = new JButton("Open Account");
		btnAccountCreate_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				AccountCreate d;
				try {
					d = new AccountCreate();
					desktopPane.add(d).setVisible(true);
					desktopPane.updateUI();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAccountCreate_3.setBorder(null);
		btnAccountCreate_3.setBounds(74, 238, 140, 53);
		accountPanel.add(btnAccountCreate_3);
		
		btnAccountCreate_1 = new JButton("Account List");
		btnAccountCreate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				AccountList d;
				try {
					d = new AccountList();
					desktopPane.add(d).setVisible(true);
					desktopPane.updateUI();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAccountCreate_1.setBorder(null);
		btnAccountCreate_1.setBounds(74, 313, 140, 53);
		accountPanel.add(btnAccountCreate_1);
		//panel.add(menu);
		
		btnStaffList = new JButton("Staff List");
		btnStaffList.setBounds(10, 503, 116, 57);
		panel.add(btnStaffList);
		btnStaffList.setBorder(null);
		btnStaffList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffList g;
				try {
					g = new StaffList();
					desktopPane.removeAll();
					desktopPane.add(g).setVisible(true);
					desktopPane.updateUI();
				} catch ( ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStaffList.setFont(new Font("Pyidaungsu", Font.PLAIN, 12));
		
		btnCustomerList = new JButton("Customer List");
		btnCustomerList.setBounds(162, 503, 116, 57);
		panel.add(btnCustomerList);
		btnCustomerList.setBorder(null);
		btnCustomerList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				CustomerList g;
				try {
					g = new CustomerList();
					desktopPane.add(g).setVisible(true);
					desktopPane.updateUI();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
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
			}
		});
		mnuAccount.setBounds(10, 12, 85, 19);
		contentPane.add(mnuAccount);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.BLUE);
		panel_2.setBounds(0, 0, 10000, 39);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		mnuDepoAndWith = new JLabel("Deposit & Withdraw");
		mnuDepoAndWith.setForeground(Color.WHITE);
		mnuDepoAndWith.setFont(new Font("Tahoma", Font.BOLD, 13));
		mnuDepoAndWith.setBounds(100, 13, 152, 19);
		panel_2.add(mnuDepoAndWith);
		
		mnuTransfer = new JLabel("Transfer");
		mnuTransfer.setForeground(Color.WHITE);
		mnuTransfer.setFont(new Font("Tahoma", Font.BOLD, 13));
		mnuTransfer.setBounds(268, 13, 85, 19);
		panel_2.add(mnuTransfer);
		
		mnuCustomer_3 = new JLabel("Customer");
		mnuCustomer_3.setForeground(Color.WHITE);
		mnuCustomer_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		mnuCustomer_3.setBounds(355, 13, 85, 19);
		panel_2.add(mnuCustomer_3);
		
		mnuStaff = new JLabel("Staff");
		mnuStaff.setForeground(Color.WHITE);
		mnuStaff.setFont(new Font("Tahoma", Font.BOLD, 13));
		mnuStaff.setBounds(450, 12, 85, 19);
		panel_2.add(mnuStaff);
		
		
	}
}
