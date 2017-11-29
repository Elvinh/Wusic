package com.sjsu.wusic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjsu.wusic.dao.GenreRepository;
import com.sjsu.wusic.dao.SongsInGenreRepository;
import com.sjsu.wusic.model.Genre;
import com.sjsu.wusic.model.Song;

@Controller
public class SongsInGenreController {
	
	@Autowired
	private SongsInGenreRepository songsInGenreDao;
	
	@Autowired
	private GenreRepository genreDao;
	
	@RequestMapping("/songs-in-genre")
	public String songsInGenre(Model model, @RequestParam(value = "id") int id) { 
		
		List<Song> songsInGenre = songsInGenreDao.findSongsInGenre(id);
		
		Genre genre = genreDao.findById(id);
		model.addAttribute("genre_name", genre.getName());
		model.addAttribute("songs", songsInGenre);
		
		return "displaySongsInGenre";
		
	}
	
}
