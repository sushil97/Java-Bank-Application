package com.demo.OOPD_Project.Bean;

public class AdminBean {
	private String username;
	private String password;
	private double interest;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	@Override
	public String toString() {
		return "AdminBean [username=" + username + ", password=" + password + ", interest=" + interest + "]";
	}
	public AdminBean() {
		this.username = null;
		this.password = null;
		this.interest = 0.0;
	}
	
}
