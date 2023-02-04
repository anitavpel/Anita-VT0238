package startTest;

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
import javax.swing.border.MatteBorder;

import database.Connect;
import main.MainFrame;

public class BeginTest extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private static String username;
	private static String courseName;
	QuestionPanel questionPanel;
	static int eachMarks;
	private int HH=0;
	private int MM=0;
	private int SS=0;
	
	public BeginTest()
	{
		
	}
	private static String[] computeResult()
	{
		String Result[]=new String[9];
		int totalQuestions=QuestionPanel.qInfo.length;
		int timeTaken=Clock.time;
		int CQ=0;
		for(String s[]:QuestionPanel.qInfo)
		{
			if(s[1].equals(s[2]) && !(s[1].equals("-1")))
				CQ++;
		}
		String time;
		int temp=timeTaken%60;
		time=":"+temp;
		temp=(timeTaken-temp)/60;
		time=":"+(temp%60)+time;
		temp=(temp-temp%60)/60;
		time=temp+time;
		
		Result[0]=courseName;
		Result[1]=time;//timeTaken;
		Result[2]=""+totalQuestions;  //total questions
		Result[3]=""+QuestionPanel.q_attempted;//attemptedQuestion;
		Result[4]=""+CQ;//correct questions;
		Result[5]=""+(QuestionPanel.q_attempted-CQ);//wrong;
		Result[6]=""+(CQ*eachMarks);//marksobtained;
		Result[7]=""+(totalQuestions*eachMarks);//total marks;
		Result[8]=""+(((float)(CQ*eachMarks)/(totalQuestions*eachMarks))*100);//percentage;
		return Result;
	}
	
	private void executeQuery()
	{
		Connect c=new Connect("root","root");
		try{
			Statement st=c.con.createStatement();
			String temp="select time from course_details where course_name='"+courseName+"'";
			ResultSet rs=st.executeQuery(temp);
			rs.next();
			temp=rs.getString(1);

			HH=Integer.parseInt(temp.substring(0, 2));
			MM=Integer.parseInt(temp.substring(3, 5));
			SS=Integer.parseInt(temp.substring(6, 8));
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
	
	static void endTest()
	{
		//Compute result , show result
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainFrame.AddPanel(new ShowResult(computeResult(),username));
		QuestionPanel.q_attempted=0;
		Clock.time=0;
	}
	
	public BeginTest(String Username,String CourseName,String eachMarks) {
		courseName=CourseName;
		username=Username;
		executeQuery();
		BeginTest.eachMarks=Integer.parseInt(eachMarks);
		makeGUI();
	}
	private Clock c;
	public void makeGUI()
	{
		
		JLabel lblCourseName = new JLabel("COURSE NAME : "+courseName);
		lblCourseName.setForeground(new Color(255,255,255));
		lblCourseName.setFont(new Font("Kayak Sans", Font.BOLD, 25));
		lblCourseName.setBounds(10, 26, 382, 34);
		add(lblCourseName);
		//addTimer
		
		c=new Clock(HH,MM,SS);
		c.setBounds(1100,11,166,42);
		c.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(20, 112, 185)));
		
		add(c);
		//add QuestionPanel
		questionPanel= new QuestionPanel(courseName);
		questionPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(20, 112, 185)));
		questionPanel.setBounds(100, 71, 1000, 1000);
		add(questionPanel);
		
		//add submit button 
		setLayout(null);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.setToolTipText("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				int result = JOptionPane.showConfirmDialog(null, "Do you really want to submit?", "Confirm Submission", JOptionPane.YES_NO_OPTION);
		        if (result == JOptionPane.YES_OPTION) {
		        	
		            // Submit the form
		        
		        	
		        	
				endTest();
				c.t.stop();//if submit button clicks then stop the thread of clock
			}
		        else {
		        	continueTest();
					
				}
				
			}

			private void continueTest() {
				// TODO Auto-generated method stub
				
			}
		});
		btnSubmit.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(20, 112, 185)));
		btnSubmit.setForeground(new Color(20, 112, 185));
		btnSubmit.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnSubmit.setBounds(1150, 550, 120, 50);
		btnSubmit.setFocusable(false);
		add(btnSubmit);
		
		
		JLabel lblNewLabel2 = new JLabel("");
		lblNewLabel2.setIcon(new ImageIcon("assets\\A.png"));
		lblNewLabel2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel2.setBounds(4,4, 1920, 100);
		add(lblNewLabel2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("assets\\image.jpg"));
		lblNewLabel.setForeground(Color.PINK);
		lblNewLabel.setBackground(Color.PINK);
		lblNewLabel.setBorder(new LineBorder(new Color(20, 112, 185), 4));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		add(lblNewLabel);
		
	}
}