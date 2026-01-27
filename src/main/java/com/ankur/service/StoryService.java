package com.ankur.service;

import java.util.List;

import com.ankur.exception.StoryException;
import com.ankur.exception.UserException;
import com.ankur.model.Story;

public interface StoryService {

	public Story createStory(Story story,Integer userId) throws UserException;
	
	public List<Story> findStoryByUserId(Integer userId) throws UserException, StoryException;
	
	
}
