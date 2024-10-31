package com.codewithdurgesh.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.CommentsDto;
import com.codewithdurgesh.blog.services.CommentServices;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentServices commentservie;
	
	@PostMapping("/post/{postId}/user/{userId}")
	public ResponseEntity<CommentsDto> createComment(
			@RequestBody CommentsDto commentDto,
			@PathVariable("postId") Integer postId,
			@PathVariable("userId") Integer userId)
	{
	CommentsDto commentDto1=this.commentservie.createComment(commentDto, postId,userId);
		return new ResponseEntity<CommentsDto>(commentDto1,HttpStatus.CREATED);
	}

	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deletCommetn(@PathVariable("commentId") Integer commentId){
		this.commentservie.delteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("deleted sucesfully ",true),HttpStatus.OK);
		
	}
}
