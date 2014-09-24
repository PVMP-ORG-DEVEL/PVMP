package models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import database.DatabaseHelper;
import android.content.Context;

public class User {
	private static final String TABLE_NAME = "USER";
	private String username;
	private String password;
	private String name;
	private String email;
	private String age;
	private String education;
	private String sex;
	private Context context;
	
	public User () {
		this.name = null;
		this.username = null;
		this.password = null;
		this.email = null;
		this.age = null;
		this.education = null;
		this.sex = null;
	}
	
	public User(String name, String username, String password, String email
			, String age, String education, String sex){
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
		this.education = education;
		this.sex = sex;
    }
	
	public String getName() {
        
        return name;
    }
	
	public String getUsername() {
        
        return this.username;
    }

	public String getPassword() {
		return this.password;
	}

	public void setUserPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUserName(String username) {
		this.username = username;
	}
	
	public User select (String username) throws Exception {
		User user = null;
		Cursor cursor = null;
		
		Context context = null;
		SQLiteDatabase sqlLite = new DatabaseHelper(context).getReadableDatabase();
		
		String where = "USERNAME = ?";
		String[] columns = new String[] {"USERNAME", "PASSWORD", "NAME", "EMAIL", "AGE", "EDUCATION", "SEX"};
		String arguments[] = new String[] {username};
		cursor = sqlLite.query(TABLE_NAME, columns, where, arguments, null, null, null);
		
		if (cursor != null && cursor.moveToFirst()) {
			user = new User();
			user.setUserName(cursor.getString(cursor.getColumnIndex("USERNAME")));
			user.setUserPassword(cursor.getString(cursor.getColumnIndex("PASSWORD")));
			user.setName(cursor.getString(cursor.getColumnIndex("NAME")));
			user.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
			user.setAge(cursor.getString(cursor.getColumnIndex("AGE")));
			user.setEducation(cursor.getString(cursor.getColumnIndex("EDUCATION")));
			user.setSex(cursor.getString(cursor.getColumnIndex("SEX")));
		}
		
		if (cursor != null)
			cursor.close();
				
		return user;
	}
	
	public long insert () {

		
		ContentValues content = new ContentValues();
		
		content.put("USERNAME", this.username);
		content.put("PASSWORD", this.password);
		content.put("NAME", this.name);
		content.put("EMAIL", this.email);
		content.put("AGE", this.age);
		content.put("EDUCATION", this.education);
		content.put("SEX", this.sex);
		
		return 3;
	}
	
	public int update (User user) {
		SQLiteDatabase sqlLite = new DatabaseHelper(context).getWritableDatabase();
		
		ContentValues content = new ContentValues();
		
		content.put(username, user.getUsername());
		content.put(password, user.getPassword());
		content.put(name, user.getName());
		content.put(email, user.getEmail());
		content.put(age, user.getAge());
		content.put(education, user.getEducation());
		content.put(sex, user.getSex());
		
		String where = "LOGIN = ?";
		String[] arguments = {user.getUsername()};
		
		return sqlLite.update(User.TABLE_NAME, content, where, arguments);
	}
	
	public int delete (String username) {
		SQLiteDatabase sqlLite = new DatabaseHelper(context).getWritableDatabase();
		
		String where = "LOGIN = ?";
		String[] arguments = new String[] {username};
		
		return sqlLite.delete(User.TABLE_NAME, where, arguments);
	}
}
