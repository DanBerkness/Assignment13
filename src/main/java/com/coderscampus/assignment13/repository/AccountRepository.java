package com.coderscampus.assignment13.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.assignment13.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	
	Optional<Account> findById(Long accountId);
}