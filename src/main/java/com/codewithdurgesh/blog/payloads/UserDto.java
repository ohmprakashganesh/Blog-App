package com.codewithdurgesh.blog.payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private int id;

	
	private String name;
	
	private String email;

	private String password;

	private String about;

}
