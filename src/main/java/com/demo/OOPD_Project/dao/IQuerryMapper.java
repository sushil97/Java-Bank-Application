package com.demo.OOPD_Project.dao;

public interface IQuerryMapper {
	public static final String SELECT_USER = "SELECT * FROM account_holders where account_number=? and password=sha1(?)";
}
