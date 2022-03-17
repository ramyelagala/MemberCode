package com.aetna.member.service;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.aetna.member.dao.MemberRepository;
import com.aetna.member.dto.Login;
import com.aetna.member.dto.Member;

@RestController
@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepo;
	
	@RequestMapping(value = "/createMember", method = RequestMethod.POST)
	public Member createMember(@Valid @RequestBody Member member) throws IOException, SQLIntegrityConstraintViolationException
	{
		Member member1 = null ;
		member1 =  memberRepo.save(member);
		return member1;
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
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> 
			errors.put(error.getField(), error.getDefaultMessage()));
		
		return errors;
	}
	
	/*
	 * @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	 * 
	 * @ExceptionHandler(ConstraintViolationException.class) public Map<String,
	 * String> handleConstraintViolation(ConstraintViolationException ex) {
	 * Map<String, String> errors = new HashMap<>();
	 * 
	 * ex.getConstraintViolations().forEach(cv -> { errors.put("message",
	 * cv.getMessage()); errors.put("path", (cv.getPropertyPath()).toString()); });
	 * 
	 * return errors; }
	 */
	
	 @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
	        return new ResponseEntity<>(ex.getConstraintName(), HttpStatus.BAD_REQUEST);
	    }
	
}
