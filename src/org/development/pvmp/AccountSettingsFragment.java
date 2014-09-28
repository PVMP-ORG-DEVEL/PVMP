package org.development.pvmp;

import java.util.List;

import dao.UserDAO;

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
	private List<User> users;
	
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
		
		receiveData();
		
		return viewAccountSettings;
	}
	
	public void receiveData () {
		UserDAO userDAO = UserDAO.getInstance(getActivity().getApplicationContext());
		users = userDAO();
		
		//vai ser mudado
		textView_showName.setText(users.get(0).getName());
		textView_showEmail.setText(users.get(0).getEmail());
		textView_showAge.setText(Integer.toString((users.get(0).getAge())));
		textView_showEducation.setText(users.get(0).getEducation());
		textView_showSex.setText(users.get(0).getSex());
	}
}
