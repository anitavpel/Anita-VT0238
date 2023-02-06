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
import course.CoursePanel;
import database.Connect;
import main.MainFrame;

public class MainAdmin extends JPanel {

	private static final long serialVersionUID = 1L;
	protected static final String Username = null;
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
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminPanel.password=JOptionPane.showInputDialog(null, "New password");
			}
		});
		btnChangePassword.setForeground(new Color(20,112,185));
		btnChangePassword.setBackground(new Color(255,255,255));
		btnChangePassword.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnChangePassword.setBounds(880, 50, 180, 42);
		add(btnChangePassword);
		
		
		btnUsers.setForeground(new Color(255,255,255));
		btnUsers.setFont(new Font("Kayak Sans", Font.BOLD, 25));
		btnUsers.setBackground(new Color(20, 112, 185));
		btnUsers.setBounds(511, 520, 332, 79);
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
		btnResults.setForeground(new Color(255,255,255));
		btnResults.setFont(new Font("Kayak Sans", Font.BOLD, 25));
		btnResults.setBackground(new Color(20, 112, 185));
		btnResults.setBounds(511, 250, 332, 79);
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
		btnVerification.setForeground(new Color(255,255,255));
		btnVerification.setFont(new Font("Kayak Sans", Font.BOLD, 25));
		btnVerification.setBackground(new Color(20, 112, 185));
		btnVerification.setBounds(511, 340, 332, 79);
		add(btnVerification);
		
		JButton btnManageCourses = new JButton("MANAGE COURSES");
		btnManageCourses.setFocusable(false);
		btnManageCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new CoursePanel());
			}
		});
		btnManageCourses.setForeground(new Color(255,255,255));
		btnManageCourses.setFont(new Font("Kayak Sans", Font.BOLD, 25));
		btnManageCourses.setBackground(new Color(20, 112, 185));
		btnManageCourses.setBounds(511, 430, 332, 79);
		add(btnManageCourses);
		
		JButton btnNewButton = new JButton("LOGOUT");
		btnNewButton.setToolTipText("LOGOUT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new AdminPanel());
				JOptionPane.showMessageDialog(null, "Successfully Logout.");	
			}
		});
		btnNewButton.setForeground(new Color(20, 112, 185));
		btnNewButton.setBackground(new Color(255,255,255));
		btnNewButton.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnNewButton.setBounds(1100, 50, 166, 42);
		add(btnNewButton);
		
		JLabel lblOnlineExamination = new JLabel("ADMIN");
		lblOnlineExamination.setForeground(new Color(255,255,255));
		lblOnlineExamination.setBackground(Color.GREEN);
		lblOnlineExamination.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlineExamination.setFont(new Font("Kayak Sans", Font.BOLD, 41));
		lblOnlineExamination.setBounds(350, 50, 603, 105);
		add(lblOnlineExamination);
		
		
		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setIcon(new ImageIcon("assets\\logo-web-transparent.png"));
		lblNewLabel1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel1.setBounds(5, 5, 500, 150);
		add(lblNewLabel1);
		
		JLabel lblNewLabel2 = new JLabel("");
		lblNewLabel2.setIcon(new ImageIcon("assets\\A.png"));
		lblNewLabel2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel2.setBounds(4,4, 1920, 150);
		add(lblNewLabel2);
			
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("assets\\image.jpg"));
		lblNewLabel.setBorder(new LineBorder(new Color(20, 112, 185), 4));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		add(lblNewLabel);
		
	}
}
