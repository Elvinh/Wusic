package com.sjsu.wusic.controller;

import com.sjsu.wusic.WebSecurityConfig;
import com.sjsu.wusic.dao.ArtistRepository;
import com.sjsu.wusic.dao.PlaylistsByUserRepository;
import com.sjsu.wusic.dao.SongsByArtistRepository;
import com.sjsu.wusic.model.Artist;
import com.sjsu.wusic.model.Playlist;
import com.sjsu.wusic.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private PlaylistsByUserRepository playlistsByUserDao;


    @RequestMapping("/songs_by_artist")
    public String songsByArtist(Model model, @RequestParam(value = "artist_id") String id) {

        List<Song> songsByArtist = songsByArtistDao.findByArtistId(id);
        Artist artist = artistDao.findById(id);

        List<Playlist> playlists = playlistsByUserDao.playlistsByUser(
                SecurityContextHolder.getContext().getAuthentication().getName());

        model.addAttribute("artist_name", artist.getName());
        model.addAttribute("songs", songsByArtist);
        model.addAttribute("playlist_options", playlists);

        return "displaySongsByArtist";
    }

}
