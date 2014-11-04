/**
* @file PVMPView.java
* @brief
* @see
* @see http://developer.android.com/training/implementing-navigation/nav-drawer.html
* @see http://www.androidhive.info/2013/11/android-sliding-menu-using-navigation-drawer/
*/
package com.pvmp.view;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pvmp.util.Util;
import com.pvmp.models.Proposition;
import com.pvmp.models.User;
import com.pvmp.view.adapter.NavigationDrawerAdapter;
import com.pvmp.view.model.AbstractDrawerItem;
import com.pvmp.view.model.NavigationDrawerItem;
import com.pvmp.view.fragment.HomeFragment;
import com.pvmp.view.fragment.LoginFragment;
import com.pvmp.view.fragment.EditSettingsFragment;
import com.pvmp.view.fragment.PropositionFragment;
import com.pvmp.view.fragment.UserRegisterFragment;
import com.pvmp.view.fragment.FeedBackFragment;
import com.pvmp.view.fragment.PartyFragment;
import com.pvmp.view.fragment.SettingsFragment;
import com.pvmp.view.fragment.CategoriesFragment;
import com.pvmp.view.ViewObserverInterface;
import com.pvmp.controller.ControllerInterface;
import com.pvmp.controller.PVMPController;
import com.pvmp.R;

/**
* @class PVMPView
* @brief
*/
public class PVMPView extends Activity implements ViewObserverInterface 
{
	private static User user;/**< */
	//Might change.
	public static ArrayList<Proposition> propositions;

	private DrawerLayout mainDrawerLayout; /**< */
	private ListView mainDrawerList; /**< */
	private ActionBarDrawerToggle mainDrawerToggle; /**< */

	//Used to store app title
	private CharSequence mainTitle;

	//Slide menu items
	private String[] navigationMenuTitles;
	private TypedArray navigationMenuIcons;

	private ArrayList<AbstractDrawerItem> navigationDrawerItems;
	private NavigationDrawerAdapter adapter;

	//Controller and Model reference
	private ControllerInterface controller;
	//private ModelSubjectInterface model; <-Still not using

	public PVMPView()
	{}

	@Override
	public void onCreate(Bundle _savedInstanceState) 
	{
		Util.debug("+++ DO THE MAGIC! +++");
		super.onCreate(_savedInstanceState);

		//Extra data
		Util.debug("PVMPView: Add object parameter - Controller");
		this.controller = new PVMPController(this);
		this.controller.setView(this);
		
		//Start the party!!! 
		user = this.controller.openSession();
		propositions = null;

		//1 - Adjust interface
		setContentView(R.layout.activity_main);
		this.mainTitle = getTitle();

		//Load Slide menu items
		this.navigationMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		//Navigation drawer icons from resources
		this.buildIconTitleItemNavigation();
		this.buildListItemNavigation();
		//Recycle the typed array
		this.navigationMenuIcons.recycle();
		this.mainDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		//Setting the navigation drawer list adapter
		this.adapter = new NavigationDrawerAdapter(getApplicationContext(), this.navigationDrawerItems);
		this.mainDrawerList.setAdapter(this.adapter);

		//Enabling action bar app icon and bringing it a toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mainDrawerToggle = new ActionBarDrawerToggle(this, this.mainDrawerLayout, 
				R.drawable.ic_drawer,
				R.string.app_name,
				R.string.app_name)
				{
					public void onDrawerClosed(View _view)
					{
						getActionBar().setTitle(mainTitle);
						//Calling onPrepareOptinMenu() to show action bar icon
						invalidateOptionsMenu();
					}
				};
		
		if (_savedInstanceState == null)
		{
			//On first time display view for first navigation item
			this.controller.openApplication();
		}
		
