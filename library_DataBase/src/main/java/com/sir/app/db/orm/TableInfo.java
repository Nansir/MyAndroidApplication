package com.sir.app.db.orm;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TableInfo {
	private static final HashMap<Class<?>, TableInfo> tableInfoMap = new HashMap<Class<?>, TableInfo>();

	public final HashMap<String, Property> propertyMap = new HashMap<String, Property>();
	private boolean checkDatabese;

	public static <T> TableInfo get(Class<T> clazz) {
		TableInfo tableInfo = (TableInfo) tableInfoMap.get(clazz);

		if (tableInfo == null) {
			tableInfo = new TableInfo();

			clazz.getName();
			getTableName(clazz);

			List<Property> pList = getPropertyList(clazz);
			for (Property p : pList) {
				tableInfo.propertyMap.put(p.getFieldName(), p);
			}

			tableInfoMap.put(clazz, tableInfo);
		}

		return tableInfo;
	}

	public static <T> String getTableName(Class<T> clazz) {
		String tableName = clazz.getName();
		tableName = tableName.replaceAll("\\.", "_");

		return tableName;
	}

	public static List<Property> getPropertyList(Class<? extends Object> clazz) {
		List<Property> propertyList = new ArrayList<Property>();

		Field[] fields = getDeclaredFieldsJustReflect(clazz);
		for (Field field : fields) {
			Property property = new Property();
			property.setField(field);
			property.setFieldName(field.getName());
			property.setDataType(field.getType());

			propertyList.add(property);
		}

		return propertyList;
	}

	public static Field[] getDeclaredFieldsJustReflect(
			Class<? extends Object> clazz) {
		List<Field> list = new ArrayList<Field>();

		Class<?> tClazz = clazz;

		while (!tClazz.equals(Object.class)) {
			list.addAll(Arrays.asList(tClazz.getDeclaredFields()));

			for (int j = 0; j < list.size(); j++) {
				Field field = (Field) list.get(j);

				field.setAccessible(true);
			}

			tClazz = tClazz.getSuperclass();
		}
		return (Field[]) list.toArray(new Field[0]);
	}

	public boolean isCheckDatabese() {
		return this.checkDatabese;
	}

	public void setCheckDatabese(boolean checkDatabese) {
		this.checkDatabese = checkDatabese;
	}
}
