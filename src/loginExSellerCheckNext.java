//���� �ֹ� ���� Ȯ��

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextPane;

public class loginExSellerCheckNext {

	private JFrame SCNextframe;

	/**
	 * Launch the application.
	 */
	public static void Newscreen9() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginExSellerCheckNext window = new loginExSellerCheckNext();
					window.SCNextframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public loginExSellerCheckNext() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		SCNextframe = new JFrame();
		SCNextframe.setTitle("�ֹ� ���� Ȯ��");
		SCNextframe.setBounds(100, 100, 1000, 600);
		SCNextframe.getContentPane().setLayout(null);
		
		JTextPane SCNexttextPane = new JTextPane();
		SCNexttextPane.setText("������ �Ϸ�Ǿ����ϴ�.");
		SCNexttextPane.setBounds(14, 12, 954, 479);
		SCNextframe.getContentPane().add(SCNexttextPane);
		
	}

}
