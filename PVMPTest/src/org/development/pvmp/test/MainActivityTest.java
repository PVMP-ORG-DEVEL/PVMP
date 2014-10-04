package org.development.pvmp.test;

import android.test.ActivityInstrumentationTestCase2;
import models.User;

import org.development.pvmp.MainActivity;;

public class MainActivityTest extends
		ActivityInstrumentationTestCase2<MainActivity> {
	
	private MainActivity mainActivity;
	private User user;

	public MainActivityTest () {
		super(MainActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		setActivityInitialTouchMode(false);
		
		this.mainActivity = getActivity();
	}
	
	public void testPreConditions () {
		assertNotNull (this.mainActivity);
	}
}
