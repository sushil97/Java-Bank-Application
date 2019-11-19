package com.demo.OOPD_Project.dao;

public interface IQuerryMapper {
	/*Client Queries */
	public static final String SELECT_USER = "SELECT * FROM account_holders where account_number=? and password=sha1(?)";
	public static final String SET_ACTIVE_TRUE = "UPDATE account_holders SET active_session = true where account_number = ?";
	public static final String LOGOUT_USER = "UPDATE account_holders set active_session = false where account_number = ?";
	
	/*Admin Queries*/
	public static final String ADMIN_LOGIN = "SELECT * FROM admin where username = ? and password = sha1(?)";
	public static final String SET_ACTIVE_TRUE_ADMIN = "UPDATE admin SET active_status=true where username = ?";
	public static final String LOGOUT_ADMIN = "UPDATE admin set active_status = false where username = ?";
	public static final String GET_CURR_ACC_NUM = "SELECT * FROM current_account_number";
	public static final String INC_ACC_NUM = "UPDATE current_account_number SET account_number = ? WHERE account_number = ?";
	public static final String ADD_USER = "Insert into account_holders values(?,'sushil','tiwari',DATE '2015-12-17',SHA1(\"123\"),false);";
}
