package com.demo.OOPD_Project.dao;


/*
 * This is a AccountHolderDAO class which is managing all the functionalities for the user by making connection to the database.
 * 
 * ClientLogin() function takes account holder's bean object as argument and  provides them login function if password and account number matches to any entry in the database
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.demo.OOPD_Project.Bean.AccountHolderBean;
import com.demo.OOPD_Project.DBUtil.Database;
import com.demo.OOPD_Project.exception.OOPDException;


public class AccountHolderDAO implements IAccountHolderDAO{
	
		public int ClientLogin(AccountHolderBean client) throws OOPDException {
		Connection con=Database.estabblishConnection();						//Making connection to the database
		int status = 0;												//Used to store status if login is or not
		try {
		con.setAutoCommit(false);
	/* Running the SQL query to get information about the user with given username and password */
		PreparedStatement ps = con.prepareStatement(IQuerryMapper.SELECT_USER);
		ps.setString(1, client.getAccountNumber());
		ps.setString(2, client.getPassword());
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			PreparedStatement ps1 = con.prepareStatement(IQuerryMapper.SET_ACTIVE_TRUE);
			ps1.setString(1, client.getAccountNumber());
			ps1.executeUpdate();
			if(rs.getBoolean(6))   //client is already logged in somewhere else
				status = 1;
			else 
			{
			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6));
			/*Since we got the information successful now set the respective variables with correct values*/
			client.setFname(rs.getString(2));
			client.setLname(rs.getString(3));
			client.setDateOfJoining(rs.getString(4));
			status = 2;									//If everything is fine turn login status to true.
			}
		}
		con.commit();
		con.close();
		}
		catch(Exception e)
		{
			throw new OOPDException("Error selection users "+e);
		}
		return status;
	}

		public boolean ClientLogout(AccountHolderBean client) throws OOPDException {
			
			Connection con=Database.estabblishConnection();				//Making connection to the database
			try {
			con.setAutoCommit(false);
		/* Running the SQL query to Logout user */
			PreparedStatement ps = con.prepareStatement(IQuerryMapper.LOGOUT_USER);
			ps.setString(1, client.getAccountNumber());
			ps.executeUpdate();
			client.setAccountNumber(null);
			client.setDateOfJoining(null);
			client.setFname(null);
			client.setLname(null);
			client.setPassword(null);
			client = null;
			con.commit();
			con.close();
			}
			catch(Exception e)
			{
				throw new OOPDException("Error selection users "+e);
			}
			return true;
		}
}
