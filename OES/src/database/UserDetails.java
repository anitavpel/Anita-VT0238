package database;

import java.awt.Color;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import admin.LoginAttempts;
import admin.UsersData;
import admin.Verification;
import main.MainFrame;
import results.UserResult;

public class UserDetails extends JPanel{
	private static final long serialVersionUID = 1L;
	
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

	public UserDetails() {
	}

	public String USERNAME;
	public String PASSWORD;
	public String FIRST;
	public String MIDDLE;
	public String LAST;
	public String EMAIL;
	public String MOBILE;
	public String LOGINATTEMPTS;
	public String COLLEGE;
	public String ADDRESS;
	public String Gender;
	public int Date;
	public int Month;
	public int Year;
	public String RegDate;
	public String RegNo;
	public boolean Verify;
	
	
	public JTextField JUSERNAME;
	public JTextField JPASSWORD;
	public JTextField JFIRST;
	public JTextField JMIDDLE;
	public JTextField JLAST;
	public JTextField JEMAIL;
	public JTextField JMOBILE;
	public JTextField JLOGINATTEMPTS;
	public JTextField JCOLLEGE;
	public JTextField JADDRESS;
	public char JGender;
	public int JDate;
	public int JMonth;
	public int JYear;
	private String PanelName;
	
	
	JComboBox<String> year;
	JComboBox<String> month;
	JComboBox<String> date;
	
