package com.codewithdurgesh.blog.services;

import java.util.List;

import com.codewithdurgesh.blog.payloads.CategoryDto;

public interface CategoryService {
	//create 
	 CategoryDto creteCategory(CategoryDto createDto);
	//
	 CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	//delete
	 void deleteCategory(Integer categoryId);
	//get
	 CategoryDto getCategory(Integer categoryId); 
	//get all
	 List<CategoryDto> getCategories();

}
