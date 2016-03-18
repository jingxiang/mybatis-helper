package com.kalman.lang;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.kalman.Config;
import com.kalman.domain.Table;

/**
 * @since 2016年3月18日
 * @author kalman03
 */
public class MybatisBuilder {
	
	private static String templateLocation = "/config/";
	
	private static VelocityEngine getVelocityEngine(){
		VelocityEngine velocityEngine = new VelocityEngine(); 
		return velocityEngine;
	}
	
	private static String getSince(){
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	
	private static void checkConfig(){
		checkArgument(isNotBlank(Config.FILE_SAVE_PATH), "Config.FILE_SAVE_PATH is required.");
		checkArgument(isNotBlank(Config.MYSQL_CONNECTION_URL),"Config.MYSQL_CONNECTION_URL is required.");
		checkArgument(isNotBlank(Config.PACKAGE),"Config.PACKAGE is required.");
		checkArgument(isNotBlank(Config.AUTHOR),"Config.AUTHOR is required.");
	}
	
	public static void build(){
		checkConfig();
		try {
			VelocityEngine velocityEngine = getVelocityEngine();
			VelocityContext context = new VelocityContext(); 
			context.put("since", getSince()); 
			context.put("author", Config.AUTHOR); 
			context.put("package", Config.PACKAGE); 
			
			List<Table> tables = MysqlUtils.getTables(Config.MYSQL_CONNECTION_URL);
			if(tables != null && !tables.isEmpty()){
				for(Table table : tables){
					context.put("table", table); 
					
					FileWriter pojoWriter = new FileWriter(new File(Config.FILE_SAVE_PATH+"/"+table.getClassNameDefine()+".java"));
					velocityEngine.mergeTemplate(templateLocation+"pojo.vm","utf-8", context, pojoWriter);
					pojoWriter.flush();
					pojoWriter.close();
					
					FileWriter mapperWriter = new FileWriter(new File(Config.FILE_SAVE_PATH+"/"+table.getClassNameDefine()+"Mapper.java"));
					velocityEngine.mergeTemplate(templateLocation+"mapper.vm","utf-8", context, mapperWriter);
					mapperWriter.flush();
					mapperWriter.close();
					
					FileWriter xmlWriter = new FileWriter(new File(Config.FILE_SAVE_PATH+"/"+table.getClassNameDefine()+"Mapper.xml"));
					velocityEngine.mergeTemplate(templateLocation+"mapperXML.vm","utf-8", context, xmlWriter);
					xmlWriter.flush();
					xmlWriter.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
