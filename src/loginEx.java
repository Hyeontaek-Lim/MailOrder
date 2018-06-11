//로그인

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class loginEx {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MySQL.SQLConnect();				//MySQL 과 프로그램을 연결.
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginEx window = new loginEx();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		loginExNext ex = new loginExNext();
		System.out.println(ex.text);
	}

	/**
	 * Create the application.
	 */
	public loginEx() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Login");
		frame.setBackground(Color.WHITE);
		frame.setSize(200,120);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnGuestLogin = new JButton("Guest Login");
		btnGuestLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			//Guest Login 화면으로 이동	
			loginExNext nw = new loginExNext();
			nw.Newscreen();
				
			}
		});
		btnGuestLogin.setBounds(133, 47, 149, 30);
		frame.getContentPane().add(btnGuestLogin);
		
		JButton btnJoinButton = new JButton("회원가입");
		btnJoinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//회원가입 화면으로 이동
				loginExMemberJoin nw2 = new loginExMemberJoin();
				nw2.Newscreen2();
				
			}
		});
		btnJoinButton.setBounds(133, 165, 149, 30);
		frame.getContentPane().add(btnJoinButton);
		
		JButton btnEmpButton_1 = new JButton("employee Login\r\n");
		btnEmpButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//employee login 화면으로 이동
				loginExEmplyee nw3 = new loginExEmplyee();
				nw3.Newscreen3();
				
			}
		});
		btnEmpButton_1.setBounds(133, 108, 149, 27);
		frame.getContentPane().add(btnEmpButton_1);
	}
}
