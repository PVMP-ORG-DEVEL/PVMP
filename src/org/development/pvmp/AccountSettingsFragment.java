package org.development.pvmp;


import models.User;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AccountSettingsFragment extends Fragment {
	
	private TextView textView_showName;
	private TextView textView_showEmail;
	private TextView textView_showAge;
	private TextView textView_showEducation;
	private TextView textView_showSex;
	private TextView textView_showUsername;
	private Button button_edit;
	private HomeActivity mainActivity;
	private User loggedUser;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container,
							 Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		View viewAccountSettings = inflater.inflate(R.layout.account_settings_fragment, container, false);
		
		mainActivity = (HomeActivity) getActivity();
		loggedUser = mainActivity.getLoggedUser();
	
		takeDataFromView (viewAccountSettings);
		setScreenData();
		clickEdit();
		
		return viewAccountSettings;
	}
	
	public void takeDataFromView (View v) {
		textView_showName = (TextView) v.findViewById(R.id.textView_showName);
		textView_showEmail = (TextView) v.findViewById(R.id.textView_showEmail);
		textView_showAge = (TextView) v.findViewById(R.id.textView_showAge);
		textView_showEducation = (TextView) v.findViewById(R.id.textView_showEducation);
		textView_showSex = (TextView) v.findViewById(R.id.textView_showSex);
		textView_showUsername = (TextView) v.findViewById(R.id.textView_showUsername);
		button_edit = (Button) v.findViewById(R.id.button_edit);
	}
	
	public void setScreenData () {
		textView_showName.setText(loggedUser.getName());
		textView_showEmail.setText(loggedUser.getEmail());
		textView_showAge.setText(Integer.toString((loggedUser.getAge())));
		textView_showEducation.setText(loggedUser.getEducation());
		textView_showSex.setText(loggedUser.getSex());
		textView_showUsername.setText(loggedUser.getUsername());
	}
	
	public void clickEdit () {
		button_edit.setOnClickListener(new OnClickListener () {
			@Override
			public void onClick(View v) {
				mainActivity.changeFragment(5);
			}
		});
	}
}

