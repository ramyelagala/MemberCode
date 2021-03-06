package com.aetna.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class AetnaMembersApplication {

	public static void main(String[] args) {
		SpringApplication.run(AetnaMembersApplication.class, args);
	}

}
