package com.aetna.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaRepositories
@EnableWebSecurity
public class AetnaMembersApplication {

	public static void main(String[] args) {
		SpringApplication.run(AetnaMembersApplication.class, args);
	}

}
