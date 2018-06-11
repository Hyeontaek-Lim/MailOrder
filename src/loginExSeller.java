//직원 주문 확인 화면

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
		Sellerframe.setTitle("주문 확인");
		Sellerframe.setBounds(100, 100, 1000, 600);
		Sellerframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Sellerframe.getContentPane().setLayout(null);
		
		
		OrdertextPane = new JTextPane();
		OrdertextPane.setText(orderInfo);	//모든 주문정보 띄우기
		OrdertextPane.setBounds(14, 12, 830, 480);
		Sellerframe.getContentPane().add(OrdertextPane);
		
		JLabel SNoLabel = new JLabel("주문 번호");
		SNoLabel.setBounds(14, 520, 62, 18);
		Sellerframe.getContentPane().add(SNoLabel);
		
		SNotextField = new JTextField();
		SNotextField.setBounds(90, 517, 184, 24);
		Sellerframe.getContentPane().add(SNotextField);
		SNotextField.setColumns(10);
		
		JButton CheckButton = new JButton("확인");
		CheckButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int orderNum = Integer.parseInt(SNotextField.getText());
				/* 주문번호를 저장. */
				//직원 주문 승인 화면으로 이동
				loginExSellerCheck nw7 = new loginExSellerCheck(orderNum);
				nw7.Newscreen7(orderNum);
			}
		});
		CheckButton.setBounds(860, 516, 105, 27);
		Sellerframe.getContentPane().add(CheckButton);
		
		
		JButton NewButton = new JButton("새로고침");
		NewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//쿼리가 업데이트 된 것 처럼 보이게 함.
				String refresh = mailOrder.ShowAllOrderInfo();
				OrdertextPane.setText(refresh);
			}
		});
		NewButton.setBounds(860, 12, 105, 27);
		Sellerframe.getContentPane().add(NewButton);
	}
}
