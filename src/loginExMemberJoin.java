//회원가입 화면

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class loginExMemberJoin {

	private JFrame frame;
	private JTextField CNotextField;
	private JTextField CNametextField;
	private JTextField CZiptextField;
	
	private MailClient mailClient;

	/**
	 * Launch the application.
	 */
	public static void Newscreen2() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginExMemberJoin window = new loginExMemberJoin();
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
	public loginExMemberJoin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mailClient = new MailClient(); 		//회원가입을 위한 클래스변수 생성.
		
		frame = new JFrame();
		frame.setTitle("회원가입하기");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel CNoLabel = new JLabel("고객 번호");
		CNoLabel.setBounds(60, 40, 62, 24);
		frame.getContentPane().add(CNoLabel);
		
		JLabel CNameLabel = new JLabel("   이름");
		CNameLabel.setBounds(60, 90, 62, 24);
		frame.getContentPane().add(CNameLabel);
		
		JLabel CZipLabel = new JLabel("   zipcode");
		CZipLabel.setBounds(60, 140, 62, 24);
		frame.getContentPane().add(CZipLabel);
		
		CNotextField = new JTextField();
		CNotextField.setBounds(170, 40, 200, 24);
		frame.getContentPane().add(CNotextField);
		CNotextField.setColumns(10);
		
		CNametextField = new JTextField();
		CNametextField.setBounds(170, 90, 200, 24);
		frame.getContentPane().add(CNametextField);
		CNametextField.setColumns(10);
		
		CZiptextField = new JTextField();
		CZiptextField.setBounds(170, 140, 200, 24);
		frame.getContentPane().add(CZiptextField);
		CZiptextField.setColumns(10);
		
		JButton btnNewButton = new JButton("회원가입");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//회원가입 화면으로 이동
				String client = CNotextField.getText();
				String name = CNametextField.getText();
				String zipcode = CZiptextField.getText();
				
				mailClient.ClientInsert(client, name, zipcode);
				
				loginExNext nw5 = new loginExNext();
				nw5.Newscreen();
			}
		});
		btnNewButton.setBounds(150, 200, 105, 27);
		frame.getContentPane().add(btnNewButton);
	}

}
