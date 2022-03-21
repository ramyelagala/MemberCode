package com.aetna.member.repository;


import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.aetna.member.config.MemberJpaConfig;
import com.aetna.member.dao.MemberRepository;
import com.aetna.member.dto.Member;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemberJpaConfig.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
@DirtiesContext
public class InMemoryDBIntegrationTest {

    @Autowired(required = true)
    private MemberRepository memberRepoTest;

    @Test
    public void givenStudent_whenSave_thenGetOk() {

		Member member = new Member();
		member.setMemberId("2");
		member.setAddress("Hyderabad");
		member.setEmail("ramyel@gmail.com");
		member.setFirstName("ram");
		member.setLastName("yel");
		member.setPassword("123456");
		member.setSsn("123456789");
		member.setUuid("987456321");
		Member member1 = this.memberRepoTest.save(member);
		assertTrue(member1 != null);
    }

   
}
