package com.demo.OOPD_Project.dao;

import com.demo.OOPD_Project.Bean.AccountHolderBean;
import com.demo.OOPD_Project.exception.OOPDException;

public interface IAccountHolderDAO {
	public int ClientLogin(AccountHolderBean client) throws OOPDException;
	public boolean ClientLogout(AccountHolderBean client) throws OOPDException;
	public boolean addClient (AccountHolderBean client) throws OOPDException;
}
