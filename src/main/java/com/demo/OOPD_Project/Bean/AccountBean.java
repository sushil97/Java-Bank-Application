package com.demo.OOPD_Project.Bean;

public class AccountBean {
	private double balance;
	private AccountHolderBean customer;
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
		return "AccountBean [balance=" + balance + ", customer=" + customer + "]";
	}	
}
