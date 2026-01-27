package com.ankur.service;

import java.util.List;

import com.ankur.exception.UserException;
import com.ankur.model.Reels;
import com.ankur.model.User;

public interface ReelsService {
	
	public Reels createReel(Reels reel,User user);
	public List<Reels> findAllReels();
	public List<Reels> findUsersReel(Integer userId) throws UserException;

}
