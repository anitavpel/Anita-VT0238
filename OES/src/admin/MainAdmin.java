package admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import course.CoursePanel;
import database.Connect;
import main.MainFrame;

public class MainAdmin extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	
	private boolean executeQuery(String query)
	{
		Connect c=new Connect("root","root");
		try{
			Statement st=c.con.createStatement();
			if(st.executeQuery(query).next())
				return true;
			else
				return false;
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		return false;
	}
	public MainAdmin() {
		setLayout(null);
		
		JButton btnUsers = new JButton("USERS");
		btnUsers.setFocusable(false);
		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(executeQuery("select *from userdetails"))
					MainFrame.AddPanel(new UsersData());
				else
					JOptionPane.showMessageDialog(null, "No users found.");
			}
		});
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setToolTipText("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminPanel.password=JOptionPane.showInputDialog(null, "New password");
			}
		});
		btnChangePassword.setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(123, 104, 238)));
		btnChangePassword.setForeground(new Color(0, 0, 140));
		btnChangePassword.setFont(new Font("Kayak Sans", Font.ITALIC, 18));
		btnChangePassword.setBounds(1100, 11, 166, 42);
		add(btnChangePassword);
		btnUsers.setForeground(new Color(0,0,140));
		btnUsers.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnUsers.setBackground(new Color(215,215,255));
		btnUsers.setBounds(511, 429, 332, 79);
		add(btnUsers);
		
		JButton btnResults = new JButton("COURSE RESULTS");
		btnResults.setFocusable(false);
		btnResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(executeQuery("select *from result"))
					MainFrame.AddPanel(new CoursesResult());
				else
					JOptionPane.showMessageDialog(null, "No result found.");
			}
		});
		btnResults.setForeground(new Color(0,0,140));
		btnResults.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnResults.setBackground(new Color(215,215,255));
		btnResults.setBounds(511, 159, 332, 79);
		add(btnResults);
		
		JButton btnVerification = new JButton("VERIFICATION");
		btnVerification.setFocusable(false);
		btnVerification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(executeQuery("select *from userdetails where verify=0"))
					new Verification();
				else
					JOptionPane.showMessageDialog(null, "No users for verification.");
			}
		});
		btnVerification.setForeground(new Color( 0, 0,140));
		btnVerification.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnVerification.setBackground(new Color(215,215,255));
		btnVerification.setBounds(511, 249, 332, 79);
		add(btnVerification);
		
		JButton btnManageCourses = new JButton("MANAGE COURSES");
		btnManageCourses.setFocusable(false);
		btnManageCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new CoursePanel());
			}
		});
		btnManageCourses.setForeground(new Color( 0, 0,140));
		btnManageCourses.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnManageCourses.setBackground(new Color(215,215,255));
		btnManageCourses.setBounds(511, 339, 332, 79);
		add(btnManageCourses);
		
		JButton btnNewButton = new JButton("LOGOUT");
		btnNewButton.setToolTipText("LOGOUT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new AdminPanel());
				JOptionPane.showMessageDialog(null, "Successfully Logout.");	
			}
		});
		btnNewButton.setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(123, 104, 238)));
		btnNewButton.setForeground(new Color(0, 0, 140));
		btnNewButton.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnNewButton.setBounds(1170, 600, 100, 50);
		add(btnNewButton);
		
		JLabel lblOnlineExamination = new JLabel("ADMIN");
		lblOnlineExamination.setForeground(new Color(0,0,140));
		lblOnlineExamination.setBackground(Color.GREEN);
		lblOnlineExamination.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlineExamination.setFont(new Font("Kayak Sans", Font.BOLD, 41));
		lblOnlineExamination.setBounds(400, 28, 603, 105);
		add(lblOnlineExamination);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("assets\\image.jpg"));
		lblNewLabel.setBorder(new LineBorder(new Color(215,215,255), 4));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		add(lblNewLabel);
		
	}
}
