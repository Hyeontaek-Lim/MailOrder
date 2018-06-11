import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

abstract public class MySQL {
	public static final String driverName = "com.mysql.jdbc.Driver"; // ����̹� �̸� ����
	public static final String dbName = "MAIL_ORDER";
	public static final String dbURL = "jdbc:mysql://localhost:3306/" + dbName; // URL ����
	public static final String PW = "lht1080";
	public static final String User = "root";
	public static Connection con;
	public static java.sql.Statement stmt;
	public static Scanner scanner;
	public static String client_login; //���� �α��ε� ���� ����
	public static String emp_login;		//���� �α��ε� ������ ����
	public static String serial_num;	//���� ���õ� ��ǰ�� ��������
	
	public static void printSeperate() {
		System.out.println("---------------------------------------------");
	}
	
	/* ������ ���̽��� ���� */
	public static void SQLConnect() {
		try {
			Class.forName(driverName);
			
			/* �߰��� ?auto~false �� ��� �޼����� ���ֱ� ���� �����̴�. */
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
