package in.pa.bankingApp.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.pa.bankingApp.entity.Account;
import in.pa.bankingApp.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{

	@ Autowired
	private AccountRepository accountRepository;
	
	@Override
	public Account saveAcc(Account account) {
		
		Account saveAccount=accountRepository.save(account);
		return saveAccount;
		//or
		//return accountRepository.save(account);
	}

	@Override
	public List<Account> fetchAll() {
		
		return accountRepository.findAll();
		// or we can write
//		List<Account> accounts=accountRepository.findAll();
//		return accounts;
	}

	@Override
	public Optional<Account> fetchSingleAcc(long accNum) {
		
		Optional<Account> dbAccount=accountRepository.findById(accNum);
		if(dbAccount.isEmpty())
		{
			throw new RuntimeException( "account number not present or wrong account number");
		}
		else
		{
			return dbAccount;
		}
	}

	@Override
	public Account updateAccount(long accNum, Account updateAccount) {
		// fetch account from database using optional<Account> or by Account
		//if account is present we update the details or throw exception
		Account dbAccount=accountRepository.findById(accNum).orElse(null);
		if(dbAccount!=null)
		{
			return accountRepository.save(updateAccount);
		}
		else
		{
			throw new RuntimeException("account number not present");
		}
	}

	@Override
	public Account depositAmt(long accNum, double depsoitAmt) {
		//we fetch account my accNum
		Account dbAccount=accountRepository.findById(accNum).orElse(null);
		if(dbAccount!=null)
		{
			if(depsoitAmt>0)
			{
				double totalBalance=dbAccount.getBalance() + depsoitAmt;
				dbAccount.setBalance(totalBalance);
				return accountRepository.save(dbAccount);
			}
			else
			{
				throw new RuntimeException(" deposit amount should be greater then 0 (i.e positive");
			}
		}
		else
		{
			throw new RuntimeException("account number not present");
		}
	}

	@Override
	public Account withdrawAmount(long accNum, double withdrawAmt) {
		
		Account dbAccount=accountRepository.findById(accNum).orElse(null);
		System.out.println(dbAccount.getBalance());
		if(dbAccount!=null)
		{
			if(dbAccount.getBalance()>=withdrawAmt && withdrawAmt>0)
			{
				double availableBalance=dbAccount.getBalance()-withdrawAmt;
				dbAccount.setBalance(availableBalance);
				return accountRepository.save(dbAccount);
				
			}
			else
			{
				throw new RuntimeException("balance is low or withdrawn amount should more then zero(i.e positive)");
			}
		}
		else
		{
			throw new RuntimeException("account number not present");
		}
	}

	@Override
	@Transactional
	public void transferMoney(long fromAccNum, double debitAmt, long toAccNum, double creditAmt) {
		Account fromAccount=accountRepository.findById(fromAccNum).orElse(null);
		Account toAccount=accountRepository.findById(toAccNum).orElse(null);
		if(fromAccount!=null&&toAccount!=null)
		{
			if(fromAccount.getBalance()>debitAmt)
			{
			
				double availableBalance=fromAccount.getBalance()-debitAmt;
				fromAccount.setBalance(availableBalance);
				accountRepository.save(fromAccount);
				
				double totalBalance=toAccount.getBalance()+creditAmt;
				toAccount.setBalance(totalBalance);
				accountRepository.save(toAccount);
			}
			else
			{
				throw new RuntimeException("balance is low");
			}
		}
		else
		{
			throw new RuntimeException("account number not present");
		}
		
	}

	@Override
	public boolean closeAccount(long accNum, String panNum) {
		Account dbAccount=accountRepository.findById(accNum).orElse(null);
		if(dbAccount!=null)
		{
			if(dbAccount.getAccNum()==accNum && dbAccount.getPanNum().equals(panNum))
			{
				accountRepository.delete(dbAccount);
				return true;
			}
			else
			{
				throw new RuntimeException("pan number or account number didn't matched");
			}
		}
		else
		{
			throw new RuntimeException("account number not present");
		}
	}

}
