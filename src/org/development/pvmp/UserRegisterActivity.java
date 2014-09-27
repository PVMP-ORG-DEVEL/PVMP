package org.development.pvmp;

import dao.UserDAO;
import models.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class UserRegisterActivity extends Activity {
	
	private EditText editText_trueName;
	private EditText editText_userEmail;
	private EditText editText_userAge;
	private RadioGroup radioGroup_education;
	private RadioGroup radioGroup_sex;
	//private EditText editText_userName;
	//private EditText editText_userPassword;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_register_activity);
		
		this.editText_trueName = (EditText) findViewById(R.id.editText_trueName);
		this.editText_userEmail = (EditText) findViewById(R.id.editText_userEmail);
		this.editText_userAge = (EditText) findViewById(R.id.editText_userAge);
		this.radioGroup_education = (RadioGroup) findViewById(R.id.radioGroup_education);
		this.radioGroup_sex = (RadioGroup) findViewById(R.id.radioGroup_sex);
		//this.editText_userName = (EditText) findViewById(R.id.editText_userName);
		//this.editText_userPassword = (EditText) findViewById(R.id.editText_userPassword);
	}
	
	public void clickRegister (View view) {
		setUserData();
		
		Intent i = new Intent();
		i.setClass(this, MainActivity.class);
		startActivity(i);
	}
	
	public void setUserData() {
		User user1 = new User();
		String education = null;
		String sex = null;
		
		user1.setName(this.editText_trueName.getText().toString());
		user1.setEmail(this.editText_userEmail.getText().toString());
		user1.setAge(Integer.parseInt(this.editText_userAge.getText().toString()));
		
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
		user1.setEducation(education);
		
		switch(radioGroup_sex.getCheckedRadioButtonId()) {
			case R.id.radio_male:
				sex = "Masculino";
				break;
			case R.id.radio_female:
				sex = "Feminino";
				break;
		}
		user1.setSex(sex);
		UserDAO userDao = UserDAO.getInstance(getApplicationContext());
		/*
		 * as linhas abaixos vao ser mudadas, só foram definidas assim pq
		 *  os campos n cabem na tela. cuidado qnd forem testar o banco mais
		 *  de uma vez, pq o userName tá definido como primary key. Então,
		 *  se forem testar um outro cadastro, mudem o username aí de baixo.
		 */
		user1.setUserName("usernameQualquer");
		user1.setPassword("passwordQualquer");
		userDao.save(user1);
	}
}
