package admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import database.Connect;
import main.MainFrame;
import results.CourseResult;

@SuppressWarnings("serial")
public class CoursesResult extends JPanel {

	/**
	 * Create the panel.
	 */
		private static Connect c=new Connect("root","root");
		private JComboBox<String> comboBox;
		private String CourseName;
		private String[] DATA;
		/**
		 * Create the panel.
		 */
		public CoursesResult() {
			databaseVerify();
			makeGUI();
		}
		private void databaseVerify()
		{
			try{
				Statement st=c.con.createStatement();
				String query="select count(course_name) from course_details";
				java.sql.ResultSet rs=st.executeQuery(query);
				rs.next();
				int i=rs.getInt(1);
				DATA=new String[i];
				i=0;
				query="select course_name from course_details";
				rs=st.executeQuery(query);
				while(rs.next())
					DATA[i++]=rs.getString("course_name");
				for(String s:DATA)
					System.out.println(s);
			}
			catch(SQLException e)
			{
				System.out.println(e);
			}

		}
		public void makeGUI() {
			//public Verification(){
				setLayout(null);
				
				JButton btnSearch = new JButton("SEARCH");
				btnSearch.setToolTipText("SEARCH");
				btnSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//Show details of that username
						new CourseResult(CourseName);
					}
				});
				btnSearch.setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(123, 104, 238)));
				btnSearch.setForeground(new Color(0, 0, 140));
				btnSearch.setFont(new Font("Kayak Sans", Font.BOLD, 18));
				btnSearch.setBounds(800, 223, 125, 36);
				add(btnSearch);
				
				
				
				JLabel lblUsersForVerification = new JLabel("Available Courses");
				lblUsersForVerification.setForeground(new Color(0,0,140));
				lblUsersForVerification.setFont(new Font("Kayak Sans", Font.BOLD, 18));
				lblUsersForVerification.setBounds(300, 223, 156, 28);
				add(lblUsersForVerification);
				
				comboBox = new JComboBox<String>();
				comboBox.setModel(new DefaultComboBoxModel<String>(DATA));
				comboBox.setFont(new Font("Kayak Sans", Font.BOLD, 16));
				comboBox.setBounds(500, 223, 266, 30);
				add(comboBox);
				CourseName=DATA[0];
				comboBox.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						CourseName=(String)comboBox.getSelectedItem();				
					}
				});
				
				JButton btnNewButton = new JButton("BACK");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						MainFrame.AddPanel(new MainAdmin());
					}
				});
				btnNewButton.setForeground(new Color(0, 0, 140));
				btnNewButton.setBackground(new Color(215, 215, 255));
				btnNewButton.setFont(new Font("Kayak Sans", Font.BOLD, 18));
				btnNewButton.setBounds(10, 600, 89, 50);
				add(btnNewButton);
				
				JLabel lblOnlineExamination = new JLabel("COURSES");
				lblOnlineExamination.setForeground(new Color(0,0,140));
				lblOnlineExamination.setBackground(Color.GREEN);
				lblOnlineExamination.setHorizontalAlignment(SwingConstants.CENTER);
				lblOnlineExamination.setFont(new Font("Kayak Sans", Font.BOLD, 41));
				lblOnlineExamination.setBounds(400, 28, 378, 82);
				add(lblOnlineExamination);
				
				JLabel lblNewLabel = new JLabel("");
				lblNewLabel.setIcon(new ImageIcon("assets\\\\image.jpg"));
				lblNewLabel.setBorder(new LineBorder(new Color(215,215,255), 4));
				lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
				lblNewLabel.setBounds(0, 0, 1920, 1080);
				add(lblNewLabel);
			}

}