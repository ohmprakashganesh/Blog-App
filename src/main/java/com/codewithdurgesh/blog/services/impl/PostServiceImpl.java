package com.codewithdurgesh.blog.services.impl;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.excepctions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;
import com.codewithdurgesh.blog.repositories.CategoryRepo;
import com.codewithdurgesh.blog.repositories.PostRepo;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId){
		LocalDate date=LocalDate.now();
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "user Id", userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","category Id", categoryId));

		Post post=this.modelMapper.map(postDto, Post.class);
		post.setCategory(category);
		post.setImageName("default.png");
		post.setUser(user);	
		post.setAddDate(date);
		
		Post newpost=this.postRepo.save(post);
		return this.modelMapper.map(newpost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","post Id",postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post temp=this.postRepo.save(post);
		return this.modelMapper.map(temp, PostDto.class);
		
	
	}

	@Override
	public void deltePost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "post id" , postId));
		this.postRepo.delete(post);
		// TODO Auto-generated method stub
		
	}
	
	
//this for simple getting all post
//	@Override
//	public List<PostDto> getAllPost() {
//		List<Post> posts = this.postRepo.findAll();
//		List<PostDto>postDtos=posts.stream().map((Post)-> this.modelMapper.map(Post, PostDto.class)).collect(Collectors.toList());
//		
//		return postDtos;
//	}
//this for paging and sorting while fetching
	
	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		Sort sort=null;
       if(sortDir.equalsIgnoreCase("asc")) {
    	   sort=Sort.by(sortBy).ascending();
       }else {
    	   sort=Sort.by(sortBy).descending();
       }
       //this is sort taking input and defualt para too and asc is done internally in function
//		Pageable p=PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
       
    	Pageable p=PageRequest.of(pageNumber, pageSize,sort);

		Page<Post> pagePost=this.postRepo.findAll(p);
		List<Post>allPosts=pagePost.getContent();
		
		List<PostDto>postDtos=allPosts.stream().map((Post)-> this.modelMapper.map(Post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse ();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "postId", postId));
		PostDto postDto=this.modelMapper.map(post, PostDto.class);
		
		return postDto;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user ", "user Id", userId));
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> postDtos=posts.stream().map((Post)->this.modelMapper.map(Post, PostDto.class)).collect(Collectors.toList());
	               return postDtos;
		
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("cateogry ","category Id",categoryId));
		List<Post> posts=this.postRepo.findByCategory(cat);
	   List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	   return postDtos;
		
		
		
	}

	@Override
	public List<PostDto> searchPosts(String keywordd) {
	List<Post> posts=this.postRepo.findByTitleContaining(keywordd);
    List<PostDto> postDtos=posts.stream().map((Post)-> this.modelMapper.map(Post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}
	
	
}
