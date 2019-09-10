package com.ansuarez.shared;

import org.springframework.beans.factory.annotation.Autowired;

import com.ansuarez.entities.Company;
import com.ansuarez.entities.User;
import com.ansuarez.models.UserSo;
import com.ansuarez.repositories.CompanyRepository;
import com.ansuarez.repositories.PostRepository;
import com.ansuarez.repositories.UserRepository;

public class SharedProvider {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	// Used in UserProvider & SharedProvider
	protected Company getCompanyNameByUser(Long companyId) {
		return companyRepository.findById(companyId).orElse(null);
	}
	
	// Used in PostProvider & CompanyProvider
	protected UserSo getUserById(Long userId){
		User userEntity = userRepository.findById(userId).orElse(null);
		UserSo userSo = new UserSo();
		
		
		userSo.setId(userEntity.getId());
		userSo.setFirstName(userEntity.getFirstName());
		userSo.setLastName(userEntity.getLastName());
		userSo.setUserName(userEntity.getUserName());
		
		if(userEntity.getCompanyId() != null) {
			String companyName = getCompanyNameByUser(userEntity.getCompanyId()).getCompanyName();
			userSo.setCompanyName(companyName);
		}
		
		return userSo;
	}

}
