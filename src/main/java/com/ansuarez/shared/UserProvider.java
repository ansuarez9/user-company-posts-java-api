package com.ansuarez.shared;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.server.ResponseStatusException;

import com.ansuarez.entities.User;
import com.ansuarez.models.UserSo;

public class UserProvider extends SharedProvider {
	
	protected ArrayList<UserSo> getListOfUsers(){
		ArrayList<UserSo> userSos = new ArrayList<UserSo>();
		List<User> userEntities = userRepository.findAll();
		for(User userEntity : userEntities) {
			UserSo userSo = new UserSo();
			userSo.setId(userEntity.getId());
			userSo.setFirstName(userEntity.getFirstName());
			userSo.setLastName(userEntity.getLastName());
			userSo.setUserName(userEntity.getUserName());
			
			if(userEntity.getCompanyId() != null) {
				String companyName = getCompanyNameByUser(userEntity.getCompanyId()).getCompanyName();
				userSo.setCompanyName(companyName);
			}
			
			userSos.add(userSo);
		}
		
		return userSos;
		
	}
	
	protected Boolean createOrUpdateUser(UserSo userSo){
		User userEntity = new User();
		
		// IF UPDATING USER
		if(userSo.getId() != null) {
			userEntity.setId(userSo.getId());
		}
		
		userEntity.setFirstName(userSo.getFirstName());
		userEntity.setLastName(userSo.getLastName());
		userEntity.setUserName(userSo.getUserName());
		userEntity.setCompanyId(userSo.getCompanyId());
		
		try {
			userRepository.save(userEntity);
			return true;
		} catch (Exception e) {
			throw new ResponseStatusException(null, "Error Creating New User");
		}
		
	}
	
	protected boolean deleteUserById(Long userId){
		try {
			userRepository.deleteById(userId);
			return true;
		} catch (Exception e) {
			throw new ResponseStatusException(null, "Error Deleting User");
		}
	}

}
