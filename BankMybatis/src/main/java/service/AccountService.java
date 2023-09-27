package service;

import bean.Account;

public interface AccountService {

	void makeAccount(Account acc) throws Exception;

	Account accountInfo(String id) throws Exception;
}
