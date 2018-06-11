import java.sql.ResultSet;
import java.sql.SQLException;

public class MailClient{
	private ResultSet rs;
	
	public MailClient() {
		
	}

	/* 
	 * 텍스트 필드에서 받아온 고객번호와 이름, zipcode의 정보를 
	 * 데이터베이스에 저장한다.
	 * 같은 고객 번호가 있다면 데이터는 저장되지 않는다.
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

	/* 해당 클라이언트번호 값의 행을 전체 삭제한다. */
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
	
	
	/* 고객의 로그인 정보가 맞는지 확인 
	 * 해당 정보가 없다면 null을 리턴한다.
	 * 아니라면 로그인 정보를 리턴한다.
	 * */
	String GetLoginInfo(String ID) {
		try {
			rs = MySQL.stmt.executeQuery("SELECT * FROM CLIENT_INFO WHERE CLIENT = " + "'" + ID + "';");
			String client=null;
			
			while(rs.next()) { 		//해당 고객 번호의 정보가 있는지 찾고
				client = rs.getString("client");
			}
			return client; 		//있다면 해당 고객 번호를 반환한다.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
	/* 현재 로그인 정보를 저장함. */
	void MemeberLogin(String client) {	
		String login = GetLoginInfo(client); 
		
		/* null은 에러를 의미함. 해당 정보가 없을경우 */
		if(login == null) {
			System.out.println("Not exist Client number");
			System.exit(0);
		}
		/* null이 아니라면 현재 로그인 정보에 저장을 한다. */
		else {
			MySQL.client_login = login;
		}
	}
	
	/* 자신의 주문이 완료되었는지 확인 시킨다. */
	String getOrderCheck() {
		String curClient = MySQL.client_login;
		String orderList = MySQL.client_login + "님의 주문 정보입니다. \n\n";
		orderList += "주문번호 \t 부품명 \t 수량 \t 결제일 \t\t 예상배송일 \n";
		
		try {
			/* 현재 로그인 된 아이디의 정보를 받아옴 */
			rs = MySQL.stmt.executeQuery("SELECT * FROM ORDER_INFO WHERE CLIENT = " + "'" + curClient + "';");
			
			/* 주문번호, 부품명, 수량, 결제일, 예상 배송일들의 리스트를 저장한다. */
			while(rs.next()) {
				int orderNum = rs.getInt("ORDER_NUMBER");
				String parts = rs.getString("PARTS");
				int quantity = rs.getInt("QUANTITY");
				String paymentDate = rs.getString("PAYMENT_DATE");
				String deliveryDate = rs.getString("DELIVERY_DATE");
				
				orderList += orderNum + " \t " + parts + " \t " + quantity + " \t " +
						paymentDate + " \t " + deliveryDate + "\n";
			}
			orderList += "\n\n 배송일이 null이라는 건 주문이 처리되지 않았음을 의미합니다.";
			
			return orderList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			return null;
		}
		
	}
}
