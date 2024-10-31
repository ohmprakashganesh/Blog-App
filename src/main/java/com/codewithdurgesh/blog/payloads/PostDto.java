package com.codewithdurgesh.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.codewithdurgesh.blog.entities.Comments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	private String title;
	private String content;
	private String imageName;
	private Date addDate;
	private CategoryDto category;
	private UserDto user;
	private Set<CommentsDto> comments=new HashSet<>(); 

}
