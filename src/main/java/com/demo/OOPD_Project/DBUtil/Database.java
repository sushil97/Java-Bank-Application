package com.demo.OOPD_Project.DBUtil;

import java.sql.*;
import com.demo.OOPD_Project.exception.OOPDException;

/*
 * This utility provides the interface to connect to the database without requiring to pass URL string every time
 */
public class Database {

static Connection conn=null;			//declaring a Connection variable
	
	public static Connection estabblishConnection() throws OOPDException{
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/OOPD","root","System"); //Calling the database drivers
		}
		catch(SQLException e){
			throw new OOPDException(e+"Error in database connection");
		}
		return conn;			//returning the connection object
	}
}
