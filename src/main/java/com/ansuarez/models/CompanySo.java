package com.ansuarez.models;

import java.util.List;

public class CompanySo {
	private Long id;
	
	private String companyName;
	
	private String country;
	
	private String state;
	
	private List<PostSo> posts;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<PostSo> getPosts() {
		return posts;
	}
	public void setPosts(List<PostSo> posts) {
		this.posts = posts;
	}
	
}
