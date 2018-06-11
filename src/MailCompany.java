import java.sql.ResultSet;
import java.sql.SQLException;

public class MailCompany {
	private ResultSet rs;
	
	public MailCompany(){
		
	}
	
	/* �����α��ο� ���� ������ ã�´�. 
	 * ������ȣ�� ���ڷ� ���޹޴´�.
	 * ������ȣ�� ���� �� ���� �ִ��� �������� Ȯ���Ѵ�.
	 * �ش� ���� ��ȣ�� �����Ѵ�.
	 * */
	String GetLoginInfo(String ID) {
		try {
			rs = MySQL.stmt.executeQuery("SELECT * FROM COMP_INFO WHERE EMPLOYEE = " + "'" + ID + "';");
			String employee=null;
			
			while(rs.next()) {
				employee = rs.getString("employee");
			}
			return employee; 	//�ش� ������ȣ�� �����Ѵٸ� ���� ��ȣ����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
	/* �α��� ������ ������. */
	void EmployeeLogin(String employee) {
		
		String login = GetLoginInfo(employee);
		
		/* �����α��� ������ ã�Ҵٸ� ���� ���� �α��� ���̵� �����Ѵ�. */
		if(login == null) {
			System.out.println("Not exist Employee number");
			System.exit(0);
		}
		else
			MySQL.emp_login = login; 		//���� ���ӵ� ���� �α��� ������ ������ȣ�� �����Ѵ�.
	}
}
