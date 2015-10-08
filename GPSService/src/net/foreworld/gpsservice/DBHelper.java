package net.foreworld.gpsservice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context) {
		super(context, "gps.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table g_record (info varchar(256) not null);";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO
	}

}
