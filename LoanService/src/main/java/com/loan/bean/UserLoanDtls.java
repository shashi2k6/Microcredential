package com.loan.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userLoanDtls")
public class UserLoanDtls {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="address")
	private String address;
	
	@Column(name="phoneno")
	private String phoneno;
	
	@Column(name="loanamt")
	private long loanAmt;
	
	public UserLoanDtls(String firstname, String lastname, String address, String phoneno, int loanAmt) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneno = phoneno;
		this.loanAmt = loanAmt;
		this.address = address;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public long getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(long loanAmt) {
		this.loanAmt = loanAmt;
	}
	

}
