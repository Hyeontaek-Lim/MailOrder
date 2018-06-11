//���� �ֹ� ����

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
	private int orderNum; 		//���� ȭ�鿡�� �Ѿ�� �ֹ���ȣ��
	private MailOrder mailOrder; 		//�ֹ����� �ҷ����µ� ����
	
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
		this.orderNum = orderNum; //����ȭ�鿡�� �Ѿ�� ID ���� ����.
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mailOrder = new MailOrder();
		String order = mailOrder.OrderInfo(orderNum); 	//Ư�� �ֹ���ȣ�� ���� ����
		//String OrderInfo()
		
		SCheckframe = new JFrame();
		SCheckframe.setTitle("���� Ȯ��");
		SCheckframe.setBounds(500, 400, 1000, 600);
		SCheckframe.getContentPane().setLayout(null);
		
		JPanel SCpanel = new JPanel();
		SCpanel.setBounds(0, 0, 980, 512);
		SCheckframe.getContentPane().add(SCpanel);
		SCpanel.setLayout(null);
		
		ImptextPane = new JTextPane();
		ImptextPane.setText(order); 		//�ֹ����� ����
		ImptextPane.setBounds(14, 12, 840, 490);
		SCpanel.add(ImptextPane);
		
		JButton NewButton = new JButton("���ΰ�ħ");
		NewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* ���ΰ�ġ�� ȿ�� */
				String refresh = mailOrder.OrderInfo(orderNum);
				ImptextPane.setText(refresh);
			}
		});
		NewButton.setBounds(865, 12, 105, 27);
		SCpanel.add(NewButton);
		
		JLabel DayLabel = new JLabel("���� �����");
		DayLabel.setBounds(14, 524, 92, 18);
		SCheckframe.getContentPane().add(DayLabel);
		
		//������ �Է�
		DaytextField = new JTextField();
		DaytextField.setBounds(120, 521, 150, 24);
		SCheckframe.getContentPane().add(DaytextField);
		DaytextField.setColumns(10);
		
		JButton CancleButton = new JButton("�ֹ�����");
		CancleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//������ �ֹ��� �������� Ȯ���Ѵ�.(����)
				mailOrder.OrderDelete(orderNum);
				loginExSellerCheckNext nw9 = new loginExSellerCheckNext();
				nw9.Newscreen9();
				
				/* ���ΰ�ġ�� ȿ�� */
				String refresh = mailOrder.OrderInfo(orderNum);
				ImptextPane.setText(refresh);
			}
		});
		CancleButton.setBounds(747, 515, 105, 27);
		SCheckframe.getContentPane().add(CancleButton);
		
		JButton OkButton = new JButton("����");
		OkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* �������� �����Ѵ�. */
				String deliveryDate = DaytextField.getText();
				mailOrder.OrderConfirm(orderNum, deliveryDate);
				
				/* ���ΰ�ġ�� ȿ�� */
				String refresh = mailOrder.OrderInfo(orderNum);
				ImptextPane.setText(refresh);
			}
		});
		OkButton.setBounds(866, 515, 105, 27);
		SCheckframe.getContentPane().add(OkButton);
	}
}
