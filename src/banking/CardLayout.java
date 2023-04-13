package banking;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class CardLayout extends JFrame {

	private JPanel contentPane;
	private JPanel leftPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardLayout frame = new CardLayout();
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
	public CardLayout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		leftPanel = new JPanel();
		splitPane.setLeftComponent(leftPanel);
		leftPanel.setBackground(new Color(51, 204, 153));
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		JButton btnNewButton = new JButton("New button");
		leftPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		leftPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		leftPanel.add(btnNewButton_2);
		
		JPanel rightPanel = new JPanel();
		splitPane.setRightComponent(rightPanel);
		rightPanel.setLayout(new java.awt.CardLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(204, 204, 0));
		rightPanel.add(panel_2, "name_1063283020994399");
		
		JPanel panel_1 = new JPanel();
		rightPanel.add(panel_1,"name_1063283020994398");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 102, 0));
		rightPanel.add(panel,"name_1063283020994397");
	}
}
