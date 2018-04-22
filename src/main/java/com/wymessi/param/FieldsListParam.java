package com.wymessi.param;

public class FieldsListParam {

	private String fieldName;
	
	//分页
	private int offset;
	private int limit;
	
	public String getFieldName() {
		return fieldName;
	}
	public int getOffset() {
		return offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
}
