package in.pa.bankingApp.service;

import java.util.List;
import java.util.Optional;

import in.pa.bankingApp.entity.Account;

public interface AccountService {

	public Account saveAcc(Account account);
	public List<Account> fetchAll();
	public Optional<Account> fetchSingleAcc(long accNum);
	public Account updateAccount(long accNum, Account account);
	public Account depositAmt(long accNum, double depositAmt);
	public Account withdrawAmount(long accNum, double withdrawAmt);
	public void transferMoney(long fromAccNum, double debitAmt, long toAccNum, double creditAmt);
	public boolean closeAccount(long accNum, String panNum);
}
