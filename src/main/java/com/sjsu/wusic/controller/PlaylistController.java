package com.sjsu.wusic.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjsu.wusic.dao.PlaylistRepository;
import com.sjsu.wusic.dao.SongRepository;
import com.sjsu.wusic.dao.SongsInPlaylistRepository;
import com.sjsu.wusic.model.Artist;
import com.sjsu.wusic.model.Song;

@Controller
public class PlaylistController {

	@Autowired 
	private SongsInPlaylistRepository songsInPlaylistDao;
	
	@Autowired
	private PlaylistRepository playlistDao;
	
	@Autowired
	private SongRepository songDao;
	
	@RequestMapping("/playlist")
	public String playlist(Model model, @RequestParam(value = "id") int id) {
		
		List<Song> songsInPlaylist = songsInPlaylistDao.findSongsInPlaylist(id);
		String playlistName = playlistDao.findById(id).getName();
		
		List<Artist> artistInPlaylist = new ArrayList<>();
		List<String> albumsInPlaylist = new ArrayList<>();
		for(Song song : songsInPlaylist) {
			Artist artist = songDao.findArtistOfSong(song.getId());
			artistInPlaylist.add(artist);
			String albumName = songDao.findAlbumOfSong(song.getId());
			albumsInPlaylist.add(albumName);
			
		}
		
		model.addAttribute("playlist_name", playlistName);
		model.addAttribute("songs", songsInPlaylist);
		model.addAttribute("artists", artistInPlaylist);
		model.addAttribute("albums", albumsInPlaylist);
		
		return "displaySongsInPlaylist"; 
	}
	
}
