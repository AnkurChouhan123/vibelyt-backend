package com.ankur.service;

import com.ankur.exception.CommentException;
import com.ankur.exception.PostException;
import com.ankur.exception.UserException;
import com.ankur.model.Comments;

public interface CommentService {
	
	public Comments createComment(Comments comment,Integer postId,Integer userId) throws PostException, UserException;

	public Comments findCommentById(Integer commentId) throws CommentException;
	public Comments likeComment(Integer CommentId,Integer userId) 
			throws UserException, CommentException;
}
