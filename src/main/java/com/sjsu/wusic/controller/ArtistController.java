package com.sjsu.wusic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sjsu.wusic.dao.ArtistRepository;
import com.sjsu.wusic.dao.SongRepository;
import com.sjsu.wusic.model.Artist;
import com.sjsu.wusic.model.Song;

@Controller
public class ArtistController {
	
	@Autowired
	private ArtistRepository artistDao;
	
	@RequestMapping("/artist")
	public String artist(Model model, @RequestParam(value="artist_id") String id) {
		
		Artist artist = artistDao.findById(id);
		model.addAttribute("artist", artist);
		
		return "displayArtist";
	
	} 
	
}