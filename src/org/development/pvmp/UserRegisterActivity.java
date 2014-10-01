/*
  *This class is responsible for managing the Activity UserRegister. 
  * This activity is the screen where the user will hold its registration on the app, 
  * Providing your data and when clicked the "Register" button, this data is 
  * Saved in the bank and he is directed to the main screen.
 */


package org.development.pvmp;

import dao.UserDAO;
import models.User;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UserRegisterActivity extends Activity {
	
	private EditText editText_trueName;
	private EditText editText_userEmail;
	private EditText editText_userAge;
	private RadioGroup radioGroup_education;
	private RadioGroup radioGroup_sex;
	private EditText editText_userName;
	private EditText editText_userPassword;
	private static User registeredUser;
	public Context context;
	private UserDAO userDao;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_register_activity);
		
		registeredUser = new User();
		this.context = getApplicationContext();
		this.userDao = UserDAO.getInstance(getApplicationContext());	
		
		takeDataFromView();
	}
	
	public void takeDataFromView () {
		this.editText_trueName = (EditText) findViewById(R.id.editText_trueName);
		this.editText_userEmail = (EditText) findViewById(R.id.editText_userEmail);
		this.editText_userAge = (EditText) findViewById(R.id.editText_userAge);
		this.radioGroup_education = (RadioGroup) findViewById(R.id.radioGroup_education);
		this.radioGroup_sex = (RadioGroup) findViewById(R.id.radioGroup_sex);
		this.editText_userName = (EditText) findViewById(R.id.editText_username_register);
		this.editText_userPassword = (EditText) findViewById(R.id.editText_password_register);
	}
	
	public void emailError(){
		showErrorToast("Email já cadastrado.");
		requestAttention(this.editText_userEmail);
	}
	
	public void usernameError(){
		showErrorToast("Nome de usuário já cadastro.");
		editText_userPassword.setText("");
		requestAttention(this.editText_userName);
	}
	
	public void fieldsError(){
		showErrorToast("Informações inválidas nos campos.");
	}
	
	public void clickRegister (View view) {
		setUserData();

		if(registeredUser.validateExistingUser(registeredUser.getUsername(), context)){
			if(registeredUser.validateExistingEmail(registeredUser.getEmail(), context)){
				if(registeredUser.validateFields(registeredUser.getPassword(), registeredUser.getName(),
						registeredUser.getEmail(), registeredUser.getAge())) {
					
					userDao.save(registeredUser);
					
					Intent i = new Intent();
					i.setClass(this, MainActivity.class);
					i.putExtra("User", registeredUser);
					startActivity(i);
					}
				else fieldsError();
			}
			else {
				emailError();
			}
		}
		else {
			usernameError();
		}
	}
	
	public void setUserData() {
		String education = null;
		String sex = null;
		
		registeredUser.setName(this.editText_trueName.getText().toString());
		registeredUser.setEmail(this.editText_userEmail.getText().toString());
		registeredUser.setAge(Integer.parseInt(this.editText_userAge.getText().toString()));
		registeredUser.setUsername(this.editText_userName.getText().toString());
		registeredUser.setPassword(this.editText_userPassword.getText().toString());
		
		switch (radioGroup_education.getCheckedRadioButtonId()) {
			case R.id.radio_elementarySchool:
				education = "Fundamental";
				break;
			case R.id.radio_highSchool:
				education = "Ensino Medio";
				break;
			case R.id.radio_graduated:
				education = "Superior";
				break;	
		}
		registeredUser.setEducation(education);
		
		switch(radioGroup_sex.getCheckedRadioButtonId()) {
			case R.id.radio_male:
				sex = "Masculino";
				break;
			case R.id.radio_female:
				sex = "Feminino";
				break;
		}
		registeredUser.setSex(sex);
	}
	
	public void requestAttention (EditText editText) {
		editText.setText("");
		editText.setFocusableInTouchMode(true);
		editText.requestFocus();
	}
	
	public void showErrorToast (CharSequence text) {
		int duration = Toast.LENGTH_LONG;

		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
}
