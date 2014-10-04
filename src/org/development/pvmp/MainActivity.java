package org.development.pvmp;


import models.User;
import dao.UserDAO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends Activity {
	
	private User user;
	private UserDAO userDao;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		user = new User();
		userDao = UserDAO.getInstance(getApplicationContext());
		user = userDao.selectByDefault("S");
		
		
		if (user.getDefaultUser() == null){
			Intent i = new Intent();
			i.setClass(this, LoginActivity.class);
			startActivity(i);
			this.finish();
		}
		else {
			Intent i = new Intent();
			i.setClass(this, HomeActivity.class);
			i.putExtra ("User", user);
			startActivity(i);
			this.finish();
		}
	}
}
