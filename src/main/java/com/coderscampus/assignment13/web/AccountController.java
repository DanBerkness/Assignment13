package com.coderscampus.assignment13.web;

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
		User user = new User();
		userService.saveUser(user);
		return "redirect:/users/" + user.getUserId();
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
		System.out.println(account.getAccountId() + " " + account.getAccountName() + account.getUsers());
		accountService.saveAccount(account);
		

		return "redirect:/users/{userId}/accounts/{accountId}";
	}
}
