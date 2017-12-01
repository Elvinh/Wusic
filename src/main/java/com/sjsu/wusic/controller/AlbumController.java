package com.sjsu.wusic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjsu.wusic.dao.AlbumRepository;
import com.sjsu.wusic.dao.ArtistRepository;
import com.sjsu.wusic.model.Album;
import com.sjsu.wusic.model.Artist;

@Controller
public class AlbumController {
	
	@Autowired
	private AlbumRepository albumDao;
	@Autowired
	private AlbumRepository albumsByArtistDao;
	@Autowired
	private ArtistRepository artistDao;
	
	@RequestMapping("/get_artist_albums")
	 public String songsByArtist(Model model, @RequestParam(value = "artist_id") String id) {

        List<Album> albumsByArtist = albumsByArtistDao.findByArtistId(id);
        Artist artist = artistDao.findById(id);
        model.addAttribute("artist_name", artist.getName());
        model.addAttribute("albums", albumsByArtist);

        return "displayAlbum";

    }


}
