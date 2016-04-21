package com.sir.app.db.orm.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.sir.app.db.orm.Property;
import com.sir.app.db.orm.TableInfo;

public class ClassUtil {
	public static <T> String getTableName(Class<T> clazz) {
		return TableInfo.getTableName(clazz);
	}

	public static <T> Field[] getDeclaredFields(Class<T> clazz) {
		List<Field> list = new ArrayList<Field>();

		TableInfo table = TableInfo.get(clazz);

		Set<String> keys = table.propertyMap.keySet();
		for (String key : keys) {
			Field field = ((Property) table.propertyMap.get(key)).getField();
			list.add(field);
		}

		return (Field[]) list.toArray(new Field[0]);
	}
}
