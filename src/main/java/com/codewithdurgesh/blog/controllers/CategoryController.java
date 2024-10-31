package com.codewithdurgesh.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.CategoryDto;
import com.codewithdurgesh.blog.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService  categoryService;
	
	//create the user
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
	{
	CategoryDto temp = this.categoryService.creteCategory(categoryDto);
	return new ResponseEntity<CategoryDto>(temp,HttpStatus.CREATED);
		
	}
	
	//updatethe cateogry
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
        CategoryDto updated=this.categoryService.updateCategory(categoryDto, categoryId);
      return  ResponseEntity.ok(updated);
		
	}
	//get category
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Integer categoryId){
		CategoryDto temp= this.categoryService.getCategory(categoryId);
		return ResponseEntity.ok(temp);	
	}

	//getall categorie
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDto>> getAllCategoreis(){
	return	ResponseEntity.ok(this.categoryService.getCategories());
	}
	 
	
	//delete category 
	@DeleteMapping("/categoryId")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId")Integer categoryId) {
	this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("deleted sucesfully ",true),HttpStatus.OK);

		
	}
}
