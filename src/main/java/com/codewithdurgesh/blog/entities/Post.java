package com.codewithdurgesh.blog.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(length=100, nullable=false)
	private String title;
	
	@Column(length=500, nullable=false)
	private String content;
	
	@Column(nullable=false)
	private String imageName;
	@Column( nullable=false)
	private LocalDate addDate;
	
	@OneToMany(mappedBy="post",cascade=CascadeType.ALL )
	private Set<Comments> comments=new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name="category_id")//other wirse field is joint of category + category_id 
	private Category category;
	@ManyToOne
	private User user;

}
