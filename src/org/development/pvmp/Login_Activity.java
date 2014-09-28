package org.development.pvmp;


import models.User;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Login_Activity extends ActionBarActivity {

	private User userToBeLogged = new User();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
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
		Intent i = new Intent();
		i.setClass(this, MainActivity.class);
		
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
