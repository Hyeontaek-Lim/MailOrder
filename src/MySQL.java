import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

abstract public class MySQL {
	public static final String driverName = "com.mysql.jdbc.Driver"; // 드라이버 이름 지정
	public static final String dbName = "MAIL_ORDER";
	public static final String dbURL = "jdbc:mysql://localhost:3306/" + dbName; // URL 지정
	public static final String PW = "lht1080";
	public static final String User = "root";
	public static Connection con;
	public static java.sql.Statement stmt;
	public static Scanner scanner;
	public static String client_login; //현재 로그인된 고객의 정보
	public static String emp_login;		//현재 로그인된 직원의 정보
	public static String serial_num;	//현재 선택된 부품에 대한정보
	
	public static void printSeperate() {
		System.out.println("---------------------------------------------");
	}
	
	/* 데이터 베이스의 연결 */
	public static void SQLConnect() {
		try {
			Class.forName(driverName);
			
			/* 중간에 ?auto~false 는 경고 메세지를 없애기 위한 문장이다. */
			con = DriverManager.getConnection(dbURL + "?autoReconnect=true&useSSL=false", User,
					PW);
			System.out.println("MySQL DB(MAIL_ORDER) Connection");

			stmt = con.createStatement();

		} catch (Exception e) {
			System.out.println("MySQL server not connection");
			e.printStackTrace();
		}
	}
}
