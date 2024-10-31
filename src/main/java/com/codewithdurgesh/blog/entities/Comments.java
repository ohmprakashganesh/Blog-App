package com.codewithdurgesh.blog.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Comments {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String content;
	
	@ManyToOne
	@JoinColumn(name="Post_id")
	private  Post post;
	@ManyToOne
	private User user;
	
	
	

}
