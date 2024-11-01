package com.codewithdurgesh.blog.payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;
	private String categoryTitle;
	private String categoryDescription;
}