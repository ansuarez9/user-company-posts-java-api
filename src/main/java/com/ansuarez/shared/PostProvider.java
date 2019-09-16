package com.ansuarez.shared;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.ansuarez.entities.Company;
import com.ansuarez.entities.Post;
import com.ansuarez.models.CompanySo;
import com.ansuarez.models.PostSo;
import com.ansuarez.models.UserSo;

public class PostProvider extends SharedProvider {
	
	protected ArrayList<PostSo> findAllPosts(){
		List<Post> postEntities = postRepository.findAll();
		ArrayList<PostSo> postSos = new ArrayList<PostSo>();
		
		for(Post post : postEntities) {
			PostSo postSo = new PostSo();
			postSo.setId(post.getId());
			postSo.setDescription(post.getDescription());
			postSo.setTitle(post.getTitle());
			
			postSo.setCompany(getCompanyFromPost(post.getCompany().getId()));
			
			UserSo userSo = getUserById(post.getUser().getId());
			postSo.setUser(userSo);
			postSo.getUser().setCompanySo(null);
			
			postSos.add(postSo);
		}
		
		return postSos;
	}
	
	protected PostSo getPostById(Long postId) {
		Post post = postRepository.findById(postId).orElse(null);
		PostSo postSo = new PostSo();
		
		postSo.setId(post.getId());
		postSo.setDescription(post.getDescription());
		postSo.setTitle(post.getTitle());
		
		postSo.setCompany(getCompanyFromPost(post.getCompany().getId()));
		
		UserSo userSo = getUserById(post.getUser().getId());
		postSo.setUser(userSo);
		
		return postSo;
	}
	
	protected Boolean createNewPost(PostSo postSo){
		Post postEntity = new Post();
		
		if(postSo.getId() != null) {
			throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "Post with ID " + postSo.getId() + " already exists");
		}

		postEntity.setTitle(postSo.getTitle());
		postEntity.setDescription(postSo.getDescription());
		
		postEntity.setCompany(getCompanyEntityById(postSo.getCompanyId()));
		
		if(postSo.getUserId() != null) {
			postEntity.setUser(getUserEntityById(postSo.getUserId()));
		} 
		
		try {
			postRepository.save(postEntity);
			return true;
		} catch (Exception e) {
			throw new RuntimeException("Error While Creating New Post");
		}
		
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

}
