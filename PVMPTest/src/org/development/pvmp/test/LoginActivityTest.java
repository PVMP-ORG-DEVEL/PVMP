package org.development.pvmp.test;

import org.development.pvmp.LoginActivity;
import org.development.pvmp.R;


import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {
	
	LoginActivity activity;
	EditText editText_username;
	EditText editText_password;
	TextView textView_errorLogin;
	 
	public LoginActivityTest() {
		super(LoginActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		// Find views.
		activity = getActivity();	
		editText_username = (EditText) activity.findViewById(R.id.editText_username);
		editText_password = (EditText) activity.findViewById(R.id.editText_password);
		textView_errorLogin = (TextView) activity.findViewById(R.id.textView_errorLogin);
		Button text_login = (Button) activity.findViewById(org.development.pvmp.R.id.button_login);

		
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		
		//close all resources over here.
		super.tearDown();
	}
	
	 public void testViewsVisible() {
		    ViewAsserts.assertOnScreen(editText_username.getRootView(), editText_username);
		    ViewAsserts.assertOnScreen(editText_password.getRootView(), editText_password);
		    ViewAsserts.assertOnScreen(textView_errorLogin.getRootView(), textView_errorLogin);
		  }
	
	 public void testStartingEmpty() {
		    assertTrue("Username field is empty", "".equals(editText_username.getText().toString()));
		    assertTrue("Password field is empty", "".equals(editText_password.getText().toString()));
		  }
	
	public void testPreConditions () {
		assertNotNull (getActivity());
		assertNotNull (editText_username);
		assertNotNull (editText_password);
		assertNotNull (textView_errorLogin);
	}
	
	public void testClickLogin() {
		 editText_username.clearComposingText();
		 
		// tapping the username edit text through TouchUtils class
		 TouchUtils.tapView(this, editText_username);
		 
		// sending input to username as cidadao.
		 sendKeys("cidadao");
		 
		// Clicking on the button
		 TouchUtils.clickView(this, editText_username);
		 String username;
		 
		 try {
			   // getting the input from the TextView reference
			  username = editText_username.getText().toString();
			   } catch (Exception e) {
			     return;
			   }
		 
		 editText_password.clearComposingText();
		 TouchUtils.tapView(this, editText_password);
		 sendKeys("cidadao");
		 TouchUtils.clickView(this, editText_password);
		 String password;
		 
		 try {
			   // getting the input from the TextView reference
			 password = editText_password.getText().toString();
			   } catch (Exception e) {
			     return;
			   }
	}
	
	
	
}
