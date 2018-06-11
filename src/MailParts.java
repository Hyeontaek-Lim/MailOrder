import java.sql.ResultSet;
import java.sql.SQLException;

public class MailParts {

	private ResultSet rs;
	
	
	public MailParts(){
		
	}
	
	/* 부품 데이터를 넣는다
	 * 
	 *  */
	void PartsInsert(String serials_num, String name, int price, int inventory) {
		try {
			MySQL.stmt.executeUpdate("insert into parts_info value(" + "'" + serials_num + "', " 
		+ "'" + name + "', "
		+ price + ", "
		+ inventory
		+ ");"
		);
			System.out.println("Insert data " + serials_num + " " + name + " " + price + " " + inventory);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Insert error !");
			e.printStackTrace();
		}
	}
	
	/* 모든 부품에 대한 정보를 보여준다. 
	 * 이 함수는 고객이 로그인 했을 때
	 * 상품 리스트를 보여주기 위함이다.
	 * 이 역시 setText()함수를 위해 String을 이어붙여 리턴한다.
	 * */
	String ShowAllPartsInfo() {
		String retString = "부품번호 \t 부품이름 \t 가격 \t 재고 \n\n";
		try {
			rs = MySQL.stmt.executeQuery("SELECT * FROM PARTS_INFO;");
			
			while (rs.next()) {
				String serials_num = rs.getString("SERIALS_NUM");
				String name = rs.getString("NAME");
				int price = rs.getInt("PRICE");
				int inventory = rs.getInt("INVENTORY");
				
				retString += serials_num + "\t" + name + "\t" + price + "\t" + inventory + "\n";
			}
			
			return retString;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	/* 새로고침버튼 눌렀을 때 ShowAllPartsInfo() 재호출함 */
	String Refresh() {
		/* 출력화면 모두 지우는 기능 */
		
		
		String retString = ShowAllPartsInfo();
		
		return retString;
	}
	
	/* 해당 제품의 수량을 리턴한다. */
	int getPartsQuantity(String name) {
		try {
			rs = MySQL.stmt.executeQuery("SELECT * FROM PARTS_INFO WHERE NAME = " + "'" + name + "';");
		
			int inventory = 0;
			while (rs.next())
				inventory = rs.getInt("INVENTORY"); 		//제품의 수량을 저장한다.
		
		return inventory;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1; 		//해당 상품명이 없을 경우.
		}
		
	}
}
