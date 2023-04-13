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

public class Main extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private JButton btnStaffList;
	

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(348, 121, Constants.c_width, Constants.c_height);
		contentPane.add(desktopPane);
		
		
		setTitle("Bank Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 969, 744);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 164, 690);
		panel.setBackground(new Color(0, 0, 128));
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setSize(300,690);
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
		//panel.add(menu);
		
		btnStaffList = new JButton("Staff List");
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
		btnStaffList.setBounds(93, 110, 116, 53);
		panel.add(btnStaffList);
		
		JButton btnStaffEntry_1 = new JButton("Customer List");
		btnStaffEntry_1.setBorder(null);
		btnStaffEntry_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CustomerList1 g;
				desktopPane.removeAll();
				CustomerList1 g = new CustomerList1();
				//a.
				desktopPane.add(g).setVisible(true);
				desktopPane.updateUI();
			}
		});
		btnStaffEntry_1.setBounds(93, 196, 116, 53);
		panel.add(btnStaffEntry_1);
		
		JButton btnRemoveALl = new JButton("Remove All");
		btnRemoveALl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				desktopPane.updateUI();
				
			}
		});
		btnRemoveALl.setBorder(null);
		btnRemoveALl.setBounds(93, 286, 116, 53);
		panel.add(btnRemoveALl);
		
		JLabel lblNewLabel = new JLabel("Welcome To Bank Management System");
		lblNewLabel.setFont(new Font("Pyidaungsu", Font.BOLD, 24));
		lblNewLabel.setBounds(335, 42, 590, 32);
		contentPane.add(lblNewLabel);
		
		
	}
}
