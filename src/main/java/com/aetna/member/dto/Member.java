package com.aetna.member.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "member")
public class Member {
	

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String memberId;
	
	@NotNull(message = "SSN Should not be Null")
	@NotBlank(message = "SSN Should not be Null")
	@Column(name ="SSN" ,unique = true)
	private String ssn;
	
	@NotBlank
	@Column(name ="FirstName")
	private String firstName;
	
	@NotBlank
	@Column(name ="LastName")
	private String lastName;
	
	@Email(message = "Email Should be Proper")
	@Column(name ="Email",unique = true)
	private String email;
	
	@NotBlank
	@Column(name ="Address")
	private String address;
	
	@NotNull
	@NotBlank
	@Column(name ="UUID")
	private String uuid;
	
	@NotNull
	@Column(name ="Password",length =48)
	private String password;

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

}
