package com.sjsu.wusic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjsu.wusic.dao.ArtistRepository;
import com.sjsu.wusic.dao.SongRepository;
import com.sjsu.wusic.model.Artist;
import com.sjsu.wusic.model.Song;

@Controller
public class SongController {

	@Autowired
	private SongRepository songDao;
	private ArtistRepository artistDao;
	
	@RequestMapping("/song")
	public String song(Model model, @RequestParam(value="id", defaultValue="1") int id) {
		Song s = songDao.findById(id);
		model.addAttribute("name", s.getName());
		// "year"
		// "artist_name"
		return "displaySong";
	}

	@RequestMapping("/songandartist")

	public String song(Model model, @RequestParam(value="songId", defaultValue="1") int songId,
			@RequestParam(value="artistId", defaultValue="1")int artistId) {
		Song s = songDao.findById(songId);
		Artist a = artistDao.findById(artistId);
		model.addAttribute("name", s.getName());
		model.addAttribute("year", s.getYear());
		model.addAttribute("artistName", a.getName());
		// "year"
		// "artist_name"
		return "displaySong";
	}
	
}
