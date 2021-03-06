package org.development.pvmp;

import dao.UserDAO;
import models.User;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.view.View.OnClickListener;

public class EditSettingsFragment extends Fragment {

	private EditText editText_editName;
	private EditText editText_editEmail;
	private EditText editText_editAge;
	private RadioGroup radioGroup_editEducation;
	private EditText editText_oldPassword;
	private EditText editText_newPassword;
	private Button button_save;
	private HomeActivity mainActivity;
	private User loggedUser;
	private Context context;
	
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container,
							 Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		View viewEditSettings = inflater.inflate(R.layout.edit_settings_fragment, container, false);
		
		mainActivity = (HomeActivity) getActivity();
		loggedUser = mainActivity.getLoggedUser();
		context = mainActivity.getApplicationContext();
		
		takeDataFromView(viewEditSettings);
		clickSave();
		setFieldData();
		
		return viewEditSettings;		
	}
	
	public void takeDataFromView (View v) {
		editText_editName = (EditText) v.findViewById(R.id.editText_editName);
		editText_editEmail = (EditText) v.findViewById(R.id.editText_editEmail);
		editText_editAge = (EditText) v.findViewById(R.id.editText_editAge);
		radioGroup_editEducation = (RadioGroup) v.findViewById(R.id.radioGroup_editEducation);
		editText_oldPassword = (EditText) v.findViewById(R.id.editText_oldPassword);
		editText_newPassword = (EditText) v.findViewById(R.id.editText_newPassword);
		button_save = (Button) v.findViewById(R.id.button_save);
	}
	
	public void clickSave () {
		button_save.setOnClickListener(new OnClickListener () {
			@Override
			public void onClick(View v) {
				saveUserEdition();
				
				boolean passVerification = passwordsVerification();
				int validationResult = User.validationResult(loggedUser, context);
				
				if (validationResult > 0 && validationResult < 6) {
					ErrorHandlingUtil.displayEditError(editText_editEmail, editText_editName,
													   editText_newPassword, editText_editAge,
													   validationResult, context);
				}
				else if (passVerification) {
					if (!editText_newPassword.getText().toString().equals("")) {
						loggedUser.setPassword(editText_newPassword.getText().toString());
						ErrorHandlingUtil.showToast("Senha alterada com sucesso.", context);
					}
					
					UserDAO userDao = UserDAO.getInstance(mainActivity.getApplicationContext());
					userDao.edit(loggedUser);
					mainActivity.changeFragment(3);
				}
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
		if(this.editText_editAge.getText().toString().equals(""))
			loggedUser.setAge(0);
		else
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
	}
	
	public boolean passwordsVerification () {
		String oldP = editText_oldPassword.getText().toString();
		String newP = editText_newPassword.getText().toString();
		
		if (!oldP.equals("")) {
			if (oldP.equals(loggedUser.getPassword())) {
				if(User.validatePasswordSize(newP)) {
					if (User.validatePasswordFormat(newP))
						return true;
					else {
						ErrorHandlingUtil.genericError(editText_newPassword, "Sua senha deve ser formada apenas por letras e n�meros.", context);
						return false;
					}
					
				}
				else {
					ErrorHandlingUtil.genericError(editText_newPassword, "Sua senha deve ter de 6 a 15 caracteres.", context);
					return false;
				}
			}
			else {
				ErrorHandlingUtil.genericError(editText_oldPassword, "Senha antiga n�o correspondente.", context);
				return false;
			}
		}
		else {
			if(!newP.equals("")) {
				ErrorHandlingUtil.genericError(editText_oldPassword, "Senha antiga n�o correspondente.", context);
				return false;
			}		
		}
		return true;
	}
}
