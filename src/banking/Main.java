package banking;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Font;

public class Main extends JFrame {
	private JButton btnStaffEntry;

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
		setTitle("Bank Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 969, 744);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 128));
		panel.setBounds(0, 0, 62, 690);
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
		menu.setBounds(10, 15, 42, 34);
		panel.add(menu);
		
		btnStaffEntry = new JButton("Staff Entry");
		btnStaffEntry.setFont(new Font("Pyidaungsu", Font.PLAIN, 12));
		btnStaffEntry.setBounds(122, 110, 87, 27);
		panel.add(btnStaffEntry);
		
		JButton btnStaffEntry_1 = new JButton("Customer Entry");
		btnStaffEntry_1.setBounds(122, 164, 143, 27);
		panel.add(btnStaffEntry_1);
		
		JButton btnStaffEntry_2 = new JButton("Staff Entry");
		btnStaffEntry_2.setBounds(122, 224, 87, 27);
		panel.add(btnStaffEntry_2);
		
		JButton btnStaffEntry_3 = new JButton("Staff Entry");
		btnStaffEntry_3.setBounds(122, 283, 87, 27);
		panel.add(btnStaffEntry_3);
	}
}
