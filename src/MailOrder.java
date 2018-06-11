import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MailOrder {
	private ResultSet rs;
	int order = 0; 		//���Ƿ� ������ �ֹ���ȣ�̴�. �ֹ���ȣ�� primary key�� �ߺ��� �� ����.
	
	/* �ֹ���ȣ�� �����Ѵ�.
	 * �Ϲ������� �ֹ���ȣ�� �ڵ����� �����Ǳ� ������
	 * */
	void setOrderNumber() {
		try {
			rs = MySQL.stmt.executeQuery("SELECT * FROM ORDER_INFO;");
			//�ֹ������� ��� ������ �˻��Ѵ�.
			
			int orderNum=0; 	// 0���� �ʱ�ȭ 
			
			while (rs.next()) { 		//���� ������ �ֹ���ȣ�� ã�Ƽ�
				orderNum = rs.getInt("ORDER_NUMBER");
			}
			this.order += orderNum+1; 	//���� ������ �ֹ���ȣ+1�� �ֹ���ȣ�� �����Ѵ�.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* �ֹ� ���� �ϱ� ���� �ٸ� Ŭ�������� ��ü ���� �� �ֹ���ȣ�� �������ش�.*/
	public MailOrder() {
		setOrderNumber();
	}

	/* �����͸� �����Ѵ�. */
	/* ����� ��ǰ�̸�, ����, ������, ������� �����ϰ� �ֹ���ȣ�� �ڵ����� �����ȴ�. */
	void OrderInsert(String client, String parts, int quantity, String payment_date, String delivery_date) {
		try {
			MySQL.stmt.executeUpdate("insert into ORDER_INFO value("+ "'" + client + "', " + order + ", " 
					+ "'" + parts + "', " + quantity + ", "
					+ "'" + payment_date + "', " + "'" + delivery_date + "'" + ");");
			order++;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Insert error !");
			e.printStackTrace();
		}
	}

	/* �ش� �ֹ���ȣ�� ����Ʈ�� �����Ѵ�. 
	 * ��ư �̺�Ʈ �� �Է¹��� �ؽ�Ʈ�ʵ��� ���� �Ű� ������ �����ϰ�
	 * �ش� �ֹ���ȣ�� �̿��Ͽ� �������� �̿��� ����
	 * */
	
	
	void OrderDelete(int order_number) {
		try {
			rs = MySQL.stmt.executeQuery("SELECT * FROM ORDER_INFO WHERE ORDER_NUMBER = " + order_number +";");
			int quantity = 0; 		//������ ���� ��ŭ +������Ѵ�.
			String parts = ""; 		//��ǰ������ �����Ѵ�.
			while(rs.next()) {
				quantity = rs.getInt("QUANTITY");
				parts = rs.getString("PARTS");
			}
			
			rs = MySQL.stmt.executeQuery("SELECT * FROM PARTS_INFO WHERE NAME = " + "'" + parts + "';");
			while(rs.next())
				quantity += rs.getInt("INVENTORY");
			
			//�ش��ֹ��� �����ϰ�
			MySQL.stmt.executeUpdate("DELETE FROM ORDER_INFO WHERE ORDER_NUMBER = " + "" + order_number + ";");
			//������ ����ŭ ��� �����־���Ѵ�
			MySQL.stmt.executeUpdate("UPDATE PARTS_INFO SET INVENTORY = " + quantity + " WHERE NAME = "
			+ "'" + parts + "';");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Don't exist " + "'" + order_number + "'" + "table.");
			e.printStackTrace();
		}
	}

	
	/* ��� �ֹ��� ���� ������ �����ش�. 
	 * �̰��� ������������ �α��� ���� ��� �������� �����̴�.
	 * setText() �Լ��� �Ű������� String ���� ���ϹǷ� ��� ������ 
	 * ��Ʈ������ �̾�ٿ��� �� ���ڿ��� ��ȯ�Ѵ�.
	 * */
	String ShowAllOrderInfo() {
		String retString = "����ȣ \t �ֹ���ȣ \t ��ǰ�� \t ���� \t ������ \t\t �������� \n\n";
		try {
			rs = MySQL.stmt.executeQuery("SELECT * FROM ORDER_INFO;");
			
			while (rs.next()) {
				String client = rs.getString("CLIENT");
				int order_number = rs.getInt("ORDER_NUMBER");
				String parts = rs.getString("PARTS");
				int quantity = rs.getInt("QUANTITY");
				String payment_date = rs.getString("PAYMENT_DATE");
				String delivery_date = rs.getString("DELIVERY_DATE");
				
				retString += client + "\t" + order_number + "\t" + parts + "\t" + quantity + "\t" + payment_date + "\t" + delivery_date + "\n";
			}
			return retString;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
	/* Ư�� �ֹ���ȣ�� ���� �ֹ����� 
	 * �ش� �ֹ���ȣ�� �Է����� �� �� �Լ��� ȣ��ȴ�.
	 * �� �Լ������� �ش� �ֹ���ȣ�� �̿��Ͽ� �ֹ��� ���� ������
	 * ��Ʈ������ �����Ѵ�. ��Ʈ���� �����ϴ� ������  setText() �Լ���
	 * �Ű������� ��Ʈ���� �ޱ� �����̴�.
	 * */
	String OrderInfo(int orderNum) {
		String retString = "";
		
		try {
			//�ش� �ֹ���ȣ�� ���� ���� ã�´�.
			rs = MySQL.stmt.executeQuery("SELECT * FROM ORDER_INFO WHERE ORDER_NUMBER = " + "" + orderNum + ";");
			
			/* ���� ��´�. */
			while (rs.next()) {
				String client = rs.getString("CLIENT");
				int order_number = orderNum;
				String parts = rs.getString("PARTS");
				int quantity = rs.getInt("QUANTITY");
				String payment_date = rs.getString("PAYMENT_DATE");
				String delivery_date = rs.getString("DELIVERY_DATE");
				
				retString += "����ȣ \t" + client + "\n" +
						"�ֹ���ȣ \t" + order_number + "\n" +
						"��ǰ�� \t" + parts + "\n" +
						"���� \t" + quantity + "\n" +
						"������ \t" + payment_date + "\n" + 
						"���� ����� \t" + delivery_date + "\n";
			}
			return retString; 	//���� ����ó�� ��Ʈ���� �ٿ����� �����.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
	/* ���� �ð��� ��´�. �ý��� ���� ����ð��� �����Ѵ�.
	 *
	 *  �̰��� �����ð��� ��� �����ε� �Ϲ� ���� ��İ� �����ϰ�
	 *  ���� �����Ͱ� �־����� �������� �����Ѵ�.
	*/
	String getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		java.util.Date date = calendar.getTime();
		
		String payment_date = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
		
		return payment_date;
	}
	
	/* 
	 �ش� ��ǰ�� ��� ���� �� ��� ���ٸ� ������ �߰����� �ʴ´�. 
	 �켱 �ش� ��ǰ�� ��� ������ŭ ���̳ʽ� ��Ų��.
	 ���� �� ���� 0���� ���ų� �۴ٸ� �� ��ǰ�� �����Ǿ����Ƿ�
	 �ش� ��ǰ�� �ֹ����� �ʴ´�.
	*/
	boolean OrderCheck(String parts, int quantity) {
		MailParts mailParts = new MailParts(); 		//getPartsQuantity() �Լ��� ���� ���� ��ü ����
		
		int curQuantity = mailParts.getPartsQuantity(parts);		//�ش� ��ǰ�� ���� ���� ���´�.
		int inventory = curQuantity - quantity;
		
		if(curQuantity <= 0)			//���� ��� 0�̰ų� �� �۴ٸ� ������ �߻��߰ų� ��� ���ٴ� ���̴�.
			return false;
		
		if(inventory < 0)		//���� ��� �ش緮��ŭ ���ٴ� ���̴�.
			return false;
		
		/* ��� �䱸 �������� ����ϴٸ� ������ ���ҽ����ְ�*/
		try {
			MySQL.stmt.executeUpdate("UPDATE PARTS_INFO SET INVENTORY = " + inventory + 
					" WHERE NAME = " + "'" + parts + "';");
			return true;
		} catch (SQLException e) { //�ش� ��ǰ���� ���ٸ�
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/* ���� �ֹ��� ���� �� �θ��� �Լ� 
	 * �ؽ�Ʈ �ʵ忡�� ��ǰ��� ������ �޾ƿ´�.
	 * 
	 * */
	void Order(String parts, int quantity) { 	
		/*
		 ���� ��ǰ�� �̸��� �������� ����
		 */
		String curClient = MySQL.client_login;		// ���� �α��� �� ������ �����ϰ� �ִ� ���
		String payment_date = getCurrentTime();		// �������� �����Ͱ� ���� ������ �ð����� ���� (ex.20170101164010 = 2017�� 1�� 1�� 16�� 40�� 10��)
		String delivery_date = null;				// ������� ������ �����ؾ��Ѵ�.
		
		/* false ��� ���� �ش� ��ǰ�� ��� 0����ų� ���ϴ� ������ŭ �ֹ�������� ������ ���ڸ� ����̰ų� ������ �����̴�. 
		 * ������ ������ �Ͼ�ٴ� ���� �ش� ��ǰ�� ���� ����̴�. 
		 * ���� �ϳ��� ������ ���� ��� �ֹ� �����ͺ��̽��� �߰����� �ʴ´�.
		 * */
		if(OrderCheck(parts, quantity) == false)
			return;
		
		else {
		/* ������ ������ �����ͺ��̽��� �ִ´�. */
			OrderInsert(curClient, parts, quantity, payment_date, delivery_date);
		}
		
	}
	
	
	// ����� ��۽ð��� ������ �� �ִ�. ��۽ð��� null�� �ƴ϶�� ���� ������ �Ϸ�Ǿ��ٴ� ��.
	void OrderConfirm(int order_number, String delivery_date) {
		try { 		//�ش� ���� �ֹ���ȣ���� ������� ������Ʈ�Ѵ�. 
			MySQL.stmt.executeUpdate("UPDATE ORDER_INFO SET DELIVERY_DATE = " + "'" + delivery_date + "'" + 
		"WHERE ORDER_NUMBER = " + order_number + ";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}