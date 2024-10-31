package com.codewithdurgesh.blog.services;

import java.util.List;

import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;

public interface PostService {
//create post
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	//update post
	PostDto updatePost(PostDto postDto, Integer postId);
	//delete post
	void deltePost(Integer postId);
	//get all posts
	//List<PostDto> getAllPost();

	//List<PostDto> getAllPost(Integer pageNumber,Integer pageSize);
	
	//PostResponse getAllPost(Integer pageNumber,Integer pageSize);

	//get single post
	PostDto getPostById(Integer postId);
	//get all post by user
	List<PostDto> getPostByUser(Integer userId);
	//get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	//search posts
	List<PostDto> searchPosts(String keywordd);
//	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
}
