//직원 주문 승인

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class loginExSellerCheck {

	private JFrame SCheckframe;
	private JTextField DaytextField;
	private int orderNum; 		//이전 화면에서 넘어온 주문번호값
	private MailOrder mailOrder; 		//주문정보 불러오는데 쓰임
	
	JTextPane ImptextPane;

	/**
	 * Launch the application.
	 */
	public static void Newscreen7(int orderNum) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginExSellerCheck window = new loginExSellerCheck(orderNum);
					window.SCheckframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public loginExSellerCheck(int orderNum) {
		this.orderNum = orderNum; //이전화면에서 넘어온 ID 값을 저장.
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mailOrder = new MailOrder();
		String order = mailOrder.OrderInfo(orderNum); 	//특정 주문번호에 대한 정보
		//String OrderInfo()
		
		SCheckframe = new JFrame();
		SCheckframe.setTitle("직원 확인");
		SCheckframe.setBounds(500, 400, 1000, 600);
		SCheckframe.getContentPane().setLayout(null);
		
		JPanel SCpanel = new JPanel();
		SCpanel.setBounds(0, 0, 980, 512);
		SCheckframe.getContentPane().add(SCpanel);
		SCpanel.setLayout(null);
		
		ImptextPane = new JTextPane();
		ImptextPane.setText(order); 		//주문정보 띄우기
		ImptextPane.setBounds(14, 12, 840, 490);
		SCpanel.add(ImptextPane);
		
		JButton NewButton = new JButton("새로고침");
		NewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* 새로고치는 효과 */
				String refresh = mailOrder.OrderInfo(orderNum);
				ImptextPane.setText(refresh);
			}
		});
		NewButton.setBounds(865, 12, 105, 27);
		SCpanel.add(NewButton);
		
		JLabel DayLabel = new JLabel("예상 배송일");
		DayLabel.setBounds(14, 524, 92, 18);
		SCheckframe.getContentPane().add(DayLabel);
		
		//결제일 입력
		DaytextField = new JTextField();
		DaytextField.setBounds(120, 521, 150, 24);
		SCheckframe.getContentPane().add(DaytextField);
		DaytextField.setColumns(10);
		
		JButton CancleButton = new JButton("주문삭제");
		CancleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//직원이 주문을 최종으로 확인한다.(삭제)
				mailOrder.OrderDelete(orderNum);
				loginExSellerCheckNext nw9 = new loginExSellerCheckNext();
				nw9.Newscreen9();
				
				/* 새로고치는 효과 */
				String refresh = mailOrder.OrderInfo(orderNum);
				ImptextPane.setText(refresh);
			}
		});
		CancleButton.setBounds(747, 515, 105, 27);
		SCheckframe.getContentPane().add(CancleButton);
		
		JButton OkButton = new JButton("승인");
		OkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* 결제일을 변경한다. */
				String deliveryDate = DaytextField.getText();
				mailOrder.OrderConfirm(orderNum, deliveryDate);
				
				/* 새로고치는 효과 */
				String refresh = mailOrder.OrderInfo(orderNum);
				ImptextPane.setText(refresh);
			}
		});
		OkButton.setBounds(866, 515, 105, 27);
		SCheckframe.getContentPane().add(OkButton);
	}
}
