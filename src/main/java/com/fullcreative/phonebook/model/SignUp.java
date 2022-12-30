package com.fullcreative.phonebook.model;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;


@Entity(name="register")
public class SignUp{
	
	@Id
	private String email;
	private String name;
	private Long phone;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "SignUp [email=" + email + ", name=" + name + ", phone=" + phone + ", password=" + password + "]";
	}
	

}