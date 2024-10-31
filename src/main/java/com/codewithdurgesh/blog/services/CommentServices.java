package com.codewithdurgesh.blog.services;

import com.codewithdurgesh.blog.payloads.CommentsDto;

public interface CommentServices {
	CommentsDto createComment(CommentsDto commentDto, Integer PostId,Integer userId);
	void delteComment(Integer commentId);

}
