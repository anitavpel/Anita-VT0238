package course;

import java.awt.Color;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import main.MainFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import com.mysql.cj.jdbc.ClientPreparedStatement;

import database.Connect;

public class EditCourse extends JPanel implements ItemListener{

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private String CourseName;
	private String[] QuestionID;
	private static int TotalQuestions;
	private Connect c=new Connect("root","root");
	private JTabbedPane jt;
	private static int eachMark;
	private static int selectedMark=eachMark;
	private static int correctOption=1;
	private void fetchData()
	{
		try{
			Statement st=c.con.createStatement();
			String query ="select count(q_id) from questions where course_name='"+CourseName+"'";
			ResultSet rs=st.executeQuery(query);
			rs.next();
			QuestionID=new String[rs.getInt(1)];
			query="select *from course_details where course_name='"+CourseName+"'";
			rs=st.executeQuery(query);
			rs.next();
			TotalQuestions=rs.getInt("total_question");
			
			time=rs.getString("time");
			eachMark=rs.getInt("question_mark");
			System.out.println("course : "+rs.getString("course_name")+time+rs.getInt("question_mark"));
			query="select *from questions where course_name='"+CourseName+"'";
			rs=st.executeQuery(query);
			int i=0;
			while(rs.next())
			{
				QuestionID[i++]=rs.getString("q_id");
			}
			System.out.println("Data is set to questionID");
		}
		catch(SQLException e)
		{
			System.out.println("Edit Course error in fetching data : "+e);
		}
	}
	
