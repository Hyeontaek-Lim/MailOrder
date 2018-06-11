//���� �ֹ� Ȯ�� ȭ��

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class loginExSeller {

	private JFrame Sellerframe;
	private JTextField SNotextField;
	private MailOrder mailOrder;

	
	JTextPane OrdertextPane;
	/**
	 * Launch the application.
	 */
	public static void Newscreen6() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginExSeller window = new loginExSeller();
					window.Sellerframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public loginExSeller() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mailOrder = new MailOrder();
		String orderInfo = mailOrder.ShowAllOrderInfo();
		
		Sellerframe = new JFrame();
		Sellerframe.setTitle("�ֹ� Ȯ��");
		Sellerframe.setBounds(100, 100, 1000, 600);
		Sellerframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Sellerframe.getContentPane().setLayout(null);
		
		
		OrdertextPane = new JTextPane();
		OrdertextPane.setText(orderInfo);	//��� �ֹ����� ����
		OrdertextPane.setBounds(14, 12, 830, 480);
		Sellerframe.getContentPane().add(OrdertextPane);
		
		JLabel SNoLabel = new JLabel("�ֹ� ��ȣ");
		SNoLabel.setBounds(14, 520, 62, 18);
		Sellerframe.getContentPane().add(SNoLabel);
		
		SNotextField = new JTextField();
		SNotextField.setBounds(90, 517, 184, 24);
		Sellerframe.getContentPane().add(SNotextField);
		SNotextField.setColumns(10);
		
		JButton CheckButton = new JButton("Ȯ��");
		CheckButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int orderNum = Integer.parseInt(SNotextField.getText());
				/* �ֹ���ȣ�� ����. */
				//���� �ֹ� ���� ȭ������ �̵�
				loginExSellerCheck nw7 = new loginExSellerCheck(orderNum);
				nw7.Newscreen7(orderNum);
			}
		});
		CheckButton.setBounds(860, 516, 105, 27);
		Sellerframe.getContentPane().add(CheckButton);
		
		
		JButton NewButton = new JButton("���ΰ�ħ");
		NewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//������ ������Ʈ �� �� ó�� ���̰� ��.
				String refresh = mailOrder.ShowAllOrderInfo();
				OrdertextPane.setText(refresh);
			}
		});
		NewButton.setBounds(860, 12, 105, 27);
		Sellerframe.getContentPane().add(NewButton);
	}
}
