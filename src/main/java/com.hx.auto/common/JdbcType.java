package com.hx.auto.common;

/**javaType和jdbcType对比
 * 
 * @author chenjiahe
 *
 */
public class JdbcType {
	
	/**
	 * @param type 字段类型
	 * @param isBol 是否长字符串
	 * @return
	 */
	public static String jdbcTypeData(String type,boolean isBol) {
		//字母转化为小字母
		type = type.toLowerCase();
		if(type.equals("java.lang.string")) {
			if(!isBol) {
				type = "VARCHAR";
			}else {
				type = "LONGVARCHAR";
			}
		}else if(type.equals("int")) {
			type = "INTEGER";
		}else if(type.equals("java.lang.integer")) {
			type = "INTEGER";
		}else if(type.equals("boolean")) {
			type = "BOOLEAN";
		}else if(type.equals("byte")) {
			type = "TINYINT";
		}else if(type.equals("short")) {
			type = "SMALLINT";
		}else if(type.equals("long")) {
			type = "BIGINT";
		}else if(type.equals("float")) {
			type = "REAL";
		}else if(type.equals("double")) {
			type = "DOUBLE";
		}else if(type.equals("java.lang.double")) {
			type = "DOUBLE";
		}else if(type.equals("java.util.date")) {
			type = "TIMESTAMP";
		}else if(type.equals("java.sql.date")) {
			type = "TIMESTAMP";
		}else if(type.equals("blob")) {
			type = "BLOB";
		}
		return type;
	}
}
