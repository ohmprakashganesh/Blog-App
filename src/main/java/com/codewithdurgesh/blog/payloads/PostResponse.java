package com.codewithdurgesh.blog.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
private List<PostDto> content;
private int pageNumber;
private int pageSize;
private long totalElement;
private int totalPages;

private boolean lastPage;

}
