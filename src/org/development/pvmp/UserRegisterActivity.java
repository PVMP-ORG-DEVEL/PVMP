/*
  *This class is responsible for managing the Activity UserRegister. 
  * This activity is the screen where the user will hold its registration on the app, 
  * Providing your data and when clicked the "Register" button, this data is 
  * Saved in the bank and he is directed to the main screen.
 */


package org.development.pvmp;

import dao.UserDAO;
import models.User;
import org.development.pvmp.ErrorHandlingUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

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
	
	public void clickRegister (View view) {
		setUserData();
		int validationResult;
		
		validationResult = User.validationResult(registeredUser, context);
		
		switch(validationResult) {
			case 0:					
				userDao.save(registeredUser);
				
				ErrorHandlingUtil.showToast("Cadastro realizado com sucesso!", context);
				
				Intent i = new Intent();
				i.setClass(this, MainActivity.class);
				i.putExtra("User", registeredUser);
				startActivity(i);
				break;
			case 1:
				ErrorHandlingUtil.genericError(editText_userName, "Nome de usuário já existente.", context);
				break;
			case 2:
				ErrorHandlingUtil.genericError(editText_userEmail, "Email já existente.", context);
				break;
			case 3:
				ErrorHandlingUtil.genericError(editText_trueName, "Nome inválido.", context);
				break;
			case 4:
				ErrorHandlingUtil.genericError(editText_trueName, "Seu nome deve ter de 3 a 50 caracteres.", context);
				break;
			case 5:
				ErrorHandlingUtil.genericError(editText_userPassword, "Sua senha deve ter de 6 a 15 caracteres.", context);
				break;
			case 6:
				ErrorHandlingUtil.genericError(editText_userEmail, "Formato de email inválido.", context);
				break;
			case 7:
				ErrorHandlingUtil.genericError(editText_userEmail, "Seu email deve ter, no máximo, 40 caracteres.", context);
				break;
			case 8:
				ErrorHandlingUtil.genericError(editText_userAge, "Sua idade tem de estar entre o intervalo 10-99.", context);
				break;
			case 9:
				ErrorHandlingUtil.genericError(editText_userName, "Seu nome de usuário deve ter de 3 a 15 caracteres.", context);
				break;
			case 10:
				ErrorHandlingUtil.genericError(editText_userName, "Seu nome de usuário deve começar com uma letra.", context);
				break;
			case 11:
				ErrorHandlingUtil.genericError(editText_userName, "Seu nome de usuário deve ser composto apenas de letras e números.", context);
		}
	}
				
	public void setUserData() {
		String education = null;
		String sex = null;
		
		registeredUser.setName(this.editText_trueName.getText().toString());
		registeredUser.setEmail(this.editText_userEmail.getText().toString());
		if (this.editText_userAge.getText().toString().equals(""))
			registeredUser.setAge(0);
		else
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
}
