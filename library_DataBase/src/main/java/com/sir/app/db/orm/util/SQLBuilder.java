package com.sir.app.db.orm.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SQLBuilder {
	public static String primary_key = "id";

	public static String getSelectSQL(Class<?> clazz, String where) {
		StringBuilder sb = new StringBuilder();

		Object entity = new Object();
		try {
			entity = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		sb.append("SELECT * FROM ");
		sb.append(ClassUtil.getTableName(entity.getClass()) + " ");

		if ((where != null) && (!where.equals(""))) {
			sb.append(where);
		}
		sb.append(" ORDER BY id");

		return sb.toString();
	}

	public static String getSelectSQL(Class<?> clazz) {
		return getSelectSQL(clazz, null);
	}

	public static String getCreatTableSQL(Class<?> clazz) {
		StringBuilder sql = new StringBuilder();

		String table = ClassUtil.getTableName(clazz);

		sql.append("CREATE TABLE IF NOT EXISTS ");
		sql.append(table);
		sql.append("( ");

		Field[] fields = ClassUtil.getDeclaredFields(clazz);
		fields = removePrimaryKey(fields);

		sql.append("\"" + primary_key + "\" INTEGER PRIMARY KEY AUTOINCREMENT,");

		for (Field field : fields) {
			sql.append(field.getName() + " ");
			sql.append(FieldUtil.getSqlType(field));
			sql.append(",");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(" );");

		return sql.toString();
	}

	public static String getInsertSQL(Object entity) {
		Class<? extends Object> clazz = entity.getClass();

		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO ");
		sql.append(ClassUtil.getTableName(clazz));
		sql.append(getPropertiesAndBracket(clazz));
		sql.append(" VALUES ");
		sql.append(getValuesAndBracket(entity));
		sql.append(";");

		return sql.toString();
	}

	public static <T> String getDropTableSQL(Class<T> clazz) {
		StringBuilder sql = new StringBuilder();
		sql.append("DROP TABLE ");
		sql.append(ClassUtil.getTableName(clazz));
		sql.append(";");

		return sql.toString();
	}

	private static String getValuesAndBracket(Object entity) {
		Class<? extends Object> clazz = entity.getClass();

		StringBuilder sb = new StringBuilder();
		sb.append("(");

		Field[] fields = ClassUtil.getDeclaredFields(clazz);
		fields = removePrimaryKey(fields);

		for (Field field : fields) {
			field.setAccessible(true);

			Object value = new Object();
			try {
				value = field.get(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}

			sb.append("\"" + value + "\"");
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");

		return sb.toString();
	}

	public static String getPropertiesAndBracket(Class<?> clazz) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");

		Field[] fields = ClassUtil.getDeclaredFields(clazz);
		fields = removePrimaryKey(fields);

		for (Field field : fields) {
			sb.append(field.getName());
			sb.append(",");
		}

		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");

		return sb.toString();
	}

	public static String getDeleteSQL(Object entity) {
		Object primaryKeyValue = FieldUtil.getValue(entity, primary_key);

		StringBuilder where = new StringBuilder();
		if ((primaryKeyValue != null) && (!primaryKeyValue.equals(""))) {
			where.append(primary_key);
			where.append(" = ");
			where.append("\"" + primaryKeyValue + "\"");
		}

		return getDeleteSqlByWhere(entity.getClass(), where.toString());
	}

	public static <T> String getDeleteSqlByWhere(Class<T> clazz, String where) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ");
		sql.append(ClassUtil.getTableName(clazz));

		sql.append(" WHERE ");
		if ((where != null) && (!where.equals("")))
			sql.append(where);
		else {
			sql.append(primary_key + "=\"\"");
		}
		sql.append(";");

		return sql.toString();
	}

	public static <T> String getDeletAllSQL(Class<T> clazz) {
		return "DELETE FROM " + ClassUtil.getTableName(clazz);
	}

	public static String getUpdateSQL(Object entity) {
		return getUpdateSQLByWhere(entity, null);
	}

	public static String getUpdateSQLByWhere(Object entity, String where) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE " + ClassUtil.getTableName(entity.getClass())
				+ " SET ");

		Field[] fields = ClassUtil.getDeclaredFields(entity.getClass());
		fields = removePrimaryKey(fields);

		for (Field field : fields) {
			Object value = FieldUtil.getValue(entity, field.getName());

			sql.append(field.getName());
			sql.append("=");
			sql.append("\"" + value + "\"");
			sql.append(",");
		}
		sql.deleteCharAt(sql.length() - 1);

		sql.append(" WHERE ");
		if ((where != null) && (!where.equals(""))) {
			sql.append(where);
		} else {
			Object primaryKeyValue = FieldUtil.getValue(entity, primary_key);

			sql.append(primary_key + "=");
			sql.append("\""
					+ (primaryKeyValue == null ? "\"\"" : primaryKeyValue)
					+ "\"");
		}
		sql.append(";");

		return sql.toString();
	}

	private static Field[] removePrimaryKey(Field[] fields) {
		List<Field> list = new ArrayList<Field>(Arrays.asList(fields));

		for (int i = 0; i < list.size(); i++) {
			Field field = (Field) list.get(i);
			String name = field.getName();
			if (name.equals(primary_key)) {
				list.remove(i--);
			}
		}

		return (Field[]) list.toArray(new Field[0]);
	}
}
