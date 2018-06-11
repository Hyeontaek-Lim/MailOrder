//회원 주문 확인 화면

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextPane;

public class loginExCosumerNext {

	private JFrame CNextframe;
	
	private MailClient mailClient;

	/**
	 * Launch the application.
	 */
	public static void Newscreen8() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginExCosumerNext window = new loginExCosumerNext();
					window.CNextframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public loginExCosumerNext() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mailClient = new MailClient();
		String orderInfo = mailClient.getOrderCheck();
		
		CNextframe = new JFrame();
		CNextframe.setTitle("회원 주문 확인");
		CNextframe.setBounds(100, 100, 1000, 600);
		CNextframe.getContentPane().setLayout(null);
		
		JTextPane CNChecktextPane = new JTextPane();
		CNChecktextPane.setBounds(14, 12, 954, 482);
		
		CNChecktextPane.setText(orderInfo);
		
		CNextframe.getContentPane().add(CNChecktextPane);
		
	}
}
