package com.sjsu.wusic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjsu.wusic.dao.PlaylistsByUserRepository;
import com.sjsu.wusic.dao.UserRepository;
import com.sjsu.wusic.model.Playlist;
import com.sjsu.wusic.model.User;

@Controller
public class UserInfoController {
	
	@Autowired 
	UserRepository userDao;
	
	@Autowired 
	PlaylistsByUserRepository playlistByUserDao;
	
	@RequestMapping("/user")
	public String song(Model model, @RequestParam(value="id", defaultValue="1") int id) {
		User user = userDao.findById(id);
		List<Playlist> playlistsByUser = playlistByUserDao.playlistsByUser(id);

		model.addAttribute("user_name", user.getName());
		model.addAttribute("user_email", user.getEmail());
		model.addAttribute("playlists", playlistsByUser);
		
		return "displayUserInfo";
	}
}
