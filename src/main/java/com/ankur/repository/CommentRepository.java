package com.ankur.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankur.model.Comments;


public interface CommentRepository extends JpaRepository<Comments, Integer> {

}
