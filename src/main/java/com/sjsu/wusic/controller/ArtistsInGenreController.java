package com.sjsu.wusic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjsu.wusic.dao.ArtistsInGenreRepository;
import com.sjsu.wusic.dao.GenreRepository;
import com.sjsu.wusic.model.Artist;
import com.sjsu.wusic.model.Genre;

@Controller
public class ArtistsInGenreController {
	
	@Autowired
	private ArtistsInGenreRepository artistsInGenreDao;
	
	@Autowired
	private GenreRepository genreDao;
	
	@RequestMapping("/artists_in_genre")
	public String songsInGenre(Model model, @RequestParam(value = "genre_name") String genre_name) { 
		
		List<Artist> artistsInGenre = artistsInGenreDao.findArtistsInGenre(genre_name);
		
		Genre genre = genreDao.findById(genre_name);
		model.addAttribute("genreName", genre_name);
		model.addAttribute("artists", artistsInGenre);
		
		return "displayArtistsInGenre";
		
	}
	
}
