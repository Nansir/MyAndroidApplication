package com.sir.app.db.orm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sir.app.db.orm.util.ClassUtil;
import com.sir.app.db.orm.util.CursorUtils;
import com.sir.app.db.orm.util.SQLBuilder;

import java.util.ArrayList;
import java.util.List;

public class DB {
	private static DB myDb;
	private static SQLiteHelpder sqliteHelpder;
	private SQLiteDatabase db;

	private DB(Context context) {
		sqliteHelpder = new SQLiteHelpder(context);
		this.db = sqliteHelpder.getWritableDatabase();
	}

	public static synchronized DB getInstance(Context context) {
		if ((myDb == null)
				|| ((myDb != null) && (myDb.db != null) && (!myDb.db.isOpen()))) {
			myDb = new DB(context);
		}

		return myDb;
	}

	public void beginTransaction() {
		this.db.beginTransaction();
	}

	public void commitTransaction() {
		this.db.setTransactionSuccessful();
		this.db.endTransaction();
	}

	public void save(Object entity) {
		checkTableExist(entity.getClass());
		execSQL(SQLBuilder.getInsertSQL(entity));
	}

	public void delete(Object entity) {
		checkTableExist(entity.getClass());
		execSQL(SQLBuilder.getDeleteSQL(entity));
	}

	public <T> void deleteByWhere(Class<T> clazz, String where) {
		checkTableExist(clazz);
		execSQL(SQLBuilder.getDeleteSqlByWhere(clazz, where));
	}

	public <T> void deleteAll(Class<T> clazz) {
		checkTableExist(clazz);
		execSQL(SQLBuilder.getDeletAllSQL(clazz));
	}

	public void update(Object entity) {
		checkTableExist(entity.getClass());
		execSQL(SQLBuilder.getUpdateSQL(entity));
	}

	public void update(Object entity, String strWhere) {
		checkTableExist(entity.getClass());
		execSQL(SQLBuilder.getUpdateSQLByWhere(entity, strWhere));
	}

	public <T> void dropTable(Class<T> clazz) {
		checkTableExist(clazz);
		execSQL(SQLBuilder.getDropTableSQL(clazz));

		TableInfo table = TableInfo.get(clazz);
		table.setCheckDatabese(false);
	}

	public <T> List<Object> findAll(Class<T> clazz) {
		return findAllByWhere(clazz, null);
	}

	public <T> List<Object> findAllByWhere(Class<T> clazz, String where) {
		checkTableExist(clazz);

		String select = SQLBuilder.getSelectSQL(clazz, where);
		debugSql(select);

		List<Object> list = new ArrayList<Object>();

		Cursor cursor = this.db.rawQuery(select, null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				try {
					Object entity = CursorUtils.getEntity(cursor, clazz);

					list.add(entity);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (cursor != null)
			cursor.close();
		cursor = null;

		return list;
	}

	private void execSQL(String sql) {
		if ((this.db == null) || ((this.db != null) && (!this.db.isOpen()))) {
			this.db = sqliteHelpder.getWritableDatabase();
		}

		debugSql(sql);
		try {
			this.db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void debugSql(String sql) {
		Log.d("KK_ORM sql", sql);
	}

	public synchronized void closeDB() {
		if ((this.db != null) && (this.db.isOpen()) && (sqliteHelpder != null))
			sqliteHelpder.close();
	}

	private <T> boolean tableIsExist(Class<T> clazz) {
		TableInfo table = TableInfo.get(clazz);
		if (table.isCheckDatabese()) {
			return true;
		}

		String tabName = ClassUtil.getTableName(clazz);
		boolean result = false;
		if (tabName == null) {
			return false;
		}

		Cursor cursor = null;
		try {
			String sql = "select count(*) as c from sqlite_master where type ='table' and name ='"
					+ tabName.trim() + "' ";
			debugSql(sql);
			cursor = this.db.rawQuery(sql, null);
			if (cursor.moveToNext()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					result = true;
					table.setCheckDatabese(true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
			cursor = null;
		}
		return result;
	}

	private <T> void checkTableExist(Class<T> clazz) {
		if (!tableIsExist(clazz)) {
			String createSQL = SQLBuilder.getCreatTableSQL(clazz);
			execSQL(createSQL);
		}
	}

	public void beginTrancation() {
		this.db.beginTransaction();
	}

	public void endTrancation() {
		this.db.setTransactionSuccessful();
		this.db.endTransaction();
	}

	private class SQLiteHelpder extends SQLiteOpenHelper {
		public SQLiteHelpder(Context context) {
			super(context, SQLiteHelpder.class.getName(), null, 1);
		}

		public void onCreate(SQLiteDatabase db) {
		}

		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}
}