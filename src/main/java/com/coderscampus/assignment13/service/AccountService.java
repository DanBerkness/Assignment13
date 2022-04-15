package com.coderscampus.assignment13.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;
import com.coderscampus.assignment13.repository.UserRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	UserService userService;

		public Account findById(Long accountId) {
			return accountRepo.findById(accountId).orElse(null);
		}
		
		public Account saveAccount(Account account) {
			accountRepo.findById(account.getAccountId()).orElse(null);
			return accountRepo.save(account);
			
		}

		public Account createAccount(Long size, Long userId) {
			User user = userRepo.findById(userId).orElse(null);
			String accountName = "Account#" + size;
			List<User> users = new ArrayList<>();
			users.add(user);
			Account account = new Account();
			account.setAccountName(accountName);
			account.setUsers(users);
			user.getAccounts().add(account);
			userService.saveUser(user);
			
			return accountRepo.save(account);
			
		}
				
}
