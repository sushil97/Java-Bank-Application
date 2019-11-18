package com.demo.OOPD_Project.dao;

import com.demo.OOPD_Project.Bean.AccountHolderBean;
import com.demo.OOPD_Project.exception.OOPDException;

public interface IAccountHolderDAO {
	public boolean ClientLogin(AccountHolderBean client) throws OOPDException;
}
