package com.codewithdurgesh.blog.services.impl;

import java.util.ArrayList;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.excepctions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.CategoryDto;
import com.codewithdurgesh.blog.repositories.CategoryRepo;
import com.codewithdurgesh.blog.services.CategoryService;
@Service
public class CategoryServiceimpl implements CategoryService {
@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto creteCategory(CategoryDto cateogryDto) {
	 Category temp=this.dtoToCategory(cateogryDto);
	 Category save=this.categoryRepo.save(temp);
	 return this.categoryToDto(save);	
	}

	
	
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category temp=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("User","user id",categoryId));

		temp.setCategoryTitle(categoryDto.getCategoryTitle());
		temp.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updated=this.categoryRepo.save(temp);
		return this.categoryToDto(updated);
	}

	
	
	@Override
	public void deleteCategory(Integer categoryId) {
    Category temp=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("user","user id", categoryId));;
       this.categoryRepo.delete(temp);
	}

	
	
	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category temp=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("user","user id", categoryId));;
		return this.categoryToDto(temp);
	}

	
	
	@Override
	public List<CategoryDto> getCategories() {
		List<Category> list1= this.categoryRepo.findAll();
		List<CategoryDto> list2=new ArrayList();
		for(Category temp: list1) {
			list2.add(this.categoryToDto(temp));
		}
			return list2;		
	}

	
//	public CategoryDto categoryToDto(Category category) { 
//	CategoryDto temp=this.modelMapper.map(category, CategoryDto.class);
//	return temp;
//	}
	
	public CategoryDto categoryToDto(Category category) {
		CategoryDto temp=new CategoryDto();
		temp.setCategoryId(category.getCategoryId());
		temp.setCategoryTitle(category.getCategoryTitle());
		temp.setCategoryDescription(category.getCategoryDescription());
		return temp;
		
		
	}
	public Category  dtoToCategory(CategoryDto categoryDto) {
		Category temp= new Category();
		temp.setCategoryId(categoryDto.getCategoryId());
		temp.setCategoryTitle(categoryDto.getCategoryTitle());
		temp.setCategoryDescription(categoryDto.getCategoryDescription());
		return temp;

	}
	
}
