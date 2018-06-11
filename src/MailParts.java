import java.sql.ResultSet;
import java.sql.SQLException;

public class MailParts {

	private ResultSet rs;
	
	
	public MailParts(){
		
	}
	
	/* ��ǰ �����͸� �ִ´�
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
	
	/* ��� ��ǰ�� ���� ������ �����ش�. 
	 * �� �Լ��� ���� �α��� ���� ��
	 * ��ǰ ����Ʈ�� �����ֱ� �����̴�.
	 * �� ���� setText()�Լ��� ���� String�� �̾�ٿ� �����Ѵ�.
	 * */
	String ShowAllPartsInfo() {
		String retString = "��ǰ��ȣ \t ��ǰ�̸� \t ���� \t ��� \n\n";
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
	
	/* ���ΰ�ħ��ư ������ �� ShowAllPartsInfo() ��ȣ���� */
	String Refresh() {
		/* ���ȭ�� ��� ����� ��� */
		
		
		String retString = ShowAllPartsInfo();
		
		return retString;
	}
	
	/* �ش� ��ǰ�� ������ �����Ѵ�. */
	int getPartsQuantity(String name) {
		try {
			rs = MySQL.stmt.executeQuery("SELECT * FROM PARTS_INFO WHERE NAME = " + "'" + name + "';");
		
			int inventory = 0;
			while (rs.next())
				inventory = rs.getInt("INVENTORY"); 		//��ǰ�� ������ �����Ѵ�.
		
		return inventory;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1; 		//�ش� ��ǰ���� ���� ���.
		}
		
	}
}
