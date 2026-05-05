package in.pa.bankingApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.pa.bankingApp.Dto.Deposit;
import in.pa.bankingApp.Dto.Transfer;
import in.pa.bankingApp.Dto.Withdraw;
import in.pa.bankingApp.entity.Account;
import in.pa.bankingApp.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountControllers {

	@Autowired
	private AccountService accountService;
	
	@PostMapping("/create")
	public ResponseEntity<Account> saveAccDetails(@RequestBody Account account)
	{
		 accountService.saveAcc(account);
		 return ResponseEntity.status(HttpStatus.CREATED).body(account);
	}
	
	@GetMapping("/accounts")
	public List<Account> fetchAllAccountDetails()
	{
		return accountService.fetchAll();
	}
	
	@GetMapping("/account/{accNum}")
	public ResponseEntity<Account> fetchSingleAccountDetails(@PathVariable long accNum)
	{
		Account dbaccount=accountService.fetchSingleAcc(accNum).orElse(null);
		if(dbaccount!=null)
		{
			return ResponseEntity.ok().body(dbaccount);
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/update/{accNum}")
	public ResponseEntity<Account> updateAccountDetails(@PathVariable long accNum, @RequestBody Account updatedaccount)
	{
		Account dbAccount=accountService.updateAccount(accNum, updatedaccount);
		if(updatedaccount!=null)
		{
			return ResponseEntity.ok().body(dbAccount);
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/deposit")
	public ResponseEntity<Double> depositAmount(@RequestBody Deposit deposit)
	{
		Account dbAccount=accountService.depositAmt(deposit.getAccNum(), deposit.getDepositAmt());
		if(dbAccount!=null)
		{
			return ResponseEntity.ok().body(dbAccount.getBalance());
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/withdrawn")
	public ResponseEntity<Double> withdrawnAmount(@RequestBody Withdraw withdraw)
	{
		Account dbAccount=accountService.withdrawAmount(withdraw.getAccNum(), withdraw.getWithdrawAmt());
				if(dbAccount!=null)
				{
					return ResponseEntity.ok().body(dbAccount.getBalance());
				}
				else
				{
					return ResponseEntity.notFound().build();
				}
	}
	
	@PutMapping("/transfer")
	
	public ResponseEntity<String> tranferMoneyDetails(@RequestBody Transfer transfer)
	{
		accountService.transferMoney(transfer.getFromAccNum(),transfer.getDebitAmt(),transfer.getToAccNum(),transfer.getCreditAmt());
		return ResponseEntity.ok().body("transcation successful");
	}
	
	@DeleteMapping("/close/{accNum}/{panNum}")
	public ResponseEntity<String> deleteAccount(@PathVariable long accNum, @PathVariable String panNum)
	{
		accountService.closeAccount(accNum, panNum);
		return ResponseEntity.ok().body("account closed successfully");
	}
	
}
