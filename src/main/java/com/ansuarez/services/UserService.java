package com.ansuarez.services;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ansuarez.models.UserSo;
import com.ansuarez.shared.UserProvider;

@Service
public class UserService extends UserProvider {

	public ArrayList<UserSo> getAllUsers(){
		return getListOfUsers();
	}
	
	public UserSo getUser(Long id) {
		UserSo user = getUserById(id);
		if(user != null) {
			return user;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
		}
	}
	
	public Boolean createUser(UserSo newUser) {
		if(newUser.getId() != null) {
			UserSo user = getUser(newUser.getId());
			if(user != null) {
				throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "User with id " + newUser.getId() + " already exists");
			}
		}
		
		return createOrUpdateUser(newUser);
	}
	
	public Boolean updateUser(Long id, UserSo user) {
		if(getUser(user.getId()) != null) {
			return createOrUpdateUser(user);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
		}

	}
	
	public Boolean deleteUser(Long id) {
		UserSo user = getUser(id);
		if(user != null) {
			return deleteUserById(id);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
		}
	}
}
