package course;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import admin.MainAdmin;
import main.MainFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CoursePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public CourseDetails CD;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox1;
	private String SelectedCourse = "";

	private boolean deleteCourse(String courseName) {

		try {
			Statement st = CourseDetails.c.con.createStatement();
			String query = "DELETE FROM oes.course_details where course_name='"+ courseName + "'";			
			st.execute(query);
			query = "DELETE FROM questions where course_name='" + courseName + "'";
			st.execute(query);
			return true;
		} catch (SQLException e) {
			System.out.println("Course cannot be deleted. : " + e);
			return false;
		}
	}

	public CoursePanel() {
		setLayout(null);
		CD = new CourseDetails();

		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		comboBox.setModel(new DefaultComboBoxModel<String>(CD.Courses));
		comboBox.setBounds(375, 255, 206, 36);
		comboBox.setSelectedIndex(-1);
		add(comboBox);
//		SelectedCourse=CD.Courses[0];
		comboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				SelectedCourse = (String) comboBox.getSelectedItem();
			}
		});

		JButton btnViewCourse = new JButton("EDIT COURSE");
		btnViewCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// edit course and save changes in courses if any
				if (!SelectedCourse.equals(""))
					MainFrame.AddPanel(new EditCourse(SelectedCourse));
				else
					JOptionPane.showMessageDialog(null, "No course selected.\nSelect course first.");
			}
		});
		btnViewCourse.setForeground(new Color(20, 112, 185));
		btnViewCourse.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnViewCourse.setBounds(375, 400, 206, 36);
		add(btnViewCourse);

		
		comboBox1 = new JComboBox<String>();
		comboBox1.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		comboBox1.setModel(new DefaultComboBoxModel<String>(CD.Courses));
		comboBox1.setBounds(750, 255, 206, 36);
		comboBox1.setSelectedIndex(-1);
		add(comboBox1);
//		SelectedCourse=CD.Courses[0];
		comboBox1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				SelectedCourse = (String) comboBox1.getSelectedItem();
			}
		});

		
		JButton btnDeleteCourse = new JButton("DELETE COURSE");
		btnDeleteCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// delete selected course and refresh GUI

				if (!SelectedCourse.equals("")) {
					if (JOptionPane.showConfirmDialog(null,
							"Are you sure you want to delete course " + SelectedCourse) == 0) {
						if (deleteCourse(SelectedCourse)) {
							// course deleted successfully
							JOptionPane.showMessageDialog(null, SelectedCourse + " deleted successfully");
							MainFrame.AddPanel(new CoursePanel());
						} else {
							JOptionPane.showMessageDialog(null, SelectedCourse + " cannot be deleted.");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "No course Selected.\nSelect course first");
				}
			}
		});
		
		
		
		btnDeleteCourse.setForeground(new Color(20, 112, 185));
		btnDeleteCourse.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnDeleteCourse.setBounds(750,400, 206, 36);
		add(btnDeleteCourse);

		JButton btnAddNewCourse = new JButton("ADD NEW COURSE");
		btnAddNewCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// add new course to course_details table in database
				MainFrame.AddPanel(new AddNewCourse());
			}
		});
		btnAddNewCourse.setForeground(new Color(20, 112, 185));
		btnAddNewCourse.setFont(new Font("Kayak Sans", Font.BOLD, 18));
		btnAddNewCourse.setBounds(530, 520, 300, 50);
		add(btnAddNewCourse);

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

		
		
		
		JLabel lblOnlineExamination = new JLabel("MANAGE COURSES");
		lblOnlineExamination.setForeground(new Color(255,255,255));
		lblOnlineExamination.setBackground(Color.GREEN);
		lblOnlineExamination.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlineExamination.setFont(new Font("Kayak Sans", Font.BOLD, 41));
		lblOnlineExamination.setBounds(350, 30, 603, 105);
		add(lblOnlineExamination);
		
		
		JLabel lblNewLabel4 = new JLabel("");
		lblNewLabel4.setIcon(new ImageIcon("assets\\C.png"));
		lblNewLabel4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel4.setBounds(700,200, 300, 300);
		add(lblNewLabel4);
		
		
		JLabel lblNewLabel3 = new JLabel("");
		lblNewLabel3.setIcon(new ImageIcon("assets\\C.png"));
		lblNewLabel3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel3.setBounds(330,200, 300, 300);
		add(lblNewLabel3);
		
		JLabel lblNewLabel5 = new JLabel("");
		lblNewLabel5.setIcon(new ImageIcon("assets\\C.png"));
		lblNewLabel5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel5.setBounds(480,500, 400, 100);
		add(lblNewLabel5);
		

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
