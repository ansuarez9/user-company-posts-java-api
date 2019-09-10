package com.ansuarez.shared;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.ansuarez.entities.Company;
import com.ansuarez.entities.Post;
import com.ansuarez.models.CompanySo;
import com.ansuarez.models.PostSo;

public class CompanyProvider extends SharedProvider {
	
	protected Boolean createNewCompany(CompanySo companySo){
		if(companySo.getId() != null) {
			throw new RuntimeException("Id must be set to null if registering new company");
		}
		
		Company companyEntity = new Company();
		companyEntity.setCompanyName(companySo.getCompanyName());
		companyEntity.setCountry(companySo.getCountry());
		
		if(!companySo.getState().isEmpty()) {
			companyEntity.setState(companySo.getState());
		} else {
			companyEntity.setState(null);
		}
		
		try {
			companyRepository.save(companyEntity);
			return true;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while saving new company");
		}
	}

	protected ArrayList<CompanySo> getAllCompanies(){
		ArrayList<CompanySo> companySos = new ArrayList<CompanySo>();
		List<Company> Companies = companyRepository.findAll();
		
		for(Company company : Companies) {
			CompanySo companySo = new CompanySo();
			companySo.setId(company.getId());
			companySo.setCompanyName(company.getCompanyName());
			companySo.setCountry(company.getCountry());
			
			if(!company.getState().isEmpty()) {
				companySo.setState(company.getState());
			} else {
				companySo.setState(null);
			}
			
			companySo.setPosts(getAllPostByCompanyId(company.getId()));
			companySos.add(companySo);
		}
		
		return companySos;
	}
	
	protected CompanySo getCompanyById(Long companyId) {
		Company company = companyRepository.findById(companyId).orElse(null);
		CompanySo companySo = new CompanySo();
		
		companySo.setId(companyId);
		companySo.setCompanyName(company.getCompanyName());
		
		if(!company.getState().isEmpty()) {
			companySo.setState(company.getState());
		} else {
			companySo.setState(null);
		}
			
			companySo.setPosts(getAllPostByCompanyId(company.getId()));
		
		return companySo;
	}
	
	protected List<PostSo> getAllPostByCompanyId(Long companyId){
		List<Post> posts = postRepository.findAllByCompanyId(companyId);
		List<PostSo> postSos = new ArrayList<PostSo>();
		
		for(Post post : posts) {
			PostSo postSo = new PostSo();
			postSo.setId(post.getId());
			postSo.setDescription(post.getDescription());
			postSo.setTitle(post.getTitle());
			postSo.setUser(getUserById(post.getUserId()));
			postSos.add(postSo);
		}
		
		return postSos;
	}
}
