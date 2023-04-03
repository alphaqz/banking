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

public class Main extends JFrame {

	private JPanel contentPane;
	private JDesktopPane a;
	private JButton btnStaffList;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
		setBounds(100, 100, 1015, 744);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		a = new JDesktopPane();
		a.setBounds(335, 121, 765, 555);
		contentPane.add(a);
		
		
		setTitle("Bank Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 969, 744);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 60, 690);
		panel.setBackground(new Color(0, 0, 128));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JToggleButton menu = new JToggleButton("");
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(menu.isSelected()) {
					panel.setSize(300,690);
				}
				else {
					panel.setSize(71,690);
				}
			}
		});
		menu.setIcon(new ImageIcon("D:\\MMDCR_Project\\project image\\menu-icon.png"));
		menu.setBounds(10, 28, 42, 34);
		panel.add(menu);
		
		btnStaffList = new JButton("Staff List");
		btnStaffList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffList1 g;
				try {
					g = new StaffList1();
					a.removeAll();
					a.add(g).setVisible(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStaffList.setFont(new Font("Pyidaungsu", Font.PLAIN, 12));
		btnStaffList.setBounds(122, 110, 87, 27);
		panel.add(btnStaffList);
		
		JButton btnStaffEntry_1 = new JButton("Customer List");
		btnStaffEntry_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerList1 g;
				g = new CustomerList1();
				a.removeAll();
				a.add(g).setVisible(true);
			}
		});
		btnStaffEntry_1.setBounds(93, 164, 116, 27);
		panel.add(btnStaffEntry_1);
		
		JButton btnStaffEntry_2 = new JButton("Staff Entry");
		btnStaffEntry_2.setBounds(122, 224, 87, 27);
		panel.add(btnStaffEntry_2);
		
		JButton btnStaffEntry_3 = new JButton("Staff Entry");
		btnStaffEntry_3.setBounds(122, 283, 87, 27);
		panel.add(btnStaffEntry_3);
		
		JLabel lblNewLabel = new JLabel("Welcome To Bank Management System");
		lblNewLabel.setFont(new Font("Pyidaungsu", Font.BOLD, 24));
		lblNewLabel.setBounds(335, 42, 414, 32);
		contentPane.add(lblNewLabel);
		
		
	}
}
