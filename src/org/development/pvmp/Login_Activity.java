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

public class Login_Activity extends ActionBarActivity {

	private User userToBeLogged = new User();
	private EditText editText_username;
	//private EditText editText_password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		editText_username = (EditText) findViewById(R.id.editText_username);
		//editText_password = (EditText) findViewById(R.id.editText_password);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void clickLogin (View view) {
		String username = editText_username.getText().toString();
		UserDAO userDao = UserDAO.getInstance(getApplicationContext());
		userToBeLogged = userDao.selectByUsername(username);
		Intent i = new Intent();
		i.setClass(this, MainActivity.class);
		i.putExtra ("User", userToBeLogged);
		startActivity(i);
	}
	
    public void clickRegister (View view) {
    	Intent i = new Intent();
    	i.setClass(this, UserRegisterActivity.class);
    	startActivity(i);
    }
    
    public void clickGuest (View view) {
    	Intent i = new Intent();
    	i.setClass(this, MainActivity.class);
    	i.putExtra("User", userToBeLogged);
    	startActivity(i);
    }
}
