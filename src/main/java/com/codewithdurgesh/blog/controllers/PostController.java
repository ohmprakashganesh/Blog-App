package com.codewithdurgesh.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codewithdurgesh.blog.config.AppConstants;
import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;
import com.codewithdurgesh.blog.services.FileService;
import com.codewithdurgesh.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService PostService;
	
	@Autowired
	private FileService fileService;
	
    @Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable("userId") Integer userId,
			@PathVariable ("categoryId")Integer categoryId){
		

		PostDto createPost=this.PostService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.OK);
			
		
	}
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto> >getPostByUser(
			@PathVariable("userId")Integer userId)
	{
		List<PostDto> posts=this.PostService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List <PostDto>> getPostByCategory(
		@PathVariable("categoryId") Integer categoryId	)
	{
		List<PostDto> posts=this.PostService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
//	@GetMapping("/getAllPosts")
//	public ResponseEntity<List <PostDto>> getAllPosts(){
//		List<PostDto> PostDtos= this.PostService.getAllPost();
//		return new ResponseEntity<List <PostDto>>(PostDtos,HttpStatus.OK);
//	}
	
	//with paging parameter
	@GetMapping("/getAllPosts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false)Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY,required=false)String sortBy,
			@RequestParam(value="sortDir",defaultValue=AppConstants.SORT_DIR,required=false)String sortDir
			
			) 
			{
		PostResponse postResponse= this.PostService.getAllPost(pageNumber,pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	@GetMapping("/post/{postId}")
	public ResponseEntity< PostDto > getPostById(@PathVariable("postId")Integer postId) {
		PostDto postDto= this.PostService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	@DeleteMapping("post/{postId}")
	public  ApiResponse deletePost(@PathVariable("postId")Integer postId) {
		this.PostService.deltePost(postId);
		return new ApiResponse("post is sucessfully deleted",true );
		
	}
	@PutMapping("/post/{postId}")
	public ResponseEntity< PostDto > updatePost(@RequestBody PostDto postDto,@PathVariable("postId")Integer postId) {
		{
			PostDto obj= this.PostService.updatePost(postDto, postId);
			return new ResponseEntity<PostDto>(obj,HttpStatus.OK);
		}
	}
	 @GetMapping("/posts/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword")String keyword){
		 List<PostDto> postDtos=this.PostService.searchPosts(keyword);
	   return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
		
	}
	 
	//image upload
	 @PostMapping("/post/image/upload/{postId}")
	 public ResponseEntity<PostDto> uploadImage(
			 @RequestParam("image")MultipartFile image,
			 @PathVariable Integer postId) throws IOException{
			PostDto postDto= this.PostService.getPostById(postId);

	 String fileName= this.fileService.uploadImage(path, image);
 	 postDto.setImageName(fileName);
 	 PostDto updated=	this.PostService.updatePost(postDto, postId);
    return new ResponseEntity<PostDto>(updated,HttpStatus.OK);
 	
	
	 }
	//method to get image
	 @GetMapping(value="/post/image/{imageName}",produces=MediaType.IMAGE_PNG_VALUE)
	 public void downloadImage(
			 @PathVariable("imageName")String imageName, HttpServletResponse response)throws IOException{
		 
		 InputStream resource =this.fileService.getResource(path, imageName);
		 response.setContentType(MediaType.IMAGE_PNG_VALUE);
		 StreamUtils.copy(resource, response.getOutputStream());
	 }
	
	 //return the name of logged user name 
	 @GetMapping("/currentuser")
	 public String getLoggedUser(Principal principal) {
		 return principal.getName();
	 }

}
