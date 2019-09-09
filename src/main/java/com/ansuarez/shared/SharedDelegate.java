package com.ansuarez.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.ansuarez.entities.Company;
import com.ansuarez.entities.Post;
import com.ansuarez.entities.User;
import com.ansuarez.models.CompanySo;
import com.ansuarez.models.PostSo;
import com.ansuarez.models.UserSo;
import com.ansuarez.repositories.CompanyRepository;
import com.ansuarez.repositories.PostRepository;
import com.ansuarez.repositories.UserRepository;

public class SharedDelegate {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
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
	
	protected Company getCompanyNameByUser(Long companyId) {
		return companyRepository.findById(companyId).orElse(null);
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
	
	protected ArrayList<PostSo> findAllPosts(){
		List<Post> postEntities = postRepository.findAll();
		ArrayList<PostSo> postSos = new ArrayList<PostSo>();
		
		for(Post post : postEntities) {
			PostSo postSo = new PostSo();
			postSo.setId(post.getId());
			postSo.setDescription(post.getDescription());
			postSo.setTitle(post.getTitle());
			
			postSo.setCompany(getCompanyFromPost(post.getCompanyId()));
			
			UserSo userSo = getUserById(post.getUserId());
			postSo.setUser(userSo);
			
			postSos.add(postSo);
		}
		
		return postSos;
	}
	
	protected CompanySo getCompanyFromPost(Long companyId) {
		Company company = companyRepository.findById(companyId).orElse(null);
		CompanySo companySo = new CompanySo();
		
		companySo.setCompanyName(company.getCompanyName());
		companySo.setId(company.getId());
		companySo.setCountry(company.getCountry());
		companySo.setState(company.getState());
		
		return companySo;
	}
	
	protected PostSo getPostById(Long postId) {
		Post post = postRepository.findById(postId).orElse(null);
		PostSo postSo = new PostSo();
		
		postSo.setId(post.getId());
		postSo.setDescription(post.getDescription());
		postSo.setTitle(post.getTitle());
		
		postSo.setCompany(getCompanyFromPost(post.getCompanyId()));
		
		UserSo userSo = getUserById(post.getUserId());
		postSo.setUser(userSo);
		
		return postSo;
	}
	
	protected Boolean createNewPost(PostSo postSo){
		
		if((postSo.getId() != null) && (postRepository.findById(postSo.getId()) != null)) {
			throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "Post with ID " + postSo.getId() + " already exists");
		} else {
			Post newPost = new Post();
			newPost.setTitle(postSo.getTitle());
			newPost.setDescription(postSo.getDescription());
			newPost.setUserId(postSo.getUserId());
			newPost.setCompanyId(postSo.getCompanyId());
			
			try {
				postRepository.save(newPost);
				return true;
			} catch (Exception e) {
				throw new RuntimeException("Error While Creating New Post");
			}
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
