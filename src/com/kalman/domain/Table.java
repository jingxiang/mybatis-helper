package com.kalman.domain;

import static com.google.common.collect.Sets.newHashSet;

import java.util.List;
import java.util.Set;

/**
 * @since 2016年3月18日
 * @author kalman03
 */
public class Table extends BaseDO {

	private String name;
	private List<Field> fields;
	private Field primaryKey;
	
	public Set<String> getImportClasses(){
		Set<String> imports = newHashSet();
		for(Field field : fields){
			if(field.getJavaType().equals("Date")){
				imports.add("java.util.Date");
			}else if(field.getJavaType().equals("BigDecimal")){
				imports.add("java.math.BigDecimal");
			}
		}
		return imports;
	}
	
	public String getPrimaryKeyType(){
		if(primaryKey != null){
			for(Field field : fields){
				if(field.getName().equals(primaryKey.getName())){
					return field.getJavaType();
				}
			}
		}
		return "Object";
	}
	
	
	public String getInsertSQL() {
		StringBuilder sqlBuilder = new StringBuilder("insert into ");
		sqlBuilder.append(name).append("(");
		
		StringBuilder builder = new StringBuilder();
		for (Field field : fields) {
			builder.append(field.getName().toLowerCase()).append(",");
		}
		String temp = builder.toString();
		temp = temp.substring(0, temp.length() - 1);
		
		sqlBuilder.append(temp).append(") values (");
		
		builder = new StringBuilder();
		for (Field field : fields) {
			if(field.getJavaType().equals("Date")){
				builder.append("now(),");
			}else{
				builder.append("#{").append(field.getHumpName()).append("},");
			}
		}
		temp = builder.substring(0, builder.length() - 1);
		sqlBuilder.append(temp).append(")");
		return sqlBuilder.toString();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getClassNameDefine(){
		String temp = getHumpName();
		return temp.substring(0,1).toUpperCase()+temp.substring(1);
	}

	public String getHumpName(){
		String temp = name.toLowerCase();
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<temp.length();i++){
			char c = temp.charAt(i);
			if(c == '_'){
				i++;
				builder.append((temp.charAt(i)+"").toUpperCase());
			}else{
				builder.append(temp.charAt(i));
			}
		}
		return builder.toString();
	}
	
	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public Field getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Field primaryKey) {
		this.primaryKey = primaryKey;
	}
}
