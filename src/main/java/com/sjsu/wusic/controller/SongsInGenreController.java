package com.sjsu.wusic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjsu.wusic.dao.GenreRepository;
import com.sjsu.wusic.dao.SongsInGenreRepository;
import com.sjsu.wusic.dao.SongsInGenreRepository.SongAndArtist;
import com.sjsu.wusic.model.Genre;

@Controller
public class SongsInGenreController {
	
	@Autowired
	private SongsInGenreRepository songsInGenreDao;
	
	@Autowired
	private GenreRepository genreDao;
	
	@RequestMapping("/songs_in_genre")
	public String songsInGenre(Model model, @RequestParam(value = "genre_name") String genre_name) { 
		
		List<SongAndArtist> songsInGenre = songsInGenreDao.findSongsInGenre(genre_name);
		
		Genre genre = genreDao.findById(genre_name);
		model.addAttribute("genreName", genre_name);
		model.addAttribute("songs", songsInGenre);
		
		return "displaySongsInGenre";
		
	}
	
}
