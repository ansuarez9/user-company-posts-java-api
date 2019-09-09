package com.ansuarez.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ansuarez.models.PostSo;
import com.ansuarez.services.PostService;

@RestController
@RequestMapping(path="api/posts")
public class PostController {
	@Autowired
	PostService postService;
	
	@GetMapping(path="/all")
	private ArrayList<PostSo> getAllPosts(){
		return postService.getAllPosts();
	}
	
	@GetMapping(path="/{postId}")
	private PostSo getPost(@PathVariable Long postId){
		return postService.getPost(postId);
	}
	
	@PostMapping(path="/new")
	private Boolean createPost(@RequestBody PostSo post){
		return postService.createPost(post);
	}
}
