package com.demo.OOPD_Project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.demo.OOPD_Project.Bean.AccountBean;
import com.demo.OOPD_Project.DBUtil.Database;
import com.demo.OOPD_Project.exception.OOPDException;

public class AccountDAO implements IAccountDAO{
	public void getBalanceFromDB(AccountBean account) throws OOPDException {
		Connection con=Database.estabblishConnection();				//Making connection to the database
		try {
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(IQuerryMapper.SET_BALANCE);
			ps.setString(1, account.getCustomer().getAccountNumber());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				account.setBalance(rs.getDouble(1)); 
			con.commit();
			con.close();
		}
		catch(Exception e)
		{
			throw new OOPDException("Error getting balance "+e);
		}
	}
}
