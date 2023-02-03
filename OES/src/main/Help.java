package main;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Help extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Help() {
		setFocusable(false);
		setEnabled(false);
		setLayout(null);
		
		JTextArea txtrAboutHelpLine = new JTextArea();
		txtrAboutHelpLine.setEditable(false);
		txtrAboutHelpLine.setForeground(Color.BLACK);
		txtrAboutHelpLine.setBackground(Color.WHITE);
		txtrAboutHelpLine.setLineWrap(true);
		txtrAboutHelpLine.setFont(new Font("Kayak Sans", Font.BOLD, 15));
		txtrAboutHelpLine.setRows(30);
		txtrAboutHelpLine.setText("HELLO!!!\r\n\r\nThis is an VIRTUOSO SOFTTECH EXAM PORTAL in which admin can conduct an online examination of users .Users have to registered himself/herself first than they must be verified by admin after that he/she can give the examination.\r\n");
		txtrAboutHelpLine.setBounds(350, 91, 700, 342);
		add(txtrAboutHelpLine);
		
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(400, 78, 491, 2);
		add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Virtuoso SoftTech Exam Portal");
		lblNewLabel_1.setForeground(new Color(20, 112, 185));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Kayak Sans", Font.BOLD, 36));
		lblNewLabel_1.setBounds(400, 24, 500, 47);
		add(lblNewLabel_1);
	
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new Panel1());
			}
		});
		btnNewButton.setForeground(new Color(255,255,255));
		btnNewButton.setBackground(new Color(20, 112, 185));
		btnNewButton.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnNewButton.setBounds(10, 600, 89, 36);
		add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("assets\\image.jpg"));
		lblNewLabel.setBorder(new LineBorder(new Color(20, 112, 185), 4));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		add(lblNewLabel);
	}
}
