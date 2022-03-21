package com.aetna.member;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.aetna.member.dao.MemberRepository;
import com.aetna.member.dto.Member;

@DataJpaTest 
public class MemberServiceTests2 {

	@Autowired
	@Qualifier("memerepo")
	private MemberRepository memberRepo;
	
	@Test
	public void createTestDb() throws IOException, SQLIntegrityConstraintViolationException
	{
		Member member = new Member();
		member.setAddress("address");
		member.setEmail("email@gma.com");
		member.setFirstName("firstName");
		member.setLastName("lastName");
		member.setPassword("password");
		member.setSsn("ssn");
		member.setUuid("uuid");
		Member member1 = this.memberRepo.save(member);
		assertTrue(member1 != null);
	}
}
