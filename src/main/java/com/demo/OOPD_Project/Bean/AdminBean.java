package com.demo.OOPD_Project.Bean;

public class AdminBean {
	private String username;
	private String password;
	private double Interest;
	private double tax;
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
	@Override
	public String toString() {
		return "AdminBean [username=" + username + ", password=" + password + "]";
	}
	public AdminBean() {
		this.username = null;
		this.password = null;
	}
	public double getInterest() {
		return Interest;
	}
	public void setInterest(double d) {
		Interest = d;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	
}
