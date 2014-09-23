package models;

public class User {
	private String name;
	private String userName;
	private String userPassword;
	private String email;
	private Integer age;
	private String education;
	private String sex;
	
	public User(String name, String userName, String userPassword, String email
			, Integer age, String education, String sex){
		this.name = name;
		this.userName = userName;
		this.userPassword = userPassword;
		this.email = email;
		this.age = age;
		this.education = education;
		this.sex = sex;

    }
	
	public String getName() {
        
        return name;
    }
	
	public String getUserName() {
        
        return userName;
    }

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
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

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
