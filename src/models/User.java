package models;

import java.io.Serializable;

import android.content.Context;

import dao.UserDAO;

public class User implements Serializable {
	private static final long serialVersionUID = -6329621094685424751L;
	
	private String username;
	private String password;
	private String name;
	private String email;
	private int age;
	private String education;
	private String sex;
	private static UserDAO userDao;
	
	public User () {
		this.name = null;
		this.username = null;
		this.password = null;
		this.email = null;
		this.age = 0;
		this.education = null;
		this.sex = null;
	}
	
	public User(String name, String username, String password, String email
			, int age, String education, String sex){
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
		this.education = education;
		this.sex = sex;
    }
	
	public String getName() {
        
        return name;
    }
	
	public String getUsername() {
        
        return this.username;
    }

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public User verifyExistingUser (String username, String password, Context context) {
		User user = new User();
		userDao = UserDAO.getInstance(context);
		user = userDao.selectByUsername(username);
		
		if (user.getUsername() != null && user.getPassword().equals(password)) 
			return user;
		else
			return null;
	}
	
	public Boolean validateExistingEmail (String email, Context context){
		User user = new User();
		userDao = UserDAO.getInstance(context);
		user = userDao.selectByEmail(email);
		
		if (user.getEmail() == null)
			return true;
		else 
			return false;
	}
	
	public Boolean validateExistingUser (String username, Context context){
		User user = new User();
		userDao = UserDAO.getInstance(context);
		user = userDao.selectByUsername(username);
		
		if (user.getUsername() == null)
			return true;
		else 
			return false;
	}
	
	public Boolean validatePassword(String password){
		if(password != null && password.length()>=6 && password.length()<=15)
			return true;
		else
			return false;
	}
	
	public Boolean validateName(String name){
		if(name != null && name.length()<=50)
			return true;
		else
			return false;
	}
	
	public Boolean validateEmail(String email){
		if(email != null && email.length()<=40)
			return true;
		else 
			return false;
	}
	
	public Boolean validateAge(int age){
		if(age > 0 && age < 120)
			return true;
		else
			return false;
	}
	
	public Boolean validateFields(String password, String name, String email, int age ){
		if(validateAge(age) && validateEmail(email) && validateName(name) && validatePassword(password))
			return true;
		else 
			return false;
	}
}