	@SuppressWarnings("deprecation")
	public UserDetails(String username,boolean makeGui,String PanelName)
	{
		this.PanelName=PanelName;
		Connect c=new Connect("root","root");
		try{
			Statement stmt=c.con.createStatement();
			String query="select *from userdetails where username='"+username+"'";
			ResultSet rs=stmt.executeQuery(query);
			rs.next();
			USERNAME=rs.getString("Username");
			PASSWORD=rs.getString("Password");
			FIRST=rs.getString("FirstName");
			MIDDLE=rs.getString("MiddleName");
			LAST=rs.getString("LastName");
			EMAIL=rs.getString("E-mail");
			MOBILE=rs.getString("Mobile");
			LOGINATTEMPTS=rs.getString("LoginAttempts");
			COLLEGE=rs.getString("College");
			ADDRESS=rs.getString("Address");
			Gender=rs.getString("Gender");
			java.util.Date d=rs.getDate("DOB");
			Date=d.getDate();
			Month=d.getMonth()+1;
			Year=d.getYear()+1900;
			RegNo=rs.getString("RegNo");
			RegDate=rs.getString("RegDate");
			System.out.println(Date+"---"+Month+"---"+Year);
			Verify=rs.getBoolean("verify");
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		if(makeGui)
		{
			makeGUI();
			MainFrame.AddPanel(this);
		}
	}

	public void makeGUI()
	{
		setLayout(null);
		
		
		
		JLabel label_2 = new JLabel(RegDate);
		label_2.setForeground(new Color(255,255,255));
		label_2.setFont(new Font("Kayak Sans", Font.BOLD, 13));
		label_2.setBounds(810, 130, 242, 26);
		add(label_2);
		
		JLabel lblRegDate = new JLabel("REG Date : ");
		lblRegDate.setForeground(new Color(255,255,255));
		lblRegDate.setFont(new Font("Kayak Sans", Font.BOLD, 13));
		lblRegDate.setBounds(750, 130, 109, 26);
		add(lblRegDate);
		
		JLabel label_1 = new JLabel(RegNo);
		label_1.setForeground(new Color(255,255,255));
		label_1.setFont(new Font("Kayak Sans", Font.BOLD, 13));
		label_1.setBounds(405,130, 85, 26);
		add(label_1);
		
		JLabel lblRegNo = new JLabel("REG NO. : ");
		lblRegNo.setForeground(new Color(255,255,255));
		lblRegNo.setFont(new Font("Kayak Sans", Font.BOLD, 13));
		lblRegNo.setBounds(350, 130, 97, 26);
		add(lblRegNo);
		
		
		JLOGINATTEMPTS = new JTextField(LOGINATTEMPTS);
		JLOGINATTEMPTS.setBounds(495, 436, 331, 20);
		add(JLOGINATTEMPTS);
		JLOGINATTEMPTS.setEditable(false);
		
		
		JLabel lblLoginAttempts = new JLabel("ATTEMPTS :");
		lblLoginAttempts.setForeground(new Color(20, 112, 185));
		lblLoginAttempts.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		lblLoginAttempts.setBounds(400, 436, 85, 26);
		add(lblLoginAttempts);
		
		JADDRESS = new JTextField(ADDRESS);
		JADDRESS.setBounds(495, 400, 331, 20);
		add(JADDRESS);
		JADDRESS.setEditable(false);
		
		
		
		JLabel lblAddress = new JLabel("ADDRESS :");
		lblAddress.setForeground(new Color(20, 112, 185));
		lblAddress.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		lblAddress.setBounds(400, 399, 85, 26);
		add(lblAddress);
		
		JCOLLEGE = new JTextField(COLLEGE);
		JCOLLEGE.setFont(new Font("Tahoma", Font.BOLD, 16));
		JCOLLEGE.setColumns(10);
		JCOLLEGE.setBounds(495, 365, 331, 20);
		add(JCOLLEGE);
		JCOLLEGE.setEditable(false);
		
		JLabel lblCollege = new JLabel("COLLEGE :");
		lblCollege.setForeground(new Color(20, 112, 185));
		lblCollege.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		lblCollege.setBounds(400, 362, 85, 26);
		add(lblCollege);
		
		year = new JComboBox<String>();
		year.setBackground(new Color(20, 112, 185));
		year.setLightWeightPopupEnabled(false);
		year.setEditable(true);
		year.setEnabled(false);
		year.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		year.setModel(new DefaultComboBoxModel<String>(new String[] {"1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006"}));
		year.setSelectedIndex(Year-1951);
		year.setBounds(686, 328, 69, 20);
		add(year);
		JYear=Year;
		
		
		month = new JComboBox<String>();
		month.setBackground(new Color(20, 112, 185));
		month.setLightWeightPopupEnabled(false);
		month.setEditable(true);
		month.setEnabled(false);
		month.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		month.setModel(new DefaultComboBoxModel<String>(new String[] {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"}));
		month.setSelectedIndex(Month-1);
		month.setBounds(579, 328, 97, 20);
		add(month);
		JMonth=Month;
		
		date = new JComboBox<String>();
		date.setBackground(new Color(20, 112, 185));
		date.setLightWeightPopupEnabled(false);
		date.setEditable(true);
		date.setEnabled(false);
		date.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		date.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		date.setSelectedIndex(Date-1);
		date.setBounds(495, 328, 74, 20);
		add(date);
		JDate=Date;
		
		JLabel label = new JLabel("MOBILE NO. :");
		label.setForeground(new Color(20, 112, 185));
		label.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		label.setBounds(741, 252, 99, 26);
		add(label);
		
		JMOBILE = new JTextField(MOBILE);
		JMOBILE.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		JMOBILE.setColumns(10);
		JMOBILE.setBounds(836, 253, 116, 20);
		add(JMOBILE);
		JMOBILE.setEditable(false);
		
		JLabel lblDob = new JLabel("D.O.B :");
		lblDob.setForeground(new Color(20, 112, 185));
		lblDob.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		lblDob.setBounds(400, 325, 85, 26);
		add(lblDob);
		
		JEMAIL = new JTextField(EMAIL);
		JEMAIL.setEditable(false);
		JEMAIL.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		JEMAIL.setColumns(10);
		JEMAIL.setBounds(495, 291, 331, 20);
		add(JEMAIL);
		
		JLabel lblEmail = new JLabel("E-MAIL :");
		lblEmail.setForeground(new Color(20, 112, 185));
		lblEmail.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		lblEmail.setBounds(400, 288, 85, 26);
		add(lblEmail);
		
		JRadioButton other = new JRadioButton("OTHER");
		other.setEnabled(false);
		other.setOpaque(false);
		other.setForeground(new Color(20, 112, 185));
		other.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		other.setBounds(632, 253, 73, 23);
		add(other);
		
		JRadioButton female = new JRadioButton("FEMALE");
		female.setEnabled(false);
		female.setOpaque(false);
		female.setForeground(new Color(20, 112, 185));
		female.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		female.setBounds(557, 253, 73, 23);
		add(female);
		
		JRadioButton male = new JRadioButton("MALE");
		male.setEnabled(false);
		male.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		male.setForeground(new Color(20, 112, 185));
		male.setOpaque(false);
		male.setBounds(495, 253, 60, 23);
		add(male);
				
		JLabel lblGender = new JLabel("GENDER :");
		lblGender.setForeground(new Color(20, 112, 185));
		lblGender.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		lblGender.setBounds(400, 251, 85, 26);
		add(lblGender);
		
		JGender=Gender.charAt(0);
		
		switch(JGender)
		{
		case 'M' :male.setSelected(true);break;
		case 'F' :female.setSelected(true);break;
		case 'O' :other.setSelected(true);break;
		}
		
		JLAST = new JTextField(LAST);
		JLAST.setEditable(false);
		JLAST.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		JLAST.setColumns(10);
		JLAST.setBounds(747, 217, 116, 20);
		add(JLAST);
		
		JMIDDLE = new JTextField(MIDDLE);
		JMIDDLE.setEditable(false);
		JMIDDLE.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		JMIDDLE.setColumns(10);
		JMIDDLE.setBounds(621, 217, 116, 20);
		add(JMIDDLE);
		
		JFIRST = new JTextField(FIRST);
		JFIRST.setEditable(false);
		JFIRST.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		JFIRST.setColumns(10);
		JFIRST.setBounds(495, 217, 116, 20);
		add(JFIRST);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setForeground(new Color(20, 112, 185));
		lblName.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		lblName.setBounds(400, 214, 85, 26);
		add(lblName);
		
		JPASSWORD = new JTextField(PASSWORD);
		JPASSWORD.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		JPASSWORD.setColumns(10);
		JPASSWORD.setBounds(795, 180, 116, 20);
		add(JPASSWORD);
		JPASSWORD.setEditable(false);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(20, 112, 185));
		separator.setBounds(400, 100, 513, 2);
		add(separator);
		
		JUSERNAME = new JTextField(USERNAME);
		JUSERNAME.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		JUSERNAME.setBounds(495, 180, 116, 20);
		JUSERNAME.setEditable(false);
		add(JUSERNAME);
		JUSERNAME.setColumns(10);
		
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setForeground(new Color(20, 112, 185));
		lblUsername.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		lblUsername.setBounds(400, 177, 85, 26);
		add(lblUsername);
		
		JButton back = new JButton("BACK");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					//Resuming Previous Panel
				if(PanelName.equals("Verification"))
					new Verification();
				else if(PanelName.equals("UsersData"))
					MainFrame.AddPanel(new UsersData());
			}
		});
		back.setForeground(new Color(255,255,255));
		back.setBackground(new Color(20, 112, 185));
		back.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		back.setBounds(10, 600, 89, 50);
		add(back);
		
		
		JLabel lblOnlineExamination = new JLabel("USER DETAILS");
		lblOnlineExamination.setBounds(400, 18, 535, 57);
		lblOnlineExamination.setForeground(new Color(255,255,255));
		lblOnlineExamination.setBackground(Color.GREEN);
		lblOnlineExamination.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlineExamination.setFont(new Font("Kayak Sans", Font.BOLD, 41));
		add(lblOnlineExamination);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(new Color(20, 112, 185));
		lblPassword.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		lblPassword.setBounds(690, 177, 85, 26);
		add(lblPassword);
		
		

		JButton EditAttempts = new JButton("EDIT LOGIN ATTEMPTS");
		EditAttempts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(executeQuery("select *from userdetails"))
					MainFrame.AddPanel(new LoginAttempts(USERNAME));
				else
					JOptionPane.showMessageDialog(null, "No users found.");
			}
		});
		
		EditAttempts.setForeground(new Color(255,255,255));
		EditAttempts.setBackground(new Color(20,112,185));
		EditAttempts.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		EditAttempts.setBounds(695, 500, 250, 43);
		add(EditAttempts);
		
		
		JButton btnNewButton = new JButton("RESULT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new UserResult(USERNAME,"admin");
			}
		});
		btnNewButton.setForeground(new Color(255,255,255));
		btnNewButton.setBackground(new Color(20,112,185));
		btnNewButton.setFont(new Font("Kayak Sans", Font.PLAIN, 16));
		btnNewButton.setBounds(400, 500, 135, 43);
		add(btnNewButton);
		
		
		
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
		lblNewLabel.setFont(new Font("Kayak Sans", Font.BOLD, 14));
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		lblNewLabel.setBorder(new LineBorder(new Color(20, 112, 185), 4));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblNewLabel);

	}
}
