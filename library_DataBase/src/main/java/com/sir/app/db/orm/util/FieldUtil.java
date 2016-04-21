package com.sir.app.db.orm.util;

import android.annotation.SuppressLint;
import java.lang.reflect.Field;

@SuppressLint("UseValueOf")
public class FieldUtil {
	public static void setValue(Object entity, String fieldName, String value) {
		try {
			Field field = getField(fieldName, entity.getClass());

			if ((field != null) && (value != null)) {
				Class<?> type = field.getType();
				String type_name = type.getName();

				if (!value.equals("null")) {
					if ((type_name.equals("boolean"))
							|| (type_name.equals("bool"))) {
						if (value.equals("false"))
							field.set(entity, Boolean.valueOf(false));
						else if (value.equals("true"))
							field.set(entity, Boolean.valueOf(true));
					} else if (type.equals(Boolean.class)) {
						if (value.equals("false"))
							field.set(entity, new Boolean(false));
						else if (value.equals("true"))
							field.set(entity, new Boolean(true));
					} else if ((type_name.equals("int"))
							|| (type_name.equals(Integer.class.getName())))
						field.set(entity, new Integer(value));
					else if ((type_name.equals("long"))
							|| (type_name.equals(Long.class.getName())))
						field.set(entity, new Long(value));
					else if ((type_name.equals("float"))
							|| (type_name.equals(Float.class.getName())))
						field.set(entity, new Float(value));
					else if ((type_name.equals("double"))
							|| (type_name.equals(Double.class.getName())))
						field.set(entity, new Double(value));
					else if ((type_name.equals("char"))
							|| (type_name.equals(Character.class.getName()))) {
						field.set(entity, new Character(value.charAt(0)));
					} else if (type_name.equals(String.class.getName())) {
						field.set(entity, new String(value));
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object getValue(Object entity, String fieldName) {
		Object obj = "";
		try {
			Field field = getField(fieldName, entity.getClass());

			if (field != null)
				obj = field.get(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return obj;
	}

	private static Field getField(String fieldName, Class<?> clazz) {
		Field field = null;
		Field[] fields = ClassUtil.getDeclaredFields(clazz);
		for (Field f : fields) {
			if (f.getName().equals(fieldName)) {
				field = f;
			}
		}
		return field;
	}

	public static String getSqlType(Field field) {
		Class<?> type = null;
		if (field != null)
			type = field.getType();
		try {
			if (type != null) {
				String type_name = type.getName();

				if ((type_name.equals("int"))
						|| (type_name.equals(Integer.class.getName())))
					return "INT";
				if ((type_name.equals("long"))
						|| (type_name.equals(Long.class.getName())))
					return "LONG";
				if ((type_name.equals("float"))
						|| (type_name.equals(Float.class.getName())))
					return "FLOAT";
				if ((type_name.equals("double"))
						|| (type_name.equals(Double.class.getName())))
					return "Double";
				if ((type_name.equals("bool")) || (type_name.equals("boolean"))
						|| (type_name.equals(Boolean.class.getName())))
					return "VARCHAR(6)";
				if ((type_name.equals("char"))
						|| (type_name.equals(Character.class.getName())))
					return "CHAR(3)";
				if (type_name.equals(String.class.getName()))
					return "VARCHAR(255)";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "VARCHAR(255)";
	}
}
