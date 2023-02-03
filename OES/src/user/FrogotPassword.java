package user;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import main.MainFrame;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class FrogotPassword extends JPanel implements FocusListener{
	private static final long serialVersionUID = 1L;
	private JTextField txtUsername;
	JLabel lblNewLabel_1;
	/**
	 * Create the panel.
	 */
	public FrogotPassword() {

		setLayout(null);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.AddPanel(new UserLogin());
			}
		});
		
		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setFont(new Font("Kayak Sans", Font.PLAIN, 32));
		lblNewLabel_1.setForeground(new Color(20, 112, 185));
		lblNewLabel_1.setBounds(500, 423, 557, 62);
		add(lblNewLabel_1);
		btnNewButton.setForeground(new Color(255,255,255));
		btnNewButton.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnNewButton.setBackground(new Color(20, 112, 185));
		btnNewButton.setBounds(10, 600, 89, 50);
		add(btnNewButton);
		
		JButton btnGetYourPassword = new JButton("GET YOUR PASSWORD");
		btnGetYourPassword.setToolTipText("GET YOUR PASSWORD");
		btnGetYourPassword.setFont(new Font("Kayak Sans", Font.PLAIN, 12));
		btnGetYourPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtUsername.getForeground()!=SystemColor.controlShadow)
				{
					String Pass=UserLogin.exixtsUsername(txtUsername.getText());
					if(Pass!=null)
					{
						lblNewLabel_1.setText("Your Password : "+Pass);
						System.out.println("Password : "+Pass);
					}
					else
					{
						lblNewLabel_1.setText("You are not registered user.");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Enter username");
				}
			}
		});
		btnGetYourPassword.setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(20, 112, 185)));
		btnGetYourPassword.setForeground(new Color(20, 112, 185));
		btnGetYourPassword.setFont(new Font("Kayak Sans", Font.BOLD, 10));
		btnGetYourPassword.setBounds(600, 377, 133, 29);
		add(btnGetYourPassword);
		
		txtUsername = new JTextField();
		txtUsername.setForeground(SystemColor.controlShadow);
		txtUsername.setSelectionColor(new Color(153, 51, 153));
		txtUsername.setSelectedTextColor(new Color(102, 204, 255));
		txtUsername.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtUsername.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtUsername.setText("USERNAME");
		txtUsername.setToolTipText("USERNAME");
		txtUsername.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		
		txtUsername.setBackground(new Color(255, 255, 255));
		txtUsername.setBounds(500, 300, 350, 50);
		
		add(txtUsername);
		txtUsername.addFocusListener(this);
		
		btnNewButton.setForeground(new Color(255,255,255));
		btnNewButton.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnNewButton.setBackground(new Color(20, 112, 185));
		btnNewButton.setBounds(10, 600, 89, 50);
		add(btnNewButton);
		
		JLabel lblOnlineExamination = new JLabel("FORGOT PASSWORD");
		lblOnlineExamination.setForeground(new Color(20, 112, 185));
		lblOnlineExamination.setBackground(Color.GREEN);
		lblOnlineExamination.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlineExamination.setFont(new Font("Kayak Sans", Font.BOLD, 41));
		lblOnlineExamination.setBounds(425, 46, 500, 200);
		add(lblOnlineExamination);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("assets\\image.jpg"));
		lblNewLabel.setBorder(new LineBorder(new Color(20, 112, 185), 4));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		add(lblNewLabel);
	}
	public void focusGained(FocusEvent arg0) {
		if(arg0.getSource()==txtUsername && txtUsername.getForeground()==SystemColor.controlShadow)
		{
			txtUsername.setText("");
			txtUsername.setForeground(Color.black);
			txtUsername.setFont(new Font("Kayak Sans", Font.BOLD, 24));
		}
		
	}
	public void focusLost(FocusEvent arg0) {
		if(txtUsername.getText().equals(""))
		{
			txtUsername.setForeground(SystemColor.controlShadow);
			txtUsername.setText("USERNAME");
			txtUsername.setFont(new Font("Kayak Sans", Font.PLAIN, 18));	
		}
	}
}