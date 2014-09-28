package dao;

import database.PersistenceHelper;

import models.User;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class UserDAO {

	public static final String TABLE_NAME = "USER";
	public static final String COLUMN_USERNAME = "USERNAME";
	public static final String COLUMN_PASSWORD = "PASSWORD";
	public static final String COLUMN_NAME = "NAME";
	public static final String COLUMN_EMAIL = "EMAIL";
	public static final String COLUMN_AGE = "AGE";
	public static final String COLUMN_EDUCATION = "EDUCATION";
	public static final String COLUMN_SEX = "SEX";
	
	public static final String SCRIPT_TABLE_CREATION_USER = 
			"CREATE TABLE " + TABLE_NAME + "(" 
		    + COLUMN_USERNAME + " VARCHAR(15) NOT NULL PRIMARY KEY,"
			+ COLUMN_PASSWORD + " VARCHAR(15) NOT NULL,"
			+ COLUMN_NAME + " VARCHAR(50) NOT NULL,"
			+ COLUMN_EMAIL + " VARCHAR(40) NOT NULL,"
			+ COLUMN_AGE + " INTEGER NOT NULL,"
			+ COLUMN_EDUCATION + " VARCHAR(11) NOT NULL,"
			+ COLUMN_SEX + " VARCHAR(9) NOT NULL"
			+ ");";
	
	public static final String SCRIPT_TABLE_DELETION = 
			"DROP TABLE IF EXISTS " + TABLE_NAME;
	
	private static SQLiteDatabase database = null;
	
	private static UserDAO instance = null;
	
	public static UserDAO getInstance(Context context) {
		if(instance == null)
			instance = new UserDAO(context.getApplicationContext());
		return instance;
	}
	
	private UserDAO(Context context) {
		PersistenceHelper persistenceHelper = PersistenceHelper.getInstance(context);
		database = persistenceHelper.getWritableDatabase();
	}
	
	public void save(User user) {
		ContentValues values = generateContentValuesUser(user);
		database.insert(TABLE_NAME, null, values);
	}
	
	public User selectByUsername(String username) {
        String queryUser = "SELECT * FROM " + TABLE_NAME + " where " + COLUMN_USERNAME + " = '" + username + "'";
        User user = recoverByQuery(queryUser);
        
        return user;   
    }
	
	public User recoverByQuery (String query) {
		Cursor cursor = database.rawQuery(query, null);
 
        User user = new User();
        
        if (cursor.moveToFirst()){
        	ContentValues contentValues = new ContentValues();
        	DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
        	user = contentValuesToUser(contentValues);
        }
        
        return user; 
	}
	
	public void delete(User user) {
		String[] valuesToReplace = {
				String.valueOf(user.getUsername())
		};		
		database.delete(TABLE_NAME, COLUMN_USERNAME + " = ?", valuesToReplace);
	}
	
	public void edit(User user) {
		ContentValues values = generateContentValuesUser(user);
		
		String[] valuesToReplace = {
				String.valueOf(user.getUsername())
		};
		
		database.update(TABLE_NAME, values, COLUMN_USERNAME + " = ?", valuesToReplace);
	}
	
	public void closeConnection() {
		if(database != null && database.isOpen())
			database.close();
	}
	
	 User contentValuesToUser(ContentValues contentValues) {
        User user = new User();
        user.setUsername(contentValues.getAsString(COLUMN_USERNAME));
        user.setPassword(contentValues.getAsString(COLUMN_PASSWORD));
        user.setName(contentValues.getAsString(COLUMN_NAME));
        user.setEmail(contentValues.getAsString(COLUMN_EMAIL));
        user.setAge(contentValues.getAsInteger(COLUMN_AGE));
        user.setEducation(contentValues.getAsString(COLUMN_EDUCATION));
        user.setSex(contentValues.getAsString(COLUMN_SEX));
        
        return user;
    }
		
	private ContentValues generateContentValuesUser(User user) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_USERNAME, user.getUsername());
		values.put(COLUMN_PASSWORD, user.getPassword());
		values.put(COLUMN_NAME, user.getName());
		values.put(COLUMN_EMAIL, user.getEmail());
		values.put(COLUMN_AGE, user.getAge());
		values.put(COLUMN_EDUCATION, user.getEducation());
		values.put(COLUMN_SEX, user.getSex());
		return values;
	}
}
