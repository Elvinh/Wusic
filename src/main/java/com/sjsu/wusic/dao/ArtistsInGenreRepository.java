package com.sjsu.wusic.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sjsu.wusic.model.Artist;

@Repository
public class ArtistsInGenreRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Artist> findArtistsInGenre(String genreName) { 
		List<Artist> artistsInGenre = new ArrayList<>();
		
		List<Map<String, Object>> maps = 
				jdbcTemplate.queryForList("SELECT artist.name, artist.artist_id FROM artist " + 
									"INNER JOIN artist_genre_tag " + 
									"ON artist.artist_id = artist_genre_tag.artist_id AND artist_genre_tag.genre_name = ?", genreName);

		for (Map<String, Object> map : maps) {
			Artist artist = new Artist();
			artist.setName((String) map.get("name"));
			artist.setId((String) map.get("artist_id"));

			artistsInGenre.add(artist);
		}

		return artistsInGenre;
	} 
	
}
