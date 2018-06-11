//로그인 화면
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class loginExNext {

	private JFrame frame;
	private JTextField IDtextField;
	public String text;
	
	MailClient mailClient;

	/**
	 * Launch the application.
	 */
	public static void Newscreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginExNext window = new loginExNext();
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
	public loginExNext() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		mailClient = new MailClient();
		
		frame = new JFrame();
		frame.setTitle("로그인하기");
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		
		JLabel IDLabel = new JLabel("      ID");
		IDLabel.setBounds(50, 100, 60, 20);
		frame.getContentPane().add(IDLabel);
		
		IDtextField = new JTextField();
		IDtextField.setBounds(140, 100, 200, 20);
		frame.getContentPane().add(IDtextField);
		IDtextField.setColumns(10);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//회원 로그인 
				String text = IDtextField.getText();
				mailClient.MemeberLogin(text);
				
				loginExCosumer nw4 = new loginExCosumer();
				nw4.Newscreen4();
			}
		});
		
		System.out.println(text);
		
		LoginButton.setBounds(160, 170, 100, 27);
		frame.getContentPane().add(LoginButton);
	}

}
