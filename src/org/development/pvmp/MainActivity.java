package org.development.pvmp;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity implements OnItemClickListener {
	
	private DrawerLayout drawerLayout_main;
	private ListView drawerList_main;
	private FragmentManager fragmentManager;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        
        this.drawerLayout_main = (DrawerLayout)findViewById(R.id.drawerLayout_main);
        this.drawerList_main = (ListView)findViewById(R.id.drawerList_main);
        this.fragmentManager = getSupportFragmentManager();
        
        this.drawerList_main.setOnItemClickListener(this);
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		String[] menuOptions = getResources().getStringArray(R.array.navigationOptions_main);
		
		this.changeTitle(menuOptions[position]);
		this.changeFragment(position);
		this.drawerLayout_main.closeDrawers();
	}
	
	private void changeTitle (String title) {
		getSupportActionBar().setTitle(title);
	}
	
	private void changeFragment(int position) {
		android.support.v4.app.FragmentTransaction fragmentTransaction;
		fragmentTransaction = this.fragmentManager.beginTransaction();
		Fragment newFragment;
		
		switch(position) {
			case 3:
				newFragment = new AccountSettingsFragment();
				break;
			default:
				newFragment = new AccountSettingsFragment();
		}
		
		fragmentTransaction.replace(R.id.frameLayout_main, newFragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}
}
