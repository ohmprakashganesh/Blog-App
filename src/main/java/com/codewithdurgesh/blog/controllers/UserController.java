package com.codewithdurgesh.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.services.UserService;

@Controller
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//post create user
	@PostMapping("/")
	 public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		UserDto created= this.userService.createUser(userDto) ;
		
		return new ResponseEntity<>(created,HttpStatus.CREATED);
	 }
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId") Integer uid ){
		UserDto userDto1= this.userService.updateUser(userDto, uid);
		
		return ResponseEntity.ok(userDto1);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser (@PathVariable("userId") Integer uid){
		  this.userService.delete(uid);
		  return new ResponseEntity(new ApiResponse("user delted sucessfullu",true), HttpStatus.OK);
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity< UserDto> getUser(@PathVariable("userId") Integer uId) {
		
		return   ResponseEntity.ok (this.userService.getUserById(uId));
		
	}
	
	@GetMapping("/")
	public ResponseEntity< List< UserDto>> getUsers() {
		return  ResponseEntity.ok( this.userService.getAllUsers());
		
	}
	

}
