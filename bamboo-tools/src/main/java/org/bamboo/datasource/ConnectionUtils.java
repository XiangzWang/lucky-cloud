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
		if (!(dbconn.createCon())) {
			log.error("数据库连接失败");
		}
		Connection conn = dbconn.getConnection();
		return conn;
	}

	/**
	 * 通过JDBC获取Connection
	 * 
	 * @return
	 */
	public static Connection getConnectionJDBC() {
		String username = "sea";
		String pwd = "sea3000";
		String url = "jdbc:oracle:thin:@192.168.176.51:1521/sea";
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, username, pwd);
		} catch (SQLException e) {
			log.error("数据库连接失败", e);
		} catch (ClassNotFoundException e1) {
			log.error("数据库连接失败", e1);
		}
		return conn;
	}

}
