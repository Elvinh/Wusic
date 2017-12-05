package com.sjsu.wusic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjsu.wusic.dao.PlaylistsByUserRepository;
import com.sjsu.wusic.dao.SearchRepository;
import com.sjsu.wusic.model.Artist;
import com.sjsu.wusic.model.Playlist;
import com.sjsu.wusic.model.Song;

@Controller
public class SearchController {

    @Autowired
    private SearchRepository searchDao;
    
    @Autowired
    private PlaylistsByUserRepository playlistDao;

    @RequestMapping("/search")
    public String artist(Model model, @RequestParam(value = "query") String query, @RequestParam(value = "type") String type) {
    	
        List<Playlist> playlists = playlistDao.playlistsByUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("playlist_options", playlists);
        
    	if(type.equals("song")) {
    		List<Song> songResults = searchDao.findSongs(query);
        	model.addAttribute("songs", songResults);
        	return "displayDiscoverSongs";
    	}
    	else if(type.equals("artist")) {
        	List<Artist> artistResults = searchDao.findArtists(query);
        	model.addAttribute("artists", artistResults);
        	return "displayDiscoverArtists";
    	}
    	else if(type.equals("genre")) {
            List<Song> songResults = searchDao.findSongs(query);
        	model.addAttribute("songs", songResults);
        	return "displayDiscoverSongs";
    	}
    	else if(type.equals("album")) {
            List<Song> songResults = searchDao.findSongs(query);
    		model.addAttribute("songs", songResults);
    		return "displayDiscoverSongs";
    	}
		return "error";


    }
}