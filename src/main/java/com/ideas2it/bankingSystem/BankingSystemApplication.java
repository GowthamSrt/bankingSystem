package com.ideas2it.bankingSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingSystemApplication {

	public static void main(String[] args) {
		System.out.println("Application starts");
		SpringApplication.run(BankingSystemApplication.class, args);
	}
}
