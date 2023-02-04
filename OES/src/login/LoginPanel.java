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
		btnAdmin.setForeground(new Color(255,255,255));
		btnAdmin.setBackground(new Color(20, 112, 185));
		btnAdmin.setFont(new Font("Kayak Sans", Font.BOLD, 30));
		btnAdmin.setBounds(420, 400, 500, 99);
		add(btnAdmin);
		
		btnUser = new JButton("USER");
		btnUser.setFocusable(false);
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new UserLogin());
			}
		});
		btnUser.setForeground(new Color(255,255,255));
		btnUser.setBackground(new Color(20, 112, 185));
		btnUser.setFont(new Font("Kayak Sans", Font.BOLD, 30));
		btnUser.setBounds(420, 290, 500, 99);
		add(btnUser);
		btnNewButton.setForeground(new Color(255,255,255));
		btnNewButton.setBackground(new Color(20, 112, 185));
		btnNewButton.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnNewButton.setBounds(10, 600, 89, 50);
		add(btnNewButton);
		
		JLabel lblOnlineExamination = new JLabel("LOGIN");
		lblOnlineExamination.setForeground(new Color(20, 112, 185));
		lblOnlineExamination.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlineExamination.setFont(new Font("Kayak Sans", Font.BOLD, 41));
		lblOnlineExamination.setBounds(440, 150, 450, 150);
		add(lblOnlineExamination);
		
		
		 JLabel lblNewLabel1 = new JLabel("");
		    lblNewLabel1.setIcon(new ImageIcon("assets\\logo-web-transparent.png"));
			lblNewLabel1.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel1.setBounds(5, 5, 500, 150);
			add(lblNewLabel1);
			
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("assets\\image.jpg"));
		lblNewLabel.setBorder(new LineBorder(new Color(20, 112, 185), 4));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		add(lblNewLabel);

	}

}
