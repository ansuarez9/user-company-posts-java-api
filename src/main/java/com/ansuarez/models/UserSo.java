package com.ansuarez.models;

import org.springframework.lang.Nullable;

public class UserSo {
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private Long companyId;
	
	@Nullable
	private CompanySo companySo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public CompanySo getCompanySo() {
		return companySo;
	}

	public void setCompanySo(CompanySo companySo) {
		this.companySo = companySo;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

}
