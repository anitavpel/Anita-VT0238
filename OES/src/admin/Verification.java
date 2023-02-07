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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import database.Connect;
import database.UserDetails;
import main.MainFrame;

public class Verification extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private JComboBox<String> comboBox;
	private String Username;
	private String[] verify;
	private static boolean enteredInGUI=false;
	
	private Connect c=new Connect("root","root");
	
	private void databaseVerify()
	{
		try{
			Statement st=c.con.createStatement();
			String query="select count(username) from userdetails where verify=0";
			java.sql.ResultSet rs=st.executeQuery(query);
			rs.next();
			int i=rs.getInt(1);
			verify=new String[i];
			i=0;
			query="select username from userdetails where verify=0";
			rs=st.executeQuery(query);
			while(rs.next())
				verify[i++]=rs.getString("username");
			for(String s:verify)
				System.out.println(s);
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}

	}
	
	private void databseExecuteQuery(String query)
	{
		try{
			Statement st=c.con.createStatement();
			st.execute(query);
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
	
	public Verification()
	{
		databaseVerify();
		if(verify.length>0)
		{
			makeGUI();
			enteredInGUI=true;
			MainFrame.AddPanel(this);
		}
		else
		{
			//No records for verification
			if(enteredInGUI)
				MainFrame.AddPanel(new MainAdmin());
			JOptionPane.showMessageDialog(null, "All current users are verified.");
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
				new UserDetails(Username,true,"Verification");
			}
		});
		btnSearch.setForeground(new Color(255,255,255));
		btnSearch.setBackground(new Color(20,112,185));
		btnSearch.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnSearch.setBounds(850, 223, 125, 36);
		add(btnSearch);
		
		JButton btnVerify = new JButton("VERIFY");
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//make the value of verify coloum=1 of userdetails table of database OES 
				databseExecuteQuery("UPDATE userdetails SET verify=1 where username='"+Username+"'");
				
				//after that make verifiacation GUI again 
				new Verification();
			}
		});
		btnVerify.setForeground(new Color(255,255,255));
		btnVerify.setBackground(new Color(20, 112, 185));
		btnVerify.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnVerify.setBounds(780, 364, 125, 36);
		add(btnVerify);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//delete that record from database
				databseExecuteQuery("DELETE FROM userdetails where username='"+Username+"'");
				//after that make verification GUI again.
				new Verification();
			}
		});
		btnDelete.setForeground(new Color(255,255,255));
		btnDelete.setBackground(new Color(20, 112, 185));
		btnDelete.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnDelete.setBounds(400, 364, 125, 36);
		add(btnDelete);
		JLabel lblUsersForVerification = new JLabel("Users for verification");
		lblUsersForVerification.setForeground(new Color(20, 112, 185));
		lblUsersForVerification.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		lblUsersForVerification.setBounds(320, 223, 190, 28);
		add(lblUsersForVerification);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(verify));
		comboBox.setFont(new Font("Kayak Sans", Font.BOLD, 16));
		comboBox.setBounds(520, 223, 266, 30);
		add(comboBox);
		Username=verify[0];
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
		btnNewButton.setForeground(new Color(255,255,255));
		btnNewButton.setBackground(new Color(20, 112, 185));
		btnNewButton.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnNewButton.setBounds(10, 600, 89, 50);
		add(btnNewButton);
		
		JLabel lblOnlineExamination = new JLabel("VERIFICATION");
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
