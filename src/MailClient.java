import java.sql.ResultSet;
import java.sql.SQLException;

public class MailClient{
	private ResultSet rs;
	
	public MailClient() {
		
	}

	/* 
	 * �ؽ�Ʈ �ʵ忡�� �޾ƿ� ����ȣ�� �̸�, zipcode�� ������ 
	 * �����ͺ��̽��� �����Ѵ�.
	 * ���� �� ��ȣ�� �ִٸ� �����ʹ� ������� �ʴ´�.
	 *  */
	void ClientInsert(String client, String name, String zipcode) {
		try {
			MySQL.stmt.executeUpdate("INSERT INTO CLIENT_INFO VALUE(" + "'" + client + "', " + "'" + name + "', " + "'"
					+ zipcode + "'" + ");");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Insert error !");
			e.printStackTrace();
		}
	}

	/* �ش� Ŭ���̾�Ʈ��ȣ ���� ���� ��ü �����Ѵ�. */
	/*
	 * 
	 */
	void ClientDelete() {
		System.out.print("Delete client_num Input ==>");
		String client = MySQL.scanner.nextLine();

		try {
			MySQL.stmt.executeUpdate("DELETE FROM client_info WHERE client = " + "'" + client + "';");
			System.out.println("DELETE CLIENT = " + client);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Don't exist " + "'" + client + "'" + "table.");
			e.printStackTrace();
		}
	}
	
	
	/* ���� �α��� ������ �´��� Ȯ�� 
	 * �ش� ������ ���ٸ� null�� �����Ѵ�.
	 * �ƴ϶�� �α��� ������ �����Ѵ�.
	 * */
	String GetLoginInfo(String ID) {
		try {
			rs = MySQL.stmt.executeQuery("SELECT * FROM CLIENT_INFO WHERE CLIENT = " + "'" + ID + "';");
			String client=null;
			
			while(rs.next()) { 		//�ش� �� ��ȣ�� ������ �ִ��� ã��
				client = rs.getString("client");
			}
			return client; 		//�ִٸ� �ش� �� ��ȣ�� ��ȯ�Ѵ�.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
	/* ���� �α��� ������ ������. */
	void MemeberLogin(String client) {	
		String login = GetLoginInfo(client); 
		
		/* null�� ������ �ǹ���. �ش� ������ ������� */
		if(login == null) {
			System.out.println("Not exist Client number");
			System.exit(0);
		}
		/* null�� �ƴ϶�� ���� �α��� ������ ������ �Ѵ�. */
		else {
			MySQL.client_login = login;
		}
	}
	
	/* �ڽ��� �ֹ��� �Ϸ�Ǿ����� Ȯ�� ��Ų��. */
	String getOrderCheck() {
		String curClient = MySQL.client_login;
		String orderList = MySQL.client_login + "���� �ֹ� �����Դϴ�. \n\n";
		orderList += "�ֹ���ȣ \t ��ǰ�� \t ���� \t ������ \t\t �������� \n";
		
		try {
			/* ���� �α��� �� ���̵��� ������ �޾ƿ� */
			rs = MySQL.stmt.executeQuery("SELECT * FROM ORDER_INFO WHERE CLIENT = " + "'" + curClient + "';");
			
			/* �ֹ���ȣ, ��ǰ��, ����, ������, ���� ����ϵ��� ����Ʈ�� �����Ѵ�. */
			while(rs.next()) {
				int orderNum = rs.getInt("ORDER_NUMBER");
				String parts = rs.getString("PARTS");
				int quantity = rs.getInt("QUANTITY");
				String paymentDate = rs.getString("PAYMENT_DATE");
				String deliveryDate = rs.getString("DELIVERY_DATE");
				
				orderList += orderNum + " \t " + parts + " \t " + quantity + " \t " +
						paymentDate + " \t " + deliveryDate + "\n";
			}
			orderList += "\n\n ������� null�̶�� �� �ֹ��� ó������ �ʾ����� �ǹ��մϴ�.";
			
			return orderList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			return null;
		}
		
	}
}
