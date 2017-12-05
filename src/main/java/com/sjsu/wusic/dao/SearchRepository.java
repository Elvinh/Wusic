package com.sjsu.wusic.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sjsu.wusic.model.Artist;
import com.sjsu.wusic.model.Song;

@Repository
public class SearchRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Song> findSongs(String query) { 
		List<Song> songResults = new ArrayList<>();
		query = "%" + query + "%";
		System.out.println(query);
		List<Map<String, Object>> maps = 
				jdbcTemplate.queryForList("SELECT * FROM song where song.title LIKE ?", query);
		

		for (Map<String, Object> map : maps) {

            Song song = new Song();
            song.setId((String) map.get("song_id"));
            song.setTitle((String) map.get("title"));
            song.setYear((Integer) map.get("year"));
            song.setDuration((Float) map.get("duration"));
            
			songResults.add(song);
		}

		return songResults;
	}
	
	public List<Artist> findArtists(String query) { 
		List<Artist> artistResults = new ArrayList<>();
		query = "%" + query + "%";
		System.out.println(query);
		List<Map<String, Object>> maps = 
				jdbcTemplate.queryForList("SELECT * FROM artist where artist.name LIKE ?", query);
		

		for (Map<String, Object> map : maps) {

            Artist artist = new Artist();
            artist.setId((String) map.get("artist_id"));
            artist.setName((String) map.get("name"));
            
            artistResults.add(artist);
		}

		return artistResults;
	}
	
}

