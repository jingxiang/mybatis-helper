package com.kalman.domain;


/**
 * @since 2016年3月18日
 * @author kalman03
 */
public class Field extends BaseDO {

	private String name;
	private String type;
	private String remark;

	public Field(String name, String type,String remark) {
		this.name = name.toLowerCase();
		this.type = type;
		this.remark = remark;
	}
	
	public String getJavaType(){
		switch (type.toUpperCase()) {
		case "TINYINT":
		case "SMALLINT":
		case "MEDIUMINT":
		case "INT":
		case "INTEGER":
			return "int";
		case "BIGINT":
			return "long";
		case "FLOAT":
		case "DOUBLE":
		case "DECIMAL":
			return "BigDecimal";
		case "DATE":
		case "DATETIME":
		case "TIMESTAMP":
			return "Date";
		default:
			return "String";
		}
	}

	public String getName() {
		return name;
	}
	
	public String getFirtLetterUpCaseName(){
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
	
	public String getType() {
		return type;
	}

	public String getRemark() {
		return remark;
	}
}
