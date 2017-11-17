package com.sjsu.wusic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sjsu.wusic.dao.SongRepository;
import com.sjsu.wusic.model.Song;

@Controller
public class SongController {

	@Autowired
	private SongRepository songDao;
	
	@RequestMapping("/song")
	public String song(Model model, @RequestParam(value="id", defaultValue="1") int id) {
		Song s = songDao.findById(id);
		model.addAttribute("name", s.getName());
		// "year"
		// "artist_name"
		return "displaySong";
	}
}
