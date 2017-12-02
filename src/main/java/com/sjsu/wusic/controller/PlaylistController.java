package com.sjsu.wusic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjsu.wusic.dao.PlaylistRepository;
import com.sjsu.wusic.dao.SongsInPlaylistRepository;
import com.sjsu.wusic.model.Song;

@Controller
public class PlaylistController {

	@Autowired 
	private SongsInPlaylistRepository songsInPlaylistDao;
	
	@Autowired
	private PlaylistRepository playlistDao;
	
	
	@RequestMapping("/playlist")
	public String playlist(Model model, @RequestParam(value = "id") int id) {
		
		List<Song> songsInPlaylist = songsInPlaylistDao.findSongsInPlaylist(id);
		String playlistName = playlistDao.findById(id).getName();
		
		
		model.addAttribute("playlist_name", playlistName);
		model.addAttribute("songs", songsInPlaylist);
		
		return "displaySongsInPlaylist"; 
	}
	
}
