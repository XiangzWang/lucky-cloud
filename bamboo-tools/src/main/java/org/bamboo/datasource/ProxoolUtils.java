package org.bamboo.datasource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bamboo.properties.ConfigUtils;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxoolUtils implements Serializable {

	private static final long serialVersionUID = 0x3dc708ae67934d92L;
	static final Logger logger = LoggerFactory.getLogger(ProxoolUtils.class);
	private static ProxoolUtils instance = null;
	private static byte lock[] = new byte[0];
	private boolean inited = false;

	public synchronized Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
			conn = DriverManager.getConnection("proxool.sea");
		} catch (Exception e) {
			logger.error("数据库连接失败", e);
		}
		return conn;
	}

	private ProxoolUtils() {
		inited = false;
	}

	public static ProxoolUtils getInstance() {
		if (instance == null)
			synchronized (lock) {
				if (instance == null) {
					instance = new ProxoolUtils();
				}
			}
		return instance;
	}

	public synchronized Connection getConnectionSf() throws SQLException {
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
			Connection conn = DriverManager.getConnection("proxool.seasf");
			return conn;
		} catch (ClassNotFoundException e) {
			logger.error("数据库连接失败", e);
		}
		return null;
	}

	public void freeConnection(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

	public boolean getInited() {
		return inited;
	}

	public synchronized boolean createCon() {
		try {
			String path = ConfigUtils.getProperty("proxool-url");
//			JAXPConfigurator.configure(path, false); // E:\\storm-conf\\proxool.xml
			
			InputStream in = ProxoolUtils.class.getClassLoader().getResourceAsStream(path);  // proxool.xml
			Reader reader = new InputStreamReader(in);
			JAXPConfigurator.configure(reader, false);
		} catch (ProxoolException e) {
			logger.error("DB createCon: 连接数据库失败", e);
		}
		if (inited) {
			logger.debug("DB createCon inited, return");
			return true;
		}
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException ex) {
			logger.error("DB createCon: 连接数据库失败", ex);
		}
		Connection conn = getConnection();
		if (conn == null)
			return false;
		try {
			inited = true;
			logger.debug("DB createCon: 连接数据库成功");
			conn.close();
		} catch (SQLException ex) {
			logger.error("DB createCon: 连接数据库失败", ex);
			return false;
		}
		return true;
	}

}
