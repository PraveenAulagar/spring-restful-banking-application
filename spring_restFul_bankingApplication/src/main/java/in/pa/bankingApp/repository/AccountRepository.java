package in.pa.bankingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.pa.bankingApp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
