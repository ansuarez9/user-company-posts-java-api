package com.ansuarez.models;

public class PostSo {

	private Long id;
	
	private String description;
	
	private String title;
	
	private Long companyId;
	
	private Long userId;
	
	private UserSo user;
	
	private CompanySo company;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CompanySo getCompany() {
		return company;
	}

	public void setCompany(CompanySo company) {
		this.company = company;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public UserSo getUser() {
		return user;
	}

	public void setUser(UserSo user) {
		this.user = user;
	}
	
}
