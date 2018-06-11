import java.sql.ResultSet;
import java.sql.SQLException;

public class MailCompany {
	private ResultSet rs;
	
	public MailCompany(){
		
	}
	
	/* 직원로그인에 대한 정보를 찾는다. 
	 * 직원번호를 인자로 전달받는다.
	 * 직원번호를 통해 그 값이 있는지 없는지를 확인한다.
	 * 해당 직원 번호를 리턴한다.
	 * */
	String GetLoginInfo(String ID) {
		try {
			rs = MySQL.stmt.executeQuery("SELECT * FROM COMP_INFO WHERE EMPLOYEE = " + "'" + ID + "';");
			String employee=null;
			
			while(rs.next()) {
				employee = rs.getString("employee");
			}
			return employee; 	//해당 직원번호가 존재한다면 직원 번호리턴
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
	/* 로그인 정보를 저장함. */
	void EmployeeLogin(String employee) {
		
		String login = GetLoginInfo(employee);
		
		/* 직원로그인 정보를 찾았다면 현재 직원 로그인 아이디를 저장한다. */
		if(login == null) {
			System.out.println("Not exist Employee number");
			System.exit(0);
		}
		else
			MySQL.emp_login = login; 		//현재 접속된 직원 로그인 정보에 직원번호를 저장한다.
	}
}
