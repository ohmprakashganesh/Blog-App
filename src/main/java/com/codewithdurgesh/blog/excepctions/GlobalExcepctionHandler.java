package com.codewithdurgesh.blog.excepctions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codewithdurgesh.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExcepctionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
	 
		String message=ex.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse> (apiResponse, HttpStatus.NOT_FOUND);
}
}
