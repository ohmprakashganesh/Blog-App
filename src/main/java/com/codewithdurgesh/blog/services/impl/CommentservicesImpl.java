package com.codewithdurgesh.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.entities.Comments;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.excepctions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.CommentsDto;
import com.codewithdurgesh.blog.repositories.CommentsRepo;
import com.codewithdurgesh.blog.repositories.PostRepo;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.services.CommentServices;

@Service
public class CommentservicesImpl implements CommentServices {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentsRepo commentsRepo;
	@Autowired
	private UserRepo usreRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentsDto createComment(CommentsDto commentDto, Integer PostId,Integer userId) {
		Post post=this.postRepo.findById(PostId).orElseThrow(()->new ResourceNotFoundException("post","postId",PostId));
		User user=this.usreRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
		
		Comments comment=this.modelMapper.map(commentDto, Comments.class);
		comment.setPost(post);
		comment.setUser(user);
		Comments saved=this.commentsRepo.save(comment);
		return this.modelMapper.map(saved, CommentsDto.class);
	}

	@Override
	public void delteComment(Integer commentId) {
		Comments comment=this.commentsRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comnt ","commentId",commentId));
		this.commentsRepo.delete(comment);

	}

}