	private boolean databaseExecuteQuery(String query)
	{
		try{
			Statement st=c.con.createStatement();
			st.execute(query);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println("Edit Course : "+e);
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
	private ResultSet databaseResultSet(String query)
	{
		try{
			Statement st=c.con.createStatement();
			return st.executeQuery(query);
		}
		catch(SQLException e)
		{
			System.out.println("Edit Course : "+e);
			return null;
		}
	}

	
	
	private JComboBox<Integer> updateEachMark;
	private JTextField updateCourseName;
	private JTextField updateHH;
	private JTextField updateMM;
	private JTextField updateSS;
	private String time;
	
	public EditCourse(String courseName) {
		CourseName=courseName;
		fetchData();
		setLayout(null);
		
		updateEachMark = new JComboBox<Integer>();
		updateEachMark.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {1,2,3,4,5,6,7,8}));
		updateEachMark.setFont(new Font("Kayak Sans", Font.BOLD, 19));
		updateEachMark.setBounds(931, 261, 57, 21);
		updateEachMark.setSelectedIndex(eachMark-1);
		add(updateEachMark);
		updateEachMark.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				selectedMark=(Integer)updateEachMark.getSelectedItem();
				System.out.println(selectedMark);
			}
		});
		
		JLabel lblTotalQuestions = new JLabel("Total Questions : "+TotalQuestions);
		lblTotalQuestions.setBounds(850, 314, 159, 24);
		add(lblTotalQuestions);
		
		JButton save = new JButton("Save");
		save.setBorder(new LineBorder(new Color(0, 0, 140), 1, true));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String query="UPDATE course_details SET course_name=?,time=?,question_mark=?"
						+ " WHERE course_name='"+CourseName+"'";
				try{
					ClientPreparedStatement ps=(ClientPreparedStatement)c.con.prepareStatement(query);
					ps.setString(1, updateCourseName.getText());
					ps.setString(2, updateHH.getText()+":"+updateMM.getText()+":"+updateSS.getText());
					ps.setInt(3, selectedMark);
					ps.executeUpdate();
				}
				catch(SQLException e)
				{
					System.out.println(e);
				}
				MainFrame.AddPanel(new EditCourse(updateCourseName.getText()));
				JOptionPane.showMessageDialog(null, "Course saved successfully.");
			}
		});
		save.setForeground(new Color(255,255,255));
		save.setBackground(new Color(20, 112, 185));
		save.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		save.setBounds(10, 600, 89, 50);
		add(save);
		
		JButton back = new JButton("BACK");
		back.setBorder(new LineBorder(new Color(20, 112, 185), 1, true));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new CoursePanel());
			}
		});
		back.setForeground(new Color(255,255,255));
		back.setBackground(new Color(20, 112, 185));
		back.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		back.setBounds(10, 600, 89, 50);
		add(back);

		JLabel label_1 = new JLabel(":");
		label_1.setBounds(897, 213, 9, 24);
		add(label_1);
		JLabel label = new JLabel(":");
		label.setBounds(897, 213, 9, 24);
		add(label);
		
		updateSS = new JTextField();
		updateSS.setColumns(10);
		updateSS.setBounds(964, 215, 45, 20);
		add(updateSS);
		
		updateMM = new JTextField();
		updateMM.setColumns(10);
		updateMM.setBounds(907, 215, 45, 20);
		add(updateMM);
		
		updateHH = new JTextField();
		updateHH.setColumns(10);
		updateHH.setBounds(850, 215, 45, 20);
		add(updateHH);
		
		updateHH.setText(time.substring(0, 2));
		updateMM.setText(time.substring(3, 5));
		updateSS.setText(time.substring(6, 8));
		
		updateCourseName = new JTextField();
		updateCourseName.setBounds(850, 136, 185, 20);
		add(updateCourseName);
		updateCourseName.setColumns(10);
		updateCourseName.setText(CourseName);
		
		JLabel lblEachMark = new JLabel("Each Mark");
		lblEachMark.setBounds(850, 261, 87, 24);
		add(lblEachMark);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(850, 177, 87, 24);
		add(lblTime);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setBounds(850, 113, 87, 24);
		add(lblCourseName);
		save.setForeground(new Color(255,255,255));
		save.setBackground(new Color(20, 112, 185));
		save.setFont(new Font("Kayak Sans", Font.PLAIN, 18));
		save.setBounds(1170, 500, 100, 50);
		add(save);
				
		jt=new JTabbedPane();
		jt.setBounds(20, 90, 741, 459);
		jt.addTab("New",New());
		jt.addTab("Update",Update());
		jt.addTab("Remove",Remove());
		add(jt);
		
		JLabel lblOnlineExamination = new JLabel("Course : "+CourseName);
		lblOnlineExamination.setBounds(10, 11, 501, 68);
		lblOnlineExamination.setForeground(new Color(25, 25, 112));
		lblOnlineExamination.setBackground(Color.WHITE);
		lblOnlineExamination.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlineExamination.setFont(new Font("Kayak Sans", Font.BOLD, 41));
		add(lblOnlineExamination);
		
		JSeparator separator = new JSeparator();
		separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setBounds(775,5,1920,570);
		add(separator);
		

		JSeparator separator1 = new JSeparator();
		separator1 = new JSeparator(SwingConstants.HORIZONTAL);
		separator1.setBounds(5,575,1920,1080);
		add(separator1);
		
		
			
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		lblNewLabel.setIcon(new ImageIcon("assets\\image.jpg"));
		lblNewLabel.setBorder(new LineBorder(new Color(20, 112, 185), 4));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblNewLabel);
		
	}
	
	private JTextField NqID;
	private JTextArea NqStatement;
	private JTextField No1;
	private JTextField No2;
	private JTextField No4;
	private JTextField No3;
	private JComboBox<String> NcorrectOptionS;
	
	
	
	
	private JPanel New()
	{
		JPanel New=new JPanel();
		New.setBackground(new Color(255,255,255));
		New.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 79, 661, 141);
		New.add(scrollPane);

		
		NqStatement = new JTextArea();
		NqStatement.setBorder(new LineBorder(new Color(20, 112, 185), 2, true));
		NqStatement.setFont(new Font("Kayak Sans", Font.BOLD, 20));
		
		NqID = new JTextField();
		NqID.setBounds(182, 15, 147, 20);
		NqID.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		New.add(NqID);
		scrollPane.setViewportView(NqStatement);
		
		JLabel lblQuestionId = new JLabel(" Question ID : ");
		lblQuestionId.setBounds(10, 11, 165, 23);
		lblQuestionId.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		New.add(lblQuestionId);
		
		JLabel lblQuestionStatement = new JLabel("Question statement : ");
		lblQuestionStatement.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		lblQuestionStatement.setBounds(20, 46, 230, 23);
		New.add(lblQuestionStatement);
		
		JLabel lblOption = new JLabel("Option 1");
		lblOption.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		lblOption.setBounds(53, 231, 91, 23);
		New.add(lblOption);
		
		JLabel lblOption_1 = new JLabel("Option 2");
		lblOption_1.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		lblOption_1.setBounds(53, 265, 91, 23);
		New.add(lblOption_1);
		
		JLabel lblOption_2 = new JLabel("Option 3");
		lblOption_2.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		lblOption_2.setBounds(53, 299, 91, 23);
		New.add(lblOption_2);
		
		JLabel lblOption_3 = new JLabel("Option 4");
		lblOption_3.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		lblOption_3.setBounds(53, 333, 91, 23);
		New.add(lblOption_3);
		
		JLabel lblCorrectOption = new JLabel("Correct option : ");
		lblCorrectOption.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		lblCorrectOption.setBounds(53, 380, 187, 23);
		New.add(lblCorrectOption);
		
		NcorrectOptionS = new JComboBox<String>();
		NcorrectOptionS.setFont(new Font("Kayak Sans", Font.PLAIN, 13));
		NcorrectOptionS.setModel(new DefaultComboBoxModel<String>(new String[] {"Option 1", "Option 2", "Option 3", "Option 4"}));
		NcorrectOptionS.setBounds(250, 380, 78, 23);
		New.add(NcorrectOptionS);
		NcorrectOptionS.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				correctOption=NcorrectOptionS.getSelectedIndex()+1;
			}
		});
		
		No1 = new JTextField();
		No1.setFont(new Font("Kayak Sans", Font.BOLD, 12));
		No1.setBounds(154, 231, 560, 20);
		New.add(No1);
		
		No2 = new JTextField();
		No2.setFont(new Font("Kayak Sans", Font.BOLD, 12));
		No2.setBounds(154, 269, 560, 20);
		New.add(No2);
		
		No3 = new JTextField();
		No3.setFont(new Font("Kayak Sans", Font.BOLD, 12));
		No3.setBounds(154, 298, 560, 20);
		New.add(No3);
		
		No4 = new JTextField();
		No4.setFont(new Font("Kayak Sans", Font.BOLD, 12));
		No4.setBounds(154, 336, 560, 20);
		New.add(No4);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query="INSERT INTO questions(q_id,q_statement,option_1,option_2,option_3,option_4,correct_option,course_name)"
						+ "VALUES (?,?,?,?,?,?,?,'"+CourseName+"')";
				try {
					java.sql.PreparedStatement ps=c.con.prepareStatement(query);
					ps.setString(1, NqID.getText());
					ps.setString(2, NqStatement.getText());
					ps.setString(3, No1.getText());
					ps.setString(4, No2.getText());
					ps.setString(5, No3.getText());
					ps.setString(6, No4.getText());
					ps.setInt(7, correctOption);
					if(ps.executeUpdate()>0)
					{
						System.out.println("Question Added"+NqStatement.getText());
						databaseExecuteQuery("UPDATE course_details SET total_question=total_question+1 where course_name='"+CourseName+"'");
						MainFrame.AddPanel(new EditCourse(CourseName));
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					System.out.println("Error in saving question  : "+e);
					
				}
			}
		});
		btnSave.setForeground(new Color(255,255,255));
		btnSave.setBackground(new Color(20, 112, 185));
		btnSave.setFont(new Font("Kayak Sans", Font.PLAIN, 18));
		btnSave.setBounds(594, 375, 120, 37);
		New.add(btnSave);
		return New;
	}

	private JTextArea UqStatement;
	private JTextField Uo1;
	private JTextField Uo2;
	private JTextField Uo3;
	private JTextField Uo4;
	private JComboBox<String> UqIdCombo;
	private JComboBox<String> UcorrectOptionS;

	private JPanel Update()
	{
		JPanel Update=new JPanel();
		Update.setBackground(new Color(255,255,255));
		Update.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 80, 661, 141);
		Update.add(scrollPane);
		
		UqStatement = new JTextArea();
		UqStatement.setFont(new Font("Kayak Sans", Font.BOLD, 20));
		UqStatement.setBorder(new LineBorder(new Color(65, 105, 225), 2, true));
		scrollPane.setViewportView(UqStatement);
		
		
		JLabel label = new JLabel(" Question ID : ");
		label.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		label.setBounds(12, 12, 165, 23);
		Update.add(label);
		
		JLabel label_1 = new JLabel("Question statement : ");
		label_1.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		label_1.setBounds(22, 47, 230, 23);
		Update.add(label_1);
		
		JLabel label_2 = new JLabel("Option 1");
		label_2.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		label_2.setBounds(55, 232, 91, 23);
		Update.add(label_2);
		
		Uo1 = new JTextField();
		Uo1.setFont(new Font("Kayak Sans", Font.BOLD, 12));
		Uo1.setBounds(156, 232, 560, 20);
		Update.add(Uo1);
		
		JLabel label_3 = new JLabel("Option 2");
		label_3.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		label_3.setBounds(55, 266, 91, 23);
		Update.add(label_3);
		
		Uo2 = new JTextField();
		Uo2.setFont(new Font("Kayak Sans", Font.BOLD, 12));
		Uo2.setBounds(156, 270, 560, 20);
		Update.add(Uo2);
		
		JLabel label_4 = new JLabel("Option 3");
		label_4.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		label_4.setBounds(55, 300, 91, 23);
		Update.add(label_4);
		
		Uo3 = new JTextField();
		Uo3.setFont(new Font("Kayak Sans", Font.BOLD, 12));
		Uo3.setBounds(156, 299, 560, 20);
		Update.add(Uo3);
		
		JLabel label_5 = new JLabel("Option 4");
		label_5.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		label_5.setBounds(55, 334, 91, 23);
		Update.add(label_5);
		
		Uo4 = new JTextField();
		Uo4.setFont(new Font("Kayak Sans", Font.BOLD, 12));
		Uo4.setBounds(156, 337, 560, 20);
		Update.add(Uo4);
		
		JLabel label_6 = new JLabel("Correct option : ");
		label_6.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		label_6.setBounds(55, 381, 187, 23);
		Update.add(label_6);
		
		UqIdCombo = new JComboBox<String>();
		UqIdCombo.setModel(new DefaultComboBoxModel<String>(QuestionID));
		UqIdCombo.setBounds(156, 14, 153, 23);
		Update.add(UqIdCombo);
		UqIdCombo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				ResultSet rs;
				try{
					rs=databaseResultSet("select *from questions where q_id='"+(String)UqIdCombo.getSelectedItem()+"'");
					if(rs.next())
						System.out.println("Done");
					System.out.println(rs.getString("q_statement")+"  kdsfhjk");
					UqStatement.setText(rs.getString("q_statement"));
					Uo1.setText(rs.getString("option_1"));
					Uo2.setText(rs.getString("option_2"));
					Uo3.setText(rs.getString("option_3"));
					Uo4.setText(rs.getString("option_4"));
					UcorrectOptionS.setSelectedIndex(((int)rs.getInt("correct_option")-1));
				}
				catch(SQLException e)
				{
					System.out.println("Error in view action listener.");
				}
				
			}
		});
		
		UcorrectOptionS = new JComboBox<String>();
		UcorrectOptionS.setModel(new DefaultComboBoxModel<String>(new String[] {"Option 1", "Option 2", "Option 3", "Option 4"}));
		UcorrectOptionS.setFont(new Font("Kayak Sans", Font.PLAIN, 13));
		UcorrectOptionS.setBounds(260, 383, 78, 23);
		Update.add(UcorrectOptionS);
		UcorrectOptionS.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				correctOption=UcorrectOptionS.getSelectedIndex()+1;
				
			}
		});
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query="UPDATE questions SET q_statement=?,option_1=?,option_2=?,option_3=?,option_4=?,correct_option=?"
						+ " WHERE q_id='"+(String)UqIdCombo.getSelectedItem()+"'";
				try {
					java.sql.PreparedStatement ps=c.con.prepareStatement(query);
					ps.setString(1, UqStatement.getText());
					ps.setString(2, Uo1.getText());
					ps.setString(3, Uo2.getText());
					ps.setString(4, Uo3.getText());
					ps.setString(5, Uo4.getText());
					ps.setInt(6, correctOption);
					if(ps.executeUpdate()>0)
					{
						System.out.println("Question Updated");
						MainFrame.AddPanel(new EditCourse(CourseName));
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Question not updated.");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
					System.out.println("Error in saving question  : "+e);
					
				}
			}
		});
		btnUpdate.setForeground(new Color(20, 112, 185));
		btnUpdate.setFont(new Font("Kayak Sans", Font.PLAIN, 18));
		btnUpdate.setBounds(597, 376, 120, 31);
		Update.add(btnUpdate);
		return Update;
	}
	
	private JLabel RcorrectOptionLabel ;
	private JComboBox<String> RqIdCombo;
	private JTextArea RqStatement;
	private JTextField Ro1;
	private JTextField Ro2;
	private JTextField Ro4;
	private JTextField Ro3;
	private JPanel Remove()
	{
		JPanel Remove=new JPanel();
		Remove.setBackground(new Color(255,255,255));
		Remove.setLayout(null);
		
		RcorrectOptionLabel = new JLabel("");
		RcorrectOptionLabel.setFont(new Font("Kayak Sans", Font.PLAIN, 16));
		RcorrectOptionLabel.setBounds(248, 381, 105, 21);
		Remove.add(RcorrectOptionLabel);
		
		JLabel label = new JLabel(" Question ID : ");
		label.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		label.setBounds(12, 12, 148, 23);
		Remove.add(label);
		
		JLabel label_1 = new JLabel("Question statement : ");
		label_1.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		label_1.setBounds(22, 46, 218, 23);
		Remove.add(label_1);
		
		RqIdCombo = new JComboBox<String>();
		RqIdCombo.setModel(new DefaultComboBoxModel<String>(QuestionID));
		RqIdCombo.setBounds(170, 16, 153, 23);
		Remove.add(RqIdCombo);
		RqIdCombo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				ResultSet rs;
				try{
					rs=databaseResultSet("select *from questions where q_id='"+(String)RqIdCombo.getSelectedItem()+"'");
					rs.next();
					RqStatement.setText(rs.getString("q_statement"));
					Ro1.setText(rs.getString("option_1"));
					Ro2.setText(rs.getString("option_2"));
					Ro3.setText(rs.getString("option_3"));
					Ro4.setText(rs.getString("option_4"));
					RcorrectOptionLabel.setText(rs.getString("correct_option"));
				}
				catch(SQLException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
					System.out.println("Error in REMOVE action listener.");
				}
				
			}
		});
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 79, 661, 141);
		Remove.add(scrollPane);
		
		RqStatement = new JTextArea();
		RqStatement.setEditable(false);
		RqStatement.setFont(new Font("Kayak Sans", Font.BOLD, 20));
		RqStatement.setBorder(new LineBorder(new Color(20, 112, 185), 2, true));
		scrollPane.setViewportView(RqStatement);
		
		JLabel label_2 = new JLabel("Option 1");
		label_2.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		label_2.setBounds(49, 232, 91, 23);
		Remove.add(label_2);
		
		JLabel label_3 = new JLabel("Option 2");
		label_3.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		label_3.setBounds(49, 266, 91, 23);
		Remove.add(label_3);
		
		JLabel label_4 = new JLabel("Option 3");
		label_4.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		label_4.setBounds(49, 300, 91, 23);
		Remove.add(label_4);
		
		JLabel label_5 = new JLabel("Option 4");
		label_5.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		label_5.setBounds(49, 334, 91, 23);
		Remove.add(label_5);
		
		JLabel label_6 = new JLabel("Correct option : ");
		label_6.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		label_6.setBounds(49, 381, 187, 23);
		Remove.add(label_6);
		
		JButton btnRemove = new JButton("REMOVE");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query1="DELETE FROM questions where q_id='"+(String)RqIdCombo.getSelectedItem()+"'";
				String query2="UPDATE course_details SET total_question=total_question-1 where course_name='"+CourseName+"'";
				if(databaseExecuteQuery(query1) && databaseExecuteQuery(query2))
				{
					
					MainFrame.AddPanel(new EditCourse(CourseName));
					JOptionPane.showMessageDialog(null, "Done.");
				}		
			}
		});
		btnRemove.setForeground(new Color(20, 112, 185));
		btnRemove.setFont(new Font("Kayak Sans", Font.PLAIN, 18));
		btnRemove.setBounds(605, 381, 120, 37);
		Remove.add(btnRemove);
		
		Ro1 = new JTextField();
		Ro1.setFont(new Font("Kayak Sans", Font.BOLD, 12));
		Ro1.setBounds(158, 232, 560, 20);
		Remove.add(Ro1);
		
		Ro2 = new JTextField();
		Ro2.setFont(new Font("Kayak Sans", Font.BOLD, 12));
		Ro2.setBounds(158, 270, 560, 20);
		Remove.add(Ro2);
		
		Ro3 = new JTextField();
		Ro3.setFont(new Font("Kayak Sans", Font.BOLD, 12));
		Ro3.setBounds(158, 299, 560, 20);
		Remove.add(Ro3);
		
		Ro4 = new JTextField();
		Ro4.setFont(new Font("Kayak Sans", Font.BOLD, 12));
		Ro4.setBounds(158, 337, 560, 20);
		Remove.add(Ro4);
		return Remove;
	}

	public void itemStateChanged(ItemEvent ie) {
		JCheckBox cb = (JCheckBox)ie.getItem();
		if(cb.isSelected())
			databaseExecuteQuery("UPDATE course_details SET hide=1 where course_name='"+CourseName+"'");
		else
			databaseExecuteQuery("UPDATE course_details SET hide=0 where course_name='"+CourseName+"'");
		
	}
}
