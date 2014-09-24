package models;

public class User {
	private String name;
	private String username;
	private String password;
	private String email;
	private Integer age;
	private String education;
	private String sex;
	
	public User () {
		this.name = null;
		this.username = null;
		this.password = null;
		this.email = null;
		this.age = null;
		this.education = null;
		this.sex = null;
	}
	
	public User(String name, String username, String password, String email
			, Integer age, String education, String sex){
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

	public void setUserPassword(String password) {
		this.password = password;
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

	public void setUserName(String username) {
		this.username = username;
	}
}
