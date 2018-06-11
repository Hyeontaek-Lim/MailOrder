//ȸ�� �ֹ� ȭ��
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class loginExCosumer {

	private JFrame Sframe;
	private JTextField TNametextField;
	private JTextField NumbertextField;
	
	private MailParts mailParts;
	private MailOrder mailOrder;
	
	JTextPane StextPane;

	/**
	 * Launch the application.
	 */
	public static void Newscreen4() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginExCosumer window = new loginExCosumer();
					window.Sframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public loginExCosumer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mailOrder = new MailOrder();
		
		mailParts = new MailParts();
		String partsInfo = mailParts.ShowAllPartsInfo();
		
		Sframe = new JFrame();
		Sframe.setTitle("ȸ�� �ֹ�");
		Sframe.setBounds(100, 100, 1000, 600);
		Sframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Sframe.getContentPane().setLayout(null);
		
		StextPane = new JTextPane();
		StextPane.setText(partsInfo); 		//�ֹ����� ���� ���
		StextPane.setBounds(14, 12, 850, 470);
		Sframe.getContentPane().add(StextPane);
		
		JLabel TNameLabel = new JLabel("��ǰ��");
		TNameLabel.setBounds(32, 507, 62, 18);
		Sframe.getContentPane().add(TNameLabel);
		
		JLabel NumberLabel = new JLabel("����");
		NumberLabel.setBounds(335, 507, 62, 18);
		Sframe.getContentPane().add(NumberLabel);
		
		TNametextField = new JTextField(); // ��ǰ�� �Է�â
		TNametextField.setBounds(108, 504, 180, 24);
		Sframe.getContentPane().add(TNametextField);
		TNametextField.setColumns(10);
		
		NumbertextField = new JTextField(); //���� �Է�â
		NumbertextField.setBounds(410, 504, 180, 24);
		Sframe.getContentPane().add(NumbertextField);
		NumbertextField.setColumns(10);
		
		JButton PurButton = new JButton("����");
		PurButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				loginExCosumerNext nw8 = new loginExCosumerNext();
				nw8.Newscreen8();
				
				String parts_name = TNametextField.getText();
				int numOfParts = Integer.parseInt(NumbertextField.getText());
				
				/* ��ǰ�̸��� ��ǰ ���� ���ؼ� �ֹ������ͺ��̽��� ���� */
				mailOrder.Order(parts_name, numOfParts);
				
				String refresh = mailParts.ShowAllPartsInfo();
				StextPane.setText(refresh);
			}
		});
		PurButton.setBounds(870, 500, 105, 27);
		Sframe.getContentPane().add(PurButton);
		
		JButton BilButton = new JButton("�ֹ� ����");
		BilButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginExCosumerNext nw8 = new loginExCosumerNext();
				nw8.Newscreen8();
			}
		});
		BilButton.setBounds(750, 500, 105, 27);
		Sframe.getContentPane().add(BilButton);
	
		JButton NewButton = new JButton("���ΰ�ħ");
		NewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* ���ΰ�ġ�� ȿ�� */
				/* �ֹ��� �Ϸ�Ǳ� �������� ��� �������� �ʴ´�. */
				String refresh = mailParts.ShowAllPartsInfo();
				StextPane.setText(refresh);
				
			}
		});
		NewButton.setBounds(870, 12, 105, 27);
		Sframe.getContentPane().add(NewButton);
	}

}
