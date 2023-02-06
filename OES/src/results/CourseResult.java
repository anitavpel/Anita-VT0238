package results;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import admin.CoursesResult;
import main.MainFrame;
import database.Connect;

public class CourseResult{

	/**
	 * Create the panel.
	 */
	private String CourseName;
	private String[][] DATA;
	Connect c=new Connect("root","root");
	ResultSet rs;
	/**
	 * Create the panel.
	 */
	
	private void fetchData()
	{
		ResultSet temp1,temp2;
		try{
			temp1=databaseWork("select count(username) from result where course_name='"+CourseName+"'");
			temp1.next();
			int totalTest=temp1.getInt(1);
			DATA=new String[totalTest][11];
			temp1=databaseWork("select *from result where course_name='"+CourseName+"'");
			for(int i=0;i<totalTest;i++)
			{
				if(temp1.next())
				{
					DATA[i][0]=temp1.getString("username");
					DATA[i][2]=temp1.getString("total_questions");
					DATA[i][3]=temp1.getString("attempted_question");
					DATA[i][4]=""+(temp1.getInt("attempted_question")-temp1.getInt("wrong_question"))+"";//correct questions
					DATA[i][5]=temp1.getString("wrong_question");
					DATA[i][6]=temp1.getString("total_marks");
					DATA[i][7]=temp1.getString("obtained_marks");
					DATA[i][8]=temp1.getString("percentage");
					DATA[i][9]=temp1.getString("time_taken");
					DATA[i][10]=temp1.getString("test_date");
					temp2=databaseWork("select firstname,middlename,lastname from userdetails where username='"+temp1.getString("username")+"'");
					temp2.next();
					DATA[i][1]=temp2.getString(1)+" "+temp2.getString(2)+" "+temp2.getString(3);
				}
			}
		}
		catch(SQLException e)
		{
			System.out.println("sdfmsdmfk"+e);
		}
	}
	private ResultSet databaseWork(String query)
	{
		ResultSet rs1;
		try{
			Statement st=c.con.createStatement();
			rs1=st.executeQuery(query);
		}
		catch(SQLException e)
		{
			System.out.println("UserResult->databaseWork(String query) Exception : "+e);
			rs1=null;
		}
		return rs1;
	}
	public CourseResult(String coursename)
	{
		CourseName=coursename;
		fetchData();
		if(DATA.length==0)
		{
			JOptionPane.showMessageDialog(null, "No result found for "+coursename+" course");
		}
		else
		{
			MainFrame.AddPanel(makeGUI());
		}
	}
	public JPanel makeGUI() {
		JPanel p=new JPanel();
		p.setLayout(null);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new CoursesResult());
			}
		});
		
		
		
		String ColHeads[]={"Username","Name","Total Ques.","Attempted Ques.","Correct Ques.","Wrong Ques.","Total Marks","Obtained Marks","Percentage","Time taken","Test date"};
		
		JLabel lblCourseName_1 = new JLabel(CourseName);
		lblCourseName_1.setForeground(new Color(255,255,255));
		lblCourseName_1.setFont(new Font("Kayak Sans", Font.BOLD, 22));
		lblCourseName_1.setBounds(1200, 100, 180, 23);
		p.add(lblCourseName_1);
		
		JLabel lblCourseName = new JLabel("COURSE NAME: ");
		lblCourseName.setForeground(new Color(255,255,255));
		lblCourseName.setFont(new Font("Kayak Sans", Font.BOLD, 22));
		lblCourseName.setBounds(1050,100, 113, 23);
		p.add(lblCourseName);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 160, 1250, 430);
		p.add(panel);
		panel.setLayout(null);
		
		Result result = new Result(11,ColHeads,DATA.length,DATA);
		result.setBounds(10, 11, 1250, 418);
		panel.add(result);
			
		btnNewButton.setForeground(new Color(255,255,255));
		btnNewButton.setBackground(new Color(20, 112, 185));
		btnNewButton.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnNewButton.setBounds(10, 600, 89, 50);
		p.add(btnNewButton);
		
		JLabel lblOnlineExamination = new JLabel("COURSE RESULT");
		lblOnlineExamination.setForeground(new Color(255,255,255));
		lblOnlineExamination.setBackground(Color.pink);
		lblOnlineExamination.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlineExamination.setFont(new Font("Kayak Sans", Font.BOLD, 41));
		lblOnlineExamination.setBounds(400, 40, 535, 57);
		p.add(lblOnlineExamination);
		
		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setIcon(new ImageIcon("assets\\logo-web-transparent.png"));
		lblNewLabel1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel1.setBounds(5, 5, 500, 150);
		p.add(lblNewLabel1);
		
		JLabel lblNewLabel2 = new JLabel("");
		lblNewLabel2.setIcon(new ImageIcon("assets\\A.png"));
		lblNewLabel2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel2.setBounds(4,4, 1920, 140);
		p.add(lblNewLabel2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("assets\\image.jpg"));
		lblNewLabel.setBorder(new LineBorder(new Color(20, 112, 185), 4));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		p.add(lblNewLabel);
		
		return p;
	}
}