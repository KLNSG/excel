package com.goldgov;

public class Column {
	private String tableName;
	private String columnName;
	private String columnAnnotation;
	private String columnType;
	private Integer notNull=2;
	private Integer sole=2;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	public String getColumnAnnotation() {
		return columnAnnotation;
	}
	public void setColumnAnnotation(String columnAnnotation) {
		this.columnAnnotation = columnAnnotation;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public Integer getNotNull() {
		return notNull;
	}
	public void setNotNull(Integer notNull) {
		this.notNull = notNull;
	}
	public Integer getSole() {
		return sole;
	}
	public void setSole(Integer sole) {
		this.sole = sole;
	}
	
}
