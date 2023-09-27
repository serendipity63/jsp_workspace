package service;

import bean.Account;
import dao.AccountDAO;
import dao.AccountDAOImpl;

public class AccountServiceImpl implements AccountService {

	private AccountDAO accountDao;

	public AccountServiceImpl() {
		accountDao = new AccountDAOImpl();

	}

	@Override
	public void makeAccount(Account acc) throws Exception {
		accountDao.insertAccount(acc);
	}

	@Override
	public Account accountInfo(String id) throws Exception {
		return accountDao.selectAccount(id);
	}

}
