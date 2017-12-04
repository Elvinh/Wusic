package com.sjsu.wusic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjsu.wusic.dao.AlbumRepository;
import com.sjsu.wusic.dao.ArtistRepository;
import com.sjsu.wusic.dao.GenreRepository;
import com.sjsu.wusic.model.Album;
import com.sjsu.wusic.model.Artist;
import com.sjsu.wusic.model.Genre;

@Controller
public class ArtistController {

    @Autowired
    private ArtistRepository artistDao;

    @Autowired
    private AlbumRepository albumDao;

    @Autowired
    private GenreRepository genreDao;

    @RequestMapping("/artist")
    public String artist(Model model, @RequestParam(value = "artist_id") String id) {

        Artist artist = artistDao.findById(id);
        List<Album> albumsByArtist = albumDao.findByArtistId(id);
        List<Genre> genresByArtist = genreDao.genresByArtist(id);

        model.addAttribute("artist", artist);
        model.addAttribute("albums", albumsByArtist);
        model.addAttribute("genres", genresByArtist);

        return "displayArtist";

    }
    @RequestMapping("/discover_artists")
    public String artist(Model model) {

        List<Artist> artist = artistDao.discoverArtists();
       model.addAttribute("artists",artist);

        return "displayDiscoverArtists";

    }
}