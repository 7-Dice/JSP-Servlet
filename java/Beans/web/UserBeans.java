package Beans.web;

import java.io.Serializable;

public class UserBeans implements Serializable
{
	private String userId;
	private String pass;
	private String hundleName;
	private int age;
	private String eMail;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getHundleName() {
		return hundleName;
	}

	public void setHundleName(String hundleName) {
		this.hundleName = hundleName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
}
