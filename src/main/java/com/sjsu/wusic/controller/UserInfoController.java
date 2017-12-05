package com.sjsu.wusic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjsu.wusic.dao.PlaylistsByUserRepository;
import com.sjsu.wusic.dao.SongsLikedByUserRepository;
import com.sjsu.wusic.dao.UserRepository;
import com.sjsu.wusic.model.Playlist;
import com.sjsu.wusic.model.Song;
import com.sjsu.wusic.model.User;

@Controller
public class UserInfoController {
	
	@Autowired 
	UserRepository userDao;
	
	@Autowired 
	PlaylistsByUserRepository playlistByUserDao;
	
	@Autowired 
	SongsLikedByUserRepository likedSongsDao;

	@RequestMapping("/user")
	public String song(Model model, @RequestParam(value="username", defaultValue="adam@wusic.com") String username) {
		User user = userDao.findByUsername(username);
		List<Playlist> playlistsByUser = playlistByUserDao.playlistsByUser(username);
		List<User> userFollowing = userDao.getUserFollowing(username);
		List<User> userFollowers = userDao.getUserFollowers(username);
		List<Song> likedSongs = likedSongsDao.findByUserId(username);

		model.addAttribute("user_name", user.getName());
		model.addAttribute("user_email", user.getUsername());
		model.addAttribute("playlists", playlistsByUser);		
		model.addAttribute("followers", userFollowers);
		model.addAttribute("followings", userFollowing);
		model.addAttribute("liked_songs", likedSongs);
		
		return "displayUserInfo";
	}
}
