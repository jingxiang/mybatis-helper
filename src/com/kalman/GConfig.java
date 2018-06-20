package com.kalman;

import com.kalman.lang.Config;

/**
 * @since 2016年3月18日
 * @author kalman03
 */
public class GConfig {

	public static String M_PACKAGE = Config.getMapperPackage();
	public static String P_PACKAGE = Config.getPojoPackage();
	
	public static boolean anotation=Config.getAnotationOrNot();
	public static String AUTHOR = "kalman03";
	
	public static String FILE_SAVE_PATH = Config.getResultLocation();
	
	public static String MYSQL_CONNECTION_URL = Config.getUrl()+"?user="+Config.getUserName()+"&password="+Config.getPassword();
}
