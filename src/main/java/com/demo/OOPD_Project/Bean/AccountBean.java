package com.demo.OOPD_Project.Bean;

public class AccountBean {
	private String accountNo;
	private double balance;
	private AccountHolderBean customer;
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public AccountHolderBean getCustomer() {
		return customer;
	}
	public void setCustomer(AccountHolderBean customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "AccountBean [accountNo=" + accountNo + ", balance=" + balance + ", customer=" + customer + "]";
	}	
}
