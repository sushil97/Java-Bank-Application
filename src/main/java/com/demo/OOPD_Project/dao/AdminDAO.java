package com.demo.OOPD_Project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.demo.OOPD_Project.Bean.AdminBean;
import com.demo.OOPD_Project.DBUtil.Database;
import com.demo.OOPD_Project.exception.OOPDException;

public class AdminDAO implements IAdminDAO{

	public int adminLogin(AdminBean admin) throws OOPDException {
		Connection con=Database.estabblishConnection();						//Making connection to the database
		try {
			PreparedStatement ps = con.prepareStatement(IQuerryMapper.ADMIN_LOGIN);
			ps.setString(1, admin.getUsername());
			ps.setString(2, admin.getPassword());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				PreparedStatement ps1 = con.prepareStatement(IQuerryMapper.SET_ACTIVE_TRUE_ADMIN);
				ps1.setString(1, admin.getUsername());
				ps1.executeUpdate();
				if(rs.getBoolean(3))	//Admin is already logged in somewhere
					return 1;
				return 2;
			}
			
		}
		catch(Exception e)
		{
			throw new OOPDException("Admin Login eror"+e);
		}
		return 0;
	}

	public boolean adminLogout(AdminBean admin) throws OOPDException {
		Connection con = Database.estabblishConnection();
		try {
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(IQuerryMapper.LOGOUT_ADMIN);
			ps.setString(1,admin.getUsername());
			ps.executeUpdate();
			admin.setInterest(0.0);
			admin.setPassword(null);
			admin.setUsername(null);
			admin = null;
			con.commit();
			con.close();
		}
		catch(Exception e)
		{
			throw new OOPDException("Error loggin out for admin"+e);
		}
		return true;
	}

}
