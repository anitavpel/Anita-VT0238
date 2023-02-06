package user;

import java.awt.Color;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import main.MainFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.sql.*;
import database.Connect;
import javax.swing.JTextArea;
import startTest.BeginTest;

public class UserPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	Connect c=new Connect("root","root");
	private String[] Courses;
	private String Name;
	private String Username;
	private JComboBox<String> comboBox;
	private String Selected_course="";
	private String totalQuestions;
	private String eachMark;
	private String time;
	
	//initialises the Selected_course and fill values in courses array this function will run one time only
	private void dataBaseWork()
	{
		String course_query="select *from course_details where hide=0";
		try{
			Statement stmt=c.con.createStatement();
			ResultSet rs=stmt.executeQuery("select count(course_name) from course_details where hide=0");
			rs.next();
			int i=rs.getInt(1);
			rs=stmt.executeQuery(course_query);
			Courses=new String[i];
			i=0;
			while(rs.next())
				Courses[i++]=rs.getString("course_name");
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
	
	// this function helps the start button to add beginTest panel or not.
	private int courseDetail(String Course)
	{
		try {
			Statement stmt = c.con.createStatement();
			ResultSet rs=stmt.executeQuery("select *from course_details where course_name='"+Course+"'");
			rs.next();
			int i=(int)rs.getInt("total_question");
			time=rs.getString("time");
			totalQuestions=rs.getString("total_question");
			eachMark=rs.getString("question_mark");
			System.out.println("Questions are available : "+i);
			return i;
		} catch (SQLException e) {
			System.out.println("UserPanel->availableQuestions : "+e);
		}
		System.out.println("No questions are available ");
		return -1;
	}
	
	// this function gives the details of current user who is login
	private void dataBaseWork(String username)
	{
		String query="select * from userdetails where username='"+username+"'";
		try{
			Statement stmt=c.con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			rs.next();
			Name=rs.getString("FirstName")+" "+rs.getString("MiddleName")+" "+rs.getString("LastName");
			System.out.println(Name);
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
	JTextArea instructions;
	
	
	public UserPanel(String username) {
		setLayout(null);
		Username=username;
		dataBaseWork();
		dataBaseWork(Username);
		
		JButton btnStartTest = new JButton("START TEST");
		btnStartTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!Selected_course.equals(""))
				{
					//Start test of selected course if no of questions available are greater than 1.
					if(courseDetail(Selected_course)>0)	
					{
						Statement stmt;
						try {
							stmt = c.con.createStatement();
							String query1 = "update userdetails set LoginAttempts = LoginAttempts - 1 where username='"+username+"'";
							@SuppressWarnings("unused")
							int rs = stmt.executeUpdate(query1);							
							MainFrame.AddPanel(new BeginTest(Username,Selected_course,eachMark));
						} catch(SQLException e){
							System.out.print(e);
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Questions are not available.\nPlease contact to admin.");
				}
				else
					JOptionPane.showMessageDialog(null, "Select course first.");;	
			}
		});
		
		
		btnStartTest.setForeground(new Color(255,255,255));
		btnStartTest.setBackground(new Color(20,112,185));
		btnStartTest.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnStartTest.setBounds(770, 298, 166, 42);
		add(btnStartTest);

		
		instructions = new JTextArea();
		instructions.setToolTipText("INSTRUCTIONS");
		instructions.setEditable(false);
		instructions.setDisabledTextColor(new Color(255, 255, 255));
		instructions.setBackground(new Color(20, 112, 185));
		instructions.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		instructions.setForeground(new Color(255, 255, 255));
		instructions.setBounds(250, 380, 800, 248);
		instructions.setVisible(false);
		add(instructions);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(Courses));
		comboBox.setFont(new Font("Kayak Sans", Font.BOLD, 21));
		comboBox.setBounds(520, 298, 200, 36);
		comboBox.setSelectedIndex(-1);
		add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Selected_course=(String)comboBox.getSelectedItem();
				courseDetail(Selected_course);
				
				instructions.setText("                      \r\n                                                                    INSTRUCTIONS                                \r\n\r\n1. There are total "+totalQuestions+" questions each with "+eachMark+" marks in this course.\r\n2. Maximum time is "+time+" .\r\n3. There is no negative marking for any question.\r\n4. After time up you will be automatically logged out.\r\n5. You can see the time left on the upper right corner.\r\n6. Wish You All The Best.");
				instructions.setVisible(true);
				revalidate();
			}
		});
				
		JLabel lblSelectCourse = new JLabel("SELECT COURSE :");
		lblSelectCourse.setForeground(new Color(20, 112, 185));
		lblSelectCourse.setFont(new Font("Kayak Sans", Font.BOLD, 25));
		lblSelectCourse.setBounds(290, 300, 300, 28);
		add(lblSelectCourse);
		
		
		
		JButton btnEditDetails = new JButton("PROFILE");
		btnEditDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Edit Details of User with the username
				new EditDetails(Username);
			}
		});
		
		btnEditDetails.setForeground(new Color(20,112,185));
		btnEditDetails.setBackground(new Color(255,255,255));
		btnEditDetails.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnEditDetails.setBounds(900, 50, 166, 42);
		add(btnEditDetails);
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Save Data if any.
				MainFrame.AddPanel(new UserLogin());
				JOptionPane.showMessageDialog(null, "Logout Successfully");
			}
		});
		btnLogout.setForeground(new Color(20, 112, 185));
		btnLogout.setBackground(new Color(255,255,255));
		btnLogout.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnLogout.setBounds(1100, 50, 166, 42);
		add(btnLogout);
		
		JLabel WlecomeLabel = new JLabel("Welcome "+Name+" !");
		WlecomeLabel.setForeground(new Color(20, 112, 185));
		WlecomeLabel.setFont(new Font("Kayak Sans", Font.BOLD, 30));
		WlecomeLabel.setBounds(450, 200, 445, 28);
		add(WlecomeLabel);
		
		
		
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