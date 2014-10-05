/*
 * This class is responsible for managing the Activity Main.
 * this activity is where will be the main screen of the app, where it had propositions 
 * and will have a side menu where the user can view their account settings, 
 * logout.
 */

package org.development.pvmp;

import dao.UserDAO;
import models.User;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class HomeActivity extends ActionBarActivity implements OnItemClickListener {
	
	private DrawerLayout drawerLayout_main;
	private ListView drawerList_main;
	private FragmentManager fragmentManager;
	private static User loggedUser;
	public Context context;
	UserDAO userDao;

    public User getLoggedUser() {
		return loggedUser;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        userDao = UserDAO.getInstance(getApplicationContext());
        
        takeDataFromView();
        loggedUser = (User) getIntent().getSerializableExtra("User");
        context = getApplicationContext();
        if (loggedUser != null) {
        	loggedUser.setDefaultUser("S");
        	userDao.edit(loggedUser);
        }
    }
	
	public void takeDataFromView () {
		this.drawerLayout_main = (DrawerLayout)findViewById(R.id.drawerLayout_main);
        this.drawerList_main = (ListView)findViewById(R.id.drawerList_main);
        this.fragmentManager = getSupportFragmentManager();
        this.drawerList_main.setOnItemClickListener(this);
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		if (position == 4) {
			if (loggedUser != null) {
				loggedUser.setDefaultUser("N");
				userDao.edit(loggedUser);
			}
			leaveActivity();
		}
		else {
			this.changeFragment(position);
			this.drawerLayout_main.closeDrawers();
		}
	}
	
	private void changeTitle (String title) {
		getSupportActionBar().setTitle(title);
	}
	
	public void changeFragment(int position) {
		String[] menuOptions = getResources().getStringArray(R.array.navigationOptions_main);
		android.support.v4.app.FragmentTransaction fragmentTransaction;
		fragmentTransaction = this.fragmentManager.beginTransaction();
		Fragment newFragment;
		
		switch(position) {
			case 3:
				newFragment = new AccountSettingsFragment();
				break;
			case 5:
				newFragment = new EditSettingsFragment();
				break;
			default:
				newFragment = new AccountSettingsFragment();
		}
		
		if (loggedUser == null && position == 3) 
			ErrorHandlingUtil.showToast("Você deve estar logado para acessar essa área!", context);
		else {
			if (position >= 0 && position < 4)
				this.changeTitle(menuOptions[position]);
			fragmentTransaction.replace(R.id.frameLayout_main, newFragment);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
		}
	}
	
	public void clickDelete(View view){
		UserDAO userDao = UserDAO.getInstance(getApplicationContext());
		
		userDao.delete(loggedUser);
		
		leaveActivity();
	}
	
	private void leaveActivity () {

		Intent i = new Intent();
		i.setClass(this, LoginActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
	}
}
