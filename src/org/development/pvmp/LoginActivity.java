package org.development.pvmp;


import models.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	private User userToBeLogged;
	private EditText editText_username;
	private EditText editText_password;
	private TextView textView_errorLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		userToBeLogged = new User();
		
		takeDataFromView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void takeDataFromView () {
		editText_username = (EditText) findViewById(R.id.editText_username);
		editText_password = (EditText) findViewById(R.id.editText_password);
		textView_errorLogin = (TextView) findViewById(R.id.textView_errorLogin);
	}
	
	/*
	 * This method will be the one that do the "login" action, only if the username
	 * and the password typed exist on the DataBase.
	 */
	public void clickLogin (View view) {
		String username = editText_username.getText().toString();
		String password = editText_password.getText().toString();
		userToBeLogged = userToBeLogged.verifyExistingUser(username, password, getApplicationContext());
		
		if(userToBeLogged != null) {
			Intent i = new Intent();
			i.setClass(this, HomeActivity.class);
			i.putExtra ("User", userToBeLogged);
			startActivity(i);
			}
		else {
			userToBeLogged = new User();
			textView_errorLogin.setVisibility(1);
			editText_username.setText("");
			editText_password.setText("");
			editText_username.setFocusableInTouchMode(true);
			editText_username.requestFocus();
		}
	}
	
	/*
	 * This one do the transition between the LoginActivity (this) and the UserRegisterActivity
	 */
	public void clickRegister (View view) {
    	Intent i = new Intent();
    	i.setClass(this, UserRegisterActivity.class);
    	startActivity(i);
    }
	
	/*
	 * clickGuest goes to the MainActivity, passing a "null" instantiated User, so it cannot have
	 * some features of the app.
	 */
    public void clickGuest (View view) {
    	userToBeLogged = null;
    	Intent i = new Intent();
    	i.setClass(this, HomeActivity.class);
    	i.putExtra("User", userToBeLogged);
    	startActivity(i);
    }
}
