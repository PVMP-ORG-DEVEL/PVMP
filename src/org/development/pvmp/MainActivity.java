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
		
		checkFirstScreen(user);
		
		return;
	}
	
	public void checkFirstScreen (User user) {
		Intent i = new Intent();
		
		if (user.getDefaultUser() == null){
			i.setClass(this, LoginActivity.class);
		}
		else {
			i.setClass(this, HomeActivity.class);
			i.putExtra ("User", user);
		}
		startActivity(i);
		this.finish();
		
		return;
	}

	public User getUser() {
		return user;
	}
}
