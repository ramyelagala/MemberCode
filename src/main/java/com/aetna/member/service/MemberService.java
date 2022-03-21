package com.aetna.member.service;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.aetna.member.dao.MemberRepository;
import com.aetna.member.dto.Login;
import com.aetna.member.dto.Member;


@Component
public class MemberService {

	@Autowired
	private MemberRepository memberRepo;
	
	public Member createMember(Member member) throws IOException, SQLIntegrityConstraintViolationException
	{
		  
		return memberRepo.save(member);
	}
	
	public Member updateMember(Member member)
	{
		return memberRepo.save(member);
	}
	
	public String deleteMember(String memberId)
	{
		Optional<Member> member = memberRepo.findById(memberId);
		if(member.isPresent())
		{
		memberRepo.deleteById(memberId);
		return "Member Deleted Successfully";
		}else
		{
			return "Member not present";
		}
	}
	
	public String loginMember(Login login)
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
