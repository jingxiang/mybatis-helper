package com.kalman.lang;

import static com.google.common.collect.Lists.newArrayList;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.google.common.base.Throwables;
import com.kalman.domain.Field;
import com.kalman.domain.Table;

/**
 * @since 2016年3月18日
 * @author kalman03
 */
public class MysqlUtils {

	private static Connection getConnection(String connectionUrl) {
		try {
			return DriverManager.getConnection(connectionUrl);
		} catch (SQLException e) {
			Throwables.propagate(e);
		}
		return null;
	}
	
	public static List<Table> getTables(String connectionUrl){
		List<Table> tables = newArrayList();
		Connection connection = getConnection(connectionUrl);
		try {
			// 获取Meta信息对象
			DatabaseMetaData meta = connection.getMetaData();
			// 数据库
			String catalog = null;
			// 数据库的用户
			String schemaPattern = null;
			// 表名
			String tableNamePattern = "%";
			// types指的是table、view
			String[] types = { "TABLE" };

			ResultSet rs = meta.getTables(catalog, schemaPattern,tableNamePattern, types);
			while (rs.next()) {
				Table table = new Table();
				String tableName = rs.getString("TABLE_NAME");
				table.setName(tableName.toLowerCase());

				ResultSet rs1 = meta.getPrimaryKeys(null, null, tableName);  
				while (rs1.next()) {
					table.setPrimaryKey(new Field(rs1.getString("COLUMN_NAME"),null,null));
				}
				
				ResultSet rs2 = meta.getColumns(null, null, tableName, "%");  
				List<Field> columns = newArrayList();
				while (rs2.next()) {
					columns.add(new Field(rs2.getString("COLUMN_NAME"),rs2.getString("TYPE_NAME"),rs2.getString("REMARKS")));
				}
				
				table.setFields(columns);
				tables.add(table);
				
				rs2.close();
				rs1.close();
			}
			rs.close();
		} catch (Exception e) {
			Throwables.propagate(e);
		}finally{
			try {connection.close();} catch (Exception e2) {}
		}
		return tables;
	}
}
