package com.sjsu.wusic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sjsu.wusic.dao.GenreRepository;
import com.sjsu.wusic.model.Genre;

@Controller
public class GenreController {
	
	@Autowired
	private GenreRepository genreDao;
	
	@RequestMapping("/all_genres")
	public String songsInGenre(Model model) { 
		List<Genre> genre = genreDao.listGenres();
		model.addAttribute("genres", genre);
		
		return "displayGenres";
	}

}
