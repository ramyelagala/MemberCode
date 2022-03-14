package com.aetna.member.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aetna.member.dto.Member;


@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

	public List<Member> findByEmail(String email);
	
	
}
