package com.hx.auto.common;

import java.util.ArrayList;
import java.util.List;

/**实体类对应生成mapper.xml的数据*/
public class ReadEntityData {
	//实体类的主键id名称
	private String entityIdName;
	//表的主键id名称
	private String tableIdName;
	//主键类型
	private String keyType;
	//表的名称
	private String tableName;
	//实体类和表的所有字段名称（除了主键）
	private List<FieldAttribute> fields = new ArrayList<FieldAttribute>();
	//特殊字段，主键id,如：#{id}
	private String entityIdData;
	//特殊字段，mybatis的排序
	private String orderBy = "${orderBy}";
	//特殊字段，mybatis的实体类匹配表字段
	private String createData = "#{createData.obj}";
	//特殊字段，mybatis的传的值
	private String valueData = "#{value}";
	
	/********************************************************************/
	/** 存储实体类的表字段
	 * @param entityName 实体类字段名称
	 * @param tableName1 表名称
	 * @param dataType 类型
	 */
	public void fielData(String entityName,String tableName1,String dataType,Integer isBlob,String mybatisName) {
		fields.add(new FieldAttribute(entityName,tableName1, dataType,isBlob,mybatisName));
	}
	
	/******************************************************************/
	public String getEntityIdName() {
		return entityIdName;
	}
	public void setEntityIdName(String entityIdName) {
		this.entityIdName = entityIdName;
	}
	public String getTableIdName() {
		return tableIdName;
	}
	public void setTableIdName(String tableIdName) {
		this.tableIdName = tableIdName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<FieldAttribute> getFields() {
		return fields;
	}
	public void setFields(List<FieldAttribute> fields) {
		this.fields = fields;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getCreateData() {
		return createData;
	}
	public void setCreateData(String createData) {
		this.createData = createData;
	}
	public String getValueData() {
		return valueData;
	}

	public void setValueData(String valueData) {
		this.valueData = valueData;
	}

	public String getEntityIdData() {
		return entityIdData;
	}

	public void setEntityIdData(String entityIdData) {
		this.entityIdData = entityIdData;
	}

	/*****************************************************************/
	public static class FieldAttribute {
		//实体类字段名称
		private String entityName;
		//表字段名称
		private String tableName;
		//数据库类型
		private String dataType;
		//是否大数据
		private Integer isBlob = 0;
		//特殊字段,如在mybati中 #{id}的表示
		private String mybatisName;
		
		/**********************************/
		public FieldAttribute(String entityName,String tableName,String dataType,Integer isBlob
				,String mybatisName) {
			this.entityName = entityName;
			this.tableName = tableName;
			this.dataType = dataType;
			this.isBlob = isBlob;
			this.mybatisName = mybatisName;
		}
		
		/*********************************/
		public String getDataType() {
			return dataType;
		}
		public void setDataType(String dataType) {
			this.dataType = dataType;
		}
		public String getEntityName() {
			return entityName;
		}
		public void setEntityName(String entityName) {
			this.entityName = entityName;
		}
		public String getTableName() {
			return tableName;
		}
		public void setTableName(String tableName) {
			this.tableName = tableName;
		}
		public Integer getIsBlob() {
			return isBlob;
		}
		public void setIsBlob(Integer isBlob) {
			this.isBlob = isBlob;
		}

		public String getMybatisName() {
			return mybatisName;
		}
		public void setMybatisName(String mybatisName) {
			this.mybatisName = mybatisName;
		}
	}
}

