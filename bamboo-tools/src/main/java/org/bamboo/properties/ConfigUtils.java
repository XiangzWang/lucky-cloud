package org.bamboo.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {

	private static Properties properties = new Properties();
	static {
		InputStream in = null;
		try {
			in = ConfigUtils.class.getClassLoader().getResourceAsStream("config.properties");
//			in = ConfigUtils.class.getResourceAsStream("config.properties");
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public  static String getProperty(String name)
	{
		return properties.getProperty(name);
	}
}
