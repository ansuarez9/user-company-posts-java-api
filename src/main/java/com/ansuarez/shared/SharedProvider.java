package com.ansuarez.shared;

import org.springframework.beans.factory.annotation.Autowired;

import com.ansuarez.entities.Company;
import com.ansuarez.entities.User;
import com.ansuarez.models.CompanySo;
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
	
	// Used in PostProvider & CompanyProvider
	protected UserSo getUserById(Long userId){
		User userEntity = userRepository.findById(userId).orElse(null);
		UserSo userSo = new UserSo();
		
		userSo.setId(userEntity.getId());
		userSo.setFirstName(userEntity.getFirstName());
		userSo.setLastName(userEntity.getLastName());
		userSo.setUserName(userEntity.getUserName());
		
		userSo.setCompanySo(getCompanySoFromUserEntity(userEntity));
		
		return userSo;
	}
	
	// Used in UserProvider & SharedProvider
	protected CompanySo getCompanySoFromUserEntity(User userEntity) {
		CompanySo companySo = new CompanySo();
		if(userEntity.getCompany() != null) {
			companySo.setId(userEntity.getCompany().getId());
			companySo.setCompanyName(userEntity.getCompany().getCompanyName());
			companySo.setCountry(userEntity.getCompany().getCountry());
			companySo.setState(userEntity.getCompany().getState());
		}
		
		return companySo;
	}
	
	// Used in UserProvider & PostProvider
	protected Company getCompanyEntityById(Long companyId) {
		return companyRepository.findById(companyId).orElse(null);
	}
	
	// Used in PostProvider and UserProvider (WILL USE WHEN IMPLEMENTING getUserSoById())
	protected User getUserEntityById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

}
