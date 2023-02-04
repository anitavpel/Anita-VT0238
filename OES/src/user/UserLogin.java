package user;

import java.awt.Color;


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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import login.LoginPanel;
import main.MainFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import database.Connect;
import javax.swing.JPasswordField;
import java.awt.SystemColor;

public class UserLogin extends JPanel implements FocusListener{
	private static final long serialVersionUID = 1L;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	Connect c=new Connect("root","root");
	/**
	 * Create the panel.
	 */
	static HashMap<String, String> hm=new HashMap<String, String>();
	
	public static String exixtsUsername(String un)
	{
		Set<Map.Entry<String,String>> set = hm.entrySet();
		for(Map.Entry<String, String> me : set) {
			if(me.getKey().equals(un))
					{
						return me.getValue();
						//setText
					}
			}
		return null;
	}
	
	private boolean checkVerification(String u_name)
	{
		String query="select verify from userdetails where username='"+u_name+"'";
		try{
			Statement stmt=c.con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			rs.next();
			if((boolean)rs.getBoolean("verify"))
				return true;
			else
				return false;
		}
		catch(SQLException e)
		{
			System.out.println("UserLogin : "+e);
			return false;
		}
		
	}
	public UserLogin() {
		setLayout(null);
		//take username and password from database and runs separately.
		new Thread(new Runnable() {
			
			public void run() {
				hm.clear();
				String course_query="select username,password from userdetails";
				try{
					Statement stmt=c.con.createStatement();
					ResultSet rs=stmt.executeQuery(course_query);
					while(rs.next())
					{
						hm.put(rs.getString(1), rs.getString(2));
					}
				}
				catch(SQLException e)
				{
					System.out.println("UserLogin : "+e);
				}
			}
		}).start();
		
		
		JButton btnForgotPassword = new JButton("FORGOT PASSWORD?");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new FrogotPassword());
			}
		});
	
		btnForgotPassword.setForeground(new Color(255,255,255));
		btnForgotPassword.setBackground(new Color(20,112,185));
		btnForgotPassword.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnForgotPassword.setBounds(660, 500, 230,29);
		add(btnForgotPassword);

		JButton btnRegisterNow = new JButton("NEW USER?");
		btnRegisterNow.setFocusable(false);
		btnRegisterNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new RegistrationForm());
			}
		});
		btnRegisterNow.setForeground(new Color(255,255,255));
		btnRegisterNow.setBackground(new Color(20, 112, 185));
		btnRegisterNow.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnRegisterNow.setBounds(460,500, 170, 29);
		add(btnRegisterNow);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setFocusable(false);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtUsername.getForeground()!=SystemColor.activeCaptionBorder)
				{
					if(txtPassword.getForeground()!=SystemColor.activeCaptionBorder)
					{
						String username=txtUsername.getText();
						@SuppressWarnings("deprecation")
						String password=txtPassword.getText();
						String pass=exixtsUsername(username);
						if(pass!=null)
						{
							
							if(password.equals(pass))
							{
								if(checkVerification(username))
								{
									try {
										String query1 = "select LoginAttempts from userdetails where username='"+username+"'";
										Statement stmt = c.con.createStatement();
										ResultSet rs = stmt.executeQuery(query1);
										int num=0;
										System.out.println(rs.next());
										num = rs.getInt("LoginAttempts");
										if(num<=0) {
											JOptionPane.showMessageDialog(null, "Login attempts reached, please contact admin.");
										}
										else {
											MainFrame.AddPanel(new UserPanel(username));
											
										}
									}
									catch (Exception e) {
										// TODO: handle exception
										System.out.println(e);
									}
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Please verify your details by ADMIN.");
								}
							}
							else
							{
								System.out.println("Wrong Password");
								//Wrong Password.
								JOptionPane.showMessageDialog(null,"Wrong Password");
							}
						}
						else
						{
							System.out.println("Not Registered.");
							//Not registered user
							JOptionPane.showMessageDialog(null,"Not Registered.");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Enter Password.");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Enter username.");
				}
			}
		});
		btnLogin.setForeground(new Color(255,255,255));
		btnLogin.setBackground(new Color(20, 112, 185));
		btnLogin.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnLogin.setBounds(560, 380, 241, 50);
		add(btnLogin);
		
		txtPassword = new JPasswordField();
		txtPassword.setForeground(SystemColor.activeCaptionBorder);
		txtPassword.setEchoChar('*');
		txtPassword.setToolTipText(" PASSWORD");
		txtPassword.setText("Password");
		txtPassword.setSelectionColor(new Color(153, 51, 153));
		txtPassword.setSelectedTextColor(new Color(102, 204, 255));
		txtPassword.setFont(new Font("Kayak Sans", Font.PLAIN, 24));
		txtPassword.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtPassword.setColumns(10);
		txtPassword.setBorder(UIManager.getBorder("ToolTip.border"));
		txtPassword.setBackground(new Color(255, 255, 255));
		txtPassword.setBounds(520, 300, 316, 36);
		add(txtPassword);
		txtPassword.addFocusListener(this);
		
		txtUsername = new JTextField();
		txtUsername.setForeground(SystemColor.activeCaptionBorder);
		txtUsername.setSelectionColor(new Color(153, 51, 153));
		txtUsername.setSelectedTextColor(new Color(102, 204, 255));
		txtUsername.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtUsername.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtUsername.setText("USERNAME");
		txtUsername.setToolTipText("USERNAME");
		txtUsername.setFont(new Font("Vrinda", Font.PLAIN, 24));
		txtUsername.setBorder(UIManager.getBorder("ToolTip.border"));
		txtUsername.setBackground(new Color(255, 255, 255));
		txtUsername.setBounds(520,250, 316, 36);
		add(txtUsername);
		txtUsername.setColumns(10);
		txtUsername.addFocusListener(this);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new LoginPanel());
			}
		});
		btnNewButton.setForeground(new Color(255,255,255));
		btnNewButton.setBackground(new Color(20, 112, 185));
		btnNewButton.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnNewButton.setBounds(10, 600, 89, 50);
		add(btnNewButton);
		
		JLabel lblOnlineExamination = new JLabel("Login To Your Account");
		lblOnlineExamination.setBorder(null);
		lblOnlineExamination.setForeground(new Color(20, 112, 185));
		lblOnlineExamination.setBackground(new Color(20, 112, 185));
		lblOnlineExamination.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlineExamination.setFont(new Font("Kayak Sans", Font.BOLD, 41));
		lblOnlineExamination.setBounds(455, 100, 450, 170);
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
		
		
		new Thread(new Runnable() {
			
			public void run() {
				hm.clear();
				String course_query="select username,password from userdetails";
				try{
					Statement stmt=c.con.createStatement();
					ResultSet rs=stmt.executeQuery(course_query);
					while(rs.next())
					{
						hm.put(rs.getString(1), rs.getString(2));
					}
				}
				catch(SQLException e)
				{
					System.out.println("UserLogin : "+e);
				}
			}
		}).start();
	}

	public void focusGained(FocusEvent arg0) {
		if(arg0.getSource()==txtPassword && txtPassword.getForeground()==SystemColor.activeCaptionBorder)
		{
			txtPassword.setText("");
			txtPassword.setFont(new Font("Kayak Sans", Font.PLAIN, 24));
			txtPassword.setForeground(Color.black);
		}
		if(arg0.getSource()==txtUsername && txtUsername.getForeground()==SystemColor.activeCaptionBorder)
		{
			txtUsername.setText("");
			txtUsername.setFont(new Font("Kayak Sans", Font.BOLD, 24));
			txtUsername.setForeground(Color.black);
		}
	}

	@SuppressWarnings("deprecation")
	public void focusLost(FocusEvent arg0) {
		if(txtPassword.getText().equals(""))
		{
			txtPassword.setFont(new Font("Kayak Sans", Font.PLAIN, 24));
			txtPassword.setText("Password");
			txtPassword.setForeground(SystemColor.activeCaptionBorder);;
		}
		if(txtUsername.getText().equals(""))
		{
			txtUsername.setFont(new Font("Kayak Sans", Font.PLAIN, 24));
			txtUsername.setText("USERNAME");
			txtUsername.setForeground(SystemColor.activeCaptionBorder);;
		}
		
	}
}