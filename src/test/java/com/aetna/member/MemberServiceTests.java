package com.aetna.member;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.aetna.member.dao.MemberRepository;
import com.aetna.member.dto.Login;
import com.aetna.member.dto.Member;
import com.aetna.member.service.MemberService;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTests {

	
	@InjectMocks
	MemberService memberService;
	
	@Mock
	MemberRepository memberRepo;
	
	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
		this.memberService = new MemberService();
		ReflectionTestUtils.setField(this.memberService, "memberRepo", this.memberRepo);
	}
	
	@Test
	public void createTests() throws IOException
	{
		Member member = new Member();
		member.setAddress("address");
		member.setEmail("email@gma.com");
		member.setFirstName("firstName");
		member.setLastName("lastName");
		member.setMemberId("memberId");
		member.setPassword("password");
		member.setSsn("ssn");
		member.setUuid("uuid");
		when(this.memberRepo.save(member)).thenReturn(member);
		assertNotNull(memberService.createMember(member));
	}
	
	@Test
	public void updateTests() throws IOException
	{
		Member member = new Member();
		member.setAddress("address");
		member.setEmail("email@gma.com");
		member.setFirstName("firstName");
		member.setLastName("lastName");
		member.setMemberId("memberId");
		member.setPassword("password");
		member.setSsn("ssn");
		member.setUuid("uuid");
		when(this.memberRepo.save(member)).thenReturn(member);
		assertNotNull(memberService.updateMember(member));
	}
	
	@Test
	public void deleteTests() throws IOException
	{
		verify(memberRepo, times(0)).deleteById("memberId");
		memberService.deleteMember(Mockito.any());
	}
	
	@Test
	public void login() throws IOException
	{
		List<Member> memberList = new ArrayList<>();
		Member member = new Member();
		member.setAddress("address");
		member.setEmail("email@gma.com");
		member.setFirstName("firstName");
		member.setLastName("lastName");
		member.setMemberId("memberId");
		member.setPassword("password");
		member.setSsn("ssn");
		member.setUuid("uuid");
		memberList.add(member);
		Login login = new Login();
		login.setEmail("email@gma.com");
		login.setPassword("password");
		when(this.memberRepo.findByEmail(member.getEmail())).thenReturn(memberList);
		assertNotNull(memberService.loginMember(login));
	}
	
	@Test
	public void loginFailure() throws IOException
	{
		List<Member> memberList = new ArrayList<>();
		Member member = new Member();
		member.setAddress("address");
		member.setEmail("email@gma.com");
		member.setFirstName("firstName");
		member.setLastName("lastName");
		member.setMemberId("memberId");
		member.setPassword("password");
		member.setSsn("ssn");
		member.setUuid("uuid");
		memberList.add(member);
		Login login = new Login();
		login.setEmail("email@gma.com");
		login.setPassword("passwor");
		when(this.memberRepo.findByEmail(member.getEmail())).thenReturn(memberList);
		assertNotNull(memberService.loginMember(login));
	}
	
	@Test
	public void loginFailureEmpty() throws IOException
	{
		List<Member> memberList = new ArrayList<>();
		Login login = new Login();
		login.setEmail("email@gma.com");
		login.setPassword("passwor");
		when(this.memberRepo.findByEmail("email@gma.com")).thenReturn(memberList);
		assertNull(memberService.loginMember(login));
	}
}
