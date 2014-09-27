package org.development.pvmp;

import java.util.List;

import dao.UserDAO;

import models.User;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AccountSettingsActivity extends Activity {
	
	private TextView textView_showName;
	private TextView textView_showEmail;
	private TextView textView_showAge;
	private TextView textView_showEducation;
	private TextView textView_showSex;
	private List<User> users;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_settings_activity);
		
		textView_showName = (TextView) findViewById(R.id.textView_showName);
		textView_showEmail = (TextView) findViewById(R.id.textView_showEmail);
		textView_showAge = (TextView) findViewById(R.id.textView_showAge);
		textView_showEducation = (TextView) findViewById(R.id.textView_showEducation);
		textView_showSex = (TextView) findViewById(R.id.textView_showSex);
		
		receiveData();
	}
	
	public void receiveData () {
		UserDAO userDAO = UserDAO.getInstance(getApplicationContext());
		users = userDAO.recoverAll();
		
		textView_showName.setText(users.get(0).getName());
		textView_showEmail.setText(users.get(0).getEmail());
		textView_showAge.setText(Integer.toString((users.get(0).getAge())));
		textView_showEducation.setText(users.get(0).getEducation());
		textView_showSex.setText(users.get(0).getSex());
	}
}
