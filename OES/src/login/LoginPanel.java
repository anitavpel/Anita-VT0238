package login;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import admin.AdminPanel;
import user.UserLogin;
import main.MainFrame;
import main.Panel1;

public class LoginPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton btnUser;

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		setLayout(null);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new Panel1());
			}
		});
		
		JButton btnAdmin = new JButton("ADMIN");
		btnAdmin.setFocusable(false);
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new AdminPanel());
			}
		});
		btnAdmin.setForeground(new Color(0, 0, 140));
		btnAdmin.setBackground(new Color(215, 215, 255));
		btnAdmin.setFont(new Font("MV Boli", Font.BOLD, 30));
		btnAdmin.setBounds(256, 322, 478, 99);
		add(btnAdmin);
		
		btnUser = new JButton("USER");
		btnUser.setFocusable(false);
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new UserLogin());
			}
		});
		btnUser.setForeground(new Color(0, 0, 140));
		btnUser.setBackground(new Color(215, 215, 255));
		btnUser.setFont(new Font("MV Boli", Font.BOLD, 30));
		btnUser.setBounds(256, 212, 478, 99);
		add(btnUser);
		btnNewButton.setForeground(new Color(0, 0, 140));
		btnNewButton.setBackground(new Color(215, 215, 255));
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnNewButton.setBounds(0, 511, 89, 36);
		add(btnNewButton);
		
		JLabel lblOnlineExamination = new JLabel("LOGIN");
		lblOnlineExamination.setForeground(new Color(34, 110, 171));
		lblOnlineExamination.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlineExamination.setFont(new Font("Times New Roman", Font.BOLD, 41));
		lblOnlineExamination.setBounds(388, 46, 214, 105);
		add(lblOnlineExamination);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\\\Users\\\\Anita\\\\Pictures\\\\istockphoto-1145350494-612x612.jpg"));
		lblNewLabel.setBorder(new LineBorder(new Color(215, 215, 255), 4));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(0, 0, 990, 558);
		add(lblNewLabel);

	}

}
