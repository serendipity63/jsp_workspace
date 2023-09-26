package service;

import dao.AccountDAO;
import dto.Account;

public class AccountService {

	private AccountDAO dao = new AccountDAO();

	public void makeAccount(Account acc) throws Exception {
		Account sacc = dao.selectAccount(acc.getId());
		if (sacc != null)
			throw new Exception("계좌번호가 중복됩니다");
		dao.insertAccount(acc);
	}

}
