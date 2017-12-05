package com.sjsu.wusic.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjsu.wusic.dao.PlaylistsByUserRepository;
import com.sjsu.wusic.dao.SearchRepository;
import com.sjsu.wusic.dao.SongRepository;
import com.sjsu.wusic.model.Album;
import com.sjsu.wusic.model.Artist;
import com.sjsu.wusic.model.Genre;
import com.sjsu.wusic.model.Playlist;
import com.sjsu.wusic.model.Song;

@Controller
public class SearchController {

    @Autowired
    private SearchRepository searchDao;
    
    @Autowired
    private PlaylistsByUserRepository playlistDao;
    
	@Autowired
	private SongRepository songDao;
	
	@Autowired
	private PlaylistsByUserRepository playlistsByUserDao;

    @RequestMapping("/search")
    public String artist(Model model, @RequestParam(value = "query") String query, @RequestParam(value = "type") String type) {
    	
        List<Playlist> playlists = playlistDao.playlistsByUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("playlist_options", playlists);
        
    	if(type.equals("song")) {
    		List<Song> songResults = searchDao.findSongs(query);
        	model.addAttribute("songs", songResults);
        	
    		List<Artist> artists = new ArrayList<>();
    		List<String> albums = new ArrayList<>();
    		for(Song song : songResults) {
    			Artist artist = songDao.findArtistOfSong(song.getId());
    			artists.add(artist);
    			String albumName = songDao.findAlbumOfSong(song.getId());
    			albums.add(albumName);
    		}
    		
    		model.addAttribute("songs", songResults);
    		model.addAttribute("artists", artists);
    		model.addAttribute("albums", albums);
    		model.addAttribute("playlist_options", playlists);
    		
        	return "displayDiscoverSongs";
    	}
    	else if(type.equals("artist")) {
        	List<Artist> artistResults = searchDao.findArtists(query);
        	model.addAttribute("artists", artistResults);
        	return "displayDiscoverArtists";
    	}
    	else if(type.equals("genre")) {
            List<Genre> genreResults = searchDao.findGenres(query);
        	model.addAttribute("genres", genreResults);
        	return "displayGenres";
    	}
    	else if(type.equals("album")) {
            List<Album> albumResults = searchDao.findAlbums(query);
    		model.addAttribute("albums", albumResults);
    		return "displayDiscoverAlbums";
    	}
		return "error";


    }
}