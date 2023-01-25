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
import javax.swing.border.MatteBorder;
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
		
		JButton btnForgotPassword = new JButton("Forget Password");
		btnForgotPassword.setToolTipText("Forget Password");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new FrogotPassword());
			}
		});
		btnForgotPassword.setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(123, 104, 238)));
		btnForgotPassword.setForeground(new Color(0, 0, 140));
		btnForgotPassword.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnForgotPassword.setBounds(661, 282, 254, 44);
		add(btnForgotPassword);
		
		
		JButton btnRegisterNow = new JButton("REGISTER");
		btnRegisterNow.setToolTipText("Register(if new user)");
		btnRegisterNow.setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(123, 104, 238)));
		btnRegisterNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new RegistrationForm());
			}
		});
		btnRegisterNow.setForeground(new Color(0, 0, 140));
		btnRegisterNow.setFont(new Font("Kayak Sans", Font.ITALIC, 18));
		btnRegisterNow.setBounds(572, 377, 133, 29);
		add(btnRegisterNow);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setToolTipText("LOGIN");
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
									MainFrame.AddPanel(new UserPanel(username));
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
		btnLogin.setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(123, 104, 238)));
		btnLogin.setForeground(new Color(0, 0, 140));
		btnLogin.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnLogin.setBounds(400, 283, 241, 44);
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
		txtPassword.setBounds(500, 213, 316, 36);
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
		txtUsername.setBounds(500, 166, 316, 36);
		add(txtUsername);
		txtUsername.setColumns(10);
		txtUsername.addFocusListener(this);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new LoginPanel());
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 140));
		btnNewButton.setBackground(new Color(215, 215, 255));
		btnNewButton.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnNewButton.setBounds(10, 600, 89, 50);
		add(btnNewButton);
		
		JLabel lblOnlineExamination = new JLabel("USER LOGIN");
		lblOnlineExamination.setBorder(null);
		lblOnlineExamination.setForeground(new Color(22, 25, 144));
		lblOnlineExamination.setBackground(new Color(102, 255, 255));
		lblOnlineExamination.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlineExamination.setFont(new Font("Kayak Sans", Font.BOLD, 41));
		lblOnlineExamination.setBounds(425, 46, 450, 150);
		add(lblOnlineExamination);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("assets\\image.jpg"));
		lblNewLabel.setBorder(new LineBorder(new Color(215, 215, 255), 4));
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
			txtPassword.setFont(new Font("Lucida Bright", Font.PLAIN, 24));
			txtPassword.setForeground(Color.black);
		}
		if(arg0.getSource()==txtUsername && txtUsername.getForeground()==SystemColor.activeCaptionBorder)
		{
			txtUsername.setText("");
			txtUsername.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
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