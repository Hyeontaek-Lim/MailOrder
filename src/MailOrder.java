import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MailOrder {
	private ResultSet rs;
	int order = 0; 		//임의로 결정한 주문번호이다. 주문번호는 primary key라서 중복될 수 없다.
	
	/* 주문번호를 설정한다.
	 * 일반적으로 주문번호는 자동으로 설정되기 때문에
	 * */
	void setOrderNumber() {
		try {
			rs = MySQL.stmt.executeQuery("SELECT * FROM ORDER_INFO;");
			//주문정보의 모든 정보를 검색한다.
			
			int orderNum=0; 	// 0으로 초기화 
			
			while (rs.next()) { 		//가장 마지막 주문번호를 찾아서
				orderNum = rs.getInt("ORDER_NUMBER");
			}
			this.order += orderNum+1; 	//가장 마지막 주문번호+1로 주문번호를 설정한다.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* 주문 일을 하기 위해 다른 클래스에서 객체 생성 시 주문번호를 설정해준다.*/
	public MailOrder() {
		setOrderNumber();
	}

	/* 데이터를 저장한다. */
	/* 고객명과 부품이름, 수량, 결제일, 배송일을 저장하고 주문번호는 자동으로 결정된다. */
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

	/* 해당 주문번호의 리스트를 삭제한다. 
	 * 버튼 이벤트 시 입력받은 텍스트필드의 값을 매개 변수로 전달하고
	 * 해당 주문번호를 이용하여 쿼리문을 이용한 삭제
	 * */
	
	
	void OrderDelete(int order_number) {
		try {
			rs = MySQL.stmt.executeQuery("SELECT * FROM ORDER_INFO WHERE ORDER_NUMBER = " + order_number +";");
			int quantity = 0; 		//삭제된 수량 만큼 +해줘야한다.
			String parts = ""; 		//부품명으로 접근한다.
			while(rs.next()) {
				quantity = rs.getInt("QUANTITY");
				parts = rs.getString("PARTS");
			}
			
			rs = MySQL.stmt.executeQuery("SELECT * FROM PARTS_INFO WHERE NAME = " + "'" + parts + "';");
			while(rs.next())
				quantity += rs.getInt("INVENTORY");
			
			//해당주문을 삭제하고
			MySQL.stmt.executeUpdate("DELETE FROM ORDER_INFO WHERE ORDER_NUMBER = " + "" + order_number + ";");
			//삭제된 수만큼 재고에 더해주어야한다
			MySQL.stmt.executeUpdate("UPDATE PARTS_INFO SET INVENTORY = " + quantity + " WHERE NAME = "
			+ "'" + parts + "';");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Don't exist " + "'" + order_number + "'" + "table.");
			e.printStackTrace();
		}
	}

	
	/* 모든 주문에 대한 정보를 보여준다. 
	 * 이것은 직원계정으로 로그인 했을 경우 보여지는 값들이다.
	 * setText() 함수가 매개변수로 String 값을 원하므로 모든 정보를 
	 * 스트링으로 이어붙여서 그 문자열을 반환한다.
	 * */
	String ShowAllOrderInfo() {
		String retString = "고객번호 \t 주문번호 \t 부품명 \t 수량 \t 결재일 \t\t 예상배송일 \n\n";
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
	
	/* 특정 주문번호에 대한 주문정보 
	 * 해당 주문번호를 입력했을 때 이 함수가 호출된다.
	 * 이 함수에서는 해당 주문번호를 이용하여 주문에 대한 정보를
	 * 스트링으로 리턴한다. 스트링을 리턴하는 이유는  setText() 함수가
	 * 매개변수로 스트링을 받기 때문이다.
	 * */
	String OrderInfo(int orderNum) {
		String retString = "";
		
		try {
			//해당 주문번호에 대한 값을 찾는다.
			rs = MySQL.stmt.executeQuery("SELECT * FROM ORDER_INFO WHERE ORDER_NUMBER = " + "" + orderNum + ";");
			
			/* 값을 얻는다. */
			while (rs.next()) {
				String client = rs.getString("CLIENT");
				int order_number = orderNum;
				String parts = rs.getString("PARTS");
				int quantity = rs.getInt("QUANTITY");
				String payment_date = rs.getString("PAYMENT_DATE");
				String delivery_date = rs.getString("DELIVERY_DATE");
				
				retString += "고객번호 \t" + client + "\n" +
						"주문번호 \t" + order_number + "\n" +
						"부품명 \t" + parts + "\n" +
						"수량 \t" + quantity + "\n" +
						"결제일 \t" + payment_date + "\n" + 
						"예상 배송일 \t" + delivery_date + "\n";
			}
			return retString; 	//위의 형식처럼 스트링을 붙여가며 만든다.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
	/* 현재 시간을 얻는다. 시스템 상의 현재시간을 리턴한다.
	 *
	 *  이것은 결제시간을 얻기 위함인데 일반 결제 방식과 동일하게
	 *  결제 데이터가 넣어지는 순간으로 결정한다.
	*/
	String getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		java.util.Date date = calendar.getTime();
		
		String payment_date = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
		
		return payment_date;
	}
	
	/* 
	 해당 부품의 재고 조정 및 재고가 없다면 쿼리가 추가되지 않는다. 
	 우선 해당 상품의 재고를 수량만큼 마이너스 시킨다.
	 만약 그 수가 0보다 같거나 작다면 그 상품은 매진되었으므로
	 해당 상품은 주문되지 않는다.
	*/
	boolean OrderCheck(String parts, int quantity) {
		MailParts mailParts = new MailParts(); 		//getPartsQuantity() 함수를 쓰기 위한 객체 생성
		
		int curQuantity = mailParts.getPartsQuantity(parts);		//해당 제품의 수량 값을 얻어온다.
		int inventory = curQuantity - quantity;
		
		if(curQuantity <= 0)			//현재 재고가 0이거나 더 작다면 오류가 발생했거나 재고가 없다는 뜻이다.
			return false;
		
		if(inventory < 0)		//현재 재고가 해당량만큼 없다는 뜻이다.
			return false;
		
		/* 재고가 요구 수량보다 충분하다면 수량을 감소시켜주고*/
		try {
			MySQL.stmt.executeUpdate("UPDATE PARTS_INFO SET INVENTORY = " + inventory + 
					" WHERE NAME = " + "'" + parts + "';");
			return true;
		} catch (SQLException e) { //해당 부품명이 없다면
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/* 고객이 주문을 했을 때 부르는 함수 
	 * 텍스트 필드에서 부품명과 수량을 받아온다.
	 * 
	 * */
	void Order(String parts, int quantity) { 	
		/*
		 고객은 부품의 이름과 수량만을 결정
		 */
		String curClient = MySQL.client_login;		// 현재 로그인 된 정보를 저장하고 있는 상수
		String payment_date = getCurrentTime();		// 결제일은 데이터가 들어가는 순간의 시간으로 결정 (ex.20170101164010 = 2017년 1월 1일 16시 40분 10초)
		String delivery_date = null;				// 배송일은 직원이 결정해야한다.
		
		/* false 라는 것은 해당 제품의 재고가 0개라거나 원하는 수량만큼 주문햇을경우 개수가 모자른 경우이거나 쿼리문 오류이다. 
		 * 쿼리문 오류가 일어낫다는 것은 해당 부품이 없는 경우이다. 
		 * 셋중 하나의 오류가 있을 경우 주문 데이터베이스에 추가되지 않는다.
		 * */
		if(OrderCheck(parts, quantity) == false)
			return;
		
		else {
		/* 결정된 정보를 데이터베이스에 넣는다. */
			OrderInsert(curClient, parts, quantity, payment_date, delivery_date);
		}
		
	}
	
	
	// 사원은 배송시간을 설정할 수 있다. 배송시간이 null이 아니라는 것은 결제가 완료되었다는 것.
	void OrderConfirm(int order_number, String delivery_date) {
		try { 		//해당 고객의 주문번호에서 배송일을 업데이트한다. 
			MySQL.stmt.executeUpdate("UPDATE ORDER_INFO SET DELIVERY_DATE = " + "'" + delivery_date + "'" + 
		"WHERE ORDER_NUMBER = " + order_number + ";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}