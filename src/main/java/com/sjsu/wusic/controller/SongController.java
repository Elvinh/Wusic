package com.sjsu.wusic.controller;

import java.util.List;

import com.sjsu.wusic.dao.PlaylistsByUserRepository;
import com.sjsu.wusic.model.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@Autowired 
	private ArtistRepository artistDao;

	@Autowired
	private PlaylistsByUserRepository playlistsByUserDao;

	@RequestMapping("/song")
	public String song(Model model, @RequestParam(value="id", defaultValue="SOAAAQN12AB01856D3s") String id) {
		Song s = songDao.findById(id);
		model.addAttribute("name", s.getTitle());

		return "displaySong";
	}
	
	@RequestMapping("/discover_songs")
	public String song(Model model) {
		List<Song> songsInAlbum = songDao.findAllSongs();
		List<Playlist> playlists = playlistsByUserDao.playlistsByUser(
				SecurityContextHolder.getContext().getAuthentication().getName());

		model.addAttribute("playlist_options", playlists);
		model.addAttribute("songs", songsInAlbum);

		return "displayDiscoverSongs";
	}

	@RequestMapping("/songandartist")
	public String song(Model model, @RequestParam(value="songId", defaultValue="1") String songId,
			@RequestParam(value="artistId", defaultValue="1")String artistId) {
		Song s = songDao.findById(songId);
		Artist a = artistDao.findById(artistId);
		model.addAttribute("name", s.getTitle());
		model.addAttribute("year", s.getYear());
		model.addAttribute("artist_name", a.getName());
		return "displaySong";
	}
	
}
