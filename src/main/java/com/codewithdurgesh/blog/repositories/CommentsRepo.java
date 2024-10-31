package com.codewithdurgesh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdurgesh.blog.entities.Comments;

public interface CommentsRepo extends JpaRepository<Comments,Integer> {

}
