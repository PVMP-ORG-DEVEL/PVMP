package org.development.pvmp;

import dao.UserDAO;
import models.User;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.view.View.OnClickListener;

public class EditSettingsFragment extends Fragment{

	private EditText editText_editName;
	private EditText editText_editEmail;
	private EditText editText_editAge;
	private RadioGroup radioGroup_editEducation;
	private EditText editText_oldPassword;
	private EditText editText_newPassword;
	private Button button_save;
	private MainActivity mainActivity;
	private User loggedUser;
	
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container,
							 Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		View viewEditSettings = inflater.inflate(R.layout.edit_settings_fragment, container, false);
		
		mainActivity = (MainActivity) getActivity();
		loggedUser = mainActivity.getLoggedUser();
		
		editText_editName = (EditText) viewEditSettings.findViewById(R.id.editText_editName);
		editText_editEmail = (EditText) viewEditSettings.findViewById(R.id.editText_editEmail);
		editText_editAge = (EditText) viewEditSettings.findViewById(R.id.editText_editAge);
		radioGroup_editEducation = (RadioGroup) viewEditSettings.findViewById(R.id.radioGroup_editEducation);
		editText_oldPassword = (EditText) viewEditSettings.findViewById(R.id.editText_oldPassword);
		editText_newPassword = (EditText) viewEditSettings.findViewById(R.id.editText_newPassword);
		
		button_save = (Button) viewEditSettings.findViewById(R.id.button_save);
		
		clickSave();
		setFieldData();
		
		return viewEditSettings;		
	}
	
	public void clickSave () {
		
		button_save.setOnClickListener(new OnClickListener () {
			@Override
			public void onClick(View v) {
				
				saveUserEdition();
				UserDAO userDao = UserDAO.getInstance(mainActivity.getApplicationContext());
				userDao.edit(loggedUser);
				mainActivity.changeFragment(3);
			}
		});
	}
	
	public void setFieldData () {
		editText_editName.setText(loggedUser.getName());
		editText_editEmail.setText(loggedUser.getEmail());
		editText_editAge.setText(Integer.toString((loggedUser.getAge())));
		
		if (loggedUser.getEducation() == "Fundamental")
			radioGroup_editEducation.check(R.id.radio_editElementarySchool);
		else if (loggedUser.getEducation() == "Medio")
			radioGroup_editEducation.check(R.id.radio_editHighSchool);
		else
			radioGroup_editEducation.check(R.id.radio_editGraduated);
	}
	
	public void saveUserEdition () {
		loggedUser.setName(this.editText_editName.getText().toString());
		loggedUser.setEmail(this.editText_editEmail.getText().toString());
		loggedUser.setAge(Integer.parseInt(this.editText_editAge.getText().toString()));
		
		String education = null;
		
		switch (radioGroup_editEducation.getCheckedRadioButtonId()) {
			case R.id.radio_editElementarySchool:
				education = "Fundamental";
				break;
			case R.id.radio_editHighSchool:
				education = "Medio";
				break;
			case R.id.radio_editGraduated:
				education = "Superior";
				break;	
		}
		
		loggedUser.setEducation(education);
		
		if (!editText_oldPassword.getText().toString().equals("") &&
				!editText_newPassword.getText().toString().equals(""))
			loggedUser.setPassword(this.editText_newPassword.getText().toString());
	}
}
