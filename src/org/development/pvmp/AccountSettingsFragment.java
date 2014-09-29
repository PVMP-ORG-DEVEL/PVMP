package org.development.pvmp;


import models.User;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AccountSettingsFragment extends Fragment {
	
	private TextView textView_showName;
	private TextView textView_showEmail;
	private TextView textView_showAge;
	private TextView textView_showEducation;
	private TextView textView_showSex;
	private TextView textView_showUsername;
	private TextView textView_showPassword;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container,
							 Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		View viewAccountSettings = inflater.inflate(R.layout.account_settings_fragment, container, false);
		
		textView_showName = (TextView) viewAccountSettings.findViewById(R.id.textView_showName);
		textView_showEmail = (TextView) viewAccountSettings.findViewById(R.id.textView_showEmail);
		textView_showAge = (TextView) viewAccountSettings.findViewById(R.id.textView_showAge);
		textView_showEducation = (TextView) viewAccountSettings.findViewById(R.id.textView_showEducation);
		textView_showSex = (TextView) viewAccountSettings.findViewById(R.id.textView_showSex);
		textView_showUsername = (TextView) viewAccountSettings.findViewById(R.id.textView_showUsername);
		textView_showPassword = (TextView) viewAccountSettings.findViewById(R.id.textView_showPassword);
		
		receiveData();
		
		return viewAccountSettings;
	}
	
	public void receiveData () {
		MainActivity mainActivity = (MainActivity) getActivity();
		User loggedUser = mainActivity.getLoggedUser();
		
		textView_showName.setText(loggedUser.getName());
		textView_showEmail.setText(loggedUser.getEmail());
		textView_showAge.setText(Integer.toString((loggedUser.getAge())));
		textView_showEducation.setText(loggedUser.getEducation());
		textView_showSex.setText(loggedUser.getSex());
		textView_showUsername.setText(loggedUser.getUsername());
		textView_showPassword.setText(loggedUser.getPassword());
	}
}

