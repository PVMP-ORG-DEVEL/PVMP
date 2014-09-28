package org.development.pvmp;


import dao.UserDAO;
import models.User;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends ActionBarActivity {

	private User userToBeLogged = new User();
	private EditText editText_username;
	private EditText editText_password;
	private TextView textView_errorLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		editText_username = (EditText) findViewById(R.id.editText_username);
		editText_password = (EditText) findViewById(R.id.editText_password);
		textView_errorLogin = (TextView) findViewById(R.id.textView_errorLogin);
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
	
	/*
	 * This method will be the one that do the "login" action, only if the username
	 * and the password typed exist on the DataBase.
	 */
	public void clickLogin (View view) {
		String username = editText_username.getText().toString();
		String password = editText_password.getText().toString();
		UserDAO userDao = UserDAO.getInstance(getApplicationContext());
		userToBeLogged = userDao.selectByUsername(username);
		
		if(userToBeLogged.getUsername() != null && userToBeLogged.getPassword().equals(password)) {
			Intent i = new Intent();
			i.setClass(this, MainActivity.class);
			i.putExtra ("User", userToBeLogged);
			startActivity(i);
			}
		else {
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
    	Intent i = new Intent();
    	i.setClass(this, MainActivity.class);
    	i.putExtra("User", userToBeLogged);
    	startActivity(i);
    }
}
