//직원 로그인 화면

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class loginExEmplyee {
	private MailCompany mailCompany;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void Newscreen3() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginExEmplyee window = new loginExEmplyee();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public loginExEmplyee() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mailCompany = new MailCompany();
		
		frame = new JFrame();
		frame.setTitle("직원 로그인하기");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel IDLabel = new JLabel("      ID");
		IDLabel.setBounds(50, 100, 60, 20);
		frame.getContentPane().add(IDLabel);
		
		JTextField IDtextField = new JTextField();
		IDtextField.setBounds(140, 100, 200, 20);
		frame.getContentPane().add(IDtextField);
		IDtextField.setColumns(10);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//직원 로그인 
				String text = IDtextField.getText();
				mailCompany.EmployeeLogin(text);
				
				loginExSeller nw6 = new loginExSeller();
				nw6.Newscreen6();
			}
		});
		LoginButton.setBounds(160, 170, 100, 27);
		frame.getContentPane().add(LoginButton);
	}
	}


