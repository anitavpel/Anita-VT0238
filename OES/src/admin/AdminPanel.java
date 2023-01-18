package admin;

import java.awt.Color;


import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import login.LoginPanel;
import main.MainFrame;

import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class AdminPanel extends JPanel implements FocusListener{
	private JTextField txtUsername;

	/**
	 * Create the panel.
	 */
	static String password="admin";
	public AdminPanel() {
		setLayout(null);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setForeground(Color.LIGHT_GRAY);
		pwdPassword.setEchoChar('*');
		pwdPassword.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		pwdPassword.setText("Password");
		pwdPassword.setBounds(540, 230, 215, 40);
		add(pwdPassword);
		pwdPassword.addFocusListener(this);
	
		
		txtUsername = new JTextField();
		txtUsername.setForeground(Color.LIGHT_GRAY);
		txtUsername.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtUsername.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		txtUsername.setText("USERNAME");
		txtUsername.setBounds(540, 190, 215, 36);
		add(txtUsername);
		txtUsername.setColumns(10);
		txtUsername.addFocusListener(this);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setToolTipText("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(txtUsername.getText().equals("admin") && pwdPassword.getText().equals(password))
				{
					//goto Main Admin Panel
					MainFrame.AddPanel(new MainAdmin());
				}
				else
				{
					//Show error popup message
					JOptionPane.showMessageDialog(null, "Only for Administrator");
				}
			}
		});
		btnLogin.setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(123, 104, 238)));
		btnLogin.setForeground(new Color(0, 0, 140));
		btnLogin.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnLogin.setBounds(550, 300, 190, 56);
		add(btnLogin);
		
	
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new LoginPanel());
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 140));
		btnNewButton.setBackground(new Color(215,215,255));
		btnNewButton.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnNewButton.setBounds(10, 600, 89, 50);
		add(btnNewButton);
		
		JLabel lblOnlineExamination = new JLabel("ADMIN LOGIN");
		lblOnlineExamination.setForeground(new Color(0, 0, 140));
		lblOnlineExamination.setBackground(new Color(0, 0, 140));
		lblOnlineExamination.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlineExamination.setFont(new Font("Kayak Sans", Font.BOLD, 41));
		lblOnlineExamination.setBounds(425, 46, 450, 150);
		add(lblOnlineExamination);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(new LineBorder(new Color(215,215,255), 4));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setIcon(new ImageIcon("assets\\image.jpg"));
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		add(lblNewLabel);
	}

	private JPasswordField pwdPassword;
	
	public void focusGained(FocusEvent arg0) {
		if(arg0.getSource()==pwdPassword && pwdPassword.getForeground()==Color.LIGHT_GRAY)
		{
			pwdPassword.setText("");
			pwdPassword.setFont(new Font("Kayak Sans", Font.BOLD, 18));
			pwdPassword.setForeground(Color.black);
		}
		if(arg0.getSource()==txtUsername && txtUsername.getForeground()==Color.LIGHT_GRAY)
		{
			txtUsername.setText("");
			txtUsername.setFont(new Font("Kayak Sans", Font.BOLD, 18));
			txtUsername.setForeground(Color.black);
		}
		
	}

	@SuppressWarnings("deprecation")
	public void focusLost(FocusEvent arg0) {
		if(pwdPassword.getText().equals(""))
		{
			pwdPassword.setFont(new Font("Kayak Sans", Font.BOLD, 20));
			pwdPassword.setText("Password");
			pwdPassword.setForeground(Color.LIGHT_GRAY);
		}
		if(txtUsername.getText().equals(""))
		{
			txtUsername.setFont(new Font("Kayak Sans", Font.BOLD, 20));
			txtUsername.setText("USERNAME");
			txtUsername.setForeground(Color.LIGHT_GRAY);
		}
	}
}
