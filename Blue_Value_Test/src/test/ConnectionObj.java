package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionObj {
	private static String driverClassName;
	private static String connectionUrl;
	private static String dbUser;
	private static String dbPassword;

	private final static Properties dbProperties = new Properties();
	static {
		driverClassName = "com.mysql.jdbc.Driver";
		connectionUrl = "jdbc:mysql://localhost/test?useLegacyDatetimeCode=false&serverTimezone=UTC";
		dbUser = "tester";
		dbPassword = "test123";
	}

	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(connectionUrl, dbUser, dbPassword);
		return conn;
	}
}