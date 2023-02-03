package admin;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


public class LoginAttempts extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Integer> updateAttempts;
	private static int eachattempt;
	private static int selectedattempt=eachattempt;
	
	
	public LoginAttempts(String username) {
		setLayout(null);
		JLabel lblOnlineExamination = new JLabel("EDIT ATTEMPTS");
	lblOnlineExamination.setForeground(new Color(20, 112, 185));
	lblOnlineExamination.setBackground(new Color(20, 112, 185));
	lblOnlineExamination.setHorizontalAlignment(SwingConstants.CENTER);
	lblOnlineExamination.setFont(new Font("Kayak Sans", Font.BOLD, 41));
	lblOnlineExamination.setBounds(425, 46, 450, 150);
	add(lblOnlineExamination);
	
	
	
	
	updateAttempts = new JComboBox<Integer>();
	updateAttempts.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}));
	updateAttempts.setFont(new Font("Kayak Sans", Font.BOLD, 19));
	updateAttempts.setBounds(500, 223, 266, 30);
	updateAttempts.setSelectedIndex(eachattempt-1);
	add(updateAttempts);
	updateAttempts.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent arg0) {
			selectedattempt=(Integer)updateAttempts.getSelectedItem();
			System.out.println(selectedattempt);
		}
	});
	
	
	JButton btnsave = new JButton("SAVE");
	btnsave.setToolTipText("SAVE");
	btnsave.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				Connect c=new Connect("root","root");
				Statement stmt = c.con.createStatement();
				String query = "update userdetails set LoginAttempts='"+selectedattempt+"' where username='"+username+"'";
				stmt.executeUpdate(query);
			}
			catch(Exception e) {
				System.out.println(e);
			}
			
			MainFrame.AddPanel(new UsersData());
		}
		
	});
	btnsave.setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(20, 112, 185)));
	btnsave.setForeground(new Color(20, 112, 185));
	btnsave.setFont(new Font("Kayak Sans", Font.BOLD, 18));
	btnsave.setBounds(800, 223, 125, 36);
	add(btnsave);
	
	JButton btnNewButton = new JButton("BACK");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			MainFrame.AddPanel(new UsersData());
		}
	});
	btnNewButton.setForeground(new Color(255,255,255));
	btnNewButton.setBackground(new Color(20, 112, 185));
	btnNewButton.setFont(new Font("Kayak Sans", Font.BOLD, 18));
	btnNewButton.setBounds(10, 600, 89, 50);
	add(btnNewButton);
	
	JLabel lblNewLabel = new JLabel("");
	lblNewLabel.setBorder(new LineBorder(new Color(20, 112, 185), 4));
	lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
	lblNewLabel.setIcon(new ImageIcon("assets\\image.jpg"));
	lblNewLabel.setBounds(0, 0, 1920, 1080);
	add(lblNewLabel);
	
	
}
}
	
