package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

	private static final String DATABASE_NAME = "PVMP_Database";
	private static final int DATABASE_VERSION = 3;
	private static final String TABLE_NAME = "USER";
	
	private static final String SCRIPT_TABLE_CREATE =
			"CREATE TABLE " + TABLE_NAME + " (" +
			"USERNAME VARCHAR(15), " +
			"PASSWORD VARCHAR(15), " +
			"NAME VARCHAR(40), " +
			"EMAIL VARCHAR(40), " +
			"AGE INTEGER, " +
			"EDUCATION VARCHAR(11), " +
			"SEX VARCHAR(10));";
	
	public DatabaseHelper (Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(SCRIPT_TABLE_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub		
	}
}
