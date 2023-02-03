package main;

import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import login.LoginPanel;

public class Panel1 extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Panel1() {
		setLayout(null);
		
		JButton btnNext = new JButton("NEXT");
		btnNext.setFocusable(false);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new LoginPanel());
			}
		});
		btnNext.setForeground(new Color(0,0,140));
		btnNext.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnNext.setBackground(new Color(215,215,255));
		btnNext.setBounds(1170, 600, 100, 50);
		add(btnNext);
		
		JButton btnNewButton = new JButton("HELP");
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new Help());
			}
		});
		btnNewButton.setForeground(new Color(0,0,140));
		btnNewButton.setBackground(new Color(215,215,255));
		btnNewButton.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnNewButton.setBounds(10, 600, 89, 50);
		add(btnNewButton);
		
		JLabel lblOnlineExamination = new JLabel("Virtuoso SoftTech");
		lblOnlineExamination.setForeground(new Color(0,0,140));
		lblOnlineExamination.setBackground(Color.GREEN);
		lblOnlineExamination.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlineExamination.setFont(new Font("Kayak Sans", Font.BOLD, 50));
		lblOnlineExamination.setBounds(300, 250, 703, 150);
		add(lblOnlineExamination);
		
		JLabel lblSystem = new JLabel("Exam Portal");
		lblSystem.setForeground(new Color(0,0,140));
		lblSystem.setBackground(new Color(215, 215, 255));
		lblSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSystem.setFont(new Font("Kayak Sans", Font.BOLD, 50));
		lblSystem.setBounds(300, 370, 703, 150);
		add(lblSystem);
		
	    JLabel lblNewLabel1 = new JLabel("");
	    lblNewLabel1.setIcon(new ImageIcon("assets\\logo-web.jpg"));
		lblNewLabel1.setBorder(new LineBorder(new Color(255, 255, 255), 4));
		lblNewLabel1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel1.setBounds(0, 5, 500, 150);
		add(lblNewLabel1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("assets\\image.jpg"));
		lblNewLabel.setBorder(new LineBorder(new Color(215, 215, 255), 4));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		add(lblNewLabel);
	
	}
}
