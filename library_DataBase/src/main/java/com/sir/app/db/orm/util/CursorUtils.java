package com.sir.app.db.orm.util;

import android.database.Cursor;

public class CursorUtils {
	public static <T> Object getEntity(Cursor cursor, Class<T> clazz) {
		Object entity = null;
		try {
			entity = clazz.newInstance();
			for (int column = 0; column < cursor.getColumnCount(); column++) {
				// int type = cursor.getType(column);
				String name = cursor.getColumnName(column);
				String value = cursor.getString(column);
				FieldUtil.setValue(entity, name, value);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
}