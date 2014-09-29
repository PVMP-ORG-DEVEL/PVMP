package org.development.pvmp;

import models.User;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

public class EditSettingsFragment extends Fragment{

	private EditText editText_editName;
	private EditText editText_editEmail;
	private EditText editText_editAge;
	private RadioGroup radioButton_editEducation;
	private EditText editText_oldPassword;
	private EditText editText_newPassword;
	private FragmentManager fragmentManager;
	
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container,
							 Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		View viewEditSettings = inflater.inflate(R.layout.edit_settings_fragment, container, false);
		
		editText_editName = (EditText) viewEditSettings.findViewById(R.id.editText_editName);
		editText_editEmail = (EditText) viewEditSettings.findViewById(R.id.editText_editEmail);
		editText_editAge = (EditText) viewEditSettings.findViewById(R.id.editText_editAge);
		// radioButton_editEducation = (RadioGroup) viewEditSettings.findViewById(R.id.editText_editName);
		editText_oldPassword = (EditText) viewEditSettings.findViewById(R.id.editText_oldPassword);
		editText_newPassword = (EditText) viewEditSettings.findViewById(R.id.editText_newPassword);
				
		return viewEditSettings;		
	}
	
}
