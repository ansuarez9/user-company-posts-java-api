package com.ansuarez.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ansuarez.models.UserSo;
import com.ansuarez.services.UserService;

@RestController
@RequestMapping(path="api/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping(path="/all")
	private List<UserSo> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping(path="/{id}")
	private UserSo getUser(@PathVariable Long id){
		return userService.getUser(id);
	}
	
	@PostMapping(path="/new")
	private Boolean createUser(@RequestBody UserSo user){
		return userService.createUser(user);
	}
	
	@PutMapping(path="/{id}")
	private Boolean updateUser(@PathVariable Long id, @RequestBody UserSo user) {
		return userService.updateUser(id, user);
	}
	
	@DeleteMapping(path="/{id}")
	private Boolean deleteUser(@PathVariable Long id){
		return userService.deleteUser(id);
	}

}
