package com.coderscampus.assignment13.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.service.AccountService;
import com.coderscampus.assignment13.service.UserService;

@Controller
public class AccountController {
	@Autowired
	UserService userService;
	
	@Autowired
	AccountService accountService;


	@PostMapping("/users/{userId}/accounts")
	public String setUserAccounts(@PathVariable Long userId) {
		User user = userService.findById(userId);
		List<Account> accts = user.getAccounts();
		Long size = (long) (accts.size() + 1);
		Account account = accountService.createAccount(size, userId);
		account.getUsers().add(user);
		user.getAccounts().add(account);
		
		 account = accountService.saveAccount(account);
		 
		return "redirect:/users/{userId}/accounts/" + account.getAccountId();
	}

	@GetMapping("/users/{userId}/accounts/{accountId}")
	public String getAccount(@PathVariable Long userId, ModelMap model, @PathVariable Long accountId) {
		Account account = accountService.findById(accountId);
		User user = userService.findById(userId);
		
		model.put("user", user);
		model.put("accounts", account);

		return "update";
	}
	@PostMapping("/users/{userId}/accounts/{accountId}")
	public String updateAccount(@PathVariable Long userId, @PathVariable Long accountId, Account account) {
		
		accountService.saveAccount(account);
		

		return "redirect:/users/{userId}/accounts/{accountId}";
	}
}
