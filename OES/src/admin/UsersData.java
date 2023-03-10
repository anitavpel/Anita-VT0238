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
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import database.Connect;
import database.UserDetails;
import main.MainFrame;

public class UsersData extends JPanel {


	private static final long serialVersionUID = 1L;
	private static Connect c=new Connect("root","root");
	private JComboBox<String> comboBox;
	private String Username;
	private String[] DATA;
	/**
	 * Create the panel.
	 */
	public UsersData() {
		databaseVerify();
		makeGUI();
	}
	private void databaseVerify()
	{
		try{
			Statement st=c.con.createStatement();
			String query="select count(username) from userdetails";
			java.sql.ResultSet rs=st.executeQuery(query);
			rs.next();
			int i=rs.getInt(1);
			DATA=new String[i];
			i=0;
			query="select username from userdetails";
			rs=st.executeQuery(query);
			while(rs.next())
				DATA[i++]=rs.getString("username");
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
					new UserDetails(Username,true,"UsersData");
				}
			});
			btnSearch.setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(20, 112, 185)));
			btnSearch.setForeground(new Color(20, 112, 185));
			btnSearch.setFont(new Font("Kayak Sans", Font.BOLD, 18));
			btnSearch.setBounds(800, 223, 125, 36);
			add(btnSearch);
			
			
			
			JLabel lblUsersForVerification = new JLabel("Available Users");
			lblUsersForVerification.setForeground(new Color(20, 112, 185));
			lblUsersForVerification.setFont(new Font("Kayak Sans", Font.BOLD, 18));
			lblUsersForVerification.setBounds(300, 223, 156, 28);
			add(lblUsersForVerification);
			
			comboBox = new JComboBox<String>();
			comboBox.setModel(new DefaultComboBoxModel<String>(DATA));
			comboBox.setFont(new Font("Kayak Sans", Font.BOLD, 16));
			comboBox.setBounds(500, 223, 266, 30);
			add(comboBox);
			Username=DATA[0];
			comboBox.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					Username=(String)comboBox.getSelectedItem();				
				}
			});
			
			JButton btnNewButton = new JButton("BACK");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainFrame.AddPanel(new MainAdmin());
				}
			});
			btnNewButton.setForeground(new Color(20, 112, 185));
			btnNewButton.setBackground(UIManager.getColor("Button.disabledForeground"));
			btnNewButton.setFont(new Font("Kayak Sans", Font.BOLD, 18));
			btnNewButton.setBounds(10, 600, 89, 50);
			add(btnNewButton);
			
			JLabel lblOnlineExamination = new JLabel("USERS");
			lblOnlineExamination.setForeground(new Color(20, 112, 185));
			lblOnlineExamination.setBackground(Color.GREEN);
			lblOnlineExamination.setHorizontalAlignment(SwingConstants.CENTER);
			lblOnlineExamination.setFont(new Font("Kayak Sans", Font.BOLD, 41));
			lblOnlineExamination.setBounds(400, 28, 378, 82);
			add(lblOnlineExamination);
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon("assets\\image.jpg"));
			lblNewLabel.setBorder(new LineBorder(new Color(20, 112, 185), 4));
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel.setBounds(0, 0, 1920, 1080);
			add(lblNewLabel);
		}
}
