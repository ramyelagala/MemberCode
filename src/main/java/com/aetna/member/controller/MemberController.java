package com.aetna.member.controller;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.aetna.member.dto.Login;
import com.aetna.member.dto.Member;
import com.aetna.member.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/createMember", method = RequestMethod.POST)
	public Member createMember(@Valid @RequestBody Member member) throws IOException, SQLIntegrityConstraintViolationException
	{
		return memberService.createMember(member);
		
	}
	
	@RequestMapping(value = "/updateMember", method = RequestMethod.POST)
	public Member updateMember(@Valid @RequestBody Member member)
	{
		return memberService.updateMember(member);
	}
	
	@RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
	public String deleteMember(@RequestParam String memberId)
	{
		return memberService.deleteMember(memberId);
	}
	
	@RequestMapping(value = "/loginMember", method = RequestMethod.POST)
	public String loginMember(@Valid @RequestBody Login login)
	{
		return memberService.loginMember(login);
		
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> 
			errors.put(error.getField(), error.getDefaultMessage()));
		
		return errors;
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getSQLException().getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}
	
}
