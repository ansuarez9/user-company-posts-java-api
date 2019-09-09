package com.ansuarez.services;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ansuarez.models.PostSo;
import com.ansuarez.shared.SharedDelegate;

@Service
public class PostService extends SharedDelegate {
	
	public ArrayList<PostSo> getAllPosts(){
		return findAllPosts();
	}
	
	public PostSo getPost(Long postId){
		PostSo post = getPostById(postId);
		if(post != null) {
			return post;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post with id " + postId + " not found");
		}
	}
	
	public Boolean createPost(PostSo postSo){
		return createNewPost(postSo);
	}
}
