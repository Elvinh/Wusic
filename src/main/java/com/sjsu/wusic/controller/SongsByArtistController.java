package com.sjsu.wusic.controller;

import com.sjsu.wusic.dao.ArtistRepository;
import com.sjsu.wusic.dao.SongsByArtistRepository;
import com.sjsu.wusic.model.Artist;
import com.sjsu.wusic.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SongsByArtistController {

    @Autowired
    private SongsByArtistRepository songsByArtistDao;

    @Autowired
    private ArtistRepository artistDao;


    @RequestMapping("/songs-by-artist")
    public String songsByArtist(Model model, @RequestParam(value = "id") String id) {

        List<Song> songsByArtist = songsByArtistDao.findByArtistId(id);
        Artist artist = artistDao.findById(id);
        model.addAttribute("artist_name", artist.getName());
        model.addAttribute("songs", songsByArtist);

        return "displaySongsByArtist";

    }


}
