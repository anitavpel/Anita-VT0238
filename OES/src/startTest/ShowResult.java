package startTest;

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
import database.Connect;
import user.UserPanel;
import main.MainFrame;
import main.Panel1;

import java.sql.SQLException;
import java.sql.Statement;

public class ShowResult extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private String Username;
	private String query;
	public ShowResult(String[] r,String username) {
		Username=username;
		setLayout(null);
		
		
		
		query="INSERT INTO result (username,course_name,time_taken,total_questions,wrong_question,"
				+ "attempted_question,obtained_marks,total_marks,percentage,test_date) VALUES('"
				+ Username+"','"+r[0]+"','"+r[1]+"',"+r[2]+","+r[5]+","+r[3]+","+r[6]+","+r[7]+","+r[8]+",current_timestamp)";
		
		Connect c=new Connect("root","root");
		try{
			Statement st=c.con.createStatement();
			System.out.println("sdsd");
			st.execute(query);
			JOptionPane.showMessageDialog(null, "Thank You.\nYour result is saved successfully.");
			MainFrame.AddPanel(new UserPanel(Username));
		}
		catch(SQLException e)
		{
			System.out.println("Error in saving result : "+e);
		}
		
		JButton Discard = new JButton("LOGOUT");
		Discard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new Panel1());
			}
		});
		Discard.setForeground(new Color(255,255,255));
		Discard.setBackground(new Color(20,112,185));
		Discard.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		Discard.setBounds(600, 550, 152, 50);
		add(Discard);
		
		JLabel lblOnlineExamination = new JLabel("Thank You, Your response has been saved!");
		lblOnlineExamination.setForeground(new Color(20, 112, 185));
		lblOnlineExamination.setBackground(Color.GREEN);
		lblOnlineExamination.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlineExamination.setFont(new Font("Kayak Sans", Font.BOLD, 41));
		lblOnlineExamination.setBounds(270, 200, 800, 100);
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