		return;
	}

	private void buildIconTitleItemNavigation()
	{
		this.navigationMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
		this.mainDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		this.mainDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		this.navigationDrawerItems = new ArrayList<AbstractDrawerItem>();

		return;
	}

	public void updateUser(User _user)
	{
		user = _user;
	}
	private void buildListItemNavigation()
	{
		//Adding navigation drawer items to array
		//Proposition
		this.navigationDrawerItems.add(new NavigationDrawerItem(this.navigationMenuTitles[0], 
			this.navigationMenuIcons.getResourceId(ViewObserverInterface.CATEGORY, -1)));
		//Party
		this.navigationDrawerItems.add(new NavigationDrawerItem(this.navigationMenuTitles[1], 
			this.navigationMenuIcons.getResourceId(ViewObserverInterface.PARTY, -1)));
		//Feedback
		this.navigationDrawerItems.add(new NavigationDrawerItem(this.navigationMenuTitles[2], 
			this.navigationMenuIcons.getResourceId(ViewObserverInterface.FEEDBACK, -1)));
		//Settings
		this.navigationDrawerItems.add(new NavigationDrawerItem(this.navigationMenuTitles[3], 
			this.navigationMenuIcons.getResourceId(ViewObserverInterface.SETTING, -1)));
		//Logout
		this.navigationDrawerItems.add(new NavigationDrawerItem(this.navigationMenuTitles[4], 
			this.navigationMenuIcons.getResourceId(ViewObserverInterface.LOGOUT, -1)));

		return;
	}

	public void setController(ControllerInterface _controller)
	{
		this.controller = _controller;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu _menu)
	{
		//getMenuInflater().inflate(R.menu.main, _menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem _item)
	{
		
		//Toggle navigation drawer on selecting action bar app icon/title
		if(this.mainDrawerToggle.onOptionsItemSelected(_item))
		{
			return true;
		}
	
		//Handle action bar click
		switch(_item.getItemId())
		{
			case R.id.action_settings:
				return true;
			default:
				return super.onOptionsItemSelected(_item);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu _menu)
	{
		/*
		//If navigation drawer is opened, hide the action items
		boolean drawerOpen = this.mainDrawerLayout.isDrawerOpen(this.mainDrawerList);

		_menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(_menu);
		*/
		return true;
	}

	@Override
	public void setTitle(CharSequence _title)
	{
		this.mainTitle = _title;
		getActionBar().setTitle(this.mainTitle);
	}

	@Override
	protected void onPostCreate(Bundle _savedInstanceState)
	{
		super.onPostCreate(_savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has ocurred.
		mainDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration _newConfig)
	{
		super.onConfigurationChanged(_newConfig);
		// Pass any configuration change to the drawer toggls
		mainDrawerToggle.onConfigurationChanged(_newConfig);
	}

	//ViewObserverInterface methods
	@Override
	public void enableScreenInteraction(boolean _enable)
	{
		getActionBar().setDisplayHomeAsUpEnabled(_enable);
		getActionBar().setHomeButtonEnabled(_enable);

		return;
	}

	@Override
	public void enableDrawer(boolean _enable)
	{
		if (_enable)
		{
			this.mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
			return;
		}
		this.mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
		return;
	}

	@Override
	public void displayFragment(int _position)
	{
		Util.debug("PVMPView: Start display fragment");
		Fragment fragment = null;
		
		switch(_position)
		{
			case CATEGORY:
				fragment = new CategoriesFragment();
				break;
			case PARTY:
				fragment = new PartyFragment();
				break;
			case FEEDBACK:
				fragment = new FeedBackFragment();
				break;
			case SETTING:
				fragment = new SettingsFragment();
				break;
			case LOGOUT:
				this.updateUser(this.controller.openSession());
				if (user != null) 
				{
					user.setDefaultUser("N");
					this.controller.editUser(user);
				}
				fragment = new LoginFragment();
				break;
			case LOGIN:
				fragment = new LoginFragment();
				break;
			case REGISTER:
				fragment = new UserRegisterFragment();
				break;
			case HOME:
				fragment = new HomeFragment();
				break;
			case EDIT:
				fragment = new EditSettingsFragment();
				break;
			case PROPOSITION:
				fragment = new PropositionFragment();
				break;
			default:
				break;
		}

		Util.debug("MainActivity: Create a HOME ");
		if(fragment != null)
		{
			FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction.replace(R.id.frame_container, fragment);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
			
			//Update selected item and title, then close the drawer
			this.mainDrawerList.setItemChecked(_position, true);
			this.mainDrawerList.setSelection(_position);
			this.setTitleBasedOnMenuItem(_position);
			this.mainDrawerLayout.closeDrawer(this.mainDrawerList);
		}
		else
		{
			Util.debug("MainActivity: error in create fragment");
		}
	}

	private void setTitleBasedOnMenuItem(int _range)
	{
		if (_range < 5 && _range >= 0)
		{
			if(_range == 4)
			{
				setTitle(getTitle());
				return;
			}
			setTitle(this.navigationMenuTitles[_range]);
			return;
			
		}
		this.setTitle(getTitle());
		return;
	}
	
	//Inner class
	private class SlideMenuClickListener implements ListView.OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> _parent, View _view, int _position, long _id)
		{
			//Display view for selected navigation item
			displayFragment(_position);

			return;
		}
	}
}
