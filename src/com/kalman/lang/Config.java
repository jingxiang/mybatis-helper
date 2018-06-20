package com.kalman.lang;

import static com.google.common.collect.Maps.newHashMap;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

/**
 * @since 2018年6月10日
 * @author kalman03
 */
public class Config {

	private static Map<String, String> configMap = newHashMap();

	static {
		try {
			String file = System.getProperty("user.dir") + "/config.properties";
			Properties properties = new Properties();
			properties.load(new FileInputStream(file));
			Enumeration<?> enum1 = properties.propertyNames();// 得到配置文件的名字
			while (enum1.hasMoreElements()) {
				String strKey = (String) enum1.nextElement();
				configMap.put(strKey, properties.getProperty(strKey));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String getUserName() {
		return configMap.get("jdbc.username");
	}

	public static String getPassword() {
		return configMap.get("jdbc.password");
	}

	public static String getUrl() {
		return configMap.get("jdbc.url");
	}

	public static String getMapperPackage() {
		return configMap.get("mapper.package");
	}

	public static String getPojoPackage() {
		return configMap.get("pojo.package");
	}

	public static boolean getAnotationOrNot() {
		return configMap.get("anotation").equals("true");
	}

	public static String getResultLocation() {
		return configMap.get("result.location");
	}
}
