package main;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.Rectangle;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel MainPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
		//frame.add(new Help());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				 
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setResizable(true);
		setTitle("Virtuoso SoftTech\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080);
		MainPanel = new JPanel();
		MainPanel.setBounds(new Rectangle(0, 0, 1920, 1080));
		MainPanel.setBorder(UIManager.getBorder("ComboBox.editorBorder"));
		setContentPane(MainPanel);
		MainPanel.setLayout(null);
		AddPanel(new Panel1());
		
	}
	public static void AddPanel(JPanel p)
	{
		
		MainFrame.RemoveAllPanel();
		p.setBounds(new Rectangle(0, 0, 1920, 1080));
		MainPanel.add(p);
	}
	public static void RemoveAllPanel()
	{
		MainPanel.removeAll();
		MainPanel.repaint();
		MainPanel.revalidate();
	}
	public static Rectangle Dimensions()
	{
		return MainPanel.getBounds();
	}

	
}