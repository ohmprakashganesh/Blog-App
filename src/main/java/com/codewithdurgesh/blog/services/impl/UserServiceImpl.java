package com.codewithdurgesh.blog.services.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.excepctions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.services.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User saveeduser=this.userRepo.save(user);
		return  this.userToUserDto(saveeduser);
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User","user id",userId));
		
		user.setName(userDto.getName());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		User updatedUser=this.userRepo.save(user);
		UserDto userDto1=this.userToUserDto(updatedUser);
		return userDto1;
		
		
		
		
	}
	

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User","user id",userId));
		return this.userToUserDto(user);
		
	}
	
	

//	@Override
//	public List<UserDto> getAllUsers() {
//		List<User > users= this.userRepo.findAll();
//		List <UserDto> userDtos=users.stream().map(e -> this.userToUserDto(e)).collect(Collectors.toList());
//	 return userDtos;
//	}
	
//	  @Override
//	    public List<UserDto> getAllUsers() {
//	        List<User> users = this.userRepo.findAll();
//	        return users.stream()
//	            .map(this::userToUserDto)
//	            .collect(Collectors.toList());
//	    }
	
	@Override
	public List<UserDto> getAllUsers() {
	    List<User> users = this.userRepo.findAll();
	    List<UserDto> userDtos = new ArrayList<>();
	    for (User user : users) {
	        userDtos.add(this.userToUserDto(user));
	    }
	    return userDtos;
	}
	

	@Override
	public void delete(Integer userId) {
   User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","user id", userId));
    this.userRepo.delete(user);
	}

	
	
	
	private User dtoToUser(UserDto userDto) {
		User user=new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		return user;
		
		
		
	}
	private UserDto userToUserDto(User user) {
		UserDto userDto=new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setAbout(user.getAbout());
		userDto.setPassword(user.getPassword());
		return userDto;
	}
}
