package com.aetna.member.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.validation.Valid;
import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.aetna.member.dao.MemberRepository;
import com.aetna.member.dto.Login;
import com.aetna.member.dto.Member;

@RestController
@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepo;
	
	@RequestMapping(value = "/createMember", method = RequestMethod.POST)
	public Member createMember(@Valid @RequestBody Member member) throws IOException
	{
		String originalPass = member.getPassword();

		//member.setPassword(Base64.getEncoder().encodeToString(originalPass.getBytes()));

		return memberRepo.save(member);
	}
	
	@RequestMapping(value = "/updateMember", method = RequestMethod.POST)
	public Member updateMember(@Valid @RequestBody Member member)
	{
		//String originalPass = member.getPassword();

		//member.setPassword(Base64.getEncoder().encodeToString(originalPass.getBytes()));
		
		return memberRepo.save(member);
	}
	
	@RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
	public void deleteMember(@RequestParam String memberId)
	{
		memberRepo.deleteById(memberId);
	}
	
	@RequestMapping(value = "/loginMember", method = RequestMethod.POST)
	public String loginMember(@Valid @RequestBody Login login)
	{
		List<Member> members = memberRepo.findByEmail(login.getEmail());
		if(!members.isEmpty())
		{
			if(members.get(0).getPassword().equals(login.getPassword()))
			{
				return "success";
			}else
				return "User is UnAuthorized";
		}
		return null;
	}
	
}
