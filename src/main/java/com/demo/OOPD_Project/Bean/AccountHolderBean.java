package com.demo.OOPD_Project.Bean;


/*
 * This call is making a bean for account holder information and provides a way to store all the information related to the user with all the getters and setters for every variable
 */

public class AccountHolderBean {

	private String accountNumber;
	private String fname;
	private String lname;
	private String dateOfJoining;
	private String password;
	
	public AccountHolderBean()
	{
		this.accountNumber=null;
		this.fname = null;
		this.lname = null;
		this.dateOfJoining = null;
		this.password= null;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "AccountHolder [accountNumber=" + accountNumber + ", fname=" + fname + ", lname=" + lname
				+ ", dateOfJoining=" + dateOfJoining + ", password=" + password + "]";
	}
}
