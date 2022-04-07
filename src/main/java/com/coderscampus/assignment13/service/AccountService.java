package com.coderscampus.assignment13.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepo;

		public Account findById(Long accountId) {
			return accountRepo.findById(accountId).orElse(null);
		}
		
		public Account saveAccount(Account account) {
			accountRepo.findById(account.getAccountId()).orElse(null);
			System.out.println("in the service" + account.getAccountName());
			return accountRepo.save(account);
			
		}
				
}
