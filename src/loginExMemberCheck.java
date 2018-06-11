//주문 확인

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class loginExMemberCheck {

	private JFrame Checkframe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginExMemberCheck window = new loginExMemberCheck();
					window.Checkframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public loginExMemberCheck() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Checkframe = new JFrame();
		Checkframe.setBounds(100, 100, 500, 400);
		Checkframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Checkframe.getContentPane().setLayout(null);
		
		JTextPane ChecktextPane = new JTextPane();
		ChecktextPane.setBounds(14, 12, 454, 290);
		Checkframe.getContentPane().add(ChecktextPane);
		
		JButton CheckButton = new JButton("확인");
		CheckButton.setBounds(363, 314, 105, 27);
		Checkframe.getContentPane().add(CheckButton);
	}

}
