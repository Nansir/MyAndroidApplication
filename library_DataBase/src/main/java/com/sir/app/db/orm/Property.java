package com.sir.app.db.orm;

import java.lang.reflect.Field;

public class Property {
	private String fieldName;
	private Class<?> dataType;
	private Field field;

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Class<?> getDataType() {
		return this.dataType;
	}

	public void setDataType(Class<?> dataType) {
		this.dataType = dataType;
	}

	public Field getField() {
		return this.field;
	}

	public void setField(Field field) {
		this.field = field;
	}
}
