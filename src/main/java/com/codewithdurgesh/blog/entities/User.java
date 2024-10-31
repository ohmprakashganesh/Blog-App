package com.codewithdurgesh.blog.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table
@Getter
@Setter
@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String about;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<Post> posts=new ArrayList<>();
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private Set<Comments> comments=new HashSet<>();
	
	@ManyToMany
	@JoinTable(name="user_role",
	joinColumns = @JoinColumn(name="user",referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="role",referencedColumnName="id")
	)
	private Set<Role> roles=new HashSet<>();

	
//	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//	List<SimpleGrantedAuthority>authorites=	this.roles.stream().map((role)->new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
//		return authorites;
//	}
//
//	@Override
//	public String getUsername() {
//		
//		return this.getUsername();
//	}
	
	
	
}
