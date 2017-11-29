package com.sjsu.wusic.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sjsu.wusic.model.Song;

@Repository
public class SongsInGenreRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Song> findSongsInGenre(int genreId) { 
	
		List<Song> songsInGenre = new ArrayList<>();
		
		List<Map<String, Object>> maps = 
				jdbcTemplate.queryForList("SELECT song.song_id, song.name, song.year FROM song_in_genre "
						+ "INNER JOIN song INNER JOIN genre WHERE song_in_genre.song_id = song.song_id "
						+ "AND song_in_genre.genre_id = genre.genre_id");
		
		for(Map<String, Object> map : maps) {
			Song song = new Song();
			song.setId((Integer) map.get("song_id"));
			song.setName((String) map.get("name"));
			song.setYear((Integer) map.get("year"));
			
			songsInGenre.add(song);
		};
		
		return songsInGenre;
	} 
}
