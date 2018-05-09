package org.bamboo.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionUtils {
	private final static Logger log = LoggerFactory.getLogger(ConnectionUtils.class);

	private static ProxoolUtils dbconn = null;

	/**
	 * 通过proxool获取Connection
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		if (dbconn == null) {
			dbconn = ProxoolUtils.getInstance();
		}

		Connection conn = dbconn.getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			log.error("数据库连接失败", e);
		}
		return conn;
	}

	/**
	 * 通过JDBC获取Connection
	 * 
	 * @return
	 */
	public static Connection getConnectionJDBC() {
		String username = "root";
		String pwd = "123";
		String url = "jdbc:mysql://localhost:3306/sea";
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, pwd);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			log.error("数据库连接失败", e);
		} catch (ClassNotFoundException e1) {
			log.error("数据库连接失败", e1);
		}
		return conn;
	}

}
