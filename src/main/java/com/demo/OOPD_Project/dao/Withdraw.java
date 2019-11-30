package com.demo.OOPD_Project.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.demo.OOPD_Project.Bean.AccountBean;
import com.demo.OOPD_Project.DBUtil.Database;
import com.demo.OOPD_Project.exception.OOPDException;

public class Withdraw extends AccountDAO{
	public int doWithdraw(AccountBean account,double amount) throws OOPDException
	{
		Connection con=Database.estabblishConnection();				//Making connection to the database
		int status = 0;
		try {
			con.setAutoCommit(false);
			if(amount>=account.getBalance())
				status = 1;
			else {
			PreparedStatement ps = con.prepareStatement(IQuerryMapper.WITHDRAW_ACCOUNT);
			ps.setDouble(1, account.getBalance()-amount);
			ps.setString(2, account.getCustomer().getAccountNumber());
			ps.executeUpdate();
			account.setBalance(account.getBalance()-amount);
			status = 2;
			con.commit();
			con.close();
			}
			return status;
		}
		catch(Exception e)
		{
			throw new OOPDException("error withdrawing"+e);
		}
	}
}
