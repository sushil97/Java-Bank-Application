package com.demo.OOPD_Project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.demo.OOPD_Project.Bean.AdminBean;
import com.demo.OOPD_Project.DBUtil.Database;
import com.demo.OOPD_Project.exception.OOPDException;

public class AdminDAO implements IAdminDAO{
	public int adminLogin(AdminBean admin) throws OOPDException {
		int status = 0;
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
					status = 1;
				else
					status = 2;
			}
			
		}
		catch(Exception e)
		{
			throw new OOPDException("Admin Login eror"+e);
		}
		if(status==2)
		{

			Timer timer = new Timer();
			  
		      Calendar calendar = Calendar.getInstance();
		      calendar.set(Calendar.HOUR_OF_DAY, 23);
		      calendar.set(Calendar.MINUTE, 58);
		      calendar.set(Calendar.SECOND, 00);
		      Date time = calendar.getTime();
		  
		      timer.schedule(new YourTask(),
		                     time);    
		}
		return status;
	}
	public boolean adminLogout(AdminBean admin) throws OOPDException {
		Connection con = Database.estabblishConnection();
		try {
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(IQuerryMapper.LOGOUT_ADMIN);
			ps.setString(1,admin.getUsername());
			ps.executeUpdate();
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
	
	static class YourTask extends TimerTask {
	      public void run() {
	         try {
				calculateInterest();
			} catch (OOPDException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
	      @SuppressWarnings("resource")
		public void calculateInterest() throws OOPDException {
	    	  Date date = new Date();
	    	  double sum = 0;
	    	  double tax = 0;
	    	  LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    	  int currentDay = localDate.getDayOfMonth();
	    	  int currentMonth = localDate.getMonthValue();
	    	  
	    	  Connection con=Database.estabblishConnection();						//Making connection to the database
	    	  try {
	    	  con.setAutoCommit(false);
	    	  PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts");
	    	  ResultSet rs = ps.executeQuery();
	    	  while(rs.next())
	    	  {
	    		  System.out.println(rs.getString(1)+" "+rs.getString(2));
	    		  ps = con.prepareStatement("SELECT interest,tax FROM admin");
    			  ResultSet r = ps.executeQuery();
    			  double itr = 0;
    			  double temp1=0;
    			  if(r.next())
    			  {
    				  itr = r.getDouble(1);
    				  tax = r.getDouble(2);
    			  }
    			  ps = con.prepareStatement("INSERT INTO "+rs.getString(1)+"_daily VALUES (?,?)");
    			  ps.setInt(1, currentDay);
    			  ps.setDouble(2,(rs.getDouble(2)*itr)/100);
    			  ps.executeUpdate();
    			  
   	    	  
    	    	  double temp=0;
	    		  if(currentDay==31 &&(currentMonth==1||currentMonth==3||currentMonth==5||currentMonth==7||currentMonth==8||currentMonth==10||currentMonth==12))
	    		  {
	    			  ps = con.prepareStatement("DELETE FROM "+rs.getString(1)+"_daily");
	    			  ps.executeUpdate();
	    			  ps = con.prepareStatement("SELECT Interest FROM "+rs.getString(1)+"_daily");
	    	    	  ResultSet rg = ps.executeQuery();
	    	    	  while(rg.next()) {
	    	    		  sum+=rg.getDouble(1);
	    	    	  }
	    	    	  temp = sum-((sum*tax)/100);
	    	    	  ps = con.prepareStatement("INSERT INTO "+rs.getString(1)+"_monthly VALUES(?,?)");
	    	    	  ps.setInt(1, currentMonth);
	    	    	  ps.setDouble(2,temp);
	    	    	  ps.executeUpdate();
	    	    	  ps = con.prepareStatement("SELECT balance FROM accounts WHERE account_number = ?");
	    	    	  ps.setString(1, rs.getString(1));
	    	    	  rg = ps.executeQuery();
	    	    	  if(rg.next())
	    	    		  temp1=rg.getDouble(1);
	    	    	  temp1+=temp;
	    	    	  ps = con.prepareStatement("UPDATE TABLE accounts SET balance = ? WHERE account_number = ?");
	    	    	  ps.setDouble(1, temp1);
	    	    	  ps.setString(2, rs.getString(1));	  
	    		  }
	    		  else if(currentDay==30&&(currentMonth==4||currentMonth==6||currentMonth==9||currentMonth==11))
	    		  {

	    			  ps = con.prepareStatement("DELETE FROM "+rs.getString(1)+"_daily");
	    			  ps.executeUpdate();
	    			  ps = con.prepareStatement("SELECT Interest FROM "+rs.getString(1)+"_daily");
	    	    	  ResultSet rx = ps.executeQuery();
	    	    	  while(rx.next()) {
	    	    		  sum+=rx.getDouble(1);
	    	    	  }
	    	    	  temp = sum-((sum*tax)/100);
	    	    	  ps = con.prepareStatement("INSERT INTO "+rs.getString(1)+"_monthly VALUES(?,?)");
	    	    	  ps.setInt(1, currentMonth);
	    	    	  ps.setDouble(2,temp);
	    	    	  ps.executeUpdate();
	    	    	  ps = con.prepareStatement("SELECT balance FROM accounts WHERE account_number = ?");
	    	    	  ps.setString(1, rs.getString(1));
	    	    	  rx = ps.executeQuery();
	    	    	  if(rx.next())
	    	    		  temp1=rx.getDouble(1);
	    	    	  temp1+=temp;
	    	    	  ps = con.prepareStatement("UPDATE TABLE accounts SET balance = ? WHERE account_number = ?");
	    	    	  ps.setDouble(1, temp1);
	    	    	  ps.setString(2, rs.getString(1));
	    		  }
	    		  else if((currentDay==28 || currentDay == 29)&&(currentMonth==2))
	    		  {

	    			  ps = con.prepareStatement("DELETE FROM "+rs.getString(1)+"_daily");
	    			  ps.executeUpdate();
	    			  ps = con.prepareStatement("SELECT Interest FROM "+rs.getString(1)+"_daily");
	    	    	  ResultSet ry = ps.executeQuery();
	    	    	  while(ry.next()) {
	    	    		  sum+=ry.getDouble(1);
	    	    	  }
	    	    	  temp = sum-((sum*tax)/100);
	    	    	  ps = con.prepareStatement("INSERT INTO "+rs.getString(1)+"_monthly VALUES(?,?)");
	    	    	  ps.setInt(1, currentMonth);
	    	    	  ps.setDouble(2,temp);
	    	    	  ps.executeUpdate();
	    	    	  ps = con.prepareStatement("SELECT balance FROM accounts WHERE account_number = ?");
	    	    	  ps.setString(1, rs.getString(1));
	    	    	  ry = ps.executeQuery();
	    	    	  if(ry.next())
	    	    		  temp1=ry.getDouble(1);
	    	    	  temp1+=temp;
	    	    	  ps = con.prepareStatement("UPDATE TABLE accounts SET balance = ? WHERE account_number = ?");
	    	    	  ps.setDouble(1, temp1);
	    	    	  ps.setString(2, rs.getString(1));
	    		  }
	    	  }
	    	  con.commit();
	    	  con.close();
	    	  }
	    	  catch(Exception e)
	    	  {
	    		  throw new OOPDException("Error fetching records for calculate interest"+e);
	    	  }
	    	  
	      }
	      
	      public void calculateMonthInterest(int CurrentMonth,String account) throws OOPDException{
	    	  double tax=0;
	    	  double sum = 0;
	    	  double balance = 0;
	    	  Connection con=Database.estabblishConnection();						//Making connection to the database
	    	  try {
	    	  con.setAutoCommit(false);
	    	  PreparedStatement ps = con.prepareStatement("SELECT tax FROM admin");
	    	  ResultSet rs = ps.executeQuery();
	    	  if(rs.next())
	    		  tax=rs.getDouble(1);
	    	  System.out.println(tax);
	    	  ps = con.prepareStatement("SELECT * FROM abcd123_daily");
	    	  rs = ps.executeQuery();
	    	  if(rs.next()) {
	    		  System.out.println(rs.getInt(1));
	    	  }
	    	  System.out.println(sum);
	    	  sum = sum -((sum*tax)/100);
	    	  ps = con.prepareStatement("INSERT INTO "+account+"_monthly VALUES(?,?)");
	    	  ps.setInt(1, CurrentMonth);
	    	  ps.setDouble(2, sum);
	    	  ps.executeUpdate();
	    	  ps = con.prepareStatement("SELECT balance FROM accounts where account_number = ?");
	    	  ps.setString(1, account);
	    	  rs = ps.executeQuery();
	    	  if(rs.next())
	    		  balance = rs.getDouble(1);
	    	  ps = con.prepareStatement("UPDATE accounts set balance = ? where account_number = ?");
	    	  ps.setDouble(1, balance+sum);
	    	  con.commit();
	    	  con.close();
	    	  }
	    	  catch(Exception e)
	    	  {
	    		  throw new OOPDException("Error calculating monthly intrest");
	    	  }
	      }
	   }

	public boolean setInterestDB(AdminBean admin) throws OOPDException {
		Connection con=Database.estabblishConnection();						//Making connection to the database
		try {
		PreparedStatement ps = con.prepareStatement("UPDATE admin set interest = ? where username = ?");
		ps.setDouble(1,admin.getInterest());
		ps.setString(2, admin.getUsername());
		ps.executeUpdate();
		}
		catch(Exception e)
		{
			throw new OOPDException("error updating interest"+e);
		}
		return false;
	}
	public double getInterestDB(AdminBean admin) throws OOPDException {
		Connection con=Database.estabblishConnection();						//Making connection to the database
		double interest=0.0;
		try {
		PreparedStatement ps = con.prepareStatement("SELECT interest FROM admin WHERE username = ?");
		ps.setString(1, admin.getUsername());
		ResultSet rs = ps.executeQuery();
		if(rs.next())
			interest=rs.getDouble(1);
		}
		catch(Exception e)
		{
			throw new OOPDException("error updating interest"+e);
		}
		admin.setInterest(interest);
		return interest;
	}
	public boolean setTaxDB(AdminBean admin) throws OOPDException{
		Connection con=Database.estabblishConnection();						//Making connection to the database
		try {
		PreparedStatement ps = con.prepareStatement("UPDATE admin set tax = ? where username = ?");
		ps.setDouble(1,admin.getInterest());
		ps.setString(2, admin.getUsername());
		ps.executeUpdate();
		}
		catch(Exception e)
		{
			throw new OOPDException("error updating interest"+e);
		}
		return false;
		
	}
	public double getTaxDB(AdminBean admin) throws OOPDException{
		Connection con=Database.estabblishConnection();						//Making connection to the database
		double interest=0.0;
		try {
		PreparedStatement ps = con.prepareStatement("SELECT tax FROM admin WHERE username = ?");
		ps.setString(1, admin.getUsername());
		ResultSet rs = ps.executeQuery();
		if(rs.next())
			interest=rs.getDouble(1);
		}
		catch(Exception e)
		{
			throw new OOPDException("error updating interest"+e);
		}
		admin.setTax(interest);
		return interest;
		
	}
	public String showReport(String accountNumber) throws OOPDException{
		
		Connection con=Database.estabblishConnection();						//Making connection to the database
		System.out.println(accountNumber);
		String results=null;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts WHERE account_number = ?");
			ps.setString(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				PreparedStatement p = con.prepareStatement("SELECT * FROM "+accountNumber+"_monthly");
				ResultSet r = p.executeQuery();
				r.next();
				String x=r.getString(1)+" "+r.getString(2)+"\n";
				while(r.next())
				{
					x+=r.getString(1)+" "+r.getString(2)+"\n";
				}
				results = x;
			}
			else
			results = "None";
		}
		catch(Exception e)
		{
			throw new OOPDException("Show report error"+e);
		}
		return results;
		
	}
}
