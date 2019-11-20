package com.demo.OOPD_Project.dao;

import com.demo.OOPD_Project.Bean.AdminBean;
import com.demo.OOPD_Project.exception.OOPDException;

public interface IAdminDAO {
	public int adminLogin(AdminBean admin) throws OOPDException;
	public boolean adminLogout(AdminBean admin) throws OOPDException;
}
