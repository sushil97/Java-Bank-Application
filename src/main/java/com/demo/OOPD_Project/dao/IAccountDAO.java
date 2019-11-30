package com.demo.OOPD_Project.dao;

import com.demo.OOPD_Project.Bean.AccountBean;
import com.demo.OOPD_Project.exception.OOPDException;

public interface IAccountDAO {
	public void getBalanceFromDB(AccountBean account) throws OOPDException;
}
